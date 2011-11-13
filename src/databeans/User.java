package databeans;


public class User {
	//basic information for a user.
	private String  emailaddress;
	private String  password;
	private String firstName;
	private String lastName;
    //more information
	private String gender;
	private String country;
	private String state;

	//constructor, use it as primary key in user table.
	public User(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	//getters and setters.
	public String getEmailAddress() {
		return emailaddress;
	}
	public String getPassword(){ 
		return password;	
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
	public String getCountry() {
		return country;
	}
	public String getState() {
		return state;
	}	
	public void setPassword(String s){ 
		password = s;     
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setState(String state) {
		this.state = state;
	}
	/*
	 * When the user log in, need to check if the password is right
	 */
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
}
