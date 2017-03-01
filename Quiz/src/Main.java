import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args){
//		System.out.println("Hello world");
//		if(Database.isValidUser("Paul", "1234")){
//			System.out.println("Valid");
//		}else{
//			System.out.println("Invalid");
//		}	
		App app = new App();
//		testSignUp(app);
		//testLogin(app, "Paul", "1234");
		//testLogin(app, "Liang", "1111");
		//loadCourse();
		//loadQuestions();
		//loadQuizs();
		
		
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
					System.out.println("Question Text: " + question.getQuestion());
					for (String choice: question.getChoices().keySet()){
						System.out.println("Choice " + choice + ": " + question.getChoices().get(choice));
					}
					System.out.println("Answer: " + question.getAnswer());
				}
			}
		}
	}
	
	/*
	 * Load list of Quiz objects from DB
	 * PASS
	 */
	public static void loadQuizs(){
		List<Quiz> quizs = Database.loadQuizs(1, "teacher", 1);
		for(Quiz q : quizs) {
			System.out.println("QuizName: " + q.getName() + ", AccessTime: " + q.getAccessTime() + ", QuizTime: " + q.getQuizTime());
			
			for (Question question: q.getQuestions()) {
				System.out.println("Question Text: " + question.getQuestion());
				for (String choice: question.getChoices().keySet()){
					System.out.println("Choice " + choice + ": " + question.getChoices().get(choice));
				}
				System.out.println("Answer: " + question.getAnswer());
			}
		}
	}
	
	/*
	 * Load list of Question object from DB
	 * PASS
	 */
	public static void loadQuestions(){
		List<Question> questions = Database.loadQuestions(1, "teacher", 1);
		for (Question question: questions) {
			System.out.println("Question Text: " + question.getQuestion());
			for (String choice: question.getChoices().keySet()){
				System.out.println("Choice " + choice + ": " + question.getChoices().get(choice));
			}
			System.out.println("Answer: " + question.getAnswer());
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
		long accessTime = 1488341477;
		long quizTime = 600000;
		String questiontext = "which language do you use in the COEN 275?";
		Map<String, String> choices =  new HashMap<>();
		String answer = "A";
		choices.put("A", "java");
		choices.put("B", "c");
		choices.put("C", "python");
		choices.put("D", "ruby");
		Question q = new Question(questiontext, choices, answer);
		List<Question> list = new ArrayList<>();
		list.add(q);
		course.addQuiz("Liang test Quiz 1", accessTime, quizTime, list, course.getCourseName());
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
	
	public static void testLogin(App app, String username, String password) {
		//app.login(username, password);
		app.printCurrentUserInfo();
		app.logout();
	}
	
	public static void testSignUp(App app) {
		app.signUp("Paul", "1111", "1111", "student");
	}

}
