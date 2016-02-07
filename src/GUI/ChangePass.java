package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import mainPackage.Search;
import mainPackage.Test;

class ChangePass extends JDialog {
	
	ChangePassDialog changePassPanel;

	JTextField user = new JTextField(10);
		
	
	private static final long serialVersionUID = 1L;

	public ChangePass(JFrame parent) {
		super(parent, "Change password", true);
		setSize(280, 180);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		changePassPanel = new ChangePassDialog(this);
		getContentPane().add(changePassPanel);
	}
	
	class ChangePassDialog extends JPanel {
		
		private static final long serialVersionUID = 1L;

		JDialog dialog;
		JTextField oldPass = new JPasswordField(10);
		JTextField newPass = new JPasswordField(10);
		JTextField newPass2 = new JPasswordField(10);
		JButton change = new JButton("Change password");

		class ButtonHandler implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == change) {	
					String passwordOld = oldPass.getText();
					String passwordNew = newPass.getText();
					String passwordNew2 = newPass2.getText();
					if(passwordNew.equals(passwordNew2)){
						if((Search.searchUser(Test.getUser().getUsername(), passwordOld)!=null)){
							Test.getUser().setPassword(passwordNew);
							JOptionPane.showMessageDialog(null, "The password is changed", "FonoTech", JOptionPane.INFORMATION_MESSAGE);
							dialog.setVisible(false);
							dialog.dispose();}
					}
				}
			}
		}
		
		ChangePassDialog(JDialog dialog) {
		dialog.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
		setBackground(new Color(167,202,255));
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		change.addActionListener(buttonEvent);
		add(new JLabel("Username: "));
		add(user);
		add(new JLabel("Old password: "));
		add(oldPass);
		add(new JLabel("New password: "));
		add(newPass);
		add(new JLabel("Repeat new password: "));
		add(newPass2);
		add(change);
	}
	}


}
