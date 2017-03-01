import java.util.ArrayList;

public class Student extends User {

	public Student(String username, String password, String type) {
		super(username, password, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addCourse(String courseName, String accessCode) {
		if (isCourseExist(courseName)) {
			System.out.println("Course: " + courseName + " exists!");
		} else {
			if (isValidCode(accessCode)) {
				courses.add(new Course(courseName, accessCode, new ArrayList<Quiz>()));
				Database.addCourse(getUsername(), courseName, accessCode);
			} else {
				System.out.println("Course name or access code is wrong!");
			}
		}
	}
}
