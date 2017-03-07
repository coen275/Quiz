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
		
		currentPanel = new Login(this);
		
		//Add GUI Components
		container.add(toolbar, BorderLayout.PAGE_START);
		container.add(currentPanel, BorderLayout.CENTER);
		
		//JFrame settings
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
		testLoadCourseInUserClass(currentUser);
		//student submits the quiz
//		currentUser.submitResult(currentUser.getUsername(), selectAnswer(currentUser));
	}
	
	public void logout() {
		setActiveUser(null);
	}
	
	public void setPanel(JPanel panel){
		currentPanel = panel;
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