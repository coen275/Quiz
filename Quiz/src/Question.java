import java.util.ArrayList;
import java.util.List;

public class Question {
	private String quesiton;
	private int serialNumber;
	private List<Answer> answers;
	
	public Question(String question, int serialNumber) {
		this.quesiton = question;
		this.serialNumber = serialNumber;
		answers = new ArrayList<>();
	}
	
	public Question(String question, int serialNumber, List<Answer> answers) {
		this.quesiton = question;
		this.serialNumber = serialNumber;
		this.answers = answers;
	}
	
	public void createAnswer(String answerText, boolean isCorrect) {
		Answer answer = new Answer(answerText, isCorrect);
		answers.add(answer);
	}
	
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public void setQuestionText(String question) {
		this.quesiton = question;
	}

	public void setAnswer(List<Answer> answers) {
		this.answers = answers;
	}
	
	public String getQuestion() {
		return quesiton;
	}
	
	public List<Answer> getAnswers() {
		return answers;
	}
	
	public int getSerialNumber() {
		return serialNumber;
	}
	
	public void printQuestionInfo(){
		System.out.println("Question Text: " + getQuestion() + ", SerialNumber: " + serialNumber);
		for (Answer a: answers){
			a.printAnswerInfo();
		}
	}
}
