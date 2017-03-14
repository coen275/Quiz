import java.util.Map;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class App extends JFrame {
	
	private User currentUser;
	
	private JToolBar toolbar;
	private JLabel userNameLabel;
	private JButton signoutButton;

	private JPanel currentPanel;

	private CardLayout cardLayout;
	
	private Map<String, JPanel> panels;
	
	private CourseQuiz courseQuiz;
	private Takequiz takeQuizPanel;
	
	private static final String LOGIN_PANEL = "LoginPanel";
	private static final String CREATE_ACCOUNT_PANEL = "CreateAccountPanel";
	private static final String SELECT_QUIZ_PANEL = "SelectQuizPanel";
	private static final String TAKE_QUIZ_PANEL = "TakeQuizPanel";
	
	private static final String NOT_SIGNED_IN_MSG = "You are not logged in.";
	
	public App(){
		super("Online Quiz System");
		
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		//Create username label
		userNameLabel = new JLabel(NOT_SIGNED_IN_MSG);
		
		//Create signout button
		signoutButton = new JButton(new AbstractAction("Sign Out"){
			public void actionPerformed(ActionEvent arg0) {
				logout();	
			}			
		});
		signoutButton.setEnabled(false);
		
		//Create top toolbar
		toolbar = new JToolBar();
		toolbar.setPreferredSize(new Dimension(1080, 30));
		toolbar.setFloatable(false);
		toolbar.add(Box.createHorizontalGlue());
		toolbar.add(userNameLabel);
		toolbar.addSeparator();
		toolbar.add(signoutButton);
		
		cardLayout = new CardLayout();
		currentPanel = new JPanel(cardLayout);
		
		currentPanel.add(new Login(this), LOGIN_PANEL);
		currentPanel.add(new CreateAccount(this), CREATE_ACCOUNT_PANEL);
		courseQuiz = new CourseQuiz(this);
		currentPanel.add(courseQuiz, SELECT_QUIZ_PANEL);
		
		takeQuizPanel = new Takequiz();
		currentPanel.add(takeQuizPanel, TAKE_QUIZ_PANEL);
		
		setActiveUser(new Student("Paul", "1111", "student"));
		cardLayout.show(currentPanel, TAKE_QUIZ_PANEL);
		courseQuiz.refreshContent();
		
		
		//Add GUI Components
		container.add(toolbar, BorderLayout.PAGE_START);
		container.add(currentPanel, BorderLayout.CENTER);

		
		//JFrame settings
		setResizable(false);
		setSize(1080, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void setActiveUser(User user){
		currentUser = user;
		userNameLabel.setText(user != null ? user.username : NOT_SIGNED_IN_MSG);
		signoutButton.setEnabled(user != null);
		
		revalidate();
		//print all the courses' info after login
		//testLoadCourseInUserClass(currentUser);
		//student submits the quiz
//		currentUser.submitResult(currentUser.getUsername(), selectAnswer(currentUser));
	}
	
	public void takeQuiz(Quiz quiz){
		cardLayout.show(currentPanel, TAKE_QUIZ_PANEL);
		takeQuizPanel.takeQuiz(getActiveUser(), quiz);
	}
	
	public User getActiveUser(){
		return currentUser;
	}
	
	public void createAccount(){
		setPanel(CREATE_ACCOUNT_PANEL);
	}
	
	public void logout() {
		setActiveUser(null);
	}
	
	private void setPanel(String key){
		cardLayout.show(currentPanel, key);
		revalidate();
	}
	
	/*
	 * I think this function could be in the CreateAccountUI class so that it can properly
	 * display errors if the account already exists
	 */
	public void signUp(String username, String password, String confirm, String type) {
		if (password.equals(confirm)) {
			Database.createAccount(username, password, type);
			System.out.printf("%s Account: %s is created", type, username);
		} else {
			System.out.println("Password is not correct");
		}	
	}
	
	//Temporary
	public void printCurrentUserInfo() {
		System.out.println("Username: " + currentUser.getUsername());
		System.out.println("Password: " + currentUser.getPassword());
		System.out.println("Type: " + currentUser.getType());
	}
	
	/*
	 * Test loadcourse after login
	 */
	public void testLoadCourseInUserClass(User user){
		for (Course c : user.getCourse()) {
			c.printCourseInfo();
			for (Quiz q : c.getQuizs()) {
				q.printQuizInfo();
				for (Question question: q.getQuestions()) {
					question.printQuestionInfo();
				}
			}
		}
	}
	
	/*
	 * Test that Student choose the answer and submit the quiz.
	 * Write results into QuestionResults table
	 */
	public Quiz selectAnswer(User currentUser){
		Quiz q = currentUser.getCourse().get(0).getQuizs().get(1);
		System.out.println("-----------------------------");
		for (int i = 0; i < q.getQuestions().size(); i++) {
			q.getQuestions().get(i).getAnswers().get(i+2).selectAnswer();
		}
		return q;
	}
}