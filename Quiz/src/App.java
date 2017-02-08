
public class App {

	public App(){
		
	}
	
	public static void main(String[] args){
		System.out.println("Hello world");
		if(Database.isValidUser("Paul", "1234")){
			System.out.println("Valid");
		}else{
			System.out.println("Invalid");
		}
	}
}
