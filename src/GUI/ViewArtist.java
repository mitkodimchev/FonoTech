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

public class ViewArtist extends JDialog {
	ViewArtistPanel viewArtistPanel;

	private static final long serialVersionUID = 1L;

	public ViewArtist(JFrame parent) {
		super(parent, "View Artist", true);
		setSize(350, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewArtistPanel = new ViewArtistPanel(this);
		getContentPane().add(viewArtistPanel);
	}
}

class ViewArtistPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JButton view = new JButton("View Artist");
	ButtonGroup group = new ButtonGroup();
	ArrayList<JRadioButton> ArtistButtons = new ArrayList<JRadioButton>();

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == view) {
				for (JRadioButton selArtist : ArtistButtons) {
					if (selArtist.isSelected()) {
						String ArtistInfo = selArtist.getText();
						String[] Artist = ArtistInfo.split(" - ");
						Artist selectedArtist = Search.searchArtist(Artist[0]);
						StringBuilder ArtistData = new StringBuilder(" Artist name:" + selectedArtist.getName()
								+ "\n Producer:" + selectedArtist.getProducer().getName()+"\n Albums:\n");
						int num = 1;
						for (Album currentAlbum : selectedArtist.getAlbums()) {
							ArtistData.append(" " + num + ". " + currentAlbum.getAlbumName() + " ("
									+ DurationFono.durationToString(currentAlbum.getLength()) + ") "+currentAlbum.getYear());
							num++;
						}
						if(num==1){
							ArtistData.append(" None");
						}
						JTextArea textArea = new JTextArea(30, 30);
						textArea.setText(ArtistData.toString());
						textArea.setCaretPosition(0);
						textArea.setEditable(false);
						JScrollPane scrollPane = new JScrollPane(textArea);
						JOptionPane.showMessageDialog(null, scrollPane, ArtistInfo, JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}

	ViewArtistPanel(JDialog dialog) {
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
		view.addActionListener(buttonEvent);
		add(view);
	}
}
