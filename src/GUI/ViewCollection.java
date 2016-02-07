package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
=======

import javax.swing.ButtonGroup;
>>>>>>> origin/master
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
import mainPackage.DurationFono;
import mainPackage.Member;
import mainPackage.Search;
import mainPackage.Song;
import mainPackage.Test;

public class ViewCollection extends JDialog {
	ViewCollectionPanel viewCollectionPanel;

	private static final long serialVersionUID = 1L;

	public ViewCollection(JFrame parent) {
		super(parent, "View Collection", true);
		setSize(350, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewCollectionPanel = new ViewCollectionPanel(this);
		getContentPane().add(viewCollectionPanel);
	}
}

class ViewCollectionPanel extends JPanel {

	private static final long serialVersionUID = 1L;
<<<<<<< HEAD
	JDialog dialog;
	Member user = (Member) Test.getUser();
	ArrayList<Integer> collection = user.getAlbumCollection();
=======

	JDialog dialog;
	Member user = (Member) Test.getUser();
>>>>>>> origin/master
	JButton view = new JButton("View Album");
	JButton searchByYear = new JButton("Search by year");
	JButton searchByLength = new JButton("Search by length");
	JButton songsByArtist = new JButton("Songs by artist");
<<<<<<< HEAD
	JButton randomAlbum = new JButton("Suggest me an album");
=======
>>>>>>> origin/master
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
<<<<<<< HEAD
								+ DurationFono.toString(selectedAlbum.getLength()) + "\n Year:"
=======
								+ DurationFono.durationToString(selectedAlbum.getLength()) + "\n Year:"
>>>>>>> origin/master
								+ selectedAlbum.getYear() + "\n Producer:" + selectedAlbum.getProducer().getName()
								+ "\n\n");
						int num = 1;
						for (Song currentSong : selectedAlbum.getSongs()) {
							albumData.append(" " + num + ". " + currentSong.getSongArtist().getName() + " - "
									+ currentSong.getSongTitle() + " - "
<<<<<<< HEAD
									+ DurationFono.toString(currentSong.getLength()));
=======
									+ DurationFono.durationToString(currentSong.getLength()));
>>>>>>> origin/master
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
<<<<<<< HEAD
			if (e.getSource() == randomAlbum) {
				if (collection.size() != 0) {
					Random random = new Random();
					int number = random.nextInt(collection.size());
					Album selectedAlbum = Search.searchAlbumByID(collection.get(number));
					StringBuilder albumData = new StringBuilder(" Album name:" + selectedAlbum.getAlbumName()
							+ "\n Artist of album:" + selectedAlbum.getArtist().getName() + "\n Duration:"
							+ DurationFono.toString(selectedAlbum.getLength()) + "\n Year:"
							+ selectedAlbum.getYear() + "\n Producer:" + selectedAlbum.getProducer().getName()
							+ "\n\n");
					int num = 1;
					for (Song currentSong : selectedAlbum.getSongs()) {
						albumData.append(" " + num + ". " + currentSong.getSongArtist().getName() + " - "
								+ currentSong.getSongTitle() + " - "
								+ DurationFono.toString(currentSong.getLength()));
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
					JOptionPane.showMessageDialog(null, scrollPane, "FonoTech", JOptionPane.INFORMATION_MESSAGE);
				}
			}

=======
>>>>>>> origin/master
			if (e.getSource() == searchByYear) {
				String year = JOptionPane.showInputDialog(null, "Enter year:");
				int yearInt = 0;
				try {
					yearInt = Integer.parseInt(year);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error. Incorrect data.", "FonoTech",
							JOptionPane.INFORMATION_MESSAGE);
					dialog.setVisible(false);
					dialog.dispose();
					return;
				}
				StringBuilder albumData = new StringBuilder("Albums from " + yearInt + ": \n");
				ArrayList<Integer> collection = user.getAlbumCollection();
				for (int albumID : collection) {
					Album album = Search.searchAlbumByID(albumID);
					if (album != null) {
						if (album.getYear() == yearInt) {
							albumData.append(album.getAlbumName() + " - " + album.getArtist().getName() + "("
									+ album.getYear() + ")\n");
						}
					}
				}
				JTextArea textArea = new JTextArea(30, 70);
				textArea.setText(albumData.toString());
				textArea.setCaretPosition(0);
				textArea.setEditable(false);
				JScrollPane scrollPane = new JScrollPane(textArea);
				JOptionPane.showMessageDialog(null, scrollPane, "Albums by year", JOptionPane.INFORMATION_MESSAGE);
			}
			if (e.getSource() == searchByLength) {
				String minutes = JOptionPane.showInputDialog(null, "Enter the maximum length album in minutes:");
				int minutesInt = 0;
				try {
					minutesInt = Integer.parseInt(minutes);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error. Incorrect data.", "FonoTech",
							JOptionPane.INFORMATION_MESSAGE);
					dialog.setVisible(false);
					dialog.dispose();
					return;
				}
				StringBuilder albumData = new StringBuilder("Albums shorter than " + minutesInt + " minutes: \n");
				ArrayList<Integer> collection = user.getAlbumCollection();
				for (int albumID : collection) {
					Album album = Search.searchAlbumByID(albumID);
					if (album != null) {
						if ((album.getLength().compareTo(DurationFono.durationOfSong(minutesInt, 0)) < 0)) {
							albumData.append(album.getAlbumName() + " - " + album.getArtist().getName() + " ("
<<<<<<< HEAD
									+ DurationFono.toString(album.getLength()) + ")\n");
=======
									+ DurationFono.durationToString(album.getLength()) + ")\n");
>>>>>>> origin/master
						}
					}
				}
				JTextArea textArea = new JTextArea(30, 70);
				textArea.setText(albumData.toString());
				textArea.setCaretPosition(0);
				textArea.setEditable(false);
				JScrollPane scrollPane = new JScrollPane(textArea);
				JOptionPane.showMessageDialog(null, scrollPane, "Albums by year", JOptionPane.INFORMATION_MESSAGE);
			}
			if (e.getSource() == songsByArtist) {
				String artist = JOptionPane.showInputDialog(null, "Enter artist:");
				Artist artistTemp = Search.searchArtist(artist);
				if (artistTemp == null) {
					JOptionPane.showMessageDialog(null, "Error. Incorrect data.", "FonoTech",
							JOptionPane.INFORMATION_MESSAGE);
					dialog.setVisible(false);
					dialog.dispose();
					return;
				}
				int num = 1;
				StringBuilder songsData = new StringBuilder(" Songs by " + artist + ": \n");
				ArrayList<Integer> collection = user.getAlbumCollection();
				for (int albumID : collection) {
					Album album = Search.searchAlbumByID(albumID);
					if (album != null) {
						for (Song currentSong : album.getSongs()) {
							if (currentSong.getSongArtist().getName().equals(artist)) {
								songsData.append(" " + num + ". " + currentSong.getSongArtist().getName() + " - "
										+ currentSong.getSongTitle() + " - "
<<<<<<< HEAD
										+ DurationFono.toString(currentSong.getLength()));
=======
										+ DurationFono.durationToString(currentSong.getLength()));
>>>>>>> origin/master
								if (currentSong.isHasVideo()) {
									songsData.append(" : Video\n");
								} else {
									songsData.append("\n");
								}
								songsData.append(" \t Arrangement: " + currentSong.getArrangementBy() + " / Composer: "
										+ currentSong.getMusicBy() + " / Lyrics:" + currentSong.getLyricsBy() + " : "
										+ currentSong.getYear() + "\n");
								num++;
							}
						}
					}
				}
				JTextArea textArea = new JTextArea(30, 70);
				textArea.setText(songsData.toString());
				textArea.setCaretPosition(0);
				textArea.setEditable(false);
				JScrollPane scrollPane = new JScrollPane(textArea);
				JOptionPane.showMessageDialog(null, scrollPane, "Albums by year", JOptionPane.INFORMATION_MESSAGE);
<<<<<<< HEAD
=======

>>>>>>> origin/master
			}
		}
	}

	ViewCollectionPanel(JDialog dialog) {
<<<<<<< HEAD
		dialog.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
		setBackground(new Color(167, 202, 255));
=======
		setBackground(new Color(167, 202, 255));
		ArrayList<Integer> collection = user.getAlbumCollection();
>>>>>>> origin/master
		int i = 1;
		for (int albumID : collection) {
			Album album = Search.searchAlbumByID(albumID);
			if (album != null) {
				JRadioButton albumButton = new JRadioButton(i + " - " + album.getAlbumName() + " - "
						+ album.getArtist().getName() + " - " + album.getYear());
				group.add(albumButton);
				albumButtons.add(albumButton);
				add(albumButton);
				albumButton.setBackground(new Color(167, 202, 255));
				i++;
			}
		}
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		view.addActionListener(buttonEvent);
		searchByLength.addActionListener(buttonEvent);
		searchByYear.addActionListener(buttonEvent);
		songsByArtist.addActionListener(buttonEvent);
<<<<<<< HEAD
		randomAlbum.addActionListener(buttonEvent);
=======
>>>>>>> origin/master
		view.setPreferredSize(new Dimension(320, 30));
		add(view);
		add(searchByYear);
		add(searchByLength);
		add(songsByArtist);
<<<<<<< HEAD
		add(randomAlbum);
=======
>>>>>>> origin/master
	}
}
