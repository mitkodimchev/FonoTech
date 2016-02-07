package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mainPackage.DataStorage;
import mainPackage.Member;
import mainPackage.Test;

class MemberMenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public MemberMenuFrame() {
		setTitle("FonoTech");
		setSize(800, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}

class MemberMenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JMenuItem save;
	JMenuItem exit;
	JMenuItem changePass;
	JButton addToCollection = new JButton("Add to collection");;
	JButton removeFromCollection = new JButton("Remove from collection");
	JButton viewCollection = new JButton("View collection");
	JButton viewLibrary = new JButton("View music library");

	ActionListener b = new ButtonHandler();

	MemberMenuPanel(JFrame myframe) {
		setBackground(new Color(167,202,255));
		JMenuBar mb = new JMenuBar();
		JMenu userMenu = new JMenu(Test.getUser().getUsername());
		mb.add(userMenu);
		changePass = userMenu.add("Change password");
		userMenu.addSeparator();
		save = userMenu.add("Save");
		userMenu.addSeparator();
		exit = userMenu.add("Exit");
		ButtonHandler b = new ButtonHandler();
		addToCollection.addActionListener(b);
		removeFromCollection.addActionListener(b);
		viewCollection.addActionListener(b);
		viewLibrary.addActionListener(b);
		changePass.addActionListener(b);
		save.addActionListener(b);
		exit.addActionListener(b);
		myframe.setJMenuBar(mb);
		add(addToCollection);
		add(removeFromCollection);
		add(viewCollection);
		add(viewLibrary);
	}

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == save) {
				try {
					DataStorage.saveUserData((Member) Test.getUser());
					JOptionPane.showMessageDialog(null, "Database is saved", "FonoTech",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else if (e.getSource() == changePass) {
				ChangePass cngPass = new ChangePass(null);
				cngPass.setVisible(true);
			} else if (e.getSource() == exit) {
				int answer = JOptionPane.showConfirmDialog(null, "Do you really want to exit?", "Exit",
						JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					MemberMenu.setClose(true);
				} else {

				}

			} else if (e.getSource() == addToCollection) {
				Object[] options = { "Select from list", "Manually" };
				int choice = JOptionPane.showOptionDialog(null, "How do you want to insert album?",
						"Add album to collection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						options, options[0]);
				if (choice == JOptionPane.YES_OPTION) {
					SelectForCollection select = new SelectForCollection(null);
					select.setVisible(true);
				} else if (choice == JOptionPane.NO_OPTION){
					AddAlbumToCollection addToCollection = new AddAlbumToCollection(null);
					addToCollection.setVisible(true);
				}
			} else if (e.getSource() == removeFromCollection) {
				RemoveFromCollection removeFromCollection = new RemoveFromCollection(null);
				removeFromCollection.setVisible(true);
			} else if (e.getSource() == viewCollection) {
				ViewCollection viewCollection = new ViewCollection(null);
				viewCollection.setVisible(true);
			} else if (e.getSource() == viewLibrary) {
				ViewLibrary viewLibrary = new ViewLibrary(null);
				viewLibrary.setVisible(true);
			}
		}
	}
}

public class MemberMenu {
	static MemberMenuFrame myframe;
	static boolean close = false;

	public static void MenuStart() {
		myframe = new MemberMenuFrame();
		myframe.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
		MemberMenuPanel mypanel = new MemberMenuPanel(myframe);
		Container contentPane = myframe.getContentPane();
		contentPane.add(mypanel);
		myframe.setVisible(true);
		do {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!close);
		myframe.dispose();
	}

	public static void setClose(boolean close) {
		MemberMenu.close = close;
	}

	public static MemberMenuFrame getMyframe() {
		return myframe;
	}

}
