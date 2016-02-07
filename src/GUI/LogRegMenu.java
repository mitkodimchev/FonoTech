package GUI;

import mainPackage.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class MyFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public MyFrame() {
		setTitle("FonoTech");
		setSize(400, 80);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}

class MyPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	MyPanel() {
		setBackground(new Color(167,202,255));
		JButton login = new JButton("Login");
		JButton register = new JButton("Register");
		ActionListener buttonEvent = new ButtonHandler();
		login.addActionListener(buttonEvent);
		register.addActionListener(buttonEvent);
		add(login);
		add(register);
	}

	class ButtonHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
			if (e.getActionCommand() == "Login") {
				LoginDialog dlg = new LoginDialog(null);
				dlg.setVisible(true);
			} 
			if (e.getActionCommand() == "Register") {
				RegDialog reg = new RegDialog(null);
				reg.setVisible(true);
			}
		}

	}
}

public class LogRegMenu {
	public static User user;

	public static User start() {
		MyFrame myframe = new MyFrame();
		MyPanel mypanel = new MyPanel();
		Container contentPane = myframe.getContentPane();
		contentPane.add(mypanel);
		myframe.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
		myframe.setVisible(true);
		do{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while(user==null);
		myframe.dispose();
		return user;
	}

}
