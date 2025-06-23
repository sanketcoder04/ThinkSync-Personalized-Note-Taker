package thinksync.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "user_entity")
@Table(name = "user_details")
public class User{

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "password", nullable = false, unique = true)
	private String password;
	
	@Column(name = "about", nullable = false, length = 500)
	private String about;
	
	@Column(name = "occupation", nullable = false)
	private String occupation;
	
	@Column(name = "organization", nullable = false)
	private String organization;
	
	@Column(name = "facebook_url", nullable = true)
	private String facebookURL;
	
	@Column(name = "x_url", nullable = true)
	private String xURL;
	
	@Column(name = "linkedin_url", nullable = true)
	private String linkedinURL;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_of_creation", nullable = false)
	private Date creationDate;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", targetEntity = Note.class)
	private List<Note> notes = new ArrayList<Note>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", targetEntity = Category.class)
	private List<Category> categories = new ArrayList<Category>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "user", targetEntity = Feedback.class)
	private List<Feedback> feedbacks = new ArrayList<Feedback>();
	
	public User(Integer id, String name, String email, String password, String about, String occupation, String organization, String facebookURL, String xURL, String linkedinURL, Date creationDate, List<Note> notes, List<Category> categories, List<Feedback> feedbacks) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.occupation = occupation;
		this.organization = organization;
		this.facebookURL = facebookURL;
		this.xURL = xURL;
		this.linkedinURL = linkedinURL;
		this.creationDate = creationDate;
		this.notes = notes;
		this.categories = categories;
		this.feedbacks = feedbacks;
	}

	public User(Integer id, String name, String email, String password, String about, String occupation, String organization, String facebookURL, String xURL, String linkedinURL, Date creationDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.occupation = occupation;
		this.organization = organization;
		this.facebookURL = facebookURL;
		this.xURL = xURL;
		this.linkedinURL = linkedinURL;
		this.creationDate = creationDate;
	}

	public User(String name, String email, String password, String about, String occupation, String organization, String facebookURL, String xURL, String linkedinURL) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.occupation = occupation;
		this.organization = organization;
		this.facebookURL = facebookURL;
		this.xURL = xURL;
		this.linkedinURL = linkedinURL;
	}

	public User(String name, String email, String password, String about, String occupation, String organization, String facebookURL, String xURL, String linkedinURL, Date creationDate) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.occupation = occupation;
		this.organization = organization;
		this.facebookURL = facebookURL;
		this.xURL = xURL;
		this.linkedinURL = linkedinURL;
		this.creationDate = creationDate;
	}

	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public User(String name, String email, String password, String about, String occupation, String organization) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.occupation = occupation;
		this.organization = organization;
	}

	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getFacebookURL() {
		return facebookURL;
	}

	public void setFacebookURL(String facebookURL) {
		this.facebookURL = facebookURL;
	}

	public String getxURL() {
		return xURL;
	}

	public void setxURL(String xURL) {
		this.xURL = xURL;
	}

	public String getLinkedinURL() {
		return linkedinURL;
	}

	public void setLinkedinURL(String linkedinURL) {
		this.linkedinURL = linkedinURL;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about=" + about
				+ ", occupation=" + occupation + ", organization=" + organization + ", facebookURL=" + facebookURL
				+ ", xURL=" + xURL + ", linkedinURL=" + linkedinURL + ", creationDate=" + creationDate + ", notes="
				+ notes + ", categories=" + categories + ", feedbacks=" + feedbacks + "]";
	}
}
