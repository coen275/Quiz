package experiement_swing;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class CourseQuiz extends JPanel implements ActionListener {
	private final JTextField accesscode = new JTextField();// text field for access code

	/**
	 * Create the panel.
	 */
	public CourseQuiz() {
		
		JPanel container = new JPanel();// panel you have to add in the frame "container"
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(container, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(container, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JPanel panel1 = new JPanel();// panel, contains all course information
		
		JPanel panel2 = new JPanel();
		
		JButton takequiz = new JButton("Take quiz");// take quiz button
		takequiz.addActionListener(this);
		
		JButton viewresult = new JButton("View Result");// view result button
		viewresult.addActionListener(this);
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_container.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel1, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel2, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addGroup(gl_container.createParallelGroup(Alignment.TRAILING)
						.addComponent(viewresult)
						.addComponent(takequiz))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_container.createSequentialGroup()
					.addGroup(gl_container.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_container.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel2, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
						.addComponent(panel1, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_container.createSequentialGroup()
							.addGap(197)
							.addComponent(takequiz)
							.addGap(37)
							.addComponent(viewresult)))
					.addContainerGap())
		);
		
		JLabel quiz = new JLabel("Quizes");
		
		JButton quiz1 = new JButton("Quiz 1");
		quiz1.addActionListener(this);
		
		JButton quiz2 = new JButton("Quiz 2");
		quiz2.addActionListener(this);
		JButton quiz3 = new JButton("Quiz 3");
		quiz3.addActionListener(this);
		
		JButton quiz4 = new JButton("Quiz 4");
		quiz4.addActionListener(this);
		
		GroupLayout gl_panel2 = new GroupLayout(panel2);
		gl_panel2.setHorizontalGroup(
			gl_panel2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel2.createSequentialGroup()
					.addGroup(gl_panel2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel2.createSequentialGroup()
							.addGap(35)
							.addComponent(quiz, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel2.createSequentialGroup()
							.addGap(23)
							.addGroup(gl_panel2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(quiz1)
								.addComponent(quiz2, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
								.addComponent(quiz3, 0, 0, Short.MAX_VALUE)
								.addComponent(quiz4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_panel2.setVerticalGroup(
			gl_panel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel2.createSequentialGroup()
					.addGap(29)
					.addComponent(quiz)
					.addGap(18)
					.addComponent(quiz1)
					.addGap(18)
					.addComponent(quiz2)
					.addGap(18)
					.addComponent(quiz3)
					.addGap(18)
					.addComponent(quiz4)
					.addContainerGap(247, Short.MAX_VALUE))
		);
		panel2.setLayout(gl_panel2);
		
		JLabel Courses = new JLabel("Courses");// course label
		Courses.setVerticalAlignment(SwingConstants.TOP);
		
		JButton course1 = new JButton("Couse1 Name");// first course button, add name
		course1.addActionListener(this);
		
		JButton course2 = new JButton("course2 Name");// second course button,add name
		course2.addActionListener(this);
		
		JButton course3 = new JButton("Course3 Name");// third course button,add name
		course2.addActionListener(this);
		
		JButton course4 = new JButton("Course4 Name");// fourth course button,add name
		course4.addActionListener(this);
		
		accesscode.setColumns(10);
		
		JLabel addcourse = new JLabel("Add Course");
		
		JLabel access = new JLabel("Access code");
		
		JButton addbutton = new JButton("ADD");// add button
		addbutton.addActionListener(this);
		
		GroupLayout groupLayout_1 = new GroupLayout(panel1);
		groupLayout_1.setHorizontalGroup(
			groupLayout_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addGroup(groupLayout_1.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout_1.createSequentialGroup()
							.addContainerGap(12, Short.MAX_VALUE)
							.addGroup(groupLayout_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(addcourse, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout_1.createSequentialGroup()
									.addComponent(access)
									.addGap(3)
									.addComponent(accesscode, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout_1.createSequentialGroup()
									.addComponent(Courses, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addGap(66))))
						.addGroup(groupLayout_1.createSequentialGroup()
							.addGap(16)
							.addGroup(groupLayout_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(course3, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
								.addComponent(course2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(course1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(course4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE))
						.addGroup(groupLayout_1.createSequentialGroup()
							.addGap(30)
							.addComponent(addbutton)))
					.addContainerGap())
		);
		groupLayout_1.setVerticalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addGap(29)
					.addComponent(Courses)
					.addGap(18)
					.addComponent(course1)
					.addGap(18)
					.addComponent(course2)
					.addGap(18)
					.addComponent(course3)
					.addGap(18)
					.addComponent(course4)
					.addGap(92)
					.addComponent(addcourse)
					.addGap(18)
					.addGroup(groupLayout_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(access)
						.addComponent(accesscode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(addbutton)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		panel1.setLayout(groupLayout_1);
		container.setLayout(gl_container);
		setLayout(groupLayout);

	}

	@Override
	public void actionPerformed(ActionEvent e) // add buttons functionality here
	{
		
		
	}
}