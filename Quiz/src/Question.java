import java.util.Map;

public class Question {
	private String quesiton;
	private Map<String, String> choices; //MAP<option, details> e.g ("A", "interface."),("B", "class.")
	private String answer;
	private boolean isCorrect;
	
	public Question(String question, Map<String, String> choices, String answer) {
		this.quesiton = question;
		this.choices = choices;
		this.answer = answer;
		isCorrect = false;
	}
	
	public void setAnswer(String answer) {
		if (answer.equals("A") ||
			answer.equals("B") ||
			answer.equals("C") ||
			answer.equals("D")) {
			this.answer = answer;
		} else {
			throw new IllegalArgumentException("Answer should be A,B,C,D");
		}
	}
	
	public boolean isCorrect(String answer) {
		if(this.answer.equals(answer)) {
			isCorrect = true;
		}
		return isCorrect;
	}
	
	public String getQuestion() {
		return quesiton;
	}
	
	public Map<String, String> getChoices() {
		return choices;
	}
}
