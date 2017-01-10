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

	
	/** 
	 * Generates a hashCode for Student using all fields.
	 * @return hashCode for Student.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + maxCredits;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}


	/** 
	 * Compares a given Object to this object for equality on all fields.
	 * @param obj The Object to comare.
	 * @return true if the objects are the same on all fields. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (maxCredits != other.maxCredits)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}


	/**
	 * Returns a comma separated value String of all Student fields.
	 * @return String representation of Student.
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "firstName,lastName,id,email,password,maxCredits";
	}
	
}
	
