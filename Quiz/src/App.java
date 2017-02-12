import java.util.Map;

public class App {
	
	private User currentUser;
	public App(){
		
	}
	
	public void signUp(String username, String password, String confirm, String type) {
		if (password.equals(confirm)) {
			Database.createAccount(username, password, type);
			System.out.printf("%s Account: %s is created", type, username);
		} else {
			System.out.println("Password is not correct");
		}	
	}
	
	public void login(String username, String password) {
		if(Database.isValidUser(username, password)) {
			String type = Database.getUserType(username);
			if (type.equals("student")) {
				currentUser = new Student(username, password, type);
			} else if (type.equals("teacher")) {
				currentUser = new Teacher(username, password, type);
			} else {
				throw new IllegalArgumentException("Invaid type");
			}
		} else {
			System.out.println("Username or Password is invalid.");
		}
	}
	
	public void logout() {
		currentUser = null;
	}
	
	public void printCurrentUserInfo() {
		System.out.println("Username: " + currentUser.getUsername());
		System.out.println("Password: " + currentUser.getPassword());
		System.out.println("Type: " + currentUser.getType());
	}
	
	
}