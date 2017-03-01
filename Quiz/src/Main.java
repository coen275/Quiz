
public class Main {

	public static void main(String[] args){
//		System.out.println("Hello world");
//		if(Database.isValidUser("Paul", "1234")){
//			System.out.println("Valid");
//		}else{
//			System.out.println("Invalid");
//		}
//		Database.createCourse("test course", "1234");
//		Database.deleteCourse("test course");
//		Database.addCourse("Paul", "COEN 275 OOADP", "1234");
		if (Database.isQuizExist("COEN 275 OOADP", "quiz")) {
			System.out.println("exist");
		} else {
			System.out.println("not exist");
		}
//		Database.createCourse("COEN 275 OOADP", "1234");
//		App app = new App();
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
		app.signUp("Paul", "1111", "1111", "student");
	}

}
