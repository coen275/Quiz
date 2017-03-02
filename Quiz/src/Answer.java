
public class Answer {
	private int answerID;
	private String answerText;
	private boolean isCorrect;
	private boolean isSelected; //if student select this answer, set to true
	
	//When teacher create the answer, DONT need to set answerID and isSelected flag
	public Answer(String answerText, boolean isCorrect) {
		this.answerText = answerText;
		this.isCorrect = isCorrect;
	}
	
	public Answer(int answerID, String answerText, boolean isCorrect) {
		this.answerID = answerID;
		this.answerText = answerText;
		this.isCorrect = isCorrect;
		this.isSelected = false;
	}
	
	public void selectAnswer(){
		isSelected = true;
	}
	
	public boolean isSelected(){
		return isSelected;
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
