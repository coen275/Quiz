
public class Answer {
	private int answerID;
	private String answerText;
	private boolean isCorrect;
	
	public Answer(String answerText, boolean isCorrect) {
		this.answerText = answerText;
		this.isCorrect = isCorrect;
	}
	
	public Answer(int answerID, String answerText, boolean isCorrect) {
		this.answerID = answerID;
		this.answerText = answerText;
		this.isCorrect = isCorrect;
	}
	
	public int getAnswerID(){
		return answerID;
	}
	
	public String getAnswerText() {
		return answerText;
	}
	
	public boolean getStatus(){
		return isCorrect;
	}
	
	public void printAnswerInfo(){
		System.out.println("AnswerID: " + answerID + ", AnswerText: " + answerText + ", isCorrect: " + isCorrect);
	}
}
