package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mainPackage.DurationFono;
import mainPackage.Search;
import mainPackage.Song;

public class EditSongComponents extends JDialog {
	EditSongComponentsPanel EditSongComponentsPanel;

	private static final long serialVersionUID = 1L;

	public EditSongComponents(JFrame parent, Song Song) {
		super(parent, "Edit Song", true);
		setSize(250, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		EditSongComponentsPanel = new EditSongComponentsPanel(this, Song);
		getContentPane().add(EditSongComponentsPanel);
	}
}

class EditSongComponentsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	Song editedSong;
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
	JButton edit = new JButton("Edit Song");

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == edit) {
				editedSong.setSongTitle(songName.getText());
				if (Search.searchArtist(artist.getText()) != null)
					editedSong.setSongArtist(Search.searchArtist(artist.getText()));
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
				if (minutes > 59 || seconds > 59) {
					JOptionPane.showMessageDialog(null, "Error. Incorrect data.", "FonoTech",
							JOptionPane.INFORMATION_MESSAGE);
					dialog.setVisible(false);
					dialog.dispose();
					return;
				}
				editedSong.setLength(DurationFono.durationOfSong(minutes, seconds));
				editedSong.setYear(yearInt);
				editedSong.setArrangementBy(arrangementF.getText());
				editedSong.setMusicBy(musicF.getText());
				editedSong.setLyricsBy(lyricsF.getText());
				editedSong.setHasVideo(hasVideoB.isSelected());
			}
			dialog.setVisible(false);
			dialog.dispose();
		}
	}

	EditSongComponentsPanel(JDialog dialog, Song song) {
		dialog.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
		editedSong = song;
		setBackground(new Color(167, 202, 255));
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		edit.addActionListener(buttonEvent);
		JCheckBox hasVideoB = new JCheckBox("Video");
		artist.setText(song.getSongArtist().getName());
		songName.setText(song.getSongTitle());
		minutesF.setText(String.valueOf(song.getLength().toMinutes()));
		secondsF.setText(String.valueOf(song.getLength().minusMinutes(song.getLength().toMinutes()).getSeconds()));
		arrangementF.setText(song.getArrangementBy());
		musicF.setText(song.getMusicBy());
		lyricsF.setText(song.getLyricsBy());
		yearF.setText(String.valueOf(song.getYear()));
		if(song.isHasVideo()){
			hasVideoB.setSelected(true);
		}
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
		add(edit);
	}
}
