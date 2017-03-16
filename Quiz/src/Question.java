import java.util.ArrayList;
import java.util.List;

public class Question implements Cloneable {
	private String question;
	private int serialNumber;
	private List<Answer> answers;
	
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
	
	public void addAnswer(Answer answer){
		answers.add(answer);
	}
	
	public void removeAnswer(Answer answer){
		answers.remove(answer);
	}
	
	public void createAnswer(String answerText, boolean isCorrect) {
		Answer answer = new Answer(answerText, isCorrect);
		answers.add(answer);
	}
	
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public void setQuestionText(String question) {
		this.question = question;
	}

	public void setAnswer(List<Answer> answers) {
		this.answers = answers;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public List<Answer> getAnswers() {
		return answers;
	}
	
	public Answer[] getAnswersArray(){
		Answer[] answersArray = new Answer[answers.size()];
		for(int i = 0; i < answersArray.length; i++){
			answersArray[i] = answers.get(i);
		}
		return answersArray;
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
