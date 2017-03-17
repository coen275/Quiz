
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
import javax.swing.JOptionPane;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CourseQuiz extends JPanel implements ActionListener, ListSelectionListener {
	private JList courseList;
	private JList quizList;
	private JList resultsList;

	private JPanel quizControlPanel;
	private JLabel courseNameLabel, quizNameLabel, accessCodeLabel;
	private JLabel lowScoreLabel, highScoreLabel, avgScoreLabel, newCourseLabel, resultsLabel;
	private JTextField accessCodeTextField;
	private JTextField courseNameTextField;

	private JButton addCourseButton;
	private JButton removeCourseButton;
	private JButton takeQuizButton;
	private JButton viewResultsButton;
	private JButton addQuizButton;
	private JButton removeQuizButton;

	private App app;
	private User currentUser;
	private Course currentCourse;
	private Quiz currentQuiz;

	private final Font headerFont = new Font(Font.DIALOG, Font.BOLD, 30);
	private final Font subHeaderFont = new Font(Font.DIALOG, Font.BOLD, 24);

	private class ListRenderer extends DefaultListCellRenderer {
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
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

		// Create Course Selection Components
		JPanel courseSelectionPanel = new JPanel();

		JLabel courseSelectionHeader = new JLabel("Select Course");
		courseSelectionHeader.setFont(headerFont);

		courseList = new JList();
		courseList.setCellRenderer(new ListRenderer());
		courseList.addListSelectionListener(this);

		newCourseLabel = new JLabel("Course Name ");
		courseNameTextField = new JTextField();
		accessCodeLabel = new JLabel("Access Code ");
		accessCodeTextField = new JTextField();
		addCourseButton = new JButton("Add Course");
		addCourseButton.addActionListener(this);
		removeCourseButton = new JButton("Remove Course");
		removeCourseButton.addActionListener(this);

		// Create Course Selection Layout
		GroupLayout courseSelectionLayout = new GroupLayout(courseSelectionPanel);
		courseSelectionPanel.setLayout(courseSelectionLayout);
		courseSelectionLayout.setAutoCreateGaps(true);
		courseSelectionLayout.setHorizontalGroup(courseSelectionLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(courseSelectionHeader).addComponent(courseList)
				.addGroup(courseSelectionLayout.createSequentialGroup().addComponent(newCourseLabel)
						.addComponent(courseNameTextField))
				.addGroup(courseSelectionLayout.createSequentialGroup().addComponent(accessCodeLabel)
						.addComponent(accessCodeTextField))
				.addComponent(addCourseButton)
				.addComponent(removeCourseButton));

		courseSelectionLayout.setVerticalGroup(courseSelectionLayout.createSequentialGroup()
				.addComponent(courseSelectionHeader).addComponent(courseList).addGap(18)
				.addGroup(courseSelectionLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(newCourseLabel).addComponent(courseNameTextField, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(courseSelectionLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(accessCodeLabel).addComponent(accessCodeTextField, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addComponent(addCourseButton)
				.addComponent(removeCourseButton));

		// Create Quiz Selection Components
		JPanel quizSelectionPanel = new JPanel();

		JLabel quizSelectionHeader = new JLabel("Select Quiz");
		quizSelectionHeader.setFont(headerFont);

		quizList = new JList();
		quizList.setCellRenderer(new ListRenderer());
		quizList.addListSelectionListener(this);

		addQuizButton = new JButton("Add Quiz");
		addQuizButton.addActionListener(this);
		removeQuizButton = new JButton("Remove Quiz");
		removeQuizButton.addActionListener(this);

		// Create Quiz Selection Layout
		GroupLayout quizSelectionLayout = new GroupLayout(quizSelectionPanel);
		quizSelectionPanel.setLayout(quizSelectionLayout);
		quizSelectionLayout.setAutoCreateGaps(true);
		quizSelectionLayout.setHorizontalGroup(quizSelectionLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(quizSelectionHeader)
				.addComponent(quizList)
				.addComponent(addQuizButton)
				.addComponent(removeQuizButton));

		quizSelectionLayout.setVerticalGroup(quizSelectionLayout.createSequentialGroup()
				.addComponent(quizSelectionHeader)
				.addComponent(quizList)
				.addGap(18)
				.addComponent(addQuizButton)
				.addComponent(removeQuizButton));

		// Create Quiz Control Panel Components
		quizControlPanel = new JPanel();

		courseNameLabel = new JLabel("<Course Name>");
		courseNameLabel.setFont(headerFont);
		quizNameLabel = new JLabel("<Quiz Name>");
		quizNameLabel.setFont(subHeaderFont);
		takeQuizButton = new JButton("Take Quiz");
		takeQuizButton.addActionListener(this);
		resultsLabel = new JLabel("");
		resultsLabel.setFont(subHeaderFont);
		lowScoreLabel = new JLabel("Low Score");
		highScoreLabel = new JLabel("High Score");
		avgScoreLabel = new JLabel("Avg Score");
		resultsList = new JList();
		resultsList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

		// Create Quiz Controls Layout
		GroupLayout quizControlLayout = new GroupLayout(quizControlPanel);
		quizControlPanel.setLayout(quizControlLayout);

		quizControlLayout.setAutoCreateGaps(true);
		quizControlLayout.setHorizontalGroup(quizControlLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(courseNameLabel)
				.addComponent(quizNameLabel)
				.addComponent(takeQuizButton)
				.addComponent(lowScoreLabel)
				.addComponent(avgScoreLabel)
				.addComponent(highScoreLabel)
				.addComponent(resultsLabel)
				.addComponent(resultsList));

		quizControlLayout.setVerticalGroup(quizControlLayout.createSequentialGroup()
				.addComponent(courseNameLabel)
				.addComponent(quizNameLabel)
				.addComponent(takeQuizButton)
				.addComponent(lowScoreLabel)
				.addComponent(avgScoreLabel)
				.addComponent(highScoreLabel)
				.addComponent(resultsLabel)
				.addComponent(resultsList));

		// Create Main panel layout

		JSeparator s1 = new JSeparator(SwingConstants.VERTICAL), s2 = new JSeparator(SwingConstants.VERTICAL);

		GroupLayout panelLayout = new GroupLayout(this);
		setLayout(panelLayout);
		panelLayout.setAutoCreateContainerGaps(true);
		panelLayout.setAutoCreateGaps(true);

		panelLayout.setHorizontalGroup(panelLayout.createSequentialGroup()
				.addComponent(courseSelectionPanel, 300, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(s1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(quizSelectionPanel)
				.addComponent(s2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(quizControlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE));

		panelLayout.setVerticalGroup(panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(courseSelectionPanel).addComponent(s1).addComponent(quizSelectionPanel).addComponent(s2)
				.addComponent(quizControlPanel));
	}

	public void refreshContent() {
		currentUser = app.getActiveUser();
		currentUser.loadCourse();

		this.courseList.setListData(currentUser.getCourseArray());
		this.quizList.setListData(new String[] {});
		this.quizControlPanel.setVisible(false);

		boolean isTeacher = currentUser.getType().equals("teacher");

		this.newCourseLabel.setVisible(!isTeacher);
		this.courseNameTextField.setVisible(!isTeacher);
		this.accessCodeTextField.setVisible(!isTeacher);
		this.accessCodeLabel.setVisible(!isTeacher);
		this.addQuizButton.setVisible(isTeacher);
		
		this.removeQuizButton.setVisible(isTeacher);
		this.removeCourseButton.setVisible(isTeacher);

		this.revalidate();
	}

	private String[] getQuizNames(Course course) {
		List<Quiz> quizzes = course.getQuizs();
		String[] quizNames = new String[quizzes.size()];
		for (int i = 0; i < quizzes.size(); i++) {
			quizNames[i] = quizzes.get(i).getName();
		}
		return quizNames;
	}

	@Override
	public void actionPerformed(ActionEvent e) // add buttons functionality here
	{
		JButton src = (JButton) e.getSource();

		if (src == this.takeQuizButton) {
			app.takeQuiz(currentQuiz.clone());
		} else if (src == this.viewResultsButton) {

		} else if (src == this.addCourseButton) {
			if (currentUser.type.equals("teacher")) {
				String courseName = (String) JOptionPane.showInputDialog(this, "Course Name: ", "Add Course",
						JOptionPane.PLAIN_MESSAGE);
				if (courseName != null && courseName.length() > 0) {
					System.out.println(courseName);
					Random rng = new Random(System.currentTimeMillis());
					int accessCode = 1000 + rng.nextInt(9000);
					currentUser.addCourse(courseName, String.valueOf(accessCode));
					refreshContent();
				}
			} else {
				String courseName = this.courseNameTextField.getText();
				String accessCode = this.accessCodeTextField.getText();
				this.courseNameTextField.setText("");
				this.accessCodeTextField.setText("");
				if (courseName != null && courseName.length() > 0 && accessCode != null && accessCode.length() > 0) {
					currentUser.addCourse(courseName, accessCode);
					refreshContent();
				} else {
					JOptionPane.showMessageDialog(this, "Please enter a course name and access code", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (src == this.addQuizButton) {
			if (currentCourse == null) {
				JOptionPane.showMessageDialog(this, "Please select a course", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				app.createQuiz(currentCourse);
			}
		} else if(src == this.removeCourseButton){
			if(currentCourse == null){
				JOptionPane.showMessageDialog(this, "Please select a course.", "Error", JOptionPane.ERROR_MESSAGE);
			}else{
				int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this course?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION){
					Database.deleteCourse(currentCourse.getCourseName());
					refreshContent();
				}
			}
		} else if(src == this.removeQuizButton){
			if(currentQuiz == null){
				JOptionPane.showMessageDialog(this, "Please select a quiz.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this quiz?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION){
				currentCourse.deleteQuiz(currentQuiz.getName());
				refreshContent();
			}			
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getValueIsAdjusting())
			return;

		JList list = (JList) arg0.getSource();

		if (list == courseList) {
			currentCourse = (Course) courseList.getSelectedValue();
			if (currentCourse != null) {
				quizList.setListData(getQuizNames(currentCourse));
			}
			this.quizControlPanel.setVisible(false);
		} else if (list == quizList) {
			if (currentCourse == null) {
				return;
			}
			List<Quiz> quizzes = currentCourse.getQuizs();
			for (Quiz quiz : quizzes) {
				if (quiz.getName() == (String) quizList.getSelectedValue()) {
					currentQuiz = quiz;
					break;
				}
			}

			this.quizControlPanel.setVisible(true);

			boolean isTeacher = currentUser.getType().equals("teacher");
			this.takeQuizButton.setVisible(!isTeacher);
			this.resultsList.setVisible(isTeacher);
			this.resultsLabel.setVisible(false);
			this.lowScoreLabel.setVisible(false);
			this.avgScoreLabel.setVisible(false);
			this.highScoreLabel.setVisible(false);
			if (isTeacher) {
				List<String> resultLabels = new ArrayList<String>();
				List<String> users = Database.getQuizTakers(currentCourse.getCourseName(), currentQuiz.getName());
				for (String username : users) {
					double score = Database.getStudentScore(currentCourse.getCourseName(), currentQuiz.getName(),
							username);
					resultLabels.add(String.format("%s's Score: %.2f%%", username, score));
				}
				if (resultLabels.size() == 0) {
					resultLabels.add("No students have taken this quiz");
				}else{
					this.lowScoreLabel.setVisible(true);
					this.lowScoreLabel.setText(String.format("Lowest Score %.2f", Database.getLowestScore(currentCourse.getCourseName(), currentQuiz.getName())));
					this.avgScoreLabel.setVisible(true);
					this.avgScoreLabel.setText(String.format("Median Score %.2f", Database.getMediumScore(currentCourse.getCourseName(), currentQuiz.getName())));
					this.highScoreLabel.setVisible(true);
					this.highScoreLabel.setText(String.format("Highest Score %.2f", Database.getHighestScore(currentCourse.getCourseName(), currentQuiz.getName())));
				}
				this.resultsList.setListData(resultLabels.toArray());
			} else {
				boolean hasTakenQuiz = Database.hasTakenQuiz(this.currentUser.getUsername(),
						this.currentQuiz.getName());
				if (hasTakenQuiz) {
					double score = Database.getStudentScore(currentCourse.getCourseName(), currentQuiz.getName(),
							currentUser.getUsername());
					this.resultsLabel.setText(String.format("Your Score %.2f%%", score));
				}
				this.takeQuizButton.setEnabled(!hasTakenQuiz);
				this.resultsLabel.setVisible(hasTakenQuiz);
			}
			this.courseNameLabel.setText(currentCourse.name);
			this.quizNameLabel.setText(currentQuiz.getName());
			this.revalidate();
		}

	}
}
