package thinksync.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import thinksync.entities.Feedback;

public class FeedbackDatabase {
private Session session;
	
	public FeedbackDatabase(Session session) {
		super();
		this.session = session;
	}
	
	public void uploadFeedback(Feedback feedback) {
		Transaction transaction = session.beginTransaction();
		session.persist(feedback);
		transaction.commit();
		session.clear();
		session.close();
	}
	
	public List<Feedback> getFeedbacks() {
		Query<Feedback> query = session.createQuery("FROM feedback_entity", Feedback.class);
		List<Feedback> feedbacks = query.getResultList();
		session.clear();
		session.close();
		return feedbacks;
	}
}
