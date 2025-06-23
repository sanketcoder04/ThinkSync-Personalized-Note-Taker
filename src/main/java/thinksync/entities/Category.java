package thinksync.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "category_entity")
@Table(name = "category_details")
public class Category {
	
	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "category_title")
	private String title;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_creation")
	private Date createdDate;
	
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
	@JoinColumn(name = "user_id", nullable = false, updatable = false, referencedColumnName = "user_id")
	private User user;
	
	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Note.class, mappedBy = "categories")
	private List<Note> notes;

	public Category(Integer id, String title, Date createdDate, User user, List<Note> notes) {
		super();
		this.id = id;
		this.title = title;
		this.createdDate = createdDate;
		this.user = user;
		this.notes = notes;
	}

	public Category(String title, Date createdDate, User user) {
		super();
		this.title = title;
		this.createdDate = createdDate;
		this.user = user;
	}

	public Category(String title, Date createdDate, User user, List<Note> notes) {
		super();
		this.title = title;
		this.createdDate = createdDate;
		this.user = user;
		this.notes = notes;
	}

	public Category(String title, User user) {
		super();
		this.title = title;
		this.user = user;
	}
	
	public Category(String title) {
		super();
		this.title = title;
	}

	public Category() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
}