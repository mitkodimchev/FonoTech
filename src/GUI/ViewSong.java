package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import mainPackage.Artist;
import mainPackage.DurationFono;
import mainPackage.Search;
import mainPackage.Song;

public class ViewSong extends JDialog {
	ViewSongPanel viewSongPanel;

	private static final long serialVersionUID = 1L;

	public ViewSong(JFrame parent) {
		super(parent, "View Song", true);
		setSize(350, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewSongPanel = new ViewSongPanel(this);
		getContentPane().add(viewSongPanel);
	}
}

class ViewSongPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JButton view = new JButton("View Song");
	JTextField artist = new JTextField(10);
	JTextField song = new JTextField(10);
	ButtonGroup group = new ButtonGroup();
	ArrayList<JRadioButton> SongButtons = new ArrayList<JRadioButton>();

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == view) {
				String songName = song.getText();
				String artistName = artist.getText();
				Artist artistTemp = Search.searchArtist(artistName);
				if (artistTemp == null) {
					JOptionPane.showMessageDialog(null, "Wrong artist", "Wrong input data",
							JOptionPane.INFORMATION_MESSAGE);
					dialog.setVisible(false);
					dialog.dispose();
					return;
				}
				Song songTemp = Search.searchSong(songName, artistTemp);
				if (songTemp == null) {
					JOptionPane.showMessageDialog(null, "Wrong song name", "Wrong input data",
							JOptionPane.INFORMATION_MESSAGE);
					dialog.setVisible(false);
					dialog.dispose();
				} else {
					StringBuilder songData = new StringBuilder(songTemp.getSongArtist().getName() + " - "
							+ songTemp.getSongTitle() + " - " + DurationFono.durationToString(songTemp.getLength()));
					if (songTemp.isHasVideo()) {
						songData.append(" : Video\n");
					} else {
						songData.append("\n");
					}
					songData.append(
							" \t Arrangement: " + songTemp.getArrangementBy() + " / Composer: " + songTemp.getMusicBy()
									+ " / Lyrics: " + songTemp.getLyricsBy() + " : " + songTemp.getYear() + "\n");
					JOptionPane.showMessageDialog(null, songData, "Song info",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}

	ViewSongPanel(JDialog dialog) {
		setBackground(new Color(167, 202, 255));
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		view.addActionListener(buttonEvent);
		add(new JLabel("Artist: "));
		add(artist);
		add(new JLabel("Song:"));
		add(song);
		add(view);
	}
}
