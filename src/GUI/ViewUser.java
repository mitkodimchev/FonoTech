package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
<<<<<<< HEAD
import javax.swing.ImageIcon;
=======
>>>>>>> origin/master
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import mainPackage.Administrator;
import mainPackage.DataStorage;
import mainPackage.Member;
import mainPackage.User;

public class ViewUser extends JDialog {
	ViewUserPanel viewUserPanel;

	private static final long serialVersionUID = 1L;

	public ViewUser(JFrame parent) {
		super(parent, "View User", true);
		setSize(200, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewUserPanel = new ViewUserPanel(this);
		getContentPane().add(viewUserPanel);
	}
}

class ViewUserPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JButton view = new JButton("View User");
	ButtonGroup group = new ButtonGroup();
	ArrayList<JRadioButton> userButtons = new ArrayList<JRadioButton>();

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == view) {
				for (JRadioButton selUser : userButtons) {
					if (selUser.isSelected()) {
						String userInfo = selUser.getText();
						String[] user = userInfo.split(" - ");
						String userData = new String("User: " + user[0] + "\nType:" + user[1]);
						JOptionPane.showMessageDialog(null, userData, "Username", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}

	ViewUserPanel(JDialog dialog) {
<<<<<<< HEAD
		dialog.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
=======
>>>>>>> origin/master
		setBackground(new Color(167, 202, 255));
		ArrayList<User> users = DataStorage.getUsers();
		for (User currentUser : users) {
			if (currentUser != null) {
				String type = null;
				if (currentUser instanceof Member)
					type = "Member";
				if (currentUser instanceof Administrator)
					type = "Administrator";
				JRadioButton userButton = new JRadioButton(currentUser.getUsername() + " - " + type);
				group.add(userButton);
				userButtons.add(userButton);
				add(userButton);
				userButton.setBackground(new Color(167, 202, 255));
			}
		}
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		view.addActionListener(buttonEvent);
		add(view);
	}
}
