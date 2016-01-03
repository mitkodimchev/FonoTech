package mainPackage;
import java.util.ArrayList;

import GUI.MemberMenu;

public class Member extends User {

	ArrayList<Integer> albumCollection = new ArrayList<Integer>();

	public Member(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}

	public Member() {
		super();
	}
	
	public void setAlbumCollection(ArrayList<Integer> albumCollection) {
		this.albumCollection = albumCollection;
	}

	public void addAlbumToCollection(int id) {
		albumCollection.add(id);
	}

	public ArrayList<Integer> getAlbumCollection() {
		return albumCollection;
	}

	@Override
	public void menu() {
		new UserDataReader("C:\\Users\\mitko\\Projects\\FonoTech\\users\\"+this.getUsername().hashCode()+".xml", this);
		MemberMenu.MenuStart();
		try {
			DataStorage.saveUserData(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
