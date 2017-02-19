package UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Login extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public Login() {
		setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(196, 67, 53, 16);
		add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(132, 93, 78, 16);
		add(lblUsername);
		
		textField = new JTextField();
		textField.setBounds(218, 88, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(142, 121, 61, 16);
		add(lblPassword);
		
		textField_1 = new JTextField();
		textField_1.setBounds(218, 116, 130, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.setBounds(112, 170, 117, 29);
		add(btnCreate);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(244, 170, 117, 29);
		add(btnLogin);


	}

}
