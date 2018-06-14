package dylingfone;

//Hello World
import java.util.Date;

/**
 * Object for a single contact with all their details
 * 
 * @author Dylan Thompson & Ben Pockligton
 * 
 *  */
public class Contact {
	private int id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private String email;
	private String telMobile;
	private String telHome;
	private int pic;
	
	/**
	 * Creation of contact WITHOUT an image
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param email
	 * @param telMobile
	 * @param telHome
	 */
	public Contact(int id, String firstName, String lastName, Date birthDate, String email, String telMobile, String telHome) {
		this.setId(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.telMobile = telMobile;
		this.telHome = telHome;
		this.pic = -1;
	}
	
	/**
	 * Creation of contact WITH an image
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param email
	 * @param telMobile
	 * @param telHome
	 * @param pic
	 */
	public Contact(int id, String firstName, String lastName, Date birthDate, String email, String telMobile, String telHome, int pic) {
		this.setId(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.telMobile = telMobile;
		this.telHome = telHome;
		this.pic = pic;
	}
	/**
	 * @return First name
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Sets first name
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Sets last name
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return birth date (Date)
	 */
	public Date getBirthDate() {
		return birthDate;
	}
	/**
	 * sets birth date
	 * @param birthDate
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	/**
	 * @return id of associated image
	 */
	public int getPic() {
		return pic;
	}
	/**
	 * set picture id
	 * @param pic
	 */
	public void setPic(int pic) {
		this.pic = pic;
	}
	/**
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * set email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 
	 * @return telephone mobile
	 */
	public String getTelMobile() {
		return telMobile;
	}
	/**
	 * set telephone mobile
	 * @param telMobile
	 */
	public void setTelMobile(String telMobile) {
		this.telMobile = telMobile;
	}
	/**
	 * 
	 * @return telephone home
	 */
	public String getTelHome() {
		return telHome;
	}
	/**
	 * set telephone home
	 * @param telHome
	 */
	public void setTelHome(String telHome) {
		this.telHome = telHome;
	}
	/**
	 * 
	 * @return contact id
	 */
	public int getId() {
		return id;
	}
	/**
	 * set contact id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
}
