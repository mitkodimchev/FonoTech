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
import mainPackage.DurationFono;
import mainPackage.Search;
import mainPackage.Song;

public class RemoveSongFromAlbumComp extends JDialog {
	RemoveSongFromAlbumCompPanel removeSongFromAlbumCompPanel;

	private static final long serialVersionUID = 1L;

	public RemoveSongFromAlbumComp(JFrame parent, Album album) {
		super(parent, "View Album", true);
		setSize(350, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		removeSongFromAlbumCompPanel = new RemoveSongFromAlbumCompPanel(this, album);
		getContentPane().add(removeSongFromAlbumCompPanel);
	}
}

class RemoveSongFromAlbumCompPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JButton remove = new JButton("Remove song from");
	ButtonGroup group = new ButtonGroup();
	Album albumRem;
	ArrayList<JRadioButton> songButtons = new ArrayList<JRadioButton>();

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == remove) {
				for (JRadioButton selSong : songButtons) {
					if (selSong.isSelected()) {
						String songInfo = selSong.getText();
						String[] song = songInfo.split(" - ");
						Song selectedSong = Search.searchSong(song[2],Search.searchArtist(song[1]));
						albumRem.getSongs().remove(selectedSong);
						dialog.setVisible(false);
						dialog.dispose();
						JOptionPane.showMessageDialog(null, "Song is removed", songInfo,
								JOptionPane.INFORMATION_MESSAGE);

					}
				}
			}
		}
	}

	RemoveSongFromAlbumCompPanel(JDialog dialog, Album album) {
<<<<<<< HEAD
		dialog.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
=======
>>>>>>> origin/master
		setBackground(new Color(167, 202, 255));
		albumRem = album;
		int i = 1;
		ArrayList<Song> songList = albumRem.getSongs();
		for (Song songTemp : songList) {
				JRadioButton songButton = new JRadioButton(i + " - " + songTemp.getSongArtist().getName() + " - "
<<<<<<< HEAD
							+ songTemp.getSongTitle() + " - " + DurationFono.toString(songTemp.getLength()));
=======
							+ songTemp.getSongTitle() + " - " + DurationFono.durationToString(songTemp.getLength()));
>>>>>>> origin/master
					group.add(songButton);
					songButtons.add(songButton);
					add(songButton);
					songButton.setBackground(new Color(167, 202, 255));
					i++;
				}
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		remove.addActionListener(buttonEvent);
		add(remove);
	}
}
