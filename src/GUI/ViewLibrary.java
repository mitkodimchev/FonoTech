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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import mainPackage.Album;
import mainPackage.Artist;
import mainPackage.DataStorage;
import mainPackage.DurationFono;
import mainPackage.Producer;
import mainPackage.Search;
import mainPackage.Song;

public class ViewLibrary extends JDialog {
	ViewLibraryPanel viewLibraryPanel;

	private static final long serialVersionUID = 1L;

	public ViewLibrary(JFrame parent) {
		super(parent, "View Album", true);
		setSize(350, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewLibraryPanel = new ViewLibraryPanel(this);
		getContentPane().add(viewLibraryPanel);
	}
}

class ViewLibraryPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JButton view = new JButton("View Album");
	ButtonGroup group = new ButtonGroup();
	ArrayList<JRadioButton> albumButtons = new ArrayList<JRadioButton>();

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == view) {
				for (JRadioButton selAlbum : albumButtons) {
					if (selAlbum.isSelected()) {
						String albumInfo = selAlbum.getText();
						String[] album = albumInfo.split(" - ");
						Album selectedAlbum = Search.searchAlbum(album[1], Search.searchArtist(album[2]));
						StringBuilder albumData = new StringBuilder(" Album name:" + selectedAlbum.getAlbumName()
								+ "\n Artist of album:" + selectedAlbum.getArtist().getName() + "\n Duration:"
								+ DurationFono.durationToString(selectedAlbum.getLength()) + "\n Year:"
								+ selectedAlbum.getYear() + "\n Producer:" + selectedAlbum.getProducer().getName()
								+ "\n\n");
						int num = 1;
						for (Song currentSong : selectedAlbum.getSongs()) {
							albumData.append(" " + num + ". " + currentSong.getSongArtist().getName() + " - "
									+ currentSong.getSongTitle() + " - "
									+ DurationFono.durationToString(currentSong.getLength()));
							if (currentSong.isHasVideo()) {
								albumData.append(" : Video\n");
							} else {
								albumData.append("\n");
							}
							albumData.append(" \t Arrangement: " + currentSong.getArrangementBy() + " / Composer: "
									+ currentSong.getMusicBy() + " / Lyrics:" + currentSong.getLyricsBy() + " : "
									+ currentSong.getYear() + "\n");
							num++;
						}
						JTextArea textArea = new JTextArea(30, 70);
						textArea.setText(albumData.toString());
						textArea.setCaretPosition(0);
						textArea.setEditable(false);
						JScrollPane scrollPane = new JScrollPane(textArea);
						JOptionPane.showMessageDialog(null, scrollPane, albumInfo, JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}

	ViewLibraryPanel(JDialog dialog) {
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
		view.addActionListener(buttonEvent);
		add(view);
	}
}
