package edu.ncsu.csc216.pack_scheduler.user;

public class Student {

	private String firstName;
	private String lastName;
	private String id;
	private String email;
	private String password;
	private int maxCredits;
	static final int MAX_CREDITS = 18;
	
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(hashPW);
		setMaxCredits(maxCredits);
	}
	

	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, 18);
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		if(firstName == null || firstName.isEmpty()) throw new IllegalArgumentException();
		this.firstName = firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		if(lastName == null || lastName.isEmpty()) throw new IllegalArgumentException();
		this.lastName = lastName;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	private void setId(String id) {
		if(id == null || id.isEmpty()) throw new IllegalArgumentException();
		this.id = id;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		if(email == null || email.isEmpty()) throw new IllegalArgumentException();
		int atIndex = 0;
		int dotIndex = 0;
		for(int i = 0; i < email.length(); i++){
			if(email.charAt(i) == '@') atIndex = i;
			if(email.charAt(i) == '.') dotIndex = i;
		}
		if(dotIndex >= atIndex || dotIndex == 0) throw new IllegalArgumentException(". before @");
		if(atIndex <= 0) throw new IllegalArgumentException("Email must have @ symbol.");
		this.email = email;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		if(password == null || password.isEmpty()) throw new IllegalArgumentException("Password cannot be null or empty.");
		this.password = password;
	}


	/**
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}


	/**
	 * @param maxCredits the maxCredits to set
	 */
	public void setMaxCredits(int maxCredits) {
		if(maxCredits < 3 || maxCredits > 18) throw new IllegalArgumentException("Max credits cannot be less than 3 or greater than 18.");
		this.maxCredits = maxCredits;
	}
}
	
