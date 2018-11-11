/**
 * 
 */
package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Eduard this class has methods for validating login , password, and
 *         email for web application
 *
 */
public class Validation {
	/**
	 * 
	 * @param login incoming login or password for validation
	 * @return true if login matches pattern else returns false
	 */
	public static boolean getValid(String login) {
		boolean valid = false;
		if ((login != null) && (login.length() < 30)) {
			Pattern p = Pattern.compile("^\\w+$");
			Matcher m = p.matcher(login);
			valid = m.matches();
		}
		return valid;
	}

	/**
	 * 
	 * @param mail incoming email for validation
	 * @return true if email matches pattern else returns false
	 */
	public static boolean getValidEmail(String mail) {
		boolean valid = false;
		if ((mail != null) && (mail.length() < 30)) {
			Pattern p = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
			Matcher m = p.matcher(mail);
			valid = m.matches();
		}

		return valid;
	}

	/**
	 * 
	 * @param login     incoming login for validation
	 * @param password1 incoming first password for validation and comparison with
	 *                  password confirmation
	 * @param password2 incoming password confirmation for validation and comparison
	 *                  with first password
	 * @return true if login and password matches pattern and password1 equals
	 *         password2 else returns false
	 */
	public static boolean getRegValid(String login, String password1, String password2) {
		boolean valid = false;
		if ((login != password1) && (password1 == password2) && (Validation.getValid(login) && Validation.getValid(password1)
				&& Validation.getValid(password2))) {
			valid = true;
		}
		return valid;
	}
}
