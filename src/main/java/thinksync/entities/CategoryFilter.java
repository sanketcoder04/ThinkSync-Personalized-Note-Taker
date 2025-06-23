package thinksync.entities;

public class CategoryFilter {
	private String sortItem; 
	private String date;
	private String searchName;
	
	public CategoryFilter(String sortItem, String date, String searchName) {
		super();
		this.sortItem = sortItem;
		this.date = date;
		this.searchName = searchName;
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

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
}
