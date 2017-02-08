
import java.sql.Connection;
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
}
