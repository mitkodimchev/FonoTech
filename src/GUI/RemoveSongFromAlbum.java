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
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import mainPackage.Album;
import mainPackage.Artist;
import mainPackage.DataStorage;
import mainPackage.Producer;
import mainPackage.Search;

public class RemoveSongFromAlbum extends JDialog {
	RemoveSongFromAlbumPanel removeSongFromAlbumPanel;

	private static final long serialVersionUID = 1L;

	public RemoveSongFromAlbum(JFrame parent) {
		super(parent, "View Album", true);
		setSize(350, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		removeSongFromAlbumPanel = new RemoveSongFromAlbumPanel(this);
		getContentPane().add(removeSongFromAlbumPanel);
	}
}

class RemoveSongFromAlbumPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JButton remove = new JButton("Remove song from");
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
						RemoveSongFromAlbumComp rem = new RemoveSongFromAlbumComp(null, selectedAlbum);
						rem.setVisible(true);
						dialog.setVisible(false);
						dialog.dispose();
					}
				}
			}
		}
	}

	RemoveSongFromAlbumPanel(JDialog dialog) {
<<<<<<< HEAD
		dialog.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
=======
>>>>>>> origin/master
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
		ActionListener buttonEvent = new ButtonHandler();
		remove.addActionListener(buttonEvent);
		add(remove);
	}
}
