import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class User {
	protected String username;
	protected String password;
	protected String type;
	protected List<Course> courses;
	
	public User(String username, String password, String type){
		this.username = username;
		this.password = password;
		this.type = type;
		courses = new ArrayList<>(); 
		loadCourse();
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getType() {
		return type;
	}
	
	public void resetPassword(String password) {
		this.password = password;
	}
	
	public boolean isCourseExist(String name) {
		for (Course course : courses) {
			if (course.getCourseName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public void loadCourse() {
		List<HashMap<String, String>> list = Database.loadCourse(getUsername());
		for (HashMap<String, String> map : list) {
			courses.add(new Course(map.get("name"), map.get("accesscode")));
		}
	}
	
	public void deleteCourse(String courseName) {
		for (Course course : courses) {
			if (course.getCourseName().equals(courseName)) {
				courses.remove(course);
				Database.deleteCourse(getUsername(), courseName);
			}
		}
	}
	
	public static boolean isValidCode(String s) {
		boolean isValid = true;
		if (s.length() == 4) {
			for(char c : s.toCharArray()) {
				if (!Character.isLetterOrDigit(c)) {
					isValid = false;
				}
			}
		} else {
			isValid = false;
		}
		return isValid;
	}
	
	public abstract void addCourse(String courseName, String accessCode);
	
}
