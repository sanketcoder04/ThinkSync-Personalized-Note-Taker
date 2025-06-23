package thinksync.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType; 

@Entity(name = "feedback_entity")
@Table(name = "feedback_details")
public class Feedback {
	
	@Id
	@Column(name = "feedback_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "feedback", nullable = false, length = 1000)
	private String feedback;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_posted", nullable = false)
	private Date postedDate;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity = User.class)
	@JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
	private User user;
	
	public Feedback(Integer id, String feedback, Date postedDate, User user) {
		super();
		this.id = id;
		this.feedback = feedback;
		this.postedDate = postedDate;
		this.user = user;
	}

	public Feedback(String feedback, User user, Date posteddate) {
		super();
		this.feedback = feedback;
		this.user = user;
		this.postedDate = posteddate;
	}

	public Feedback() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}