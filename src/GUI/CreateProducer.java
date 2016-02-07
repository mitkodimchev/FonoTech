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

import mainPackage.Producer;
import mainPackage.Search;

public class CreateProducer extends JDialog {
	CreateProducerPanel createProducerPanel;

	private static final long serialVersionUID = 1L;

	public CreateProducer(JFrame parent) {
		super(parent, "Create Producer", true);
		setSize(500, 150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createProducerPanel = new CreateProducerPanel(this);
		getContentPane().add(createProducerPanel);
	}
}

class CreateProducerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JDialog dialog;
	JTextField producer = new JTextField(20);
	JTextField producerOwner = new JTextField(20);
	JButton create = new JButton("Create producer");
	
	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == create) {
				String producerName = producer.getText();
				String producerOwnerName = producerOwner.getText();
				Producer producerTemp = Search.searchProducer(producerName);
				if (producerTemp == null) {
					new Producer(producerName, producerOwnerName);
					JOptionPane.showMessageDialog(null, "Producer is created", "FonoTech", JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "Producer is already created", "FonoTech", JOptionPane.INFORMATION_MESSAGE);
					}
					dialog.setVisible(false);
					dialog.dispose();
			}
		}
	}


	CreateProducerPanel(JDialog dialog) {
		dialog.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
		setBackground(new Color(167,202,255));
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		create.addActionListener(buttonEvent);
		add(new JLabel("Producer company name: "));
		add(producer);
		add(new JLabel("Owner of producer company: "));
		add(producerOwner);
		add(create);
	}
}
