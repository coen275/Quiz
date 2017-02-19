import java.util.List;

public class Quiz {
	private String name;
	private long accessTime;
	private long quizTime;
	private List<String> answers;
	private List<Question> questions;
	
	public long getAccessTime() {
		return accessTime;
	}
	
	public long getQuizTime() {
		return quizTime;
	}
	
	public List<String> getAnswers() {
		return answers;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}
	
	public void setAccessTime(long accessTime) {
		this.accessTime = accessTime;
	}
	
	public void setQuizTime(long quizTime) {
		this.quizTime = quizTime;
	}
	
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
}
