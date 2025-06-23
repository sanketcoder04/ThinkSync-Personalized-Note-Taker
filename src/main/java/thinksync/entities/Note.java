package thinksync.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "note_entity")
@Table(name = "note_details")
public class Note {

	@Id
	@Column(name = "note_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "note_title", nullable = false, updatable = true)
	private String title;

	@Column(name = "note_content", nullable = false, updatable = true, length = 100000)
	private String content;

	@Column(name = "note_filename", nullable = false, updatable = true)
	private String filename;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_creation")
	private Date createdDate;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
	@JoinColumn(name = "user_id", nullable = false, updatable = false, referencedColumnName = "user_id")
	private User user;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Category.class)
	@JoinTable(name = "note_category", joinColumns = @JoinColumn(name = "note_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "category_id", nullable = false))
	private List<Category> categories;

	public Note(Integer id, String title, String content, String filename, Date createdDate, User user, List<Category> categories) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.createdDate = createdDate;
		this.user = user;
		this.categories = categories;
	}
	
	public Note(String title, String content, String filename, Date createdDate, User user, List<Category> categories) {
		super();
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.createdDate = createdDate;
		this.user = user;
		this.categories = categories;
	}
	
	public Note(String title, String content, String filename, Date createdDate, User user) {
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.createdDate = createdDate;
		this.user = user;
	}

	public Note(String title, String content, String filename, List<Category> categories) {
		super();
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.categories = categories;
	}

	public Note() {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
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

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}