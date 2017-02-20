import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Quiz {
	private String name;
	private long accessTime;
	private long quizTime;
	private List<Question> questions;
	
	public Quiz() {
		
	}
	
	/**
	 * Overload
	 * @param name Quiz name
	 */
	public Quiz(String name) {
		this.name = name;
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
	
	/**
	 * WARN: Have to think how to pass the params
	 */
	public void createQuestion(String question, Map<String, String> choices, String answer){
		Question q = new Question(question, choices, answer);
		questions.add(q);
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
		System.out.println(time);
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
	    System.out.println(sb.toString());
	}
}
