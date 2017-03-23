import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Quiz includes questions and specific attributes 
 *
 */
public class Quiz implements Cloneable {
	private String name;				//quiz name
	private long accessTime;			//access time
	private long quizTime;				//quiz time
	private List<Question> questions;	//list of questions
	private int serialNumber;			//serial number
	
	/**
	 * Constructor of quiz with empty question and serial number
	 */
	public Quiz() {
		questions = new ArrayList<>();
		serialNumber = 0;
	}
	
	/**
	 * Overload
	 * @param name Quiz name
	 */
	public Quiz(String name) {
		this.name = name;
		this.questions = new ArrayList<Question>();
	}

	/**
	 * Get quiz name
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get access time
	 * @return
	 */
	public long getAccessTime() {
		return accessTime;
	}
	
	/**
	 * Get quiz time
	 * @return
	 */
	public long getQuizTime() {
		return quizTime;
	}
	
	/**
	 * Get list of question objects
	 * @return
	 */
	public List<Question> getQuestions() {
		return questions;
	}
	
	/**
	 * Set quiz name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set access time
	 * @param accessTime
	 */
	public void setAccessTime(long accessTime) {
		this.accessTime = accessTime;
	}
	
	/**
	 * Set Quiz Time
	 * @param quizTime
	 */
	public void setQuizTime(long quizTime) {
		this.quizTime = quizTime;
	}
	
	/**
	 * Set list of questions
	 * @param questions
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	/**
	 * Add question into list
	 * @param question
	 */
	public void addQuestion(Question question){
		this.questions.add(question);
	}
	
	/**
	 * Remove the question from the list
	 * @param question
	 */
	public void removeQuestion(Question question){
		this.questions.remove(question);
	}
	
	/**
	 * Remove question from the list based on serial number
	 * @param serialNumber
	 */
	public void removeQuestion(int serialNumber){
		for(Question question : this.questions){
			if(question.getSerialNumber() == serialNumber){
				this.questions.remove(question);
				break;
			}
		}
	}
	
	/**
	 * Clone the quiz object
	 */
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
