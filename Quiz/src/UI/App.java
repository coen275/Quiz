package UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import Models.User;
import Models.Course;
import Models.Quiz;

@SuppressWarnings("serial")
public class App extends JFrame {
	
	private User currentUser;
	
	private JToolBar toolbar;
	private JLabel userNameLabel;
	private JButton signoutButton;

	private JPanel currentPanel;

	private CardLayout cardLayout;
	
	private CourseQuiz courseQuiz;
	private Takequiz takeQuizPanel;
	private CreateQuiz createQuizPanel;
	
	private static final String LOGIN_PANEL = "LoginPanel";
	private static final String CREATE_ACCOUNT_PANEL = "CreateAccountPanel";
	private static final String SELECT_QUIZ_PANEL = "SelectQuizPanel";
	private static final String TAKE_QUIZ_PANEL = "TakeQuizPanel";
	private static final String CREATE_QUIZ_PANEL = "CreateQuizPanel";
	
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
				setActiveUser(null);	
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
		takeQuizPanel = new Takequiz(this);
		currentPanel.add(takeQuizPanel, TAKE_QUIZ_PANEL);
		createQuizPanel = new CreateQuiz(this);
		currentPanel.add(createQuizPanel, CREATE_QUIZ_PANEL);
		
		cardLayout.show(currentPanel, LOGIN_PANEL);		
		
		//Add GUI Components
		container.add(toolbar, BorderLayout.PAGE_START);
		container.add(currentPanel, BorderLayout.CENTER);

		
		//JFrame settings
		setResizable(false);
		setSize(1080, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	//Set the currently logged in user. Set to null when signing out
	public void setActiveUser(User user){
		currentUser = user;
		userNameLabel.setText(user != null ? user.getUsername() : NOT_SIGNED_IN_MSG);
		signoutButton.setEnabled(user != null);
		if(currentUser == null){
			cardLayout.show(currentPanel, LOGIN_PANEL);		
		} else {
			mainMenu();
		}
		revalidate();
	}
	
	//Open up the TakeQuiz page
	public void takeQuiz(Quiz quiz, String courseName){
		cardLayout.show(currentPanel, TAKE_QUIZ_PANEL);
		takeQuizPanel.takeQuiz(getActiveUser(), quiz, courseName);
	}
	
	//Open up the main menu
	public void mainMenu(){
		cardLayout.show(currentPanel, SELECT_QUIZ_PANEL);
		courseQuiz.refreshContent();
	}
	
	//Open up the create quiz page
	public void createQuiz(Course course){
		cardLayout.show(currentPanel, CREATE_QUIZ_PANEL);
		createQuizPanel.setCourse(course);
	}
	
	//Access the current user
	public User getActiveUser(){
		return currentUser;
	}
	
	//Open the create account page
	public void createAccount(){
		setPanel(CREATE_ACCOUNT_PANEL);
	}
	
	//Set the current panel
	private void setPanel(String key){
		cardLayout.show(currentPanel, key);
		revalidate();
	}
}