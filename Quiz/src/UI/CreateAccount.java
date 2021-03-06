package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Server.Database;
import Models.Student;
import Models.Teacher;

//The GUI panel to create an account
public class CreateAccount extends JPanel implements ActionListener {
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JPasswordField confirmPasswordTextField;

	private JComboBox accountTypeComboBox;
	
	private JLabel errorLabel;
	
	private JPanel mainPanel;
	
	private App app;
	
	/**
	 * Create the panel.
	 */
	public CreateAccount(App app) {
		
		this.app = app;
		
		setLayout(new GridBagLayout());
		
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setPreferredSize(new Dimension(310, 300));
		add(mainPanel, new GridBagConstraints());

		//Create account header
		JLabel lblNewLabel = new JLabel("Create Account");
		lblNewLabel.setBounds(95, 18, 120, 16);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblNewLabel);
		
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(46, 73, 73, 16);
		mainPanel.add(lblUsername);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(131, 68, 170, 26);
		mainPanel.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(46, 113, 64, 16);
		mainPanel.add(lblPassword);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(131, 106, 170, 26);
		mainPanel.add(passwordTextField);
		passwordTextField.setColumns(10);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(0, 153, 114, 16);
		mainPanel.add(lblConfirmPassword);
		
		confirmPasswordTextField = new JPasswordField();
		confirmPasswordTextField.setBounds(131, 144, 170, 26);
		mainPanel.add(confirmPasswordTextField);
		confirmPasswordTextField.setColumns(10);
		
		String[] component= {"Select","Teacher","Student"} ;
		accountTypeComboBox = new JComboBox(component);
		accountTypeComboBox.setMaximumRowCount(3);
		accountTypeComboBox.setToolTipText("");
		accountTypeComboBox.setBounds(131, 180, 170, 26);
		mainPanel.add(accountTypeComboBox);
		
		errorLabel = new JLabel("");
		errorLabel.setBounds(0, 220, 310, 16);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setForeground(Color.red);
		mainPanel.add(errorLabel);
		
		JButton create = new JButton("Create");
		JButton cancel = new JButton("Cancel");
		create.addActionListener(this);
		cancel.addActionListener(this);
		create.setBounds(55, 250, 120, 30);
		cancel.setBounds(190, 250, 120, 30);
		mainPanel.add(create);
		mainPanel.add(cancel);
	}

	//Handle button presses
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String actionCommand = e.getActionCommand();
		if(actionCommand == "Create"){
			
			String username = usernameTextField.getText();
			String password = passwordTextField.getText();
			String confirmPassword = confirmPasswordTextField.getText();
			String accountType = (String) accountTypeComboBox.getSelectedItem();
			
			if(username == null || username.isEmpty()){
				errorLabel.setText("Username field is empty");
			} else if(password == null || password.isEmpty()){
				errorLabel.setText("Password field is empty");
			}else if(!password.equals(confirmPassword)){
				errorLabel.setText("Password fields do not match");
			} else if(accountType == "Select"){
				errorLabel.setText("Account type not selected");
			} else if (Database.isUserExist(username)){
				errorLabel.setText("Account:" + username +" is existing");
			} else {
				Database.createAccount(username, password, accountType.toLowerCase());
				accountType = accountType.toLowerCase();
				if (accountType.equals("student")) {
					app.setActiveUser(new Student(username, password, accountType));
				} else if (accountType.equals("teacher")) {
					app.setActiveUser(new Teacher(username, password, accountType));
				}
				System.out.printf("%s Account: %s is created", accountType, username);
				errorLabel.setText("");
			}
		}
		if(actionCommand == "Cancel") {
			app.setActiveUser(null);
		}
	}
}