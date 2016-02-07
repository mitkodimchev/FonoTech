package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mainPackage.Album;
import mainPackage.Artist;
import mainPackage.DataStorage;
import mainPackage.Producer;
import mainPackage.Search;
import mainPackage.Test;

class AdministratorMenuFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public AdministratorMenuFrame() {
		setTitle("FonoTech");
		setSize(400, 280);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}

class AdminMenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JMenuItem save;
	JMenuItem exit;
	JMenuItem changePass;
	JMenuItem usersView;
	JMenuItem addUser;
	JMenuItem removeUser;	
	JMenuItem viewAlbum;
	JMenuItem viewArtist;
	JMenuItem viewSong;
	JMenuItem viewProducer;
	JMenuItem addAlbum;
	JMenuItem addArtist;
	JMenuItem addSong;
	JMenuItem addProducer;
	JMenuItem editAlbum;
	JMenuItem editArtist;
	JMenuItem editSong;
	JMenuItem editProducer;
	JMenuItem removeAlbum;
	JMenuItem removeArtist;
	JMenuItem removeSong;
	JMenuItem removeProducer;
	JMenuItem addSongToAlbum;
	JMenuItem removeSongFromAlbum;
	ActionListener b = new ButtonHandler();

	AdminMenuPanel(JFrame myframe) {
		setBackground(new Color(167, 202, 255));
		JMenuBar mb = new JMenuBar();
		JMenu userMenu = new JMenu(Test.getUser().getUsername());
		JMenu view = new JMenu("View");
		JMenu edit = new JMenu("Edit");
		JMenu dataView = new JMenu("Data");
		JMenu userEditBar = new JMenu("User");
		JMenu dataEditBar = new JMenu("Data");
		JMenu dataAddBar = new JMenu("Add");
		JMenu dataEditBarIn = new JMenu("Edit");
		JMenu dataRemoveBar = new JMenu("Remove");
		mb.add(userMenu);
		mb.add(view);
		mb.add(edit);
		changePass = userMenu.add("Change password");
		userMenu.addSeparator();
		save = userMenu.add("Save data");
		userMenu.addSeparator();
		exit = userMenu.add("Exit");
		usersView = view.add("Users");
		view.addSeparator();
		view.add(dataView);
		viewAlbum = dataView.add("Album");
		viewArtist = dataView.add("Artist");
		viewSong = dataView.add("Song");
		viewProducer = dataView.add("Producer");

		edit.add(userEditBar);
		edit.addSeparator();
		edit.add(dataEditBar);
		addUser = userEditBar.add("Add");
		removeUser = userEditBar.add("Remove");

		dataEditBar.add(dataAddBar);
		dataEditBar.add(dataEditBarIn);
		dataEditBar.add(dataRemoveBar);

		addAlbum = dataAddBar.add("Album");
		addArtist = dataAddBar.add("Artist");
		addSong = dataAddBar.add("Song");
		addProducer = dataAddBar.add("Producer");
		addSongToAlbum = dataAddBar.add("Add song to album");

		editAlbum = dataEditBarIn.add("Album");
		editArtist = dataEditBarIn.add("Artist");
		editSong = dataEditBarIn.add("Song");
		editProducer = dataEditBarIn.add("Producer");

		removeAlbum = dataRemoveBar.add("Album");
		removeArtist = dataRemoveBar.add("Artist");
		removeSong = dataRemoveBar.add("Song");
		removeProducer = dataRemoveBar.add("Producer");
		removeSongFromAlbum = dataRemoveBar.add("Remove song from album");

		ButtonHandler b = new ButtonHandler();
		myframe.setJMenuBar(mb);
		changePass.addActionListener(b);
		save.addActionListener(b);
		exit.addActionListener(b);
		usersView.addActionListener(b);
		addUser.addActionListener(b);
		removeUser.addActionListener(b);
		viewAlbum.addActionListener(b);
		viewArtist.addActionListener(b);
		viewSong.addActionListener(b);
		viewProducer.addActionListener(b);
		addAlbum.addActionListener(b);
		addArtist.addActionListener(b);
		addSong.addActionListener(b);
		addProducer.addActionListener(b);
		editAlbum.addActionListener(b);
		editArtist.addActionListener(b);
		editSong.addActionListener(b);
		editProducer.addActionListener(b);
		removeAlbum.addActionListener(b);
		removeArtist.addActionListener(b);
		removeSong.addActionListener(b);
		removeProducer.addActionListener(b);
		addSongToAlbum.addActionListener(b);
		removeSongFromAlbum.addActionListener(b);
	}

	class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == save) {
				try {
					DataStorage.saveUsers();
					DataStorage.saveData();
					JOptionPane.showMessageDialog(null, "Database is saved", "FonoTech",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else if (e.getSource() == changePass) {
				ChangePass cngPass = new ChangePass(null);
				cngPass.setVisible(true);
			} else if (e.getSource() == exit) {
				AdministratorMenu.setClose(true);
			} else if (e.getSource() == usersView) {
				ViewUser viewUser = new ViewUser(null);
				viewUser.setVisible(true);
			} else if (e.getSource() == viewAlbum) {
				ViewLibrary viewLibrary = new ViewLibrary(null);
				viewLibrary.setVisible(true);
			}
			else if (e.getSource() == viewArtist) {
				ViewArtist viewArtist = new ViewArtist(null);
				viewArtist.setVisible(true);
			}else if (e.getSource() == viewSong) {
				ViewSong viewSong = new ViewSong(null);
				viewSong.setVisible(true);
			}else if (e.getSource() == viewProducer) {
				ViewProducer viewProducer = new ViewProducer(null);
				viewProducer.setVisible(true);
			}else if (e.getSource() == addUser) {
				RegAdminDialog reg = new RegAdminDialog(null);
				reg.setVisible(true);
			}else if(e.getSource()==removeUser){
				RemoveUser rem = new RemoveUser(null);
				rem.setVisible(true);
			}else if(e.getSource()==addAlbum){
				CreateAlbum crAlbum = new CreateAlbum(null);
				crAlbum.setVisible(true);
			}else if(e.getSource()==addArtist){
				CreateArtist crArtist = new CreateArtist(null);
				crArtist.setVisible(true);
			}else if(e.getSource()==addSong){
				Producer producer = null;
				Album album = null;
				String albumName = JOptionPane.showInputDialog (null, "Enter album name:");
				String albumArtist = JOptionPane.showInputDialog (null, "Enter artist of album:");
				String albumProducer = JOptionPane.showInputDialog (null, "Enter producer of album:");
				Artist artist = Search.searchArtist(albumArtist);
				if(artist!=null){
					producer = Search.searchProducer(albumProducer);
					if(producer!=null){
						album = Search.searchAlbum(albumName, artist);
						if(album!=null){
							CreateSong crSong = new CreateSong(null, false, producer, album);
							crSong.setVisible(true);}
					}						
				}
				if(artist==null||producer==null||album==null){
					JOptionPane.showMessageDialog(null, "Wrong input data", "FonoTech",
							JOptionPane.INFORMATION_MESSAGE);

				}
			}else if(e.getSource()==addProducer){
				CreateProducer crProducer = new CreateProducer(null);
				crProducer.setVisible(true);
			}else if(e.getSource()==editAlbum){
				EditAlbum editAlbum = new EditAlbum(null);
				editAlbum.setVisible(true);
			}
			else if(e.getSource()==editArtist){
				EditArtist editArtist = new EditArtist(null);
				editArtist.setVisible(true);
			}
			else if(e.getSource()==editSong){
				EditSong editSong = new EditSong(null);
				editSong.setVisible(true);
			}
			else if(e.getSource()==editProducer){
				EditProducer editProducer = new EditProducer(null);
				editProducer.setVisible(true);
			}else if(e.getSource()==removeAlbum){
				RemoveAlbum removeAlbum = new RemoveAlbum(null);
				removeAlbum.setVisible(true);
			}else if(e.getSource()==removeArtist){
				RemoveArtist removeArtist = new RemoveArtist(null);
				removeArtist.setVisible(true);
			}else if(e.getSource()==removeSong){
				RemoveSong removeSong = new RemoveSong(null);
				removeSong.setVisible(true);
			}else if(e.getSource()==removeProducer){
				RemoveProducer removeProducer = new RemoveProducer(null);
				removeProducer.setVisible(true);
			}else if(e.getSource()==addSongToAlbum){
				AddSongToAlbum addSongToAlbum = new AddSongToAlbum(null);
				addSongToAlbum.setVisible(true);
			}else if(e.getSource()==removeSongFromAlbum){
				RemoveSongFromAlbum removeSongFromAlbum = new RemoveSongFromAlbum(null);
				removeSongFromAlbum.setVisible(true);
			}
		}
	}
}

public class AdministratorMenu {
	static AdministratorMenuFrame myframe;
	static boolean close = false;

	public static void MenuStart() {
		myframe = new AdministratorMenuFrame();
		myframe.setIconImage(new ImageIcon(LogRegMenu.class.getResource("logo.png")).getImage());
		AdminMenuPanel mypanel = new AdminMenuPanel(myframe);
		Container contentPane = myframe.getContentPane();
		contentPane.add(mypanel);
		myframe.setVisible(true);
		do {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!close);
		myframe.dispose();		
	}

	public static void setClose(boolean close) {
		AdministratorMenu.close = close;
	}

	public static AdministratorMenuFrame getMyframe() {
		return myframe;
	}
}
