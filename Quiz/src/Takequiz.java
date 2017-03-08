package experiement_swing;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Takequiz extends JPanel implements ActionListener{

	/**
	 * Create the panel.
	 */
	public Takequiz() {
		
		JPanel container = new JPanel();// add this panel to the frame
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

	}

	@Override
	public void actionPerformed(ActionEvent e) // add every button functionality here
	{
		// TODO Auto-generated method stub
		
	}
}
