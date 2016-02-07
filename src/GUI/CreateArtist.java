package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mainPackage.Artist;
import mainPackage.Producer;
import mainPackage.Search;

public class CreateArtist extends JDialog {
	CreateArtistPanel createArtistPanel;

	private static final long serialVersionUID = 1L;

	public CreateArtist(JFrame parent) {
		super(parent, "Create Artist", true);
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createArtistPanel = new CreateArtistPanel(this);
		getContentPane().add(createArtistPanel);
	}
}

class CreateArtistPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JTextField artist = new JTextField(20);
	JTextField producer = new JTextField(20);
	JButton create = new JButton("Create artist");
	
	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == create) {
				String artistName = artist.getText();
				String producerName = producer.getText();
				Producer producerTemp = Search.searchProducer(producerName);
				if (producerTemp == null) {
					int answer = JOptionPane.showConfirmDialog(null, "The producer does not exist in the system. Would you like to create it?", "Create producer",
							JOptionPane.YES_NO_OPTION);
					if (answer == JOptionPane.YES_OPTION) {
						CreateProducer crProd = new CreateProducer(null);
						crProd.setVisible(true);
					} else {
						dialog.setVisible(false);
						dialog.dispose();
					}
				}
				else{
					producerTemp = Search.searchProducer(producerName);
					if(Search.searchArtist(artistName)==null){
					new Artist(artistName,producerTemp);
					JOptionPane.showMessageDialog(null, "Artist is created", "FonoTech", JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "Artist is already created", "FonoTech", JOptionPane.INFORMATION_MESSAGE);
					}
					dialog.setVisible(false);
					dialog.dispose();
				}
			}
		}
	}


	CreateArtistPanel(JDialog dialog) {	
		dialog.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
		setBackground(new Color(167,202,255));
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		create.addActionListener(buttonEvent);
		add(new JLabel("Artist name: "));
		add(artist);
		add(new JLabel("Producer of the artist: "));
		add(producer);
		add(create);
	}
}
