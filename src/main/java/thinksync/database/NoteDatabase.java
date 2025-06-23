package thinksync.database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import thinksync.entities.Category;
import thinksync.entities.Note;
import thinksync.entities.NoteFilter;
import thinksync.entities.User;

public class NoteDatabase {
	private Session session;

	public NoteDatabase(Session session) {
		this.session = session;
	}

	public void saveNote(String[] categoriesName, Note note, User user) {
		List<Category> categories = new ArrayList<>();
		for (String category : categoriesName) {
			categories.add(new CategoryDatabase(session).getCategory(category, user));
		}
		note.setCategories(categories);
		Transaction transaction = session.beginTransaction();
		session.persist(note);
		
		List<Note> notes = user.getNotes();
		notes.add(note);
		transaction.commit();
		session.clear();
		session.close();
	}

	public Note getNote(Integer id) {
		Query<Note> query = session.createQuery("FROM note_entity WHERE id = :id", Note.class);
		query.setParameter("id", id);
		
		Note note = query.uniqueResult();
		session.clear();
		session.close();
		return note;
	}

	public List<Note> getNotes(User user) {
		Query<Note> query = session.createQuery("FROM note_entity WHERE user = :user", Note.class);
		query.setParameter("user", user);
		
		List<Note> notes = query.getResultList();
		session.clear();
		session.close();
		return notes;
	}
	
	public List<Note> getNotesOnRequirements(User user, NoteFilter filter) {
		String sort = filter.getSortItem(); 
	    String date = filter.getDate();    
	    String title = filter.getSearchTitle();
	    String category = filter.getSearchCategory();

	    Query<Note> query = null;
	    
	    // Prepare the Query
	    StringBuilder queryString = new StringBuilder("SELECT DISTINCT n FROM note_entity n JOIN n.categories c WHERE n.user = :user");
	    
	    // Convert Date into required format
	    Date createdDate = null;
	    if(date != null && !date.isEmpty()) {
	    	try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				createdDate = formatter.parse(date);
				queryString.append(" AND n.createdDate = :date");
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    // If search name is not null then add it to the query
	    if(title != null && !title.trim().isEmpty()) {
	    	queryString.append(" AND n.title LIKE :title");
	    }
	    
	    // If search category is not null then add it to the query
	    if(category != null && !category.trim().isEmpty()) {
	    	queryString.append(" AND c.title LIKE :category");
	    }
	    
	    // If sorting option selected then add it to the query 
	    if(sort != null && (sort.equals("title") || sort.equals("createdDate"))) {
	    	queryString.append(" ORDER BY n.").append(sort);
	    	
	    	// If sort by date then Descending, Else Ascending
	        if(sort.equals("createdDate")) {
	        	queryString.append(" DESC");
	        } 
	        else {
	        	queryString.append(" ASC");
	        }
	    }
	    
	    // Create query from queryString
	    query = session.createQuery(queryString.toString(), Note.class);
	    query.setParameter("user", user);

	    // Set the date
	    if(createdDate != null) {
	        query.setParameter("date", createdDate);
	    }
	    
	    // Set the Searching name
	    if(title != null && !title.trim().isEmpty()) {
	        query.setParameter("title", "%" + title.trim() + "%");
	    }
	    
	    // Set the Searching category name
	    if(category != null && !category.trim().isEmpty()) {
	        query.setParameter("category", "%" + category.trim() + "%");
	    }
	    List<Note> notes = query.getResultList();
	    session.clear();
	    session.close();
	    return notes;
	}

	@SuppressWarnings("deprecation")
	public void editNote(Note note, User user, String title, String content, String filename) {
		Transaction transaction = session.beginTransaction();
		Query<?> query = session.createQuery("UPDATE note_entity SET title = :title, content = :content, filename = :filename WHERE id = :id");
		
		query.setParameter("title", title);
		query.setParameter("content", content);
		query.setParameter("filename", filename);
		query.setParameter("id", note.getId());
		
		query.executeUpdate();
		transaction.commit();
		session.clear();
		session.close();
	}

	public void deleteNote(Note note, User user) {
		Transaction transaction = session.beginTransaction();
		note.getCategories().clear();
		session.remove(note);
		
		List<Note> notes = user.getNotes();
		notes.remove(note);
		transaction.commit();
		session.clear();
		session.close();
	}
}
