
public class Teacher extends User {

	public Teacher(String username, String password, String type) {
		super(username, password, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addCourse(String courseName, String accessCode) {
		if (isCourseExist(courseName)) {
			System.out.println("Course: " + courseName + " exists!");
		} else {
			if (isValidCode(accessCode)) {
				Database.createCourse(getUsername(), courseName, accessCode);
			} else {
				System.out.println("Access code should be 4 letters or digits only");
			}
		}
		
	}

}
