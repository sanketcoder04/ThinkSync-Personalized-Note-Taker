package thinksync.entities;

public class NoteFilter {
	private String sortItem; 
	private String date;
	private String searchTitle;
	private String searchCategory;
	
	public NoteFilter(String sortItem, String date, String searchTitle, String searchCategory) {
		super();
		this.sortItem = sortItem;
		this.date = date;
		this.searchTitle = searchTitle;
		this.searchCategory = searchCategory;
	}

	public String getSortItem() {
		return sortItem;
	}

	public void setSortItem(String sortItem) {
		this.sortItem = sortItem;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSearchTitle() {
		return searchTitle;
	}

	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

	public String getSearchCategory() {
		return searchCategory;
	}

	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}
}