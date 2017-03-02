import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	private static final int ArrayList = 0;


	public static void main(String[] args){
//		System.out.println("Hello world");
//		if(Database.isValidUser("Paul", "1234")){
//			System.out.println("Valid");
//		}else{
//			System.out.println("Invalid");
//		}	
		//testAddQuizMethodInDBClass();
//		testSignUp(app);
		//testLogin(app, "Paul", "1234");
		//testLogin(app, "Liang", "1111");
//		loadCourse();
		
		App app = new App();

		
		
	}
	
	
	/*
	 * Load list of course objects from DB
	 * PASS
	 */
	public static void loadCourse() {
		List<Course> courses = Database.loadCourses("Liang");
		for(Course c : courses) {
			System.out.println("CourseName: " + c.getCourseName() + ", AccessCode: " + c.getAccessCode());
			
			for(Quiz q : c.getQuizs()) {
				System.out.println("QuizName: " + q.getName() + ", AccessTime: " + q.getAccessTime() + ", QuizTime: " + q.getQuizTime());
				
				for (Question question: q.getQuestions()) {
					question.printQuestionInfo();
				}
			}
		}
	}
	
	/*
	 * test addQuiz() method in DB class
	 * 
	 * PASS
	 * 
	 * command to check result in related tables:
	 * select * from Courses;select * from Quizs; select * from Questions; select * from Answers; select * from Choices;
	 */
	public static void testAddQuizMethodInDBClass(){
		Course course = new Course("COEN 275 OOADP", "1234", new ArrayList<Quiz>());
		course.createQuiz();
		Quiz tempQuiz = course.getTempQuiz();
		String quizName = "Liang test 2";
		long accessTime = 1488341477;
		long quizTime = 600000;
		tempQuiz.setName(quizName);
		tempQuiz.setAccessTime(accessTime);
		tempQuiz.setQuizTime(quizTime);

		//create duplicated questions
		String questiontext = "which language do you use in the COEN 275?";
		tempQuiz.createQuestion(questiontext);
		tempQuiz.getQuestions().get(tempQuiz.getQuestions().size() - 1).createAnswer("java", true);
		tempQuiz.getQuestions().get(tempQuiz.getQuestions().size() - 1).createAnswer("c", false);
		tempQuiz.getQuestions().get(tempQuiz.getQuestions().size() - 1).createAnswer("ruby", false);
		tempQuiz.getQuestions().get(tempQuiz.getQuestions().size() - 1).createAnswer("python", false);
			
		String questiontext1 = "which language do you use in the COEN 275?";
		tempQuiz.createQuestion(questiontext1);
		tempQuiz.getQuestions().get(tempQuiz.getQuestions().size() - 1).createAnswer("java", true);
		tempQuiz.getQuestions().get(tempQuiz.getQuestions().size() - 1).createAnswer("c", false);
		tempQuiz.getQuestions().get(tempQuiz.getQuestions().size() - 1).createAnswer("ruby", false);
		tempQuiz.getQuestions().get(tempQuiz.getQuestions().size() - 1).createAnswer("python", false);
		
		course.addQuiz(course.getCourseName());
		
		
	}
	
	/*
	 * Course table related methods
	 * 
	 * PASS
	 * 
	 */
	public void testCourseTable() {
		Database.createCourse("Liang", "test course", "1234");
		Database.deleteCourse("test course");
		Database.addCourse("Paul", "COEN 275 OOADP", "1234");
	}
	
	/*
	 * following is one of validation methods
	 * all the validation methods are all tested
	 * 
	 * PASS
	 */
	public void testValidationMethod() {
		if (Database.isQuizExist("COEN 275 OOADP", "quiz")) {
			System.out.println("exist");
		} else {
			System.out.println("not exist");
		}
	}
	
//	public static void testLogin(App app, String username, String password) {
//		app.login(username, password);
//		app.printCurrentUserInfo();
//		app.logout();
//	}
	
	public static void testSignUp(App app) {
		app.signUp("Paul", "1111", "1111", "student");
	}

}
