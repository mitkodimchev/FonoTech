
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

import mainPackage.DataStorage;
import mainPackage.Member;
import mainPackage.PasswordEncryption;
import mainPackage.Search;
import mainPackage.User;

public class RegDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	DialogRegPanel dialogPanel;

	public RegDialog(JFrame parent) {
		super(parent, "Register", true);
		setSize(260, 170);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialogPanel = new DialogRegPanel(this);
		getContentPane().add(dialogPanel);
	}
}

class DialogRegPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JTextField userField = new JTextField(10);
	JTextField passField = new JPasswordField(10);
	JTextField passField2 = new JPasswordField(10);
	JButton reg = new JButton("Register");

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == reg) {
				String username = userField.getText();
				String passwordToEncrypt = passField.getText();
				String password = PasswordEncryption.passEncryption(passwordToEncrypt);
				String passwordToEncrypt2 = passField2.getText();
				String password2 = PasswordEncryption.passEncryption(passwordToEncrypt2);
				if (password.equals(password2)) {
					if (Search.checkUsername(username)) {
						User user = new Member(username, password);
						DataStorage.storeUser(user);
						LogRegMenu.user = user;
						dialog.setVisible(false);
					} else {
						System.out.println("Съществуващо потребителско име.");
					}
				}

			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	DialogRegPanel(JDialog dialog) {
		setBackground(new Color(167,202,255));
		this.dialog = dialog;
		ButtonHandler b = new ButtonHandler();
		reg.addActionListener(b);
		add(new JLabel("Username: "));
		add(userField);
		add(new JLabel("Password: "));
		add(passField);
		add(new JLabel("Confirm password: "));
		add(passField2);
		add(reg);
	}

}
