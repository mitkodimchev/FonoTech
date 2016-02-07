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
import javax.swing.JPanel;
import javax.swing.JTextField;

import mainPackage.Artist;
import mainPackage.Search;

public class EditArtistComponents extends JDialog {
	EditArtistComponentsPanel EditArtistComponentsPanel;

	private static final long serialVersionUID = 1L;

	public EditArtistComponents(JFrame parent, Artist Artist) {
		super(parent, "Edit Artist", true);
		setSize(250, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		EditArtistComponentsPanel = new EditArtistComponentsPanel(this, Artist);
		getContentPane().add(EditArtistComponentsPanel);
	}
}

class EditArtistComponentsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	Artist editedArtist;
	JDialog dialog;
	JTextField artistField = new JTextField(20);
	JTextField producer = new JTextField(20);
	JButton edit = new JButton("Edit Artist");

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == edit) {
				editedArtist.setName(artistField.getText());
				if (Search.searchProducer(producer.getText()) != null)
					editedArtist.setProducer(Search.searchProducer(producer.getText()));
			}
			dialog.setVisible(false);
			dialog.dispose();
		}
	}

	EditArtistComponentsPanel(JDialog dialog, Artist artist) {
<<<<<<< HEAD
		dialog.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
=======
>>>>>>> origin/master
		editedArtist = artist;
		setBackground(new Color(167,202,255));
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		edit.addActionListener(buttonEvent);
		artistField.setText(artist.getName());
		producer.setText(artist.getProducer().getName());
		add(new JLabel("Artist name: "));
		add(artistField);
		add(new JLabel("Producer of the artist: "));
		add(producer);
		add(edit);
	}
}
