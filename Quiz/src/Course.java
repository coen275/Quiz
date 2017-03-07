import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Course {
	String name;
	String accessCode;
	Quiz tempQuiz;
	List<Quiz> quizs;
	
	public Course(String name, String accessCode, List<Quiz> quiz) {
		this.name = name;
		this.accessCode = accessCode;
		this.quizs = quiz;
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
	
	public Quiz getTempQuiz(){
		return tempQuiz;
	}
	
	/**
	 * Add details to tempQuiz object when click finish button
	 * @param name
	 * @param accessTime
	 * @param quizTime
	 * @param answers
	 * @param questions
	 */
	public void addQuiz(String courseName) {
		quizs.add(tempQuiz);
		Database.addQuiz(courseName, tempQuiz);
		tempQuiz = null;
	}
	
	/**
	 * remove the temp quiz object when click cancel button
	 */
	public void cancelQuiz() {
		tempQuiz = null;
	}
	
	public List<Quiz> getQuizs() {
		return quizs;
	}
	
	public void deleteQuiz(String name) {
		for (Quiz q : quizs) {
			if (q.getName().equals(name) && !Database.isQuizTaken(name, q.getName())) {
				Database.deleteQuiz(name, q.getName());
				quizs.remove(q);
			}
		}
	}
	
	public void printCourseInfo(){
		System.out.println("CourseName: " + name + ", AccessCode: " + accessCode);
	}
}
