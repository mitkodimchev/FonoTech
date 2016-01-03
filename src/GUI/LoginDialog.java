package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mainPackage.PasswordEncryption;
import mainPackage.Search;

public class LoginDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	DialogPanel dialogPanel;

	public LoginDialog(JFrame parent) {
		super(parent, "Login", true);
		setSize(260, 150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialogPanel = new DialogPanel(this);
		getContentPane().add(dialogPanel);
	}
}

class DialogPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JTextField user = new JTextField(10);
	JTextField pass = new JPasswordField(10);
	JButton login = new JButton("Login");

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == login) {
				String username = user.getText();
				String passwordToEncrypt = pass.getText();
				String password = PasswordEncryption.passEncryption(passwordToEncrypt);
				if (Search.searchUser(username, password) == null)
					System.out.println("Грешен потребител или парола.");
				else {
					LogRegMenu.user = Search.searchUser(username, password);
					System.out.println("Успешно влизане в системата.");
					dialog.setVisible(false);
				}
				
			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	DialogPanel(JDialog dialog) {
		setBackground(new Color(167,202,255));
		this.dialog = dialog;
		ButtonHandler b = new ButtonHandler();
		login.addActionListener(b);
		add(new JLabel("Username: "));
		add(user);
		add(new JLabel("Password:"));
		add(pass);
		add(login);
	}
	  

}
