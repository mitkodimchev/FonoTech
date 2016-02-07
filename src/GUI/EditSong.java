package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

<<<<<<< HEAD
import javax.swing.ImageIcon;
=======
>>>>>>> origin/master
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mainPackage.Artist;
import mainPackage.Search;
import mainPackage.Song;

public class EditSong extends JDialog {
	EditSongPanel EditSongPanel;

	private static final long serialVersionUID = 1L;

	public EditSong(JFrame parent) {
		super(parent, "Edit Song", true);
		setSize(250, 150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		EditSongPanel = new EditSongPanel(this);
		getContentPane().add(EditSongPanel);
	}
}

class EditSongPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JButton edit = new JButton("Edit Song");
	JTextField song = new JTextField(10);
	JTextField artist = new JTextField(10);

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == edit) {
				Song songTemp;
				String songTitle = song.getText();
				String songArtist = artist.getText();
				Artist artistTemp = Search.searchArtist(songArtist);
				if(artistTemp!=null){
					songTemp = Search.searchSong(songTitle, artistTemp);
					if(songTemp!=null){
						EditSongComponents editComp = new EditSongComponents(null, songTemp);
						editComp.setVisible(true);
					}
					if(songTemp==null||artistTemp==null){
						JOptionPane.showMessageDialog(null, "Wrong input data", "FonoTech", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}

	EditSongPanel(JDialog dialog) {
<<<<<<< HEAD
		dialog.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
=======
>>>>>>> origin/master
		setBackground(new Color(167, 202, 255));
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		edit.addActionListener(buttonEvent);
		add(new JLabel("Artist of song:"));
		add(artist);
		add(new JLabel("Title of song:"));
		add(song);
		add(edit);
	}
}
