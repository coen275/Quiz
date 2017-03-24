package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Models.Answer;
import Models.Question;

//The GUI panel to create a question
public class CreateQuestion extends JPanel implements ActionListener {

	private Question question;
	private CreateQuiz createQuizPanel;

	private JList answerList;
	private DefaultListModel answerListModel;
	
	private JPanel createAnswerPanel;
	private JTextArea answerTextArea;
	private JCheckBox answerIsCorrectBox;
		
	private final Font headerFont = new Font(Font.DIALOG, Font.BOLD, 30);
	private final Font subHeaderFont = new Font(Font.DIALOG, Font.BOLD, 26);
	
	private static final String ADD_BTN_LABEL = "Add";
	private static final String REMOVE_BTN_LABEL = "Remove";
	
	//A custom renderer to display the answer list
	private class ListRenderer extends DefaultListCellRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			JLabel label = (JLabel)super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
	        Font font = new Font("Ariel", Font.PLAIN, 20);
	        label.setFont(font);
	        Answer answer = (Answer)value;
	        if(answer != null && answer.getStatus()){
	        	label.setForeground(new Color(0, 128, 0));
	        }
	        return label;
	    }
	}
	
	//Constructor
	public CreateQuestion(CreateQuiz createQuizPanel, Question question){
		
		this.createQuizPanel = createQuizPanel;
		this.question = question;
		
		JLabel questionTextLabel = new JLabel(question.getQuestion());
		questionTextLabel.setFont(headerFont);
		
		answerList = new JList();
		answerList.setCellRenderer(new ListRenderer());
		answerListModel = new DefaultListModel();
		answerList.setModel(answerListModel);
		for(Answer a : question.getAnswers()){
			answerListModel.addElement(a);
		}
		
		JButton addAnswerButton = new JButton(ADD_BTN_LABEL);
		addAnswerButton.addActionListener(this);
		JButton removeAnswerButton = new JButton(REMOVE_BTN_LABEL);
		removeAnswerButton.addActionListener(this);
		
		GroupLayout groupLayout = new GroupLayout(this);
		setLayout(groupLayout);
		
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(questionTextLabel)
				.addComponent(answerList)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(addAnswerButton)
						.addComponent(removeAnswerButton)));
		
		groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
				.addComponent(questionTextLabel)
				.addGap(15)
				.addComponent(answerList)
				.addGap(50)
				.addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(addAnswerButton)
						.addComponent(removeAnswerButton)));
		
		createAnswerPanel = new JPanel();
		this.answerTextArea = new JTextArea(4, 30);
		this.answerIsCorrectBox = new JCheckBox("Is Correct?");
		JLabel answerInputLabel = new JLabel("Answer Text");
		
		GroupLayout answerLayout = new GroupLayout(createAnswerPanel);
		createAnswerPanel.setLayout(answerLayout);
		answerLayout.setHorizontalGroup(answerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(answerLayout.createSequentialGroup()
						.addComponent(answerInputLabel)
						.addGap(20)
						.addComponent(answerTextArea))
				.addComponent(answerIsCorrectBox));
		
		answerLayout.setVerticalGroup(answerLayout.createSequentialGroup()
				.addGroup(answerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(answerInputLabel)
						.addComponent(answerTextArea))
				.addComponent(answerIsCorrectBox));
	}

	//The button press handler
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command == ADD_BTN_LABEL){
			int result = JOptionPane.showConfirmDialog(this, createAnswerPanel, "Enter Answer Information", JOptionPane.OK_CANCEL_OPTION);
			if(result == JOptionPane.OK_OPTION){
				String answerText = this.answerTextArea.getText();
				boolean isCorrect = this.answerIsCorrectBox.isSelected();
				this.answerTextArea.setText("");
				this.answerIsCorrectBox.setSelected(false);
				if(answerText != null && answerText.length() > 0){
					Answer answer = new Answer(answerText, isCorrect);
					question.addAnswer(answer);
					this.answerListModel.addElement(answer);
				}
			}
		}else if(command == REMOVE_BTN_LABEL){
			Answer selectedAnswer = (Answer)answerList.getSelectedValue();
			if(selectedAnswer != null){
				answerListModel.removeElement(selectedAnswer);
				question.removeAnswer(selectedAnswer);
			}
		}
	}
}
