public class RegisteredCourse {
	private boolean onWaitlist;
	private Course course;
	private Index index; 
	private Student student;
	
	//constructor method
	public RegisteredCourse(boolean onWaitlist, Course course, Index index, Student student) {
		this.onWaitlist = onWaitlist;
		this.course = course;
		this.index = index;
		this.student = student;
	}
	
	
	//get methods
	public boolean getOnWaitlist() {
		return onWaitlist;
	}

	public Course getCourse() {
		return course;
	}
	
	public Index getIndex() {
		return index;
	}
	
	public Student getStudent() {
		return student;
	}
	
	
	//set methods
	public void setOnWaitlist(boolean onWaitlist ) {
		this.onWaitlist = onWaitlist;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public void setIndex(Index index) {
		this.index = index;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}
}
