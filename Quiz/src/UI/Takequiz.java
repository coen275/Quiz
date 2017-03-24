//package experiement_swing;

package UI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import Models.Quiz;
import Models.User;
import Models.Answer;
import Models.Question;

//The GUI Panel to let the user take a quiz
public class Takequiz extends JPanel implements ActionListener{
	
	private App app;
	private Quiz currentQuiz;
	private User currentUser;
	private String currentCourseName;
	
	private JLabel quizNameHeader;
	private JPanel questionPanel;
	private CardLayout questionCardLayout;
	
	private List<String> questionKeys;
	private int questionIndex;
	private String currentQuestionKey;
	
	private final Font headerFont = new Font(Font.DIALOG, Font.BOLD, 30);
	private final Font subHeaderFont = new Font(Font.DIALOG, Font.BOLD, 26);
	
	private static final String PREV_BTN_STR = "Prev Question";
	private static final String NEXT_BTN_STR = "Next Question";
	private static final String SUBMIT_BTN_STR = "Submit Answers";
	
	//A custom renderer to display the lists
	private class ListRenderer extends DefaultListCellRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		        JRadioButton button = new JRadioButton(value.toString());
		        button.setSelected(isSelected);
				button.setFont(new Font("Ariel", Font.PLAIN, 20));
		        return button;
		    }
	}
	
	/**
	 * Create the panel.
	 */
	public Takequiz(App app) {
		
		this.app = app;
		
		quizNameHeader = new JLabel("<Quiz Name>", SwingConstants.CENTER);
		quizNameHeader.setPreferredSize(new Dimension(1080, 50));
		quizNameHeader.setFont(headerFont);
		
		questionCardLayout = new CardLayout();
		questionPanel = new JPanel();
		questionPanel.setLayout(questionCardLayout);
		questionPanel.setPreferredSize(new Dimension(400, 300));
		questionPanel.setBackground(Color.cyan);
		
		questionKeys = new ArrayList<String>();
	
		JButton nextButton = new JButton(NEXT_BTN_STR);
		nextButton.addActionListener(this);
		JButton prevButton = new JButton(PREV_BTN_STR);
		prevButton.addActionListener(this);
		JButton submitButton = new JButton(SUBMIT_BTN_STR);
		submitButton.addActionListener(this);
		
		GroupLayout panelLayout = new GroupLayout(this);
		this.setLayout(panelLayout);
		panelLayout.setAutoCreateContainerGaps(true);
		panelLayout.setAutoCreateGaps(true);
		
		panelLayout.setHorizontalGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(quizNameHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(questionPanel)
				.addGroup(panelLayout.createSequentialGroup()
						.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
						.addComponent(prevButton)
						.addGap(250)
						.addComponent(nextButton)
						.addComponent(submitButton)
						.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)));
		
		panelLayout.setVerticalGroup(panelLayout.createSequentialGroup()
				.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
				.addComponent(quizNameHeader)
				.addGap(50)
				.addComponent(questionPanel)
				.addGap(100)
				.addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(prevButton)
						.addComponent(nextButton)
						.addComponent(submitButton))
				.addPreferredGap(ComponentPlacement.RELATED, 200, Short.MAX_VALUE));
	}
	
	//Setup the panel to display the quiz
	public void takeQuiz(User user, Quiz quiz, String courseName){
		currentUser = user;
		currentQuiz = quiz;
		currentCourseName = courseName;
		quizNameHeader.setText(quiz.getName());
		questionPanel.removeAll();
		
		questionKeys.clear();
		for(Question question : quiz.getQuestions()){
			questionKeys.add(question.getQuestion());
			questionPanel.add(new AnswerQuestion(question), question.getQuestion());
		}
		questionIndex = 0;
		currentQuestionKey = questionKeys.get(0);
		questionCardLayout.show(questionPanel, currentQuestionKey);
		
	}
	
	//Handle button presses
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == NEXT_BTN_STR && questionIndex < questionKeys.size() - 1){
			currentQuestionKey = questionKeys.get(++questionIndex);
			questionCardLayout.show(questionPanel, currentQuestionKey);
		}else if(e.getActionCommand() == PREV_BTN_STR && questionIndex > 0){
			currentQuestionKey = questionKeys.get(--questionIndex);
			questionCardLayout.show(questionPanel, currentQuestionKey);	
		}else if(e.getActionCommand() == SUBMIT_BTN_STR){
			for(Question question : currentQuiz.getQuestions()){
				boolean selectedAnswer = false;
				for(Answer answer : question.getAnswers()){
					if(answer.isSelected()){
						selectedAnswer = true;
						break;
					}
				}
				if(!selectedAnswer){
					JOptionPane.showMessageDialog(this, "You must select an answer for each question.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			currentUser.submitResult(currentUser.getUsername(), currentQuiz, currentCourseName);
			app.mainMenu();
		}
	}
}
