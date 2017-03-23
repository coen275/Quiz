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
	public void addQuiz() {
		quizs.add(tempQuiz);
		Database.addQuiz(this.name, tempQuiz);
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
	
	public void printCourseInfo(){
		System.out.println("CourseName: " + name + ", AccessCode: " + accessCode);
	}
	
	@Override
	public String toString(){
		return String.format("%s (%s)", this.name, this.accessCode);
	}
}
