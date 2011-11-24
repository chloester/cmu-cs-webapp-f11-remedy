package formbeans;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

import org.mybeans.form.FormBean;

public class LoginForm extends FormBean {
	//login form input variables.
	private String emailaddress;
	private String password;
	private String redirect;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	private Matcher matcher;
	
	//getter and setter.
	public String getRedirect(){
		return redirect;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public String getPassword(){ 
		return password; 
    }
	//trimAndConvert trim email, preventing html injection.
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = trimAndConvert(emailaddress,"<>\"");
	}	
	public void setRedirect(String redirect){
		this.redirect = trimAndConvert(redirect,"<>\"");
	}
	public void setPassword(String password) {	
		this.password = trimAndConvert(password,"<>\"");
	}
	
	//check email adress and password format.
	public List<String> checkLoginFormErrors(){
		List<String> errors = new ArrayList<String>();
		
		if(emailaddress==null||emailaddress.length()==0){
			errors.add("User Email is required");
		}else if(!emailValidator(emailaddress)){
			errors.add("Invalid format for email address");
		}else if (emailaddress.length() >= 255) {
			errors.add("Email address is too long.");
		}
		if(password==null||password.length()==0){
			errors.add("Password is required");
		}else if (password.length() >= 20) {
			errors.add("Password's length is 1-20, please re-enter.");
		}
		//if there is no error, it should be null
		return errors;
	}
	
	//using regular expression to identify email address.
	private boolean emailValidator(String EmailAddress){
		  pattern = Pattern.compile(EMAIL_PATTERN);
		  matcher = pattern.matcher(EmailAddress);
		  return matcher.matches();
	 }
}