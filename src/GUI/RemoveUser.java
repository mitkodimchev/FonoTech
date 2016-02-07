package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import mainPackage.Administrator;
import mainPackage.DataStorage;
import mainPackage.Member;
import mainPackage.Search;
import mainPackage.User;

public class RemoveUser extends JDialog {
	RemoveUserPanel removeUserPanel;

	private static final long serialVersionUID = 1L;

	public RemoveUser(JFrame parent) {
		super(parent, "Remove User", true);
		setSize(200, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		removeUserPanel = new RemoveUserPanel(this);
		getContentPane().add(removeUserPanel);
	}
}

class RemoveUserPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JButton remove = new JButton("Remove User");
	ButtonGroup group = new ButtonGroup();
	ArrayList<JRadioButton> userButtons = new ArrayList<JRadioButton>();

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == remove) {
				for (JRadioButton selUser : userButtons) {
					if (selUser.isSelected()) {
						String userInfo = selUser.getText();
						String[] user = userInfo.split(" - ");
						User userToRemove = Search.searchUserByName(user[0]);
						if (userToRemove == null) {
							JOptionPane.showMessageDialog(null, "User is not registered", "FonoTech", JOptionPane.INFORMATION_MESSAGE);
							dialog.setVisible(false);
							dialog.dispose();
							return;
						}
						DataStorage.getUsers().remove(userToRemove);
						JOptionPane.showMessageDialog(null, "User is removed", "FonoTech", JOptionPane.INFORMATION_MESSAGE);
						dialog.setVisible(false);
						dialog.dispose();
					}
				}
			}
		}
	}

	RemoveUserPanel(JDialog dialog) {
		dialog.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
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
		remove.addActionListener(buttonEvent);
		add(remove);
	}
}
