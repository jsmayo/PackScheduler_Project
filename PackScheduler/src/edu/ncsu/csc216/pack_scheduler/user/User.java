package edu.ncsu.csc216.pack_scheduler.user;

public abstract class User {

	/** String for student's first name */
	private String firstName;
	/** String for student's last name */
	private String lastName;
	/** String for student's email address */
	private String email;
	/** String for student's identification tag */
	private String id;
	/** String for student's pre-hashed password */
	private String hashedPassword;

	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	/**
	 * Retrieves the student's first name.
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Retrieves the student's last name.
	 * @return lastName
	 */
	public String getLastName() {
	
		return lastName;
	}

	/**
	 * Retrieves the student's identification tag.
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Retrieves the student's email address.
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Retrieves the student's password, which was passed in as a hashed value.
	 * @return hashedPassword
	 */
	public String getPassword() {
		return hashedPassword;
	}

	/**
	 * Sets the students first name,  which is invalid if it is a null or empty string.
	 * @param firstName The students first name
	 * @throws IllegalArgumentException if the first name is invalid
	 */
	public void setFirstName(String firstName) {
		if(firstName == null || firstName.isEmpty()) throw new IllegalArgumentException("Invalid first name");
		this.firstName = firstName;
	}

	/**
	 * Sets the student's last name,  which is invalid if it is a null or empty string.
	 * @param lastName The students last name.
	 * @throws IllegalArgumentException if the last name is invalid
	 */
	public void setLastName(String lastName) {
		if(lastName == null || lastName.isEmpty()) throw new IllegalArgumentException("Invalid last name");
		this.lastName = lastName;
	}

	/**
	 * Set's the student's identification tag, which is invalid if it is a null or empty string.
	 * @param id The student's identification tag.
	 * @throws IllegalArgumentException if the ID is invalid.
	 */
	protected void setId(String id) {
		if(id == null || id.isEmpty()) throw new IllegalArgumentException("Invalid id");
		if(id.contains("@")) throw new IllegalArgumentException("Invalid id");
		this.id = id;
	}

	/**
	 * Sets the student's email address, which is invalid if it is a null or empty string. Additionally,
	 * the student's email address must contain the "@" and "." characters with the last index of the "." appearing
	 * after the "@" character.
	 * @param email The student's email address
	 * @throws IllegalArgumentException if the email is invalid.
	 */
	public void setEmail(String email) {
		if(email == null || email.isEmpty()) throw new IllegalArgumentException("Invalid email");
		if( !(email.contains(".")) || !(email.contains("@"))) throw new IllegalArgumentException("Invalid email");
		if(email.lastIndexOf(".") < email.lastIndexOf("@")) throw new IllegalArgumentException("Invalid email");
		this.email = email;
	}

	/**
	 * Sets the student's pre-hashed password, which is invalid if it is a null or empty string.
	 * @param hashedPassword The student's password that was passed in pre-hashed via "SHA-256".
	 * @throws IllegalArgumentException if the password is invalid.
	 */
	public void setPassword(String hashedPassword) {
		if(hashedPassword == null || hashedPassword.isEmpty()) throw new IllegalArgumentException("Invalid password");
		this.hashedPassword = hashedPassword;
	}

	/**
	 * Generates a hashcode for the User class using all fields.
	 * @return hashCode for User class.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((hashedPassword == null) ? 0 : hashedPassword.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality on all fields.
	 * @param obj the object to compare.
	 * @return will return true if the objects are the same on all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
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
		if (hashedPassword == null) {
			if (other.hashedPassword != null)
				return false;
		} else if (!hashedPassword.equals(other.hashedPassword))
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
		return true;
	}

}