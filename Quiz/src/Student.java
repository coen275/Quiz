
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
			if (isValidCode(accessCode) && Database.addCourse(getUsername(), courseName, accessCode)) {
				courses.add(new Course(courseName, accessCode));
			} else {
				System.out.println("Course name or access code is wrong!");
			}
		}
	}
}
