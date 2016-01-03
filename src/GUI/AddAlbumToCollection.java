package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import mainPackage.Album;
import mainPackage.Artist;
import mainPackage.Member;
import mainPackage.Search;
import mainPackage.Test;

public class AddAlbumToCollection extends JDialog {
	AddAlbumToCollectionPanel addToCollectionPanel;

	private static final long serialVersionUID = 1L;

	public AddAlbumToCollection(JFrame parent) {
		super(parent, "Add album to collection", true);
		setSize(250, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addToCollectionPanel = new AddAlbumToCollectionPanel(this);
		getContentPane().add(addToCollectionPanel);
	}
}

class AddAlbumToCollectionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JTextField artist = new JTextField(20);
	JTextField album = new JTextField(20);
	JButton add = new JButton("Add album to collection");
	JRadioButton albumButton = new JRadioButton("Album");
	JRadioButton compilationButton = new JRadioButton("Compilation");

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == compilationButton) {
				artist.setEditable(false);
			}
			if (e.getSource() == albumButton) {
				artist.setEditable(true);
			}
			if (e.getSource() == add) {
				String artistName = null;
				if (albumButton.isSelected()) {
					artistName = artist.getText();
				}
				if (compilationButton.isSelected()) {
					artistName = "Various artists";
				}
				String albumName = album.getText();
				Artist artistTemp = Search.searchArtist(artistName);
				if (artistTemp == null) {
					int answer = JOptionPane.showConfirmDialog(null,
							"The artist does not exist in the system. Would you like to create it?", "Create artist",
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						CreateArtist crArtist = new CreateArtist(null);
						crArtist.setVisible(true);
					} else {
						dialog.setVisible(false);
						dialog.dispose();
					}
				}
				artistTemp = Search.searchArtist(artistName);
				Album albumTemp = Search.searchAlbum(albumName, artistTemp);
				if (albumTemp == null) {
					int answer = JOptionPane.showConfirmDialog(null,
							"The album does not exist in the system. Would you like to create it?", "Create album",
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						CreateAlbum crAlbum = new CreateAlbum(null);
						crAlbum.setVisible(true);
					} else {
						dialog.setVisible(false);
						dialog.dispose();
					}
				} else {
					Member user = (Member) Test.getUser();
					albumTemp = Search.searchAlbum(albumName, artistTemp);
					if (user.getAlbumCollection().contains(albumTemp.getId())) {
						JOptionPane.showMessageDialog(null, "Album is already added to collection", "FonoTech",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						user.addAlbumToCollection(albumTemp.getId());
						JOptionPane.showMessageDialog(null, "Album is added to collection", "FonoTech",
								JOptionPane.INFORMATION_MESSAGE);
						dialog.setVisible(false);
						dialog.dispose();
					}
				}
			}
		}
	}

	AddAlbumToCollectionPanel(JDialog dialog) {
		setBackground(new Color(167,202,255));
		ButtonGroup group = new ButtonGroup();
		group.add(albumButton);
		group.add(compilationButton);
		albumButton.setSelected(true);
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		add.addActionListener(buttonEvent);
		albumButton.setBackground(new Color(167,202,255));
		albumButton.addActionListener(buttonEvent);
		compilationButton.setBackground(new Color(167,202,255));
		compilationButton.addActionListener(buttonEvent);
		add(albumButton);
		add(compilationButton);
		add(new JLabel("Artist of album: "));
		add(artist);
		add(new JLabel("Album name: "));
		add(album);
		add(add);
	}
}
