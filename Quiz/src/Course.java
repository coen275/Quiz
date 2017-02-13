
public class Course {
	String name;
	String accessCode;
	
	public Course(String name, String accessCode) {
		this.name = name;
		this.accessCode = accessCode;
	}
	
	public void setCourseName(String name) {
		this.name = name;
	} 
	
	public String getCourseName() {
		return name;
	}
	
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	
	public String getAccessCode() {
		return accessCode;
	}
}
