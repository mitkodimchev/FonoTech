package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
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

public class AddSongToAlbum extends JDialog {
	AddSongToAlbumPanel addSongToAlbumPanel;

	private static final long serialVersionUID = 1L;

	public AddSongToAlbum(JFrame parent) {
		super(parent, "View Album", true);
		setSize(350, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addSongToAlbumPanel = new AddSongToAlbumPanel(this);
		getContentPane().add(addSongToAlbumPanel);
	}
}

class AddSongToAlbumPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JButton add = new JButton("Add song to");
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
						String songName = JOptionPane.showInputDialog (null, "Enter song name:");
						String songArtist = JOptionPane.showInputDialog (null, "Enter artist of song:");
						Artist artist = Search.searchArtist(songArtist);
						if(Search.searchSong(songName, artist)!=null){
							selectedAlbum.addSong(Search.searchSong(songName, artist));
							selectedAlbum.addToDuration(Search.searchSong(songName, artist).getLength());
						}
						else{
							boolean isAlbum = !selectedAlbum.getArtist().getName().equals("Various artists");
							CreateSong crSong = new CreateSong(null, isAlbum, selectedAlbum.getProducer(), selectedAlbum);
							crSong.setVisible(true);
						}
						dialog.setVisible(false);
						dialog.dispose();
					}
				}
			}
		}
	}

	AddSongToAlbumPanel(JDialog dialog) {
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
		add.addActionListener(buttonEvent);
		add(add);
	}
}
