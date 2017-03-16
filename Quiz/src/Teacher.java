import java.util.List;

public class Teacher extends User {

	public Teacher(String username, String password, String type) {
		super(username, password, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addCourse(String courseName, String accessCode) {
		System.out.println("Create Course");
		if (isCourseExist(courseName)) {
			System.out.println("Course: " + courseName + " exists!");
		} else {
			if (!Database.isCourseExist(courseName) && isValidCode(accessCode)) {
				Database.createCourse(username, courseName, accessCode);
			} else {
				System.out.println("Access code should be 4 letters or digits only");
			}
		}
		
	}
	
	public void deleteCourse(String courseName) {
		for (Course course : courses) {
			if (course.getCourseName().equals(courseName)) {
				courses.remove(course);
				Database.deleteCourse(courseName);
			}
		}
	}
	


}
