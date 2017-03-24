package Models;

import java.util.List;

import Server.Database;
import Models.Quiz;
/**
 * Course contains list of quizzes created by teachers
 * Both students and teachers are able to check the quizzes
 * from each course.
 */
public class Course {
	String name;			//course name
	String accessCode;		//access code for registration
	Quiz tempQuiz;			//temporary quiz object 
	List<Quiz> quizs;		//list of quizzes
	
	/**
	 * Course constructor includes basic course information
	 * @param name	course name
	 * @param accessCode	access code for enrollment
	 * @param quizs	list of quizzes
	 */
	public Course(String name, String accessCode, List<Quiz> quizs) {
		this.name = name;
		this.accessCode = accessCode;
		this.quizs = quizs;
	}
	
	/*
	 * Set the temp quiz
	 */	
	public void setTmpQuiz(Quiz quiz){
		this.tempQuiz = quiz;
	}
	
	/**
	 * Set course name
	 * @param name	course name
	 */
	public void setCourseName(String name) {
		this.name = name;
	} 
	
	/**
	 * Get course name
	 * @return
	 */
	public String getCourseName() {
		return name;
	}
	
	/**
	 * Set access code for course
	 * @param accessCode	access code
	 */
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	
	/**
	 * Get access code
	 * @return
	 */
	public String getAccessCode() {
		return accessCode;
	}
	
	/**
	 * Create Quiz and add records into database
	 * Then clear the information in temp quiz object
	 */
	public void addQuiz() {
		quizs.add(tempQuiz);
		Database.addQuiz(this.name, tempQuiz);
		tempQuiz = null;
	}
	
	/**
	 * Get the list of quizzes
	 * @return	list of quizzes
	 */
	public List<Quiz> getQuizs() {
		return quizs;
	}
	
	/**
	 * Delete quiz and its questions and answers
	 * @param quizName	quiz name
	 */
	public void deleteQuiz(String quizName) {
		for (Quiz q : quizs) {
			boolean isQuizTaken = Database.isQuizTaken(name, q.getName());
			if (q.getName().equals(quizName) && !isQuizTaken) {
				Database.deleteQuiz(this.name, q.getName());
				quizs.remove(q);
				break;
			}
		}
	}

	@Override
	public String toString(){
		return String.format("%s (%s)", this.name, this.accessCode);
	}
}
