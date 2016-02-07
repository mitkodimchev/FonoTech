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

import mainPackage.Album;
import mainPackage.Member;
import mainPackage.Search;
import mainPackage.Test;

public class RemoveFromCollection extends JDialog {
	RemoveFromCollectionPanel removePanel;

	private static final long serialVersionUID = 1L;

	public RemoveFromCollection(JFrame parent) {
		super(parent, "Remove from collection", true);
		setSize(350, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		removePanel = new RemoveFromCollectionPanel(this);
		getContentPane().add(removePanel);
	}
}

class RemoveFromCollectionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JButton remove = new JButton("Remove album from collection");
	ButtonGroup group = new ButtonGroup();
	ArrayList<JRadioButton> albumButtons = new ArrayList<JRadioButton>();

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == remove) {
				for (JRadioButton selAlbum : albumButtons) {
					if (selAlbum.isSelected()) {
						String albumInfo = selAlbum.getText();
						String[] album = albumInfo.split(" - ");
						Album selectedAlbum = Search.searchAlbum(album[1], Search.searchArtist(album[2]));
						Member user = (Member) Test.getUser();
						user.getAlbumCollection().remove(new Integer(selectedAlbum.getId()));
						JOptionPane.showMessageDialog(null, "Album is removed from collection", "FonoTech",
								JOptionPane.INFORMATION_MESSAGE);
						dialog.setVisible(false);
						dialog.dispose();
					}
				}
			}
		}
	}

	RemoveFromCollectionPanel(JDialog dialog) {
<<<<<<< HEAD
		dialog.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
=======
>>>>>>> origin/master
		setBackground(new Color(167, 202, 255));
		Member user = (Member) Test.getUser();
		ArrayList<Integer> collection = user.getAlbumCollection();
		int i = 1;
		for (int albumID : collection) {
			Album album = Search.searchAlbumByID(albumID);
			if(album!=null){
			JRadioButton albumButton = new JRadioButton(
					i + " - " + album.getAlbumName() + " - " + album.getArtist().getName() + " - " + album.getYear());
			group.add(albumButton);
			albumButtons.add(albumButton);
			add(albumButton);
			albumButton.setBackground(new Color(167, 202, 255));
			i++;}
		}
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		remove.addActionListener(buttonEvent);
		add(remove);
	}
}
