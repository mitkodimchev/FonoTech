package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import mainPackage.Artist;
import mainPackage.DataStorage;
import mainPackage.Producer;
import mainPackage.Search;

public class RemoveArtist extends JDialog {
	RemoveArtistPanel removeArtistPanel;

	private static final long serialVersionUID = 1L;

	public RemoveArtist(JFrame parent) {
		super(parent, "Remove Artist", true);
		setSize(350, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		removeArtistPanel = new RemoveArtistPanel(this);
		getContentPane().add(removeArtistPanel);
	}
}

class RemoveArtistPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JButton remove = new JButton("Remove Artist");
	ButtonGroup group = new ButtonGroup();
	ArrayList<JRadioButton> ArtistButtons = new ArrayList<JRadioButton>();

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == remove) {
				for (JRadioButton selArtist : ArtistButtons) {
					if (selArtist.isSelected()) {
						String ArtistInfo = selArtist.getText();
						String[] Artist = ArtistInfo.split(" - ");
						Artist selectedArtist = Search.searchArtist(Artist[0]);
						selectedArtist.getProducer().getArtistList().remove(selectedArtist);
						JOptionPane.showMessageDialog(null, "Artist is removed", ArtistInfo,
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}

	RemoveArtistPanel(JDialog dialog) {
		dialog.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
		setBackground(new Color(167, 202, 255));
		ArrayList<Producer> tempProd = DataStorage.getDatabase();
		ArrayList<Artist> tempArtist;
		for (Producer producer : tempProd) {
			tempArtist = producer.getArtistList();
			for (Artist artist : tempArtist) {
				if (!artist.getName().equals("Various artists")) {
					JRadioButton ArtistButton = new JRadioButton(
							artist.getName() + " - " + artist.getProducer().getName());
					group.add(ArtistButton);
					ArtistButtons.add(ArtistButton);
					add(ArtistButton);
					ArtistButton.setBackground(new Color(167, 202, 255));
				}
			}
		}
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		remove.addActionListener(buttonEvent);
		add(remove);
	}
}
