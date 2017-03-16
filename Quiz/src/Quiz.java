import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Quiz implements Cloneable {
	private String name;
	private long accessTime;
	private long quizTime;
	private List<Question> questions;
	private int serialNumber;
	
	public Quiz() {
		questions = new ArrayList<>();
		serialNumber = 1;
	}
	
	/**
	 * Overload
	 * @param name Quiz name
	 */
	public Quiz(String name) {
		this.name = name;
		this.questions = new ArrayList<Question>();
	}

	public String getName() {
		return name;
	}
	
	public long getAccessTime() {
		return accessTime;
	}
	
	public long getQuizTime() {
		return quizTime;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAccessTime(long accessTime) {
		this.accessTime = accessTime;
	}
	
	public void setQuizTime(long quizTime) {
		this.quizTime = quizTime;
	}
	
	
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public void addQuestion(Question question){
		this.questions.add(question);
	}
	
	public void removeQuestion(Question question){
		this.questions.remove(question);
	}
	
	/**
	 * WARN: Have to think how to pass the params
	 */
	public void createQuestion(String question){
		Question q = new Question(question, serialNumber);
		questions.add(q);
		serialNumber++;
	}
	
	/**
	 * Remove question. Index starts from 0.
	 * @param index
	 */
	public void deleteQuestion(int index) {
		questions.remove(index);
	}
	
	/**
	 * Print Access Time, Change it as needed
	 */
	public void printAccessTime() {
		Date time=new Date((long)accessTime * 1000);
		System.out.println("AccessTime: " + time);
	}
	
	/**
	 * Print Quiz Time, Change it as needed
	 */
	public void printQuizTime(){
		long hours = TimeUnit.MILLISECONDS.toHours(quizTime);
		quizTime -= TimeUnit.HOURS.toMillis(hours);
	    long minutes = TimeUnit.MILLISECONDS.toMinutes(quizTime);
	    quizTime -= TimeUnit.MINUTES.toMillis(minutes);
	    long seconds = TimeUnit.MILLISECONDS.toSeconds(quizTime);
	    StringBuilder sb = new StringBuilder(64);
	    sb.append(minutes);
	    sb.append(" Minutes ");
	    sb.append(seconds);
	    sb.append(" Seconds");
	    System.out.println("QuizTime:" + sb.toString());
	}
	
	public void printQuizInfo(){
		System.out.println("QuizName: " + name);
		printAccessTime();
		printQuizTime();
	}
	
	public Quiz clone(){
		Quiz quiz = new Quiz(this.name);
		quiz.setAccessTime(this.accessTime);
		quiz.setQuizTime(this.quizTime);
		List<Question> questionList = new ArrayList<Question>();
		for(Question a : this.questions){
			questionList.add( a.clone());
		}
		quiz.setQuestions(questionList);
		return quiz;
	}
}
