package UI;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Models.Question;
import Models.Answer;

public class AnswerQuestion extends JPanel implements ListSelectionListener {

	private Question question;
	private JList answersList;
	
	private final Font headerFont = new Font(Font.DIALOG, Font.BOLD, 30);
	private final Font subHeaderFont = new Font(Font.DIALOG, Font.BOLD, 26);
	
	
	public AnswerQuestion(Question question){
		this.question = question;
		
		JLabel questionTextLabel = new JLabel(question.getQuestion(), SwingConstants.CENTER);
		questionTextLabel.setFont(headerFont);
		
		answersList = new JList();
		answersList.setListData(question.getAnswersArray());
		answersList.setCellRenderer(new ListRenderer());
		answersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		answersList.addListSelectionListener(this);
		
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addPreferredGap(ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(questionTextLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(answersList))
				.addPreferredGap(ComponentPlacement.RELATED, 192, Short.MAX_VALUE));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(questionTextLabel)
				.addGap(50)
				.addComponent(answersList));		
	}
	
	public Question getQuestion(){
		return question;
	}
	
	private class ListRenderer extends DefaultListCellRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		        JRadioButton button = new JRadioButton(value.toString());
		        button.setSelected(isSelected);
				button.setFont(new Font("Ariel", Font.PLAIN, 20));
		        return button;
		    }
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Answer answer = (Answer)answersList.getSelectedValue();
		
		if(answer != null){
			for(Answer a : question.getAnswers()){
				a.deselectAnswer();
			}
			answer.selectAnswer();
		}
		
	}
}
