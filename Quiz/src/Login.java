import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

public class Login extends JPanel implements ActionListener {
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	
	private JLabel errorLabel;
	
	private App app;
	
	private static final int OFFSET_X = 304;
	private static final int OFFSET_Y = 150;
	
	/**
	 * Create the panel.
	 */
	public Login(App app) {
		
		this.app = app;
		
		setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(215 + OFFSET_X, 67 + OFFSET_Y, 53, 16);
		add(lblLogin);
		
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(132 + OFFSET_X, 93 + OFFSET_Y, 78, 16);
		add(lblUsername);
		
		usernameTextField = new JTextField(10);
		usernameTextField.setBounds(218 + OFFSET_X, 88 + OFFSET_Y, 130, 26);
		add(usernameTextField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(142 + OFFSET_X, 121 + OFFSET_Y, 61, 16);
		add(lblPassword);
		
		passwordTextField = new JPasswordField(10);
		passwordTextField.setBounds(218 + OFFSET_X, 116 + OFFSET_Y, 130, 26);
		add(passwordTextField);
		
		errorLabel = new JLabel("");
		errorLabel.setBounds(112 + OFFSET_X, 147 + OFFSET_Y, 200, 16);
		errorLabel.setForeground(Color.red);
		add(errorLabel);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(112 + OFFSET_X, 170 + OFFSET_Y, 117, 29);
		btnCreate.addActionListener(this);
		add(btnCreate);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(244 + OFFSET_X, 170 + OFFSET_Y, 117, 29);
		btnLogin.addActionListener(this);
		add(btnLogin);

	}
	
	private void setErrorMessage(String msg){
		errorLabel.setText(msg);
	}
	
	private void clearErrorMessage(){
		errorLabel.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String actionCommand = e.getActionCommand();
		if(actionCommand == "Login"){
			
			String username = usernameTextField.getText();
			String password = String.valueOf(passwordTextField.getPassword());
			if(username.length() == 0 || password.length() == 0){
				setErrorMessage("Not all input fields were filled out.");
				return;
			}
			
			if(Database.isValidUser(username, password)) {
				System.out.printf("Login %s %s\n", username, password);
				clearErrorMessage();
				String type = Database.getUserType(username);
				if (type.equals("student")) {
					app.setActiveUser(new Student(username, password, type));
				} else if (type.equals("teacher")) {
					app.setActiveUser(new Teacher(username, password, type));
				}
				this.usernameTextField.setText("");
				this.passwordTextField.setText("");
			} else {
				setErrorMessage("Username or Password is invalid.");
			}
		}else if(actionCommand == "Create"){
			app.createAccount();
		}
	}

}
