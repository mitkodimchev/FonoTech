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
import javax.swing.JScrollPane;

import mainPackage.Album;
import mainPackage.Artist;
import mainPackage.DataStorage;
import mainPackage.Member;
import mainPackage.Producer;
import mainPackage.Search;
import mainPackage.Test;

public class SelectForCollection extends JDialog {
	SelectForCollectionPanel selectPanel;

	private static final long serialVersionUID = 1L;

	public SelectForCollection(JFrame parent) {
		super(parent, "Select album", true);
		setSize(350, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		selectPanel = new SelectForCollectionPanel(this);
		getContentPane().add(selectPanel);
	}
}

class SelectForCollectionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JButton add = new JButton("Add album to collection");
	ButtonGroup group = new ButtonGroup();
	ArrayList<JRadioButton> albumButtons = new ArrayList<JRadioButton>();

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == add) {
				for (JRadioButton selAlbum : albumButtons) {
					if (selAlbum.isSelected()) {
						String albumInfo = selAlbum.getText();
						String[] album = albumInfo.split(" - ");
						Album selectedAlbum = Search.searchAlbum(album[1], Search.searchArtist(album[2]));
						Member user = (Member) Test.getUser();
						if (user.getAlbumCollection().contains(selectedAlbum.getId())) {
							JOptionPane.showMessageDialog(null, "Album is already added to collection", "FonoTech",
									JOptionPane.INFORMATION_MESSAGE);

						} else {
							user.addAlbumToCollection(selectedAlbum.getId());
							JOptionPane.showMessageDialog(null, "Album is added to collection", "FonoTech",
									JOptionPane.INFORMATION_MESSAGE);
							dialog.setVisible(false);
							dialog.dispose();
						}
				}
			}
		}
	}
	}
	SelectForCollectionPanel(JDialog dialog) {
<<<<<<< HEAD
		dialog.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
=======
>>>>>>> origin/master
	    JScrollPane scrollPane = new JScrollPane();
		setBackground(new Color(167, 202, 255));
		ArrayList<Producer> tempProd = DataStorage.getDatabase();
		ArrayList<Artist> tempArtist;
		ArrayList<Album> tempAlbum;
		int i = 1;
		for (Producer producer : tempProd) {
			tempArtist = producer.getArtistList();
			for (Artist artist : tempArtist) {
				tempAlbum = artist.getAlbums();
				for (Album album : tempAlbum) {
					JRadioButton albumButton = new JRadioButton(i + " - " + album.getAlbumName() + " - "
							+ album.getArtist().getName() + " - " + album.getYear());
					group.add(albumButton);
					albumButtons.add(albumButton);
					add(albumButton);
					albumButton.setBackground(new Color(167, 202, 255));
					i++;
				}

			}
		}
		this.dialog = dialog;
		dialog.add(scrollPane);
		ActionListener buttonEvent = new ButtonHandler();
		add.addActionListener(buttonEvent);
		add(add);
	}
}
