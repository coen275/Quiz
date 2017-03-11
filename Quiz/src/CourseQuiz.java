//package experiement_swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.List;

public class CourseQuiz extends JPanel implements ActionListener, ListSelectionListener {
	private final JTextField accessCodeTextField;

	private JList courseList;
	private JList quizList;
	
	private JPanel quizControlPanel;
	private JLabel courseNameLabel, quizNameLabel;
	
	private App app;
	private User currentUser;
	private Course currentCourse;
	private Quiz currentQuiz;
	
	private final Font headerFont = new Font(Font.DIALOG, Font.BOLD, 30);
	private final Font subHeaderFont = new Font(Font.DIALOG, Font.BOLD, 26);
	
	private class ListRenderer extends DefaultListCellRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		        JLabel label = (JLabel)super.getListCellRendererComponent(
		            list,value,index,isSelected,cellHasFocus);
		        Font font = new Font("Ariel", Font.PLAIN, 20);
		        label.setFont(font);
		        return label;
		    }
	}
	
	/**
	 * Create the panel.
	 */
	public CourseQuiz(App app) {
		this.app = app;
		
		//Create Course Selection Components
		JPanel courseSelectionPanel = new JPanel();		
		
		JLabel courseSelectionHeader = new JLabel("Select Course");
		courseSelectionHeader.setFont(headerFont);
		
		courseList = new JList();
		courseList.setCellRenderer(new ListRenderer());
		courseList.addListSelectionListener(this);
		
		JLabel addCourseLabel = new JLabel("Access Code");
		accessCodeTextField = new JTextField();
		JButton addCourseButton = new JButton("Add Course");
		
		//Create Course Selection Layout
		GroupLayout courseSelectionLayout = new GroupLayout(courseSelectionPanel);
		courseSelectionPanel.setLayout(courseSelectionLayout);
		courseSelectionLayout.setAutoCreateGaps(true);
		courseSelectionLayout.setHorizontalGroup(courseSelectionLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(courseSelectionHeader)
				.addComponent(courseList)
				.addGroup(courseSelectionLayout.createSequentialGroup()
						.addComponent(addCourseLabel)
						.addComponent(accessCodeTextField))
				.addComponent(addCourseButton));
		
		courseSelectionLayout.setVerticalGroup(courseSelectionLayout.createSequentialGroup()
				.addComponent(courseSelectionHeader)
				.addComponent(courseList)
				.addGap(18)
				.addGroup(courseSelectionLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(addCourseLabel)
						.addComponent(accessCodeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addComponent(addCourseButton));
		
		//Create Quiz Selection Components
		JPanel quizSelectionPanel = new JPanel();
		
		JLabel quizSelectionHeader = new JLabel("Select Course");
		quizSelectionHeader.setFont(headerFont);
		
		quizList = new JList();
		quizList.setCellRenderer(new ListRenderer());
		quizList.addListSelectionListener(this);
		
		//Create Quiz Selection Layout
		GroupLayout quizSelectionLayout = new GroupLayout(quizSelectionPanel);
		quizSelectionPanel.setLayout(quizSelectionLayout);
		quizSelectionLayout.setAutoCreateGaps(true);
		quizSelectionLayout.setHorizontalGroup(quizSelectionLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(quizSelectionHeader)
				.addComponent(quizList));
		
		quizSelectionLayout.setVerticalGroup(quizSelectionLayout.createSequentialGroup()
				.addComponent(quizSelectionHeader)
				.addComponent(quizList));
		
		//Create Quiz Control Panel Components
		quizControlPanel = new JPanel();		
		
		courseNameLabel = new JLabel("<Course Name>");
		courseNameLabel.setFont(headerFont);
		quizNameLabel = new JLabel("<Quiz Name>");
		quizNameLabel.setFont(subHeaderFont);
		JButton takeQuizButton = new JButton("Take Quiz");
		JButton viewResultsButton = new JButton("View Results");
		
		//Create Quiz Controls Layout
		GroupLayout quizControlLayout = new GroupLayout(quizControlPanel);
		quizControlPanel.setLayout(quizControlLayout);
		quizControlLayout.setAutoCreateGaps(true);
		quizControlLayout.setHorizontalGroup(quizControlLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(courseNameLabel)
				.addComponent(quizNameLabel)
				.addGroup(quizControlLayout.createSequentialGroup()
						.addComponent(takeQuizButton)
						.addComponent(viewResultsButton)));
		
		quizControlLayout.setVerticalGroup(quizControlLayout.createSequentialGroup()
				.addComponent(courseNameLabel)
				.addComponent(quizNameLabel)
				.addGroup(quizControlLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(takeQuizButton)
						.addComponent(viewResultsButton)));
		
		//Create Main panel layout
		
		JSeparator s1 = new JSeparator(SwingConstants.VERTICAL), s2 = new JSeparator(SwingConstants.VERTICAL);
		
		GroupLayout panelLayout = new GroupLayout(this);
		setLayout(panelLayout);
		panelLayout.setAutoCreateContainerGaps(true);
		panelLayout.setAutoCreateGaps(true);
		
		panelLayout.setHorizontalGroup(panelLayout.createSequentialGroup()
				.addComponent(courseSelectionPanel, 300, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(s1,  GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(quizSelectionPanel)
				.addComponent(s2,  GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(quizControlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
		
		panelLayout.setVerticalGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(courseSelectionPanel)
				.addComponent(s1)
				.addComponent(quizSelectionPanel)
				.addComponent(s2)
				.addComponent(quizControlPanel));
	
		
		/*
		JPanel container = new JPanel();// panel you have to add in the frame "container"
		container.setBackground(Color.red);
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
		*/
	}
	
	public void refreshContent(){	
		currentUser = app.getActiveUser();
		currentUser.loadCourse();
		
		this.courseList.setListData(getCourseNames(currentUser));
		this.quizList.setListData(new String[] {});
		this.quizControlPanel.setVisible(false);
		this.revalidate();
	}
	
	private String[] getCourseNames(User user){
		List<Course> courses = user.getCourse();
		String[] courseNames = new String[courses.size()];
		for(int i = 0; i < courses.size(); i++){
			courseNames[i] = courses.get(i).name;
		}
		return courseNames;
	}
	
	private String[] getQuizNames(Course course){
		List<Quiz> quizzes = course.getQuizs();
		String[] quizNames = new String[quizzes.size()];
		for(int i = 0; i < quizzes.size(); i++){
			quizNames[i] = quizzes.get(i).getName();
		}
		return quizNames;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) // add buttons functionality here
	{
		
		
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if(arg0.getValueIsAdjusting())
			return;
		
		JList list = (JList)arg0.getSource();
		
		if(list == courseList){
			List<Course> courses = currentUser.getCourse();
			for(Course course : courses){
				if(course.name == (String)courseList.getSelectedValue()){
					currentCourse = course;
					break;
				}
			}
			quizList.setListData(getQuizNames(currentCourse));
		} else if(list == quizList){
			List<Quiz> quizzes = currentCourse.getQuizs();
			for(Quiz quiz : quizzes){
				if(quiz.getName() == (String)quizList.getSelectedValue()){
					currentQuiz = quiz;
					break;
				}
			}
			this.quizControlPanel.setVisible(true);
			this.courseNameLabel.setText(currentCourse.name);
			this.quizNameLabel.setText(currentQuiz.getName());
			this.revalidate();
		}
		
	}
}
