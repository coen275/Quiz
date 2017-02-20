
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
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
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users ("
					+ "Id int PRIMARY KEY AUTO_INCREMENT, "
					+ "Username varchar(255) NOT NULL UNIQUE, "
					+ "Password varchar(255) NOT NULL, "
					+ "Type ENUM('teacher', 'student') NOT NULL);");
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

	public static void addCourse(String username, String courseName, String accessCode) {
		// TODO Auto-generated method stub
	}

	public static void createCourse(String username, String courseName, String accessCode) {
		// TODO Auto-generated method stub
		
	}

	public static List<Course> loadCourse(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void deleteCourse(String username, String courseName) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Delete quiz
	 * @param courseName
	 * @param quizName
	 */
	public static void deleteQuiz(String courseName, String quizName) {
		// TODO Auto-generated method stub
		
	}

	public static void addQuiz(Quiz tempQuiz) {
		// TODO Auto-generated method stub
		
	}

}
