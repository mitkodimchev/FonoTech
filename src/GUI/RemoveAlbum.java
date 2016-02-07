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
import mainPackage.Artist;
import mainPackage.DataStorage;
import mainPackage.Producer;
import mainPackage.Search;

public class RemoveAlbum extends JDialog {
	RemoveAlbumPanel removePanel;

	private static final long serialVersionUID = 1L;

	public RemoveAlbum(JFrame parent) {
		super(parent, "Remove Album", true);
		setSize(350, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		removePanel = new RemoveAlbumPanel(this);
		getContentPane().add(removePanel);
	}
}

class RemoveAlbumPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JButton remove = new JButton("Remove album");
	ButtonGroup group = new ButtonGroup();
	ArrayList<JRadioButton> albumButtons = new ArrayList<JRadioButton>();

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == remove) {
				for (JRadioButton selAlbum : albumButtons) {
					if (selAlbum.isSelected()) {
						Artist artist;
						String albumInfo = selAlbum.getText();
						String[] album = albumInfo.split(" - ");
						Album selectedAlbum = Search.searchAlbum(album[1], Search.searchArtist(album[2]));
						if(album[2]=="Various artists"){
							artist = Search.searchVA(album[2], selectedAlbum.getProducer());
							artist.getAlbums().remove(selectedAlbum);
						}else{
							artist = Search.searchArtist(album[2]);
							artist.getAlbums().remove(selectedAlbum);
						}
						JOptionPane.showMessageDialog(null, "Album is removed from collection", "FonoTech",
								JOptionPane.INFORMATION_MESSAGE);
						dialog.setVisible(false);
						dialog.dispose();
					}
				}
			}
		}
	}

	RemoveAlbumPanel(JDialog dialog) {
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
