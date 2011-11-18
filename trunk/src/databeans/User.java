package databeans;

public class User {
	private String emailaddress;
	private String password;
	private String firstName;
	private String lastName;
	private String gender;

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

	/*
	 * When the user log in, need to check if the password is right
	 */
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
}
