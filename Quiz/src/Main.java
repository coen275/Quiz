
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
	}
	
	public static void testLogin(App app, String username, String password) {
		//app.login(username, password);
		app.printCurrentUserInfo();
		app.logout();
	}
	
	public static void testSignUp(App app) {
		app.signUp("Liang", "1111", "1111", "teacher");
	}

}
