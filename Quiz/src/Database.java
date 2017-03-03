import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.*;

public class Database {

	private static Database instance = new Database();
	
	private static final String URL = "jdbc:mysql://45.55.27.137:3306/quiz_system?useSSL=false";
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
			//users table
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Users ("
					+ "UserID INT PRIMARY KEY AUTO_INCREMENT, "
					+ "Username VARCHAR(255) NOT NULL UNIQUE, "
					+ "Password VARCHAR(255) NOT NULL, "
					+ "Type ENUM('teacher', 'student') NOT NULL);");
			//courses table
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Courses ("
					+ "CourseID INT PRIMARY KEY AUTO_INCREMENT, "
					+ "CourseName VARCHAR(255) NOT NULL UNIQUE, "
					+ "AccessCode VARCHAR(4) NOT NULL);");
			//course enrollment table
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS CourseEnrollment ("
					+ "UserID INT NOT NULL, "
					+ "CourseID INT NOT NULL);");
			//quizs table
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Quizs ("
					+ "QuizID INT PRIMARY KEY AUTO_INCREMENT, "
					+ "QuizName VARCHAR(255) NOT NULL, "
					+ "CourseID INT NOT NULL, "
					+ "AccessTime BIGINT NOT NULL, "
					+ "QuizTime BIGINT NOT NULL);");
			//questions table
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Questions ("
					+ "QuestionID INT PRIMARY KEY AUTO_INCREMENT, "
					+ "QuizID INT NOT NULL, "
					+ "QuestionText TEXT, "
					+ "SerialNumber INT NOT NULL);");
			//answers table
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Answers ("
					+ "AnswerID INT PRIMARY KEY AUTO_INCREMENT, "
					+ "QuestionID INT NOT NULL, "
					+ "AnswerText TEXT,"
					+ "IsCorrect TINYINT(1));");
			//questionresults table(student)
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS QuestionResults ("
					+ "QuestionResultID INT PRIMARY KEY AUTO_INCREMENT, "
					+ "UserID INT NOT NULL, "
					+ "QuizID INT NOT NULL, "
					+ "QuestionID INT NOT NULL, "
					+ "AnswerID INT NOT NULL);");
			//quizresults table
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS QuizResults ("
					+ "QuizResultID INT PRIMARY KEY AUTO_INCREMENT, "
					+ "UserID INT NOT NULL, "
					+ "QuizID INT NOT NULL, "
					+ "Score INT NOT NULL, "
					+ "TotalScore INT NOT NULL);");
			
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
	
	/** 
	 * Check whether user has a valid account
	 * @param username
	 * @param password
	 */
	public static Boolean isValidUser(String username, String password){
		Boolean result = false;
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("SELECT Username "
														 + "FROM Users "
														 + "WHERE Username = '%s' AND Password = '%s';", username, password));
			result = rs.next();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static Boolean isUserExist(String username) {
		Boolean result = false;
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("SELECT Username "
														 + "FROM Users "
														 + "WHERE Username = '%s';", username));
			result = rs.next();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/** 
	 * Get the user type
	 * @param username
	 */	
	public static String getUserType(String username) {
		String type = null;
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("SELECT Type FROM Users WHERE Username = '%s';", username));
			if (rs.next()) {
				type = rs.getString("Type");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return type;
	}
	

	/** 
	 * Function to Create account by adding the details to USERS table
	 * @param username
	 * @param password
	 * @param type
	 */
	public static void createAccount(String username, String password, String type) {
		try{
			Connection c = instance.createConnection();
			String sql = "INSERT INTO Users (Username, Password, Type) "
					   + "VALUES (?, ?, ?);";
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, type);
			preparedStatement.executeUpdate(); 
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 *  Insert course details into course table
	 * @param username
	 * @param coursename
	 * @param accesscode
	 */
	public static void createCourse(String username, String courseName, String accessCode) {
		try{
			Connection c = instance.createConnection();		
			String sql = "INSERT INTO Courses (CourseName, AccessCode) "
					   + "VALUES ( ?, ?);";
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, courseName);
			preparedStatement.setString(2, accessCode);
			preparedStatement.executeUpdate(); 
		}catch(Exception e){
			e.printStackTrace();
		}
		addCourse(username, courseName, accessCode);
	}
	
	public static Boolean isCourseExist(String courseName) {
		Boolean result = false;
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM Courses WHERE CourseName = '%s';", courseName));
			result = rs.next();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static Boolean isAccessCodeCorrect(String courseName, String accessCode) {
		Boolean result = false;
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM Courses WHERE CourseName = '%s' AND AccessCode = '%s';", courseName, accessCode));
			result = rs.next();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/**
	 *  Function to Insert into course_enrollment table to know which user enrolled to which course
	 * @param username
	 * @param coursename
	 * @param accesscode
	 */
	public static void addCourse(String username, String courseName, String accessCode) {
		try{
			Connection c = instance.createConnection();
			String sql = "INSERT INTO CourseEnrollment (UserID, CourseID) "
					+ "SELECT UserID, CourseID "
					+ "FROM Users, Courses "
					+ "WHERE Username = ? AND CourseName = ? AND AccessCode = ?;";
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, courseName);
			preparedStatement.setString(3, accessCode);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	/** 
	 * delete the course by teacher
	 * @param username
	 * @param courseName
	 */
	public static void deleteCourse(String courseName) {
		try{
			Connection c = instance.createConnection();
			String sql = "DELETE Courses, CourseEnrollment "
					   + "From Courses "
					   + "JOIN CourseEnrollment ON Courses.CourseID = CourseEnrollment.CourseID "
					   + "WHERE CourseName = ?;" ;
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, courseName);
			preparedStatement.executeUpdate(); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	/**
	 * Get the list of Courses
	 * @param username
	 * @return
	 */
	public static List<Course> loadCourses(String username) {
		List <Course> courses = new ArrayList<>();
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();			
			ResultSet rs = stmt.executeQuery(String.format("SELECT * "
														 + "FROM CourseEnrollment "
														 + "INNER JOIN Users ON Users.UserID = CourseEnrollment.UserID "
														 + "INNER JOIN Courses ON Courses.CourseID = CourseEnrollment.CourseID "
														 + "WHERE Username = '%s';", username));
			while (rs.next()) {
			 	Course course = new Course(rs.getString("CourseName"),
			 			 				   rs.getString("AccessCode"), 
			 			 				   loadQuizs(rs.getInt("CourseID")));
				courses.add(course);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return courses; 			
	}
	
	/**
	 * Get list of Quizs
	 * @param type
	 * @param CourseID
	 * @return
	 */
	public static List<Quiz> loadQuizs(int courseID) {
		List<Quiz> quizs = new ArrayList<>();
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();			
			ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM Quizs WHERE CourseID = '%d';", courseID));
			while (rs.next()) {
				Quiz quiz = new Quiz();
				quiz.setAccessTime(rs.getLong("AccessTime"));
				quiz.setName(rs.getString("QuizName"));
				quiz.setQuizTime(rs.getLong("QuizTime"));
//				System.out.println("QuizName: " + quiz.getName() + ", AccessTime: " + quiz.getAccessTime() + ", QuizTime: " + quiz.getQuizTime());
				quiz.setQuestions(loadQuestions(rs.getInt("QuizID")));
				quizs.add(quiz);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return quizs;
	}
	
	public static List<Question> loadQuestions(int quizID){
		List<Question> questions = new ArrayList<>();
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();			
			ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM Questions WHERE QuizID = '%d';", quizID));
			while (rs.next()) {
				Question question = new Question(rs.getString("QuestionText"), rs.getInt("SerialNumber"), getAnswers(rs.getInt("QuestionID")));
				questions.add(question);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return questions;
	}
	
	public static List<Answer> getAnswers(int questionID) {
		List<Answer> answers = new ArrayList<>();
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();			
			ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM Answers WHERE QuestionID = '%d';", questionID));
			while(rs.next()) {
				Answer a = new Answer(rs.getInt("AnswerID"), rs.getString("AnswerText"), rs.getBoolean("IsCorrect"));
				answers.add(a);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return answers;
	}
	
	public static Boolean isQuizExist(String courseName, String quizName){
		Boolean result = false;
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("SELECT * "
														 + "FROM Quizs "
														 + "JOIN Courses ON Courses.CourseID = Quizs.CourseID "
														 + "WHERE CourseName = '%s' AND QuizName = '%s';", courseName, quizName));
			result = rs.next();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Add record to quiz and related tables
	 * @param courseName
	 * @param quiz
	 */
	public static void addQuiz(String courseName, Quiz quiz){
		
		String quizName = quiz.getName();
		long accessTime = quiz.getAccessTime();
		long quizTime = quiz.getQuizTime();
		List<Question> questions = quiz.getQuestions();
		
		try{
			Connection c = instance.createConnection();
			String sql = "INSERT INTO Quizs (QuizName, CourseID, AccessTime, QuizTime) "
					   + "SELECT ?, CourseID, ?, ? "
					   + "FROM Courses "
					   + "WHERE CourseName = ?;";
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, quizName);
			preparedStatement.setLong(2, accessTime);
			preparedStatement.setLong(3, quizTime);
			preparedStatement.setString(4, courseName);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for (Question q : questions) {
			addQuestion(courseName, quizName, q);
		}
	}

	private static void addQuestion(String courseName, String quizName, Question q) {
		String questionText = q.getQuestion();
		List<Answer> answers = q.getAnswers();
		int serialNumber = q.getSerialNumber();
		try{
			Connection c = instance.createConnection();
			String sql = "INSERT INTO Questions (QuizID, QuestionText, SerialNumber) "
					   + "SELECT QuizID, ?, ? "
					   + "FROM Quizs "
					   + "WHERE QuizName = ?;";
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, questionText);
			preparedStatement.setInt(2, serialNumber);
			preparedStatement.setString(3, quizName);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		for(Answer a : answers) {
			addAnswer(quizName, serialNumber, a.getAnswerText(), a.getStatus());
		}
	}
	
	private static void addAnswer(String quizName, int serialNumber, String answerText, boolean isCorrect) {
		try{
			Connection c = instance.createConnection();
			String sql = "INSERT INTO Answers (QuestionID, AnswerText, IsCorrect) "
					   + "SELECT QuestionID, ?, ? "
					   + "FROM Questions "
					   + "INNER JOIN Quizs ON Quizs.QuizID = Questions.QuizID "
					   + "WHERE QuizName = ? AND SerialNumber = ?;";
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, answerText);
			preparedStatement.setBoolean(2, isCorrect);
			preparedStatement.setString(3, quizName);
			preparedStatement.setInt(4, serialNumber);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Delete records from Quizs, Questions, Answers
	 */
	public static void deleteQuiz(String courseName, String quizName){
		try{			
			Connection c = instance.createConnection();
			String sql = "DELETE Quizs, Questions, Answers "
					   + "FROM Quizs "
					   + "JOIN Courses ON Courses.CourseID = Quizs.CourseID "
					   + "JOIN Questions ON Quizs.QuizID = Questions.QuizID "
					   + "JOIN Answers ON Questions.QuestionID = Answers.QuestionID "
					   + "WHERE CourseName = ? AND QuizName = ?;";
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setString(1, courseName);
			preparedStatement.setString(2, quizName);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static int getQuizID(String quizName) {
		int quizID = 0;
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("SELECT QuizID FROM Quizs WHERE QuizName = '%s';", quizName));
			if (rs.next()) {
				quizID = rs.getInt("QuizID");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return quizID;
	}
	
	public static int getUserID(String username) {
		int userID = 0;
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("SELECT UserID FROM Users WHERE Username = '%s';", username));
			if (rs.next()) {
				userID = rs.getInt("UserID");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return userID;
	}
	
	public static int getQuestionID(int answerID) {
		int questionID = 0;
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("SELECT QuestionID FROM Answers WHERE AnswerID = '%d';", answerID));
			if (rs.next()) {
				questionID = rs.getInt("QuestionID");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return questionID;
	}
	
	public static void addQuestionResult(int userID, int quizID, int questionID, int answerID){
		try{
			Connection c = instance.createConnection();
			String sql = "INSERT INTO QuestionResults (UserID, QuizID, QuestionID, AnswerID)"
					   + "VALUE (?, ?, ?, ?);";
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setInt(1, userID);
			preparedStatement.setInt(2, quizID);
			preparedStatement.setInt(3, questionID);
			preparedStatement.setInt(4, answerID);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static int getScore(int userID, int quizID) {
		int score = 0;
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("SELECT COUNT(*) AS score "
														 + "FROM QuestionResults "
														 + "JOIN Answers ON Answers.AnswerID = QuestionResults.AnswerID "
														 + "WHERE IsCorrect = 1 AND UserID = '%d' AND QuizID = '%d';", userID, quizID));
			if (rs.next()) {
				score = rs.getInt("score");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return score;
	}
	
	public static int getTotalScore(int userID, int quizID) {
		int score = 0;
		try{
			Connection c = instance.createConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("SELECT COUNT(*) AS score "
														 + "FROM QuestionResults "
														 + "JOIN Answers ON Answers.AnswerID = QuestionResults.AnswerID "
														 + "WHERE UserID = '%d' AND QuizID = '%d';", userID, quizID));
			if (rs.next()) {
				score = rs.getInt("score");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return score;
	}
	
	public static void calculateQuizResult(int userID, int quizID) {
		int score = getScore(userID, quizID);
		int totalSocre = getTotalScore(userID, quizID);
		try{
			Connection c = instance.createConnection();
			String sql = "INSERT INTO QuizResults (UserID, QuizID, Score, TotalScore)"
					   + "VALUE (?, ?, ?, ?);";
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setInt(1, userID);
			preparedStatement.setInt(2, quizID);
			preparedStatement.setInt(3, score);
			preparedStatement.setInt(4, totalSocre);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
