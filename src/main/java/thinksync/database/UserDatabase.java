package thinksync.database;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import thinksync.entities.User;

public class UserDatabase {
	private Session session;
	
	public UserDatabase(Session session) {
		super();
		this.session = session;
	}
	
	public void signupUser(User user) {
		Transaction transaction = session.beginTransaction();
		session.persist(user);
		transaction.commit();
		session.clear();
		session.close();
	}
	
	public User getUserFromEmail(User user) {
		String email = user.getEmail();
		
		Query<User> query = session.createQuery("FROM user_entity WHERE email = :email", User.class);
		query.setParameter("email", email);
		
		User userRecord = query.uniqueResult();
		session.clear();
		session.close();
		return userRecord;
	} 
	
	public User loginUser(User user) {
		String name = user.getName();
		String email = user.getEmail();
		String password = user.getPassword();
		
		Query<User> query = session.createQuery("FROM user_entity WHERE name = :name AND email = :email AND password = :password", User.class);
		query.setParameter("name", name);
		query.setParameter("email", email);
		query.setParameter("password", password);
		
		User userRecord = query.uniqueResult();
		session.clear();
		session.close();
		return userRecord;
	}
	
	public void deleteUser(User user) {
		Transaction transaction = session.beginTransaction();
		session.remove(user);
		transaction.commit();
		session.clear();
		session.close();
	}
	
	@SuppressWarnings("deprecation")
	public void updateUser(User oldUser, User newUser) {
		Transaction transaction = session.beginTransaction();
		Query<?> query = session.createQuery("UPDATE user_entity SET name = :name, email = :email, password = :password, about = :about, organization = :organization, occupation = :occupation, facebookURL = :facebookURL, xURL = :xURL, linkedinURL = :linkedinURL WHERE id = :id");
		
		query.setParameter("name", newUser.getName());
		query.setParameter("email", newUser.getEmail());
		query.setParameter("password", newUser.getPassword());
		query.setParameter("about", newUser.getAbout());
		query.setParameter("organization", newUser.getOrganization());
		query.setParameter("occupation", newUser.getOccupation());
		query.setParameter("facebookURL", newUser.getFacebookURL());
		query.setParameter("xURL", newUser.getxURL());
		query.setParameter("linkedinURL", newUser.getLinkedinURL());
		query.setParameter("id", oldUser.getId());
		
		query.executeUpdate();
		transaction.commit();
		session.clear();
		session.close();
	}
}
