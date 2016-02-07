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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import mainPackage.Artist;
import mainPackage.DataStorage;
import mainPackage.Producer;
import mainPackage.Search;

public class ViewProducer extends JDialog {
	ViewProducerPanel viewProducerPanel;

	private static final long serialVersionUID = 1L;

	public ViewProducer(JFrame parent) {
		super(parent, "View Producer", true);
		setSize(280, 700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		viewProducerPanel = new ViewProducerPanel(this);
		getContentPane().add(viewProducerPanel);
	}
}

class ViewProducerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JButton view = new JButton("View Producer");
	ButtonGroup group = new ButtonGroup();
	ArrayList<JRadioButton> ProducerButtons = new ArrayList<JRadioButton>();

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == view) {
				for (JRadioButton selProducer : ProducerButtons) {
					if (selProducer.isSelected()) {
						String ProducerInfo = selProducer.getText();
						String[] producer = ProducerInfo.split(" - ");
						Producer selectedProducer = Search.searchProducer(producer[0]);
						StringBuilder ProducerData = new StringBuilder(" Producer:" + selectedProducer.getName()
								+ "\n Owner:" + selectedProducer.getOwner() + "\n Artists:\n");
						int num = 1;
						for (Artist currentArtist : selectedProducer.getArtistList()) {
							if (!currentArtist.getName().equals("Various artists")) {
								ProducerData.append(" " + num + ". " + currentArtist.getName() + "\n");
								num++;
							}
						}
						if (num == 1) {
							ProducerData.append(" None");
						}
						JTextArea textArea = new JTextArea(30, 30);
						textArea.setText(ProducerData.toString());
						textArea.setCaretPosition(0);
						textArea.setEditable(false);
						JScrollPane scrollPane = new JScrollPane(textArea);
						JOptionPane.showMessageDialog(null, scrollPane, ProducerInfo, JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}

	ViewProducerPanel(JDialog dialog) {
		dialog.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
		setBackground(new Color(167, 202, 255));
		ArrayList<Producer> Producers = DataStorage.getDatabase();
		for (Producer currentProducer : Producers) {
			if (currentProducer != null) {
				JRadioButton ProducerButton = new JRadioButton(
						currentProducer.getName() + " - " + currentProducer.getOwner());
				group.add(ProducerButton);
				ProducerButtons.add(ProducerButton);
				add(ProducerButton);
				ProducerButton.setBackground(new Color(167, 202, 255));
			}
		}
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		view.addActionListener(buttonEvent);
		add(view);
	}
}
