import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CreateQuiz extends JPanel implements ListSelectionListener, ActionListener {
	
	private App app;
	private Course currentCourse;
	private Quiz tmpQuiz;
	
	private JList questionList;
	private DefaultListModel questionsModel;
	private int serialNumber;
	
	private JPanel questionPanel;
	
	private static final String ADD_BTN_LABEL = "Add";
	private static final String REMOVE_BTN_LABEL = "Remove";
	private static final String SAVE_BTN_LABEL = "Create Quiz";
	private static final String BACK_LABEL = "Back";
	
	private final Font headerFont = new Font(Font.DIALOG, Font.BOLD, 30);
	private final Font subHeaderFont = new Font(Font.DIALOG, Font.BOLD, 26);
	
	public CreateQuiz(App app) {
		this.app = app;
		serialNumber = 0;
		
		questionPanel = new JPanel();
		questionPanel.setLayout(new GridBagLayout());
		
		JPanel questionsColumn = new JPanel();
		JLabel questionsHeader = new JLabel("Questions");
		questionsHeader.setFont(headerFont);
		
		questionList = new JList();
		questionsModel = new DefaultListModel();
		questionList.setModel(questionsModel);
		questionList.addListSelectionListener(this);
		
		
		JButton addQuestionButton = new JButton(ADD_BTN_LABEL);
		addQuestionButton.addActionListener(this);
		
		JButton removeQuestionButton = new JButton(REMOVE_BTN_LABEL);
		removeQuestionButton.addActionListener(this);
		
		JButton createQuizButton = new JButton(SAVE_BTN_LABEL);
		createQuizButton.addActionListener(this);
		
		JButton backToMainButton = new JButton(BACK_LABEL);
		backToMainButton.addActionListener(this);
		
		GroupLayout questionsLayout = new GroupLayout(questionsColumn);
		questionsColumn.setLayout(questionsLayout);
		questionsLayout.setAutoCreateGaps(true);
		
		questionsLayout.setHorizontalGroup(questionsLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(questionsHeader)
				.addComponent(questionList)
				.addGroup(questionsLayout.createSequentialGroup()
						.addComponent(addQuestionButton)
						.addComponent(removeQuestionButton))
				.addComponent(createQuizButton)
				.addComponent(backToMainButton));
		
		questionsLayout.setVerticalGroup(questionsLayout.createSequentialGroup()
				.addComponent(questionsHeader)
				.addComponent(questionList)
				.addGroup(questionsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(addQuestionButton)
						.addComponent(removeQuestionButton))
				.addComponent(createQuizButton)
				.addComponent(backToMainButton));
		
		GroupLayout groupLayout = new GroupLayout(this);
		setLayout(groupLayout);
		groupLayout.setAutoCreateContainerGaps(true);
		groupLayout.setAutoCreateGaps(true);
		
		groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
				.addComponent(questionsColumn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(questionPanel));
		
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(questionsColumn)
				.addComponent(questionPanel));
	}
	
	public void setCourse(Course course){
		currentCourse = course;
		
		String quizName = (String)JOptionPane.showInputDialog(this, "New Quiz", "Quiz Name",JOptionPane.PLAIN_MESSAGE);
		if(quizName != null && quizName.length() > 0){
			tmpQuiz = course.tempQuiz = new Quiz(quizName);
		}else {
			app.mainMenu();
		}		
	}
	
	public void refresh(){
		revalidate();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if(e.getValueIsAdjusting()){
			return;
		}
		System.out.println("Selected");
		JList list = (JList)e.getSource();
		Question question = (Question)list.getSelectedValue();
		questionPanel.removeAll();
		if(question != null){
			questionPanel.add(new CreateQuestion(this, question), new GridBagConstraints());
		}
		questionPanel.repaint();
		revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		if(command == ADD_BTN_LABEL){
			String questionText = (String)JOptionPane.showInputDialog(this, "Question: ", "Create Question",JOptionPane.PLAIN_MESSAGE);
			if(questionText != null && questionText.length() > 0){
				Question question = new Question(questionText, serialNumber++);
				questionsModel.addElement(question);
				tmpQuiz.addQuestion(question);
			}
		} else if(command == REMOVE_BTN_LABEL){
			Object selectedValue = questionList.getSelectedValue();
			if(selectedValue == null){
				JOptionPane.showMessageDialog(this, "Please select a question to remove.", "Error", JOptionPane.ERROR_MESSAGE);
			}else{
				questionsModel.removeElement(questionList.getSelectedValue());
				tmpQuiz.removeQuestion((Question)questionList.getSelectedValue());
			}
		} else if(command == SAVE_BTN_LABEL){
			currentCourse.addQuiz();
			questionList.removeAll();
			questionsModel.removeAllElements();
			app.mainMenu();
		} else if (command == BACK_LABEL) {
			questionList.removeAll();
			questionsModel.removeAllElements();
			app.mainMenu();
		}
		
	}

}
