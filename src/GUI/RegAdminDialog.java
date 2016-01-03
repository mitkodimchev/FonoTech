
package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import mainPackage.Administrator;
import mainPackage.DataStorage;
import mainPackage.Member;
import mainPackage.PasswordEncryption;
import mainPackage.Search;
import mainPackage.User;

public class RegAdminDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	DialogRegAdminPanel dialogPanel;

	public RegAdminDialog(JFrame parent) {
		super(parent, "RegAdminister", true);
		setSize(270, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dialogPanel = new DialogRegAdminPanel(this);
		getContentPane().add(dialogPanel);
	}
}

class DialogRegAdminPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JRadioButton member = new JRadioButton("Member");
	JRadioButton admin = new JRadioButton("Administrator");
	JTextField userField = new JTextField(10);
	JTextField passField = new JPasswordField(10);
	JTextField passField2 = new JPasswordField(10);
	JButton RegAdmin = new JButton("Register");

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == RegAdmin) {
				User user = null;
				String username = userField.getText();
				String passwordToEncrypt = passField.getText();
				String password = PasswordEncryption.passEncryption(passwordToEncrypt);
				String passwordToEncrypt2 = passField2.getText();
				String password2 = PasswordEncryption.passEncryption(passwordToEncrypt2);
				if (password.equals(password2)) {
					if (Search.checkUsername(username)) {
						if(member.isSelected())
							user = new Member(username, password);
						if(admin.isSelected())
							user=new Administrator(username, password);
						DataStorage.storeUser(user);
						JOptionPane.showMessageDialog(null, "User is registered", "FonoTech", JOptionPane.INFORMATION_MESSAGE);
						dialog.setVisible(false);
						dialog.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Already registered", "FonoTech", JOptionPane.INFORMATION_MESSAGE);
						dialog.setVisible(false);
						dialog.dispose();
					}
				}

			}
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	DialogRegAdminPanel(JDialog dialog) {
		setBackground(new Color(167,202,255));
		ButtonGroup group = new ButtonGroup();
		group.add(member);
		group.add(admin);
		member.setSelected(true);
		this.dialog = dialog;
		ButtonHandler b = new ButtonHandler();
		RegAdmin.addActionListener(b);
		add(member);
		member.setBackground(new Color(167, 202, 255));
		add(admin);
		admin.setBackground(new Color(167, 202, 255));
		add(new JLabel("Username: "));
		add(userField);
		add(new JLabel("Password: "));
		add(passField);
		add(new JLabel("Confirm password: "));
		add(passField2);
		add(RegAdmin);
	}

}
