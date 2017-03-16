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
//		System.out.printf("%.2f", Database.getStudentScore("COEN 275 OOADP", "Liang test 2", "Paul"));
//		System.out.println(Database.getNumberOfRows("COEN 275 OOADP", "Liang test 2"));
//		System.out.printf("%.2f", Database.getMediumScore("COEN 275 OOADP", "Liang test 2"));
//		testSignUp(app);
//		testAddQuizMethodInDBClass();
//		Database.deleteQuiz("COEN 275 OOADP","275");
//		Database.createCourse("Liang", "test course", "1234");
		
		
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
		String quizName = "275";
		long accessTime = 1488041477;
		long quizTime = 1000000;
		tempQuiz.setName(quizName);
		tempQuiz.setAccessTime(accessTime);
		tempQuiz.setQuizTime(quizTime);

		//create duplicated questions
		String questiontext = "which city do you live in?";
		tempQuiz.createQuestion(questiontext);
		tempQuiz.getQuestions().get(tempQuiz.getQuestions().size() - 1).createAnswer("SF", true);
		tempQuiz.getQuestions().get(tempQuiz.getQuestions().size() - 1).createAnswer("SJ", false);
		tempQuiz.getQuestions().get(tempQuiz.getQuestions().size() - 1).createAnswer("NY", false);
		tempQuiz.getQuestions().get(tempQuiz.getQuestions().size() - 1).createAnswer("MI", false);
			
		String questiontext1 = "which language do you speak?";
		tempQuiz.createQuestion(questiontext1);
		tempQuiz.getQuestions().get(tempQuiz.getQuestions().size() - 1).createAnswer("EN", true);
		tempQuiz.getQuestions().get(tempQuiz.getQuestions().size() - 1).createAnswer("CN", false);
		tempQuiz.getQuestions().get(tempQuiz.getQuestions().size() - 1).createAnswer("JP", false);
		tempQuiz.getQuestions().get(tempQuiz.getQuestions().size() - 1).createAnswer("SP", false);
		
		course.addQuiz();
		
		
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
		//app.signUp("Xia", "1111", "1111", "student");
	}

}
