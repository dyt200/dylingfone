package dylingfone;

public class Pictures {
	
	private int id;
	private String title;
	private String description;
	private String path;
	
	public Pictures (int id, String title, String description, String path) {
		this.title = title;
		this.description = description;
		this.path = path;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

}
