package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Creates a Student Object to be used with the StudentRecordIO class.
 * Students are only created if the input is valid. Conditions exist for
 * the Student's: first name, last name, id, email, password, and maximum credit hours.
 * @author Steven Mayo with partial skeleton code from Sarah Heckman
 */
public class Student {
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
	/** Integer for the maximum number of credits available to the student */
	private int maxCredits;
	/** The default maximum number of credits allowed at NC State */
	public static final int MAX_CREDITS = 18;

	/**
	 * Student constructor that creates a new student object from passed in parameters using setter methods.
	 * @param firstName Student's first name.
	 * @param lastName Student's last name.
	 * @param id Students identification tag.
	 * @param email Student's email address.
	 * @param hashedPassword A pre-hashed password for the students account.
	 * @param maxCredits The maximum number of credits available to the student. 
	 */
	public Student(String firstName, String lastName, String id, String email, String hashedPassword, int maxCredits)  {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(hashedPassword);
		setMaxCredits(maxCredits);
		
	}
	
	/**
	 * An overloaded constructor for the Student Object. It uses the maximum number of courses a student can take
	 * at NC State if one is not provided initially. 
	 * @param firstName Student's first name.
	 * @param lastName Student's last name.
	 * @param id Student's identification tag.
	 * @param email Student's email address.
	 * @param hashedPassword Student's pre-hashed password. 
	 */
	public Student(String firstName, String lastName, String id, String email, String hashedPassword) {
		this(firstName, lastName, id, email, hashedPassword, MAX_CREDITS); //passes to other constructor with a default value of 18 for credits.
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
	public String getEmail(){
		return email;
	}

	/**
	 * Retrieves the student's password, which was passed in as a hashed value.
	 * @return hashedPassword
	 */
	public String getPassword(){
		return hashedPassword;
	}
	
	/**
	 * Retrieves the maximum number of course available to the student.
	 * @return maxCredits
	 */
	public int getMaxCredits(){
		return maxCredits;
	}
	
	/**
	 * Sets the students first name,  which is invalid if it is a null or empty string.
	 * @param firstName The students first name
	 * @throws IllegalArgumentException if the first name is invalid
	 */
	public void setFirstName(String firstName){
		if(firstName == null || firstName.isEmpty()) throw new IllegalArgumentException("Invalid first name");
		this.firstName = firstName;
	}
	
	/**
	 * Sets the student's last name,  which is invalid if it is a null or empty string.
	 * @param lastName The students last name.
	 * @throws IllegalArgumentException if the last name is invalid
	 */
	public void setLastName(String lastName){
		if(lastName == null || lastName.isEmpty()) throw new IllegalArgumentException("Invalid last name");
		this.lastName = lastName;
	}
	
	/**
	 * Set's the student's identification tag, which is invalid if it is a null or empty string.
	 * @param id The student's identification tag.
	 * @throws IllegalArgumentException if the ID is invalid.
	 */
	public void setId(String id){
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
	public void setEmail(String email){
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
	public void setPassword(String hashedPassword){
		if(hashedPassword == null || hashedPassword.isEmpty()) throw new IllegalArgumentException("Invalid password");
		this.hashedPassword = hashedPassword;
	}
	
	/**
	 * Sets the student's maximum number of credits available to that particular student, which must be greater than
	 * 3 hours and less than 18 hours.
	 * @param maxCredits The student's maximum number of credits they can register for.
	 * @throws IllegalArgumentException if exceeding or falling short of the requirements of NC State.
	 */
	public void setMaxCredits(int maxCredits){
		if(maxCredits < 3 || maxCredits > 18) throw new IllegalArgumentException("Invalid max credits");
		this.maxCredits = maxCredits;
	}

	/**
	 * Generates a hashcode for the Student class using all fields.
	 * @return hashCode for Student class.
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
		result = prime * result + ((hashedPassword == null) ? 0 : hashedPassword.hashCode());
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
		if (hashedPassword == null) {
			if (other.hashedPassword != null)
				return false;
		} else if (!hashedPassword.equals(other.hashedPassword))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all student fields/
	 * @return String representation of Student
	 */
	@Override
	public String toString() {
		return firstName + "," + lastName + "," + id + "," + email + "," + hashedPassword + "," + maxCredits;
	}
}
		