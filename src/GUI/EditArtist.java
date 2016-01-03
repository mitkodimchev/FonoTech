package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import mainPackage.Artist;
import mainPackage.DataStorage;
import mainPackage.Producer;
import mainPackage.Search;

public class EditArtist extends JDialog {
	EditArtistPanel EditArtistPanel;

	private static final long serialVersionUID = 1L;

	public EditArtist(JFrame parent) {
		super(parent, "Edit Artist", true);
		setSize(350, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		EditArtistPanel = new EditArtistPanel(this);
		getContentPane().add(EditArtistPanel);
	}
}

class EditArtistPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JButton edit = new JButton("Edit Artist");
	ButtonGroup group = new ButtonGroup();
	ArrayList<JRadioButton> ArtistButtons = new ArrayList<JRadioButton>();

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == edit) {
				for (JRadioButton selArtist : ArtistButtons) {
					if (selArtist.isSelected()) {
						String ArtistInfo = selArtist.getText();
						String[] Artist = ArtistInfo.split(" - ");
						Artist selectedArtist = Search.searchArtist(Artist[0]);
						EditArtistComponents editComp = new EditArtistComponents (null, selectedArtist);
						editComp.setVisible(true);
					}
				}
			}
		}
	}

	EditArtistPanel(JDialog dialog) {
		setBackground(new Color(167, 202, 255));
		ArrayList<Producer> tempProd = DataStorage.getDatabase();
		ArrayList<Artist> tempArtist;
		for (Producer producer : tempProd) {
			tempArtist = producer.getArtistList();
			for (Artist artist : tempArtist) {
				if(!artist.getName().equals("Various artists")){
					JRadioButton ArtistButton = new JRadioButton(artist.getName() + " - " + artist.getProducer().getName());
					group.add(ArtistButton);
					ArtistButtons.add(ArtistButton);
					add(ArtistButton);
					ArtistButton.setBackground(new Color(167, 202, 255));
				}
			}
		}
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		edit.addActionListener(buttonEvent);
		add(edit);
	}
}
