package mainPackage;

import java.util.ArrayList;

public class Search {
	public static Artist searchArtist(String search) {
		Artist searchedArtist = null;
		ArrayList<Producer> temp = DataStorage.getDatabase();
		for (Producer producer : temp) {
			ArrayList<Artist> temp2 = producer.getArtistList();
			for (Artist artist : temp2)
				if (search.equalsIgnoreCase(artist.getName())) {
					searchedArtist = artist;
					return searchedArtist;
				}
		}
		return null;
	}

	public static Artist searchVA(String search, Producer producer) {
		Artist searchedArtist = null;
		try {
			ArrayList<Artist> temp2 = producer.getArtistList();
			for (Artist artist : temp2)
				if (search.equalsIgnoreCase(artist.getName())) {
					searchedArtist = artist;
					return searchedArtist;
				}
		} catch (NullPointerException e) {
			System.out.println("Unsuccesful producer creation.");
		}
		return null;
	}

	public static User searchUser(String username, String password) {
		ArrayList<User> temp = DataStorage.getUsers();
		for (User userTemp : temp) {
			if (userTemp.getUsername().equalsIgnoreCase(username)) {
				if (userTemp.getPassword().equalsIgnoreCase(password))
					return userTemp;
				else
					return null;
			}
		}
		return null;
	}

	public static User searchUserByName(String username) {
		ArrayList<User> temp = DataStorage.getUsers();
		for (User userTemp : temp) {
			if (userTemp.getUsername().equalsIgnoreCase(username)) {
				return userTemp;
			}
		}
		return null;
	}

	public static boolean checkUsername(String username) {
		ArrayList<User> temp = DataStorage.getUsers();
		for (User userTemp : temp) {
			if (userTemp.getUsername().equalsIgnoreCase(username)) {
				return false;
			}
		}
		return true;
	}

	public static Song searchSong(String search, Artist songArtist) {
		ArrayList<Album> albumsList = songArtist.getAlbums();
		for (Album album : albumsList) {
			ArrayList<Song> temp = album.getSongs();
			for (Song song : temp)
				if (search.equalsIgnoreCase(song.getSongTitle()))
					return song;
		}
		if (Search.searchArtist("Various artists") != null) {
			albumsList = Search.searchArtist("Various artists").getAlbums();
			for (Album album : albumsList) {
				ArrayList<Song> temp = album.getSongs();
				for (Song song : temp){
					if (search.equalsIgnoreCase(song.getSongTitle()));
						if (song.getSongArtist().getName().equals(songArtist.getName()))
							return song;}
			}
		}
		return null;
	}

	public static Album searchAlbum(String search, Artist artist) {
		if (artist.getName().equalsIgnoreCase("Various artists")) {
			ArrayList<Producer> temp = DataStorage.getDatabase();
			for (Producer producer : temp) {
				ArrayList<Artist> temp2 = producer.getArtistList();
				for (Artist artistTemp : temp2) {{
						ArrayList<Album> temp3 = artistTemp.getAlbums();
						for (Album albumTemp : temp3) {
							if (albumTemp.getAlbumName().equalsIgnoreCase(search))
								return albumTemp;
						}
					}
				}
			}
		} else {
			Album searchedAlbum = null;
			ArrayList<Album> albumsList;
			try {
				albumsList = artist.getAlbums();
			} catch (NullPointerException ex) {
				return null;
			}
			if (albumsList == null)
				System.out.println("Empty list");
			for (Album album : albumsList) {
				if (search.equalsIgnoreCase(album.getAlbumName())) {
					searchedAlbum = album;
					return searchedAlbum;
				}
			}
		}
		return null;
	}

	public static Producer searchProducer(String search) {
		Producer searchedProducer = null;
		ArrayList<Producer> temp = DataStorage.getDatabase();
		for (Producer producer : temp) {
			if (search.equalsIgnoreCase(producer.getName())) {
				searchedProducer = producer;
				return searchedProducer;
			}
		}
		return null;
	}

	public static Album searchAlbumByID(int id) {
		ArrayList<Producer> tempProd = DataStorage.getDatabase();
		ArrayList<Artist> tempArtist;
		ArrayList<Album> tempAlbum;
		for (Producer tempP : tempProd) {
			tempArtist = tempP.getArtistList();
			for (Artist tempA : tempArtist) {
				tempAlbum = tempA.getAlbums();
				for (Album tempAl : tempAlbum) {
					if (tempAl.getId() == id)
						return tempAl;
				}
			}
		}
		return null;
	}
}
