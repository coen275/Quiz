package Models;

import java.util.ArrayList;
import java.util.List;

import Server.Database;
import Models.Course;

/**
 * Extract the relevant details and methods of student and teacher
 * Add/Delete courses
 * User is about to review results
 */
public abstract class User {
	protected String username;		//username
	protected String password;		//password
	protected String type;			//student or teacher
	protected List<Course> courses;	//list of courses
	
	/**
	 * Constructor of user to init basic info
	 * @param username
	 * @param password
	 * @param type
	 */
	public User(String username, String password, String type){
		this.username = username;
		this.password = password;
		this.type = type;
		courses = new ArrayList<>(); 
		loadCourse();
	}
	
	/**
	 * Get user name
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Get password
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Get type of user
	 * @return
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Check if course is existing
	 * @param name
	 * @return
	 */
	public boolean isCourseExist(String name) {
		for (Course course : courses) {
			if (course.getCourseName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Load course information for the current user
	 */
	public void loadCourse() {
		List<Course> list = Database.loadCourses(getUsername());
		courses.clear();
		for (Course c : list) {
			courses.add(c);
		}
	}
	
	/**
	 * Get the list of courses
	 * @return
	 */
	public List<Course> getCourse() {
		return courses;
	}
	
	/**
	 * Get the array of courses
	 * @return
	 */
	public Course[] getCourseArray(){
		Course[] courseArray = new Course[courses.size()];
		for(int i = 0; i < courseArray.length; i++){
			courseArray[i] = courses.get(i);
		}
		return courseArray;
	}
	
	/**
	 * Check if access code is valid
	 * @param s
	 * @return
	 */
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
	
	/**
	 * Delete the course based on the course name
	 * @param courseName
	 */
	public void deleteCourse(String courseName) {
		for (Course c : courses) {
			if (c.getCourseName().equals(courseName) && c.getQuizs().size() <= 0) {
				courses.remove(c);
				Database.deleteCourse(courseName);
				break;
			}
		}
	}
	
	/**
	 * Student submit the quiz result
	 * @param username
	 * @param quiz
	 * @param courseName
	 */
	public void submitResult(String username, Quiz quiz, String courseName) {
		String quizName = quiz.getName();
		int quizID = Database.getQuizID(quizName, courseName);
		int userID = Database.getUserID(username);
		int answerID, questionID;
		List<Question> questions = quiz.getQuestions();
		for (Question q : questions) {
			for(Answer a : q.getAnswers()) {
				if (a.isSelected()){
					answerID = a.getAnswerID();
					questionID = Database.getQuestionID(answerID);
					Database.addQuestionResult(userID, quizID, questionID, answerID);
				}
			}
		}
		calculateQuizResult(userID, quizID);
	}
	
	/**
	 * Calculate the quiz result
	 * @param userID
	 * @param quizID
	 */
	public void calculateQuizResult(int userID, int quizID) {
		Database.calculateQuizResult(userID, quizID);
	}
	
	/**
	 * Add/Enroll the course
	 * @param courseName
	 * @param accessCode
	 */
	public abstract void addCourse(String courseName, String accessCode);
	
}
