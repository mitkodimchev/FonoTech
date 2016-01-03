package mainPackage;

import GUI.*;

public class Test {
	private static User user;

	public static void main(String[] args) {
		new DataReader("Database.xml");
		new UserReader("users.xml");
		user = LogRegMenu.start();
		user.menu();
		try {
			DataStorage.saveUsers();
			DataStorage.saveData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public static User getUser() {
		return user;
	}

}