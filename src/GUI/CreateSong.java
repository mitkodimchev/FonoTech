package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mainPackage.Album;
import mainPackage.Artist;
import mainPackage.DurationFono;
import mainPackage.Producer;
import mainPackage.Search;
import mainPackage.Song;

public class CreateSong extends JDialog {
	CreateSongPanel createsongPanel;

	private static final long serialVersionUID = 1L;

	public CreateSong(JFrame parent, boolean isAlbum, Producer producerAlbum, Album album) {
		super(parent, "Create Song", true);
		setSize(270, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createsongPanel = new CreateSongPanel(this, isAlbum, producerAlbum, album);
		getContentPane().add(createsongPanel);

	}
}

class CreateSongPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	boolean isAlbumSong;
	Producer producer;
	Album albumSong;
	JDialog dialog;
	JTextField artist = new JTextField(20);
	JTextField songName = new JTextField(20);
	JTextField minutesF = new JTextField(2);
	JTextField secondsF = new JTextField(2);
	JTextField yearF = new JTextField(4);
	JTextField arrangementF = new JTextField(20);
	JTextField musicF = new JTextField(20);
	JTextField lyricsF = new JTextField(20);
	JCheckBox hasVideoB = new JCheckBox();
	JButton create = new JButton("Create song");

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == create) {
				Artist songArtist = null;
				if (isAlbumSong) {
					songArtist = albumSong.getArtist();
					artist.setEditable(false);
				} else {
					String artistName = artist.getText();
					songArtist = Search.searchArtist(artistName);
					if (songArtist == null) {
						int answer = JOptionPane.showConfirmDialog(null,
								"The artist does not exist in the system. Would you like to create it?",
								"Create artist", JOptionPane.YES_NO_OPTION);
						if (answer == JOptionPane.YES_OPTION) {
							CreateArtist crArtist = new CreateArtist(null);
							crArtist.setVisible(true);
						} else {
							dialog.setVisible(false);
							dialog.dispose();
						}
					}
					songArtist = Search.searchArtist(artistName);
				}
				String songTitle = songName.getText();
				String minutesS = minutesF.getText();
				String secondsS = secondsF.getText();
				String yearS = yearF.getText();
				long minutes = 0;
				long seconds = 0;
				int yearInt = 0;
				try {
					minutes = Long.parseLong(minutesS);
					seconds = Long.parseLong(secondsS);
					yearInt = Integer.parseInt(yearS);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error. Incorrect data.", "FonoTech",
							JOptionPane.INFORMATION_MESSAGE);
					dialog.setVisible(false);
					dialog.dispose();
					return;
				}
				if (minutes > 59 || seconds > 59 || yearInt>2016 || yearInt<1940) {
					JOptionPane.showMessageDialog(null, "Error. Incorrect data.", "FonoTech",
							JOptionPane.INFORMATION_MESSAGE);
					dialog.setVisible(false);
					dialog.dispose();
					return;
				}
				Duration length = DurationFono.durationOfSong(minutes, seconds);
				String arrangementBy = arrangementF.getText();
				String musicBy = musicF.getText();
				String lyricsBy = lyricsF.getText();
				boolean hasVideo = hasVideoB.isSelected();
				if (Search.searchSong(songTitle, songArtist) == null) {
					new Song(songArtist, songTitle, albumSong, length, yearInt, arrangementBy, lyricsBy, musicBy,
							hasVideo, producer);
					JOptionPane.showMessageDialog(null, "Song is created", "FonoTech", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Song is already created", "FonoTech",
							JOptionPane.INFORMATION_MESSAGE);
				}
				dialog.setVisible(false);
				dialog.dispose();
			}
		}
	}

	CreateSongPanel(JDialog dialog, boolean isAlbum, Producer producerAlbum, Album album) {
		dialog.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
		albumSong = album;
		isAlbumSong = isAlbum;
		producer = producerAlbum;
		setBackground(new Color(167, 202, 255));
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		create.addActionListener(buttonEvent);
		JCheckBox hasVideoB = new JCheckBox("Video");
		add(new JLabel("Name of artist: "));
		add(artist);
		add(new JLabel("Name of song: "));
		add(songName);
		add(new JLabel("Length: "));
		add(minutesF);
		add(new JLabel(":"));
		add(secondsF);
		add(new JLabel("Year: "));
		add(yearF);
		add(new JLabel("Arrangement by: "));
		add(arrangementF);
		add(new JLabel("Music by: "));
		add(musicF);
		add(new JLabel("Lyrics by: "));
		add(lyricsF);
		add(hasVideoB);
		add(create);
		if(isAlbumSong){
			artist.setEditable(false);
			artist.setText(album.getArtist().getName());
		}
	}
}
