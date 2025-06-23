package thinksync.database;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import thinksync.entities.Category;
import thinksync.entities.CategoryFilter;
import thinksync.entities.User;

public class CategoryDatabase {
	private Session session;

	public CategoryDatabase(Session session) {
		this.session = session;
	}

	public void saveCategory(Category category, User user) {
		Transaction transaction = session.beginTransaction();
		session.persist(category);
		
		List<Category> categories = user.getCategories();
		categories.add(category);
		transaction.commit();
		session.clear();
		session.close();
	}

	public List<Category> getCategories(User user) {
		Query<Category> query = session.createQuery("FROM category_entity WHERE user = :user", Category.class);
		query.setParameter("user", user);
		
		List<Category> categories = query.getResultList();
		session.clear();
		session.close();
		return categories;
	}
	
	public Category getCategory(String name, User user) {
		Query<Category> query = session.createQuery("FROM category_entity WHERE title = :title AND user = :user", Category.class);
		query.setParameter("title", name);
		query.setParameter("user", user);
		
		Category category = query.uniqueResult();
		return category;
	}

	public Category getCategory(Integer id) {
		Query<Category> query = session.createQuery("FROM category_entity WHERE id = :id", Category.class);
		query.setParameter("id", id);
		
		Category category = query.uniqueResult();
		session.clear();
		session.close();
		return category;
	}
	
	public List<Category> getCategoriesOnRequirements(User user, CategoryFilter filter) {
		String sort = filter.getSortItem();
		String date = filter.getDate();
		String search = filter.getSearchName();

		Query<Category> query = null;

		// Prepare the Query
		StringBuilder queryString = new StringBuilder("FROM category_entity WHERE user = :user");
		
		// Convert Date into required format
		Date createdDate = null;
	    if(date != null && !date.isEmpty()) {
	    	try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				createdDate = formatter.parse(date);
				queryString.append(" AND createdDate = :date");
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    // If name is not null then add it to the query
	    if(search != null && !search.trim().isEmpty()) {
	    	queryString.append(" AND title LIKE :title");
	    }
	    
	    // If sorting option selected then add it to the query 
	    if(sort != null && (sort.equals("title") || sort.equals("createdDate"))) {
	    	queryString.append(" ORDER BY ").append(sort);
	    	
	    	// If sort by date then Descending, Else Ascending
	        if(sort.equals("createdDate")) {
	        	queryString.append(" DESC");
	        } 
	        else {
	        	queryString.append(" ASC");
	        }
	    }
	    
	    // Create query from queryString
	    query = session.createQuery(queryString.toString(), Category.class);
	    query.setParameter("user", user);

	    // Set the date
	    if(createdDate != null) {
	        query.setParameter("date", createdDate);
	    }
	    
	    // Set the Searching name
	    if(search != null && !search.trim().isEmpty()) {
	        query.setParameter("title", "%" + search.trim() + "%");
	    }
	    List<Category> categories = query.getResultList();
	    session.clear();
	    session.close();
	    return categories;
	}

	public void deleteCategory(Category category, User user) {
		Transaction transaction = session.beginTransaction();
		session.remove(category);
		
		List<Category> categories = user.getCategories();
		categories.remove(category);
		transaction.commit();
		session.clear();
		session.close();
	}

	@SuppressWarnings("deprecation")
	public void editCategory(Category category, User user, String title) {
		Transaction transaction = session.beginTransaction();
		Query<?> query = session.createQuery("UPDATE category_entity SET title = :title WHERE id = :id");
		
		query.setParameter("title", title);
		query.setParameter("id", category.getId());
		
		query.executeUpdate();
		transaction.commit();
		session.clear();
		session.close();
	}
}