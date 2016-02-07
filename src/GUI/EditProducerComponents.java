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

import mainPackage.Producer;

public class EditProducerComponents extends JDialog {
	EditProducerComponentsPanel EditProducerComponentsPanel;

	private static final long serialVersionUID = 1L;

	public EditProducerComponents(JFrame parent, Producer Producer) {
		super(parent, "Edit Producer", true);
		setSize(250, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		EditProducerComponentsPanel = new EditProducerComponentsPanel(this, Producer);
		getContentPane().add(EditProducerComponentsPanel);
	}
}

class EditProducerComponentsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	Producer editedProducer;
	JDialog dialog;
	JTextField producerOwner = new JTextField(20);
	JTextField producerField = new JTextField(20);
	JButton edit = new JButton("Edit Producer");

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == edit) {
				editedProducer.setName(producerField.getText());
				editedProducer.setOwner(producerOwner.getText());
			}
			dialog.setVisible(false);
			dialog.dispose();
		}
	}

	EditProducerComponentsPanel(JDialog dialog, Producer producer) {
<<<<<<< HEAD
		dialog.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
=======
>>>>>>> origin/master
		editedProducer = producer;
		setBackground(new Color(167,202,255));
		this.dialog = dialog;
		ActionListener buttonEvent = new ButtonHandler();
		edit.addActionListener(buttonEvent);
		producerOwner.setText(producer.getOwner());
		producerField.setText(producer.getName());
		add(new JLabel("Producer name: "));
		add(producerField);
		add(new JLabel("Producer of the Producer: "));
		add(producerOwner);
		add(edit);
	}
}
