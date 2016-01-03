package mainPackage;
import GUI.AdministratorMenu;

public class Administrator extends User {

	public Administrator(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}

	public Administrator() {
		super();
	}

	@Override
	public void menu() {
		AdministratorMenu.MenuStart();
	}
}
