
public abstract class User {
	protected String username;
	protected String password;
	protected String type;
	
	public User(String username, String password, String type){
		this.username = username;
		this.password = password;
		this.type = type;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getType() {
		return type;
	}
	
	public void resetPassword(String password) {
		this.password = password;
	}
	
}
