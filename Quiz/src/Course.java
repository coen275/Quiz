import java.util.ArrayList;
import java.util.List;

public class Course {
	String name;
	String accessCode;
	Quiz tempQuiz;
	List<Quiz> quizs = new ArrayList<Quiz>();
	
	public Course(String name, String accessCode) {
		this.name = name;
		this.accessCode = accessCode;
	}
	
	public void setCourseName(String name) {
		this.name = name;
	} 
	
	public String getCourseName() {
		return name;
	}
	
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	
	public String getAccessCode() {
		return accessCode;
	}
	
	public void createQuiz() {
		tempQuiz = new Quiz();
	}
	
	public void editQuiz(String name, long accessTime, long quizTime, List<String> answers, List<Question> questions) {
		
	}
}
