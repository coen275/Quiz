//package experiement_swing;

import javax.swing.JPanel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.Group;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Takequiz extends JPanel implements ActionListener{
	
	private Quiz currentQuiz;
	
	private JLabel quizNameHeader;
	private JLabel questionLabel;
	
	private final Font headerFont = new Font(Font.DIALOG, Font.BOLD, 30);
	private final Font subHeaderFont = new Font(Font.DIALOG, Font.BOLD, 26);
	
	
	private class ListRenderer extends DefaultListCellRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		        JRadioButton button = new JRadioButton((String)value);
		        button.setSelected(isSelected);
				button.setFont(new Font("Ariel", Font.PLAIN, 20));
		        return button;
		    }
	}
	
	/**
	 * Create the panel.
	 */
	public Takequiz() {
		
		quizNameHeader = new JLabel("<Quiz Name>", SwingConstants.CENTER);
		quizNameHeader.setPreferredSize(new Dimension(1080, 50));
		quizNameHeader.setFont(headerFont);
		
		questionLabel = new JLabel("<Question ehjsafhjsakshf dsalfjhkjlsahsf>", SwingConstants.CENTER);
		questionLabel.setPreferredSize(new Dimension(1080, 50));
		questionLabel.setFont(subHeaderFont);
		
		JList answerList = new JList();
		answerList.setListData(new String[] { "A1", "A2", "A3"});
		answerList.setCellRenderer(new ListRenderer());
		answerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton nextButton = new JButton("Next Question");
		JButton prevButton = new JButton("Previous Question");
		
		GroupLayout panelLayout = new GroupLayout(this);
		this.setLayout(panelLayout);
		panelLayout.setAutoCreateContainerGaps(true);
		panelLayout.setAutoCreateGaps(true);
		
		panelLayout.setHorizontalGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(quizNameHeader, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(questionLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGroup(panelLayout.createSequentialGroup()
						.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
						.addComponent(answerList)
						.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE))
				.addGroup(panelLayout.createSequentialGroup()
						.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
						.addComponent(prevButton)
						.addGap(250)
						.addComponent(nextButton)
						.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)));
		
		panelLayout.setVerticalGroup(panelLayout.createSequentialGroup()
				.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
				.addComponent(quizNameHeader)
				.addGap(50)
				.addComponent(questionLabel)
				.addGap(50)
				.addComponent(answerList)
				.addGap(100)
				.addGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(prevButton)
						.addComponent(nextButton))
				.addPreferredGap(ComponentPlacement.RELATED, 200, Short.MAX_VALUE));
		
		
		
		/*JPanel container = new JPanel();// add this panel to the frame
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(container, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(container, GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel quizname = new JLabel("Quiz Name");// Jlabel quiz name, change name here is require
		
		JLabel question = new JLabel("Questions, you can add whole sentence here");// just pass the string, which you want to be displayed
		
		JRadioButton answer1 = new JRadioButton("Answer 1");// add the button text here
		answer1.addActionListener(this);
		
		JRadioButton answer2 = new JRadioButton("Answer 2");
		answer2.addActionListener(this);
		
		JRadioButton answer3 = new JRadioButton("Answer 3");
		answer3.addActionListener(this);
		
		JRadioButton answer4 = new JRadioButton("Answer 4");// if you don't want this button just remove it by commenting these two line
		answer4.addActionListener(this);
		
		JButton previousbutton = new JButton("<<Previous");
		previousbutton.addActionListener(this);
		
		JButton nextbutton = new JButton("Next>>");
		nextbutton.addActionListener(this);
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_container.createSequentialGroup()
					.addGroup(gl_container.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_container.createSequentialGroup()
							.addContainerGap()
							.addComponent(quizname, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_container.createSequentialGroup()
							.addGap(109)
							.addGroup(gl_container.createParallelGroup(Alignment.LEADING)
								.addComponent(answer1)
								.addComponent(answer2)
								.addComponent(answer3)   //))
								.addComponent(answer4)))// Commenting this line and remove comment from previous line
						.addGroup(gl_container.createSequentialGroup()
							.addGap(38)
							.addComponent(question, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_container.createSequentialGroup()
							.addContainerGap()
							.addComponent(previousbutton)
							.addPreferredGap(ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
							.addComponent(nextbutton)))
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_container.createSequentialGroup()
					.addGap(31)
					.addComponent(quizname)
					.addGap(18)
					.addComponent(question)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_container.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_container.createSequentialGroup()
							.addComponent(answer1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(answer2)
							.addGap(18)
							.addComponent(answer3)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(answer4)// comment this line too
							.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
							.addComponent(previousbutton))
						.addGroup(gl_container.createSequentialGroup()
							.addComponent(nextbutton)
							.addContainerGap())))
		);
		container.setLayout(gl_container);
		setLayout(groupLayout);
		*/
	}
	
	public void takeQuiz(User user, Quiz quiz){
		quizNameHeader.setText(quiz.getName());
		Question q = quiz.getQuestions().get(0);
		questionLabel.setText(q.getQuestion());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) // add every button functionality here
	{
		// TODO Auto-generated method stub
		
	}
}
