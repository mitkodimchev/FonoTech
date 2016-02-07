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

import mainPackage.DataStorage;
import mainPackage.Producer;
import mainPackage.Search;

public class RemoveProducer extends JDialog {
	RemoveProducerPanel removeProducerPanel;

	private static final long serialVersionUID = 1L;

	public RemoveProducer(JFrame parent) {
		super(parent, "Remove Producer", true);
		setSize(280, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		removeProducerPanel = new RemoveProducerPanel(this);
		getContentPane().add(removeProducerPanel);
	}
}

class RemoveProducerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JButton remove = new JButton("Remove Producer");
	ButtonGroup group = new ButtonGroup();
	ArrayList<JRadioButton> ProducerButtons = new ArrayList<JRadioButton>();

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == remove) {
				for (JRadioButton selProducer : ProducerButtons) {
					if (selProducer.isSelected()) {
						String ProducerInfo = selProducer.getText();
						String[] producer = ProducerInfo.split(" - ");
						Producer selectedProducer = Search.searchProducer(producer[0]);
						DataStorage.getDatabase().remove(selectedProducer);
						JOptionPane.showMessageDialog(null, "Producer is removed", "Remove producer",
								JOptionPane.INFORMATION_MESSAGE);
						dialog.setVisible(false);
						dialog.dispose();
					}
				}
			}
		}
	}

	RemoveProducerPanel(JDialog dialog) {
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
		remove.addActionListener(buttonEvent);
		add(remove);
	}
}
