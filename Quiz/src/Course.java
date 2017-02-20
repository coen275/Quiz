import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Course {
	String name;
	String accessCode;
	Quiz tempQuiz;
	List<Quiz> quizs;
	
	public Course(String name, String accessCode) {
		this.name = name;
		this.accessCode = accessCode;
		quizs = new ArrayList<Quiz>();
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
	
	/**
	 * Create an temp quiz object
	 */
	public void createQuiz() {
		tempQuiz = new Quiz();
	}
	
	/**
	 * Add details to tempQuiz object when click finish button
	 * @param name
	 * @param accessTime
	 * @param quizTime
	 * @param answers
	 * @param questions
	 */
	public void editQuiz(String name, long accessTime, long quizTime, List<String> answers, List<Question> questions) {
		tempQuiz.setName(name);
		tempQuiz.setAccessTime(accessTime);
		tempQuiz.setQuizTime(quizTime);
		tempQuiz.setQuestions(questions);
		quizs.add(tempQuiz);
		Database.addQuiz(tempQuiz);
		tempQuiz = null;
	}
	
	/**
	 * remove the temp quiz object when click cancel button
	 */
	public void cancelQuiz() {
		tempQuiz = null;
	}
	
	public List<Quiz> viewQuizs() {
		return quizs;
	}
	
	public void deleteQuiz(String name) {
		for (Quiz q : quizs) {
			if (q.getName().equals(name)) {
				Database.deleteQuiz(name, q.getName());
				quizs.remove(q);
			}
		}
	}
	
}
