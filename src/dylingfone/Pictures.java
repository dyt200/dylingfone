package dylingfone;

/**
 * Object for an individual image and its details
 * @author Dylan Thompson & Ben Pocklington
 *
 */
public class Pictures {
	
	private int id;
	private String title;
	private String description;
	private String path;
	
	/**
	 * Set picture parameters
	 * @param id
	 * @param title
	 * @param description
	 * @param path
	 */
	public Pictures (int id, String title, String description, String path) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.path = path;
	}
	
	/**
	 * @return picture title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * set picture title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return picture description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * set picture description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return picture id
	 */
	public int getId() {
		return id;
	}
	/**
	 * set picture id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return picture file path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * set picture file path
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}

}
