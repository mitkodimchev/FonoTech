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
import mainPackage.Search;
import mainPackage.Song;

public class RemoveSong extends JDialog {
	RemoveSongPanel removeSongPanel;

	private static final long serialVersionUID = 1L;

	public RemoveSong(JFrame parent) {
		super(parent, "Remove Song", true);
		setSize(350, 100);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		removeSongPanel = new RemoveSongPanel(this);
		getContentPane().add(removeSongPanel);
	}
}

class RemoveSongPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JButton remove = new JButton("Remove Song");
	JTextField artist = new JTextField(10);
	JTextField song = new JTextField(10);
	ButtonGroup group = new ButtonGroup();
	ArrayList<JRadioButton> SongButtons = new ArrayList<JRadioButton>();

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == remove) {
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
					songTemp.getAlbum().getSongs().remove(songTemp);
					JOptionPane.showMessageDialog(null, "Song is removed", "Remove song",
							JOptionPane.INFORMATION_MESSAGE);
					dialog.setVisible(false);
					dialog.dispose();
				}
			}
		}
	}

	RemoveSongPanel(JDialog dialog) {
		setBackground(new Color(167, 202, 255));
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		remove.addActionListener(buttonEvent);
		add(new JLabel("Artist: "));
		add(artist);
		add(new JLabel("Song:"));
		add(song);
		add(remove);
	}
}
