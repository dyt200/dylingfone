package dylingfone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * Basic functions for testing data added to XML
 * @author Dylan Thompson & Ben Pocklington
 */
public class StringFunc {
	
	/**
	 * Checks to see if the string can be a valid date
	 * @param inDate
	 * @return boolean
	 */
	public static boolean isValidDate(String inDate) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	    dateFormat.setLenient(false);
	    try {
	      dateFormat.parse(inDate.trim());
	    } catch (ParseException pe) {
	      return false;
	    }
	    return true;
	 }
	
	/**
	 * Checks to see if the string is a valid email
	 * @param email
	 * @return boolean
	 */
	public static boolean isValidEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                             
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
	
	/**
	 * Checks to see if the string is a valid phone number
	 * @param number
	 * @return boolean
	 */
	public static boolean isValidPhoneNumber(String number) {
		if(number.matches("[0-9.+]*"))
			return true;
		else
			return false;
	}
}
