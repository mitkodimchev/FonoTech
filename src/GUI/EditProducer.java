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
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import mainPackage.DataStorage;
import mainPackage.Producer;
import mainPackage.Search;

public class EditProducer extends JDialog {
	EditProducerPanel EditProducerPanel;

	private static final long serialVersionUID = 1L;

	public EditProducer(JFrame parent) {
		super(parent, "Edit Producer", true);
		setSize(350, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		EditProducerPanel = new EditProducerPanel(this);
		getContentPane().add(EditProducerPanel);
	}
}

class EditProducerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JButton edit = new JButton("Edit Producer");
	ButtonGroup group = new ButtonGroup();
	ArrayList<JRadioButton> ProducerButtons = new ArrayList<JRadioButton>();

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == edit) {
					for (JRadioButton selProducer : ProducerButtons) {
						if (selProducer.isSelected()) {
							String ProducerInfo = selProducer.getText();
							String[] producer = ProducerInfo.split(" - ");
							Producer selectedProducer = Search.searchProducer(producer[0]);
						EditProducerComponents editComp = new EditProducerComponents (null, selectedProducer);
						editComp.setVisible(true);
					}
				}
			}
		}
	}

	EditProducerPanel(JDialog dialog) {
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
		edit.addActionListener(buttonEvent);
		add(edit);
	}
}
