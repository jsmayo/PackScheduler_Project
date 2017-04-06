package edu.ncsu.csc216.pack_scheduler.user;



public class Faculty extends User {

	private int maxCourses;
	public static final int MIN_COURSES = 1;
	public static final int MAX_COURSES = 3;
	
	public Faculty(String firstName, String lastName, String id, String email, String hashedPassword, int maxCourses) {
		super(firstName, lastName, id, email, hashedPassword);
		setMaxCourses(maxCourses);
		//this.schedule = new Schedule();
	}
	
	public void setMaxCourses(int maxCourses) {
		if(maxCourses < Faculty.MIN_COURSES || maxCourses > Faculty.MAX_COURSES)
			throw new IllegalArgumentException();
		this.maxCourses = maxCourses;
	}
	
	
	public int getMaxCourses() {
		return this.maxCourses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Faculty [maxCourses=" + maxCourses + "]";
	}

	

	
	
}
	
	


