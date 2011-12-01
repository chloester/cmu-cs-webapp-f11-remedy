package databeans;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class User {
	private String emailaddress;
	private String firstName;
	private String lastName;
	private String gender;
	
	/*
	 * For hash function
	 * */
	private String hashedPassword = "*";
	private int salt = 0;
	//constructor, use it as primary key in user table.
	public User(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	//getters and setters.
	public String getHashedPassword() {
		return hashedPassword;
	}
	public String getEmailAddress() {
		return emailaddress;
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
	public int getSalt() {
		return salt;
	}
	public void setHashedPassword(String s) {
		hashedPassword = s;
	}
	public void setPassword(String s){ 
		salt = newSalt(); hashedPassword = hash(s);     
	}
	public void setFirstName(String s) {
		firstName = s;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setSalt(int salt) {
		this.salt = salt;
	}
	/*
	 * When the user log in, need to check if the password is right
	 */
	public boolean checkPassword(String password) {
		return hashedPassword.equals(hash(password));
	}
	
	private String hash(String clearPassword) {
		MessageDigest md = null;
		try {
		  md = MessageDigest.getInstance("SHA1");
		} catch (NoSuchAlgorithmException e) {
		  throw new AssertionError("Can't find the SHA1 algorithm in the java.security package");
		}

		String saltString = String.valueOf(salt);
		
		md.update(saltString.getBytes());
		md.update(clearPassword.getBytes());
		byte[] digestBytes = md.digest();

		// Format the digest as a String
		StringBuffer digestSB = new StringBuffer();
		for (int i=0; i<digestBytes.length; i++) {
		  int lowNibble = digestBytes[i] & 0x0f;
		  int highNibble = (digestBytes[i]>>4) & 0x0f;
		  digestSB.append(Integer.toHexString(highNibble));
		  digestSB.append(Integer.toHexString(lowNibble));
		}
		String digestStr = digestSB.toString();

		return digestStr;
	}
	private int newSalt() {
		Random random = new Random();
		return random.nextInt(8192)+1;  // salt cannot be zero, except for uninitialized password
	}
}
