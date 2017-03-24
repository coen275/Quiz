package Models;

import Server.Database;

/**
 * Teacher concrete class
 *
 */
public class Teacher extends User {

	/**
	 * Constructor
	 * @param username
	 * @param password
	 * @param type
	 */
	public Teacher(String username, String password, String type) {
		super(username, password, type);
	}

	/**
	 * Create the course
	 */
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
	


}
