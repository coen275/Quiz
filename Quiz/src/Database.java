package OnlineQuiz;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.*;

public class Database {

	private static Database instance = new Database();
	
	private static final String URL = "jdbc:mysql://45.55.27.137:3306/quiz_system";
	private static final String USER = "coen_sql";
	private static final String PASSWORD = "coenSqlSecret24";
	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private Database() {
		try{
			Class.forName(DRIVER_CLASS);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		initializeDatabase();
	}
	
	private void initializeDatabase(){
		try{
			Connection c = createConnection();
			Statement stmt = c.createStatement();
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users ("
					+ "Id int PRIMARY KEY AUTO_INCREMENT, "
					+ "Username varchar(255) NOT NULL UNIQUE, "
					+ "Password varchar(255) NOT NULL, "
					+ "Type ENUM('teacher', 'student') NOT NULL);");
			
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS course ("
					+ "courseID int PRIMARY KEY AUTO_INCREMENT, "
					+ "courseName varchar(255) NOT NULL UNIQUE, "
					+ "accessCode varchar(255) NOT NULL)");
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private Connection createConnection(){
		Connection connection = null;
		try{
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("ERROR: Unable to connect to database.");
		}
		return connection;
	} 
	
/* Function to Check whether user has a valid account
	 * @param username
	 * @param password
	 */
	
	public static Boolean isValidUser(String username, String password){
		Boolean result = false;
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("SELECT Username FROM users WHERE Username = '%s' AND Password = '%s';", username, password));
			result = rs.next();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

/* Function to Return the usertype to the User
 * @param username
 */
	
	public static String getUserType(String username) {
		String type = null;
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("SELECT Type FROM users WHERE Username = '%s';", username));
			if (rs.next()) {
				type = rs.getString("Type");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return type;
	}
	

/* Function to Create account by adding the details to USERS table
 * @param username
 * @param password
 * @param type
 */
	
	public static void createAccount(String username, String password, String type) {
		try{
			Connection c = instance.createConnection();
			String sql = "INSERT INTO users (Username, Password, Type) "
						 + "VALUES (?, ?, ?)";
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, type);
			preparedStatement.executeUpdate(); 
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

/* Function to Insert the course details into course table
 * @param username
 * @param coursename
 * @param accesscode
 */
	
	public void createCourse(String username, String courseName, String accessCode) {
		try{
		Connection c = instance.createConnection();		
		String sql = "INSERT INTO Course (courseName, accessCode) "
				 + "VALUES ( ?, ?)";
		PreparedStatement preparedStatement = c.prepareStatement(sql);
		preparedStatement.setString(2, courseName);
		preparedStatement.setString(3, accessCode);
		preparedStatement.executeUpdate(); 
		
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

/* Function to Insert into course_enrollment table to know which user enrolled to which course
 * @param username
 * @param coursename
 * @param accesscode
 */
	
	public static void addCourse(String username, String courseName, String accessCode) {
		try{
			Connection c = instance.createConnection();
			String sql = "INSERT INTO course_enrollment (userID,courseID)"
					+ "SELECT a.ID,b.courseID FROM (( SELECT  ID from USERS WHERE username ="+username+ ")"
					+"SELECT (courseID FROM COURSE where courseName =" + courseName +"and access Code =" +accessCode + "))";
			
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(2, courseName);
			preparedStatement.setString(3, accessCode);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


/* Function to Delete the course from COURSE table
 	* @param username
 	* @param courseName
 */
	
	public  void deleteCourse(String username, String courseName) {
		try{
			Connection c = instance.createConnection();
			String sql = "DELETE FROM COURSE " 
	                   + "WHERE coursename = ?;" ;
			
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, courseName);
			preparedStatement.executeUpdate(); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
/* Function to Return the Questions and answer details for the given quiz to getQuiz() function
 	*  @param quizID
*/
	
	public static List<Question> getQuestions(int quizID){
		List<Question> questions = new ArrayList<>();
		questions = null;
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();
		
		 ResultSet rs = stmt.executeQuery(String.format("SELECT qs.quizID, question, answer FROM QUESTIONS qs, ANSWERS as "
				 + "WHERE quizID = '%s' " ,quizID
				 + "AND qs.questionID = as.questionID"
				 + "order by qs.quizID "));
		 if (rs.next()){
			 Question question = new Question();
			 question.setQuestion(rs.getString("question"));
			 question.setAnswer(rs.getString("answer"));
			 questions.add(question);
		 }
	}catch(Exception e){
		e.printStackTrace();
		}
		
	return questions;
	}
	
	
/* Function to Return the quiz details (along with list of questions) to the loadCourse() function
 	*  @param courseID
 */
		
	public static List<Quiz> getQuiz(int courseID){
		List<Quiz> quizzes = new ArrayList<>();
		//quizzes = null;
		List<Question> listQuestion = new ArrayList<Question>();
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();
		
		 ResultSet rs = stmt.executeQuery(String.format("SELECT qz.quizID,qz.quizname,qz.accesstime,qz.quiztime" 
				 + "FROM QUIZ qz, COURSE cs"
				 + "WHERE qz.courseID = '%s' " ,courseID
				 + "AND cs.courseID = qz.courseID"));
		 if (rs.next()){
			 Quiz quiz = new Quiz();
			 quiz.setName(rs.getString("quizname"));
			 quiz.setAccessTime(rs.getLong("accesstime"));
			 quiz.setQuizTime(rs.getLong("quiztime"));
			 listQuestion = getQuestions(rs.getInt("quizID"));
			 quiz.setQuestions(listQuestion);

			 quizzes.add(quiz);
		 }
		
		 
	}catch(Exception e){
		e.printStackTrace();
		}
		
	return quizzes;
	
	}	
	
/* Function to Return the List of course details (along with quiz details) to the User
 	* @param username
*/
	
	public static List<Course> loadCourse(String username) {
		List <Course> courses = new ArrayList<>();
		courses = null;
		List<Quiz> listQuiz = new ArrayList<Quiz>();
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();
				
		 ResultSet rs = stmt.executeQuery(String.format("SELECT us.username,cs.courseID,cs.coursename,"
		 		+ "FROM USERS us, COURSE cs, COURSE_ENROLLMENT ce "
				+ "WHERE us.username = '%s' " ,username
				+ "AND ce.userID = us.userID"
				+ "AND cs.courseID = ce.courseID"
				+ "ORDER BY us.username"));
		 if (rs.next()) {
			 	 Course course = new Course();
				 course.setCourseName(rs.getString("coursename"));
				 listQuiz = getQuiz(rs.getInt("courseID"));
				 course.setQuizlist(listQuiz);  //add the setter in course class
				 
				 courses.add(course);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return courses; 			
	}

	
/* Insert the details to tables - QUIZ, QUESTIONS  with 
 * @param object tempQuiz
*/
	public void addQuiz(Quiz tempQuiz){
		
		PreparedStatement preparedStatement = null;
		int quizID = 0;
		
		try{
			Connection c = instance.createConnection();	
			Statement stmt = c.createStatement();
						
			String quizname = tempQuiz.getName();
			long accesstime = tempQuiz.getAccessTime();
			long quiztime 	= tempQuiz.getQuizTime();
			List<Question> questionList = tempQuiz.getQuestions();
			ResultSet rs = stmt.executeQuery(String.format("SELECT quizid FROM quiz WHERE quizname = '%s';", quizname));
			if (rs.next()) {
				quizID = rs.getInt("quizID");
			}
			
			
			String sql = "INSERT INTO quiz(quizname,accesstime,quiztime)"
					+ "VALUES(?,?,?)";
					
			preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, quizname);
			preparedStatement.setLong(2, accesstime);
			preparedStatement.setLong(3, quiztime);
			preparedStatement.executeUpdate(); 
			
			
		for (int i=0; i < questionList.size(); i++ ){
			String sql1 = "INSERT INTO questions(quizid, question)"
					+ "VALUES(?,?)";
			
			preparedStatement = c.prepareStatement(sql1);
			preparedStatement.setInt(1, quizID);
			preparedStatement.setString(2, questionList.get(i).getQuestion());
			preparedStatement.executeUpdate();			
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

/* Function to Delete the details from tables - QUIZ, QUESTIONS , ANSWERS , CHOICE with 
 	* @param courseName
 	* @param quizName
 */
	
	public void deleteQuiz(String courseName, String quizName){
		PreparedStatement preparedStatement = null;
		try{
			Connection c = instance.createConnection();
			
			String sql = "DELETE FROM answers as, choices ch "
					+ "WHERE EXISTS"
					+ "(SELECT questionID FROM questions qs WHERE"
					+ "quizID = (SELECT quizID FROM quiz WHERE quizname = ?)"
					+ "AND qs.questionID = as.questionID"
					+ "AND qs.questionID = ch.questionID"
					+ "AND as.questionID = ch.questionID )";
					
			preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, quizName);
			preparedStatement.executeUpdate(); 
			
			
			String sql1 = "DELETE FROM question "
					+ "WHERE quizID = (SELECT quizID FROM quiz WHERE quizname = ?)";
			
			preparedStatement = c.prepareStatement(sql1);
			preparedStatement.setString(1, quizName);
			preparedStatement.executeUpdate(); 
			
			String sql2 = "DELETE FROM quiz " 
	                   + "WHERE quizname = ?"
	                   + "AND courseID = (SELECT courseID FROM course WHERE coursename = ?)";
			
			preparedStatement = c.prepareStatement(sql2);
			preparedStatement.setString(1, quizName);
			preparedStatement.setString(2, courseName);
			preparedStatement.executeUpdate(); 
						
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
