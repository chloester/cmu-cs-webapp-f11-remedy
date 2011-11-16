package formbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mybeans.form.FormBean;

public class RegisterForm extends FormBean {
	private String userName;
	private String password;
	private String confirmpassword;
	private String firstName;
	private String lastName; 
	private String gender;
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;

	//getters and setters.
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getGender() {
		return gender;
	}
	
	public void setUserName(String emailaddress) {
		this.userName = trimAndConvert(emailaddress,"<>\"");
	}
	public void setPassword(String password) {
		this.password = trimAndConvert(password,"<>\"");
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = trimAndConvert(confirmpassword,"<>\"");
	}
	public void setFirstName(String firstName) {
		this.firstName = trimAndConvert(firstName,"<>\"");
	}
	public void setLastName(String lastName) {
		this.lastName = trimAndConvert(lastName,"<>\"");
	}
	public void setGender(String gender) {
		this.gender = trimAndConvert(gender,"<>\"");
	}

	//register check errors function
	public List<String> registerCheckErrors() {
		List<String> errors = new ArrayList<String>();

		if( userName==null|| userName.length()==0){
			errors.add("User Email is required");
		}else if(!emailValidator(userName)){
			errors.add("Invalid format for email address");
		}else if (userName.length() >= 255) {
			errors.add("Email address is too long");
		}else if(password==null||password.length()==0){
			errors.add("Password is required");
		}else if (password.length() >= 20) {
			errors.add("Password's length is 1-20, please re-enter.");
		}else if (confirmpassword == null || confirmpassword.length() == 0) {
			errors.add("Confirm Password is required");
		}else if (firstName == null || firstName.length() == 0) {
			errors.add("FirstName is required");
		}else if (lastName == null || lastName.length() == 0) {
			errors.add("LastName is required");
		}
		if (!password.equals(confirmpassword)) {
			errors.add("Passwords do not match, please try again");
		}

		/*if (emailAddress.length() >= 50) {
			errors.add("emailaddress's length is 3-50, please re-enter.");
		}
		if (password.length() >= 20) {
			errors.add("password's length is 3-20, please re-enter.");
		}
		if (confirmpassword.length() >= 20) {
			errors.add("Confirm Password's length is 3-20, please re-enter.");
		}
		if (firstName.length() >= 20) {
			errors.add("First Name's length is 3-20, please re-enter.");
		}
		if (lastName.length() >= 20) {
			errors.add("Last Name's length is 3-20, please re-enter.");
			}*/	
				//if there was no error, it should be null.
			return errors;
		}
		//using regular expression to identify email address.
		private boolean emailValidator(String EmailAddress){
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(EmailAddress);
			return matcher.matches();
		}
	}
