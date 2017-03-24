package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Question is created by teacher and contains multiple choices
 *
 */
public class Question implements Cloneable {
	private String question;		//question text
	private int serialNumber;		//distinguish the questions with same text 
	private List<Answer> answers;	//list of choices
	
	public Question(String question, int serialNumber) {
		this.question = question;
		this.serialNumber = serialNumber;
		answers = new ArrayList<>();
	}
	
	public Question(String question, int serialNumber, List<Answer> answers) {
		this.question = question;
		this.serialNumber = serialNumber;
		this.answers = answers;
	}
	
	/**
	 * Add answer
	 * @param answer
	 */
	public void addAnswer(Answer answer){
		answers.add(answer);
	}
	
	/**
	 * Remove the answer
	 * @param answer
	 */
	public void removeAnswer(Answer answer){
		answers.remove(answer);
	}
	
	/**
	 * Set the serial number
	 * @param serialNumber
	 */
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	/**
	 * Set the question text
	 * @param question
	 */
	public void setQuestionText(String question) {
		this.question = question;
	}

	/**
	 * Set the list of answers
	 * @param answers
	 */
	public void setAnswer(List<Answer> answers) {
		this.answers = answers;
	}
	
	/**
	 * Get the question text
	 * @return
	 */
	public String getQuestion() {
		return question;
	}
	
	/**
	 * Get the list of answers
	 * @return
	 */
	public List<Answer> getAnswers() {
		return answers;
	}
	
	/**
	 * Get answer array
	 * @return
	 */
	public Answer[] getAnswersArray(){
		Answer[] answersArray = new Answer[answers.size()];
		for(int i = 0; i < answersArray.length; i++){
			answersArray[i] = answers.get(i);
		}
		return answersArray;
	}
	
	/**
	 * Get the serial number
	 * @return
	 */
	public int getSerialNumber() {
		return serialNumber;
	}
	
	/**
	 * Clone the question
	 */
	public Question clone(){
		Question question = new Question(this.question, this.serialNumber);
		List<Answer> answerList = new ArrayList<Answer>();
		for(Answer a : this.answers){
			if(a != null){
				answerList.add( a.clone());
			}
		}
		question.setAnswer(answerList);
		return question;
	}
	
	@Override
	public String toString(){
		if(this.question.length() < 13){
			return question;
		}else{
			return question.substring(0, 10) + "...";
		}
	}
}
