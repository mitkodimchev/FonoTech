package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ButtonGroup;
<<<<<<< HEAD
import javax.swing.ImageIcon;
=======
>>>>>>> origin/master
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import mainPackage.Album;
import mainPackage.Artist;
import mainPackage.Producer;
import mainPackage.Search;

public class CreateAlbum extends JDialog {
	CreateAlbumPanel createAlbumPanel;

	private static final long serialVersionUID = 1L;

	public CreateAlbum(JFrame parent) {
		super(parent, "Create Album", true);
		setSize(250, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createAlbumPanel = new CreateAlbumPanel(this);
		getContentPane().add(createAlbumPanel);
	}
}

class CreateAlbumPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JRadioButton albumButton = new JRadioButton("Album");
	JRadioButton compilationButton = new JRadioButton("Compilation");
	JTextField artist = new JTextField(20);
	JTextField producer = new JTextField(20);
	JTextField albumName = new JTextField(20);
	JTextField year = new JTextField(4);
	JButton create = new JButton("Create album");

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == albumButton) {
				artist.setEditable(true);
			}
			if (e.getSource() == compilationButton) {
				artist.setEditable(false);
			}
			if (e.getSource() == create) {
				boolean isAlbum;
				int yearInt = 0;
				Artist artistTemp = null;
				String artistName = artist.getText();
				String producerName = producer.getText();
				try{
					yearInt = Integer.parseInt(year.getText());
				}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(null, "Year of the album is unknown.", "FonoTech",
							JOptionPane.INFORMATION_MESSAGE);
				dialog.setVisible(false);
				dialog.dispose();
				}
				String albumNameString = albumName.getText();
				Producer producerTemp = Search.searchProducer(producerName);
				if (producerTemp == null) {
					int answer = JOptionPane.showConfirmDialog(null,
							"The producer does not exist in the system. Would you like to create it?",
							"Create producer", JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						CreateProducer crProd = new CreateProducer(null);
						crProd.setVisible(true);
					} else {
						dialog.setVisible(false);
						dialog.dispose();
					}
				}
				if (albumButton.isSelected()) {	
					isAlbum = true;
					artistTemp = Search.searchArtist(artistName);
					if (artistTemp == null) {
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
					artistTemp = Search.searchArtist(artistName);
				}
				else{
					isAlbum = false;
					producerTemp = Search.searchProducer(producerName);
					artistTemp = Search.searchVA("Various artists", producerTemp);
					if (artistTemp == null&&producerTemp!=null) {
						artistTemp = new Artist("Various artists", producerTemp);
					}
					
				}
				Album album = null;
				if (Search.searchAlbum(albumNameString, artistTemp) == null) {
					producerTemp = Search.searchProducer(producerName);
					album = new Album(albumNameString, artistTemp, yearInt, producerTemp);
					JOptionPane.showMessageDialog(null, "Album is created", "FonoTech",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Album is already created", "FonoTech",
							JOptionPane.INFORMATION_MESSAGE);
				}
				int answer;
				do{
					answer = JOptionPane.showConfirmDialog(null,
						"Would you like to add song to the album?",
						"Create artist", JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					CreateSong crSong = new CreateSong(null, isAlbum, producerTemp, album);
					crSong.setVisible(true);
				} else {
					dialog.setVisible(false);
					dialog.dispose();
				}}while(answer != JOptionPane.NO_OPTION);
			}

		}
	}

	CreateAlbumPanel(JDialog dialog) {
<<<<<<< HEAD
		dialog.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
=======
>>>>>>> origin/master
		setBackground(new Color(167, 202, 255));
		try {
			year = new JFormattedTextField(new MaskFormatter("####"));
			year.setPreferredSize(new Dimension(60, 20));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ButtonGroup group = new ButtonGroup();
		group.add(albumButton);
		group.add(compilationButton);
		albumButton.setSelected(true);
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		create.addActionListener(buttonEvent);
		albumButton.addActionListener(buttonEvent);
		compilationButton.addActionListener(buttonEvent);
		add(albumButton);
		albumButton.setBackground(new Color(167, 202, 255));
		add(compilationButton);
		compilationButton.setBackground(new Color(167, 202, 255));
		add(new JLabel("Name of album: "));
		add(albumName);
		add(new JLabel("Artist of album: "));
		add(artist);
		add(new JLabel("Producer of album: "));
		add(producer);
		add(new JLabel("Year of album: "));
		add(year);
		add(create);
	}
}
