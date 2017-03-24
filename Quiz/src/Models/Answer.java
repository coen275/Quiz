package Models;

/**
 * The choices of question
 *
 */
public class Answer implements Cloneable {
	private int answerID;		//the id of answer
	private String answerText;	//the text of answer
	private boolean isCorrect;	//if the answer is the correct option
	private boolean isSelected; //if student select this answer, set to true
	
	//When teacher create the answer, DONT need to set answerID and isSelected flag
	public Answer(String answerText, boolean isCorrect) {
		this.answerText = answerText;
		this.isCorrect = isCorrect;
	}
	
	/**
	 * Constructor of answer includes basic information
	 * @param answerID	the id of answer
	 * @param answerText	the text of answer
	 * @param isCorrect		if it is the correct option
	 */
	public Answer(int answerID, String answerText, boolean isCorrect) {
		this.answerID = answerID;
		this.answerText = answerText;
		this.isCorrect = isCorrect;
		this.isSelected = false;
	}
	
	/**
	 * Select the answer
	 */
	public void selectAnswer(){
		isSelected = true;
	}
	
	/**
	 * De-select the answer
	 */
	public void deselectAnswer(){
		isSelected = false;
	}
	
	/**
	 * Check if answer is selected
	 * @return	result
	 */
	public boolean isSelected(){
		return isSelected;
	}
	
	/**
	 * Get the id of answer
	 * @return id
	 */
	public int getAnswerID(){
		return answerID;
	}
	
	/**
	 * Get the text of answer
	 * @return text
	 */
	public String getAnswerText() {
		return answerText;
	}
	
	/**
	 * Get the status if the answer is the correct one
	 * @return
	 */
	public boolean getStatus(){
		return isCorrect;
	}
	
	/**
	 * Clone the answer object
	 */
	public Answer clone(){
		return new Answer(this.answerID, this.answerText, this.isCorrect);
	}
	
	@Override
	public String toString(){
		return answerText;
	}
}
