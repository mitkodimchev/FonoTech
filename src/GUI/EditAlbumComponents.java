package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import mainPackage.Album;
import mainPackage.Search;

public class EditAlbumComponents extends JDialog {
	EditAlbumComponentsPanel EditAlbumComponentsPanel;

	private static final long serialVersionUID = 1L;

	public EditAlbumComponents(JFrame parent, Album album) {
		super(parent, "Edit Album", true);
		setSize(250, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		EditAlbumComponentsPanel = new EditAlbumComponentsPanel(this, album);
		getContentPane().add(EditAlbumComponentsPanel);
	}
}

class EditAlbumComponentsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	Album editedAlbum;
	JDialog dialog;
	JTextField artist = new JTextField(20);
	JTextField producer = new JTextField(20);
	JTextField albumName = new JTextField(20);
	JTextField year = new JTextField(4);
	JButton edit = new JButton("Edit album");

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == edit) {
				editedAlbum.setAlbumName(albumName.getText());
				if (Search.searchArtist(artist.getText()) != null)
					editedAlbum.setArtist(Search.searchArtist(artist.getText()));
				if (Search.searchProducer(producer.getText()) != null)
					editedAlbum.setProducer(Search.searchProducer(producer.getText()));
				editedAlbum.setYear(Integer.parseInt(year.getText()));
			}
			dialog.setVisible(false);
			dialog.dispose();
		}
	}

	EditAlbumComponentsPanel(JDialog dialog, Album album) {
		editedAlbum = album;
		setBackground(new Color(167, 202, 255));
		try {
			year = new JFormattedTextField(new MaskFormatter("####"));
			year.setPreferredSize(new Dimension(60, 20));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		edit.addActionListener(buttonEvent);
		artist.setText(editedAlbum.getArtist().getName());
		albumName.setText(editedAlbum.getAlbumName());
		producer.setText(editedAlbum.getProducer().getName());
		year.setText(String.valueOf(editedAlbum.getYear()));
		add(new JLabel("Name of album: "));
		add(albumName);
		add(new JLabel("Artist of album: "));
		add(artist);
		add(new JLabel("Producer of album: "));
		add(producer);
		add(new JLabel("Year of album: "));
		add(year);
		add(edit);
	}
}
