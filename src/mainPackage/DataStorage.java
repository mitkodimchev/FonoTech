package mainPackage;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class DataStorage {
	private static ArrayList<Producer> database = new ArrayList<Producer>();
	private static ArrayList<User> users = new ArrayList<User>();

	public static void storeProducer(Producer producer) {
		database.add(producer);
	}

	public static void storeUser(User user) {
		users.add(user);
	}

	public static ArrayList<Producer> getDatabase() {
		return database;
	}

	public static ArrayList<User> getUsers() {
		return users;
	}

	private static boolean createNode(XMLEventWriter eventWriter, String name, String value) throws XMLStreamException {

		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		// create Start node
		StartElement sElement = eventFactory.createStartElement("", "", name);
		eventWriter.add(tab);
		eventWriter.add(sElement);
		// create Content
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		// create End node
		EndElement eElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(eElement);
		eventWriter.add(end);
		return false;
	}

	public static void saveData() throws Exception {
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream("Database.xml"));
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent tab = eventFactory.createDTD("\t");
		XMLEvent tab2 = eventFactory.createDTD("\t\t");
		XMLEvent tab3 = eventFactory.createDTD("\t\t\t");
		XMLEvent end = eventFactory.createDTD("\n");
		StartDocument startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);
		eventWriter.add(end);
		StartElement configStartElement = eventFactory.createStartElement("", "", "FonoTech_data_producers");
		eventWriter.add(configStartElement);
		eventWriter.add(end);
		Iterator<Producer> itr = database.iterator();
		while (itr.hasNext()) {
			Producer current = itr.next();
			eventWriter.add(eventFactory.createStartElement("", "", "producer"));
			eventWriter.add(eventFactory.createAttribute("producerName", current.getName()));
			eventWriter.add(end);
			createNode(eventWriter, "producerOwner", current.getOwner());
			ArrayList<Artist> artistList = current.getArtistList();
<<<<<<< HEAD
			for(Artist currentArtist:artistList){
				if(currentArtist.getName()!=null){
				eventWriter.add(tab);
				eventWriter.add(eventFactory.createStartElement("", "", "artist"));
				eventWriter.add(end);
=======
			Iterator<Artist> itrArtist = artistList.iterator();
			while (itrArtist.hasNext()) {
				eventWriter.add(tab);
				eventWriter.add(eventFactory.createStartElement("", "", "artist"));
				eventWriter.add(end);
				Artist currentArtist = itrArtist.next();
>>>>>>> origin/master
				eventWriter.add(tab);
				createNode(eventWriter, "artistName", currentArtist.getName());
				eventWriter.add(tab);
				createNode(eventWriter, "artistProducer", currentArtist.getProducer().getName());
				ArrayList<Album> albumList = currentArtist.getAlbums();
				Iterator<Album> itrAlbum = albumList.iterator();
				while (itrAlbum.hasNext()) {
					eventWriter.add(tab2);
					eventWriter.add(eventFactory.createStartElement("", "", "album"));
					eventWriter.add(end);
					Album currentAlbum = itrAlbum.next();
					eventWriter.add(tab2);
					createNode(eventWriter, "albumName", currentAlbum.getAlbumName());
					eventWriter.add(tab2);
<<<<<<< HEAD
					createNode(eventWriter, "duration", DurationFono.toString(currentAlbum.getLength()));
=======
					createNode(eventWriter, "duration", DurationFono.durationToString(currentAlbum.getLength()));
					eventWriter.add(tab2);
					createNode(eventWriter, "artist", currentAlbum.getArtist().getName());
>>>>>>> origin/master
					eventWriter.add(tab2);
					createNode(eventWriter, "albumYear", Integer.toString(currentAlbum.getYear()));
					ArrayList<Song> songList = currentAlbum.getSongs();
					Iterator<Song> itrSong = songList.iterator();
					while (itrSong.hasNext()) {
						eventWriter.add(tab3);
						eventWriter.add(eventFactory.createStartElement("", "", "song"));
						eventWriter.add(end);
						Song currentSong = itrSong.next();
						eventWriter.add(tab3);
						createNode(eventWriter, "songTitle", currentSong.getSongTitle());
						eventWriter.add(tab3);
						createNode(eventWriter, "songArtist", currentSong.getSongArtist().getName());
						eventWriter.add(tab3);
						createNode(eventWriter, "albumOfSong", currentSong.getAlbum().getAlbumName());
						eventWriter.add(tab3);
						createNode(eventWriter, "albumArtist", currentSong.getAlbum().getArtist().getName());
						eventWriter.add(tab3);
<<<<<<< HEAD
						createNode(eventWriter, "length", DurationFono.toString(currentSong.getLength()));
=======
						createNode(eventWriter, "length", DurationFono.durationToString(currentSong.getLength()));
>>>>>>> origin/master
						eventWriter.add(tab3);
						createNode(eventWriter, "year", Integer.toString(currentSong.getYear()));
						eventWriter.add(tab3);
						createNode(eventWriter, "arrangementBy", currentSong.getArrangementBy());
						eventWriter.add(tab3);
						createNode(eventWriter, "musicBy", currentSong.getMusicBy());
						eventWriter.add(tab3);
						createNode(eventWriter, "lyricsBy", currentSong.getLyricsBy());
						eventWriter.add(tab3);
						createNode(eventWriter, "hasVideo", Boolean.toString(currentSong.isHasVideo()));
						eventWriter.add(tab3);
						eventWriter.add(eventFactory.createEndElement("", "", "song"));
						eventWriter.add(end);
					}
					eventWriter.add(tab2);
					eventWriter.add(eventFactory.createEndElement("", "", "album"));
					eventWriter.add(end);
				}
				eventWriter.add(tab);
				eventWriter.add(eventFactory.createEndElement("", "", "artist"));
				eventWriter.add(tab);
				eventWriter.add(end);
<<<<<<< HEAD
				}
=======
>>>>>>> origin/master
			}
			eventWriter.add(eventFactory.createEndElement("", "", "producer"));
			eventWriter.add(end);
		}
		eventWriter.add(eventFactory.createEndElement("", "", "FonoTech_data"));
		eventWriter.add(end);
		eventWriter.close();
	}

	public static void saveUsers() throws Exception {
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream("users.xml"));
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent tab = eventFactory.createDTD("\t");
		XMLEvent end = eventFactory.createDTD("\n");
		StartDocument startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);
		eventWriter.add(end);
		StartElement configStartElement = eventFactory.createStartElement("", "", "FonoTech_users");
		eventWriter.add(configStartElement);
		eventWriter.add(end);
		Iterator<User> itr = users.iterator();
		while (itr.hasNext()) {
			User current = itr.next();
			if (current != null) {
				eventWriter.add(tab);
				eventWriter.add(eventFactory.createStartElement("", "", "user"));
				if (current instanceof Administrator) {
					eventWriter.add(eventFactory.createAttribute("typeOfUser", "Administrator"));
				}
				if (current instanceof Member) {
					eventWriter.add(eventFactory.createAttribute("typeOfUser", "Member"));
				}
				eventWriter.add(end);
				eventWriter.add(tab);
				createNode(eventWriter, "username", current.getUsername());
				eventWriter.add(tab);
				createNode(eventWriter, "password", current.getPassword());
				eventWriter.add(tab);
				eventWriter.add(eventFactory.createEndElement("", "", "user"));
				eventWriter.add(end);
			}
		}
		eventWriter.add(eventFactory.createEndElement("", "", "FonoTech_users"));
		eventWriter.add(end);
		eventWriter.close();
	}
	
	public static void saveUserData(Member user) throws Exception {
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream("C:\\Users\\mitko\\Projects\\FonoTech\\users\\"+user.getUsername().hashCode()+".xml"));
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent tab = eventFactory.createDTD("\t");
		XMLEvent end = eventFactory.createDTD("\n");
		StartDocument startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);
		eventWriter.add(end);
		StartElement configStartElement = eventFactory.createStartElement("", "", "FonoTech_"+user.getUsername());
		eventWriter.add(configStartElement);
		eventWriter.add(end);
		Iterator<Integer> itr = user.getAlbumCollection().iterator();
		while (itr.hasNext()) {
			int current = itr.next();
			if (current != 0) {
				eventWriter.add(tab);
				eventWriter.add(eventFactory.createStartElement("", "", "albumCollection"));
				eventWriter.add(end);
				eventWriter.add(tab);
				createNode(eventWriter, "id", Integer.toString(current));
				eventWriter.add(tab);
				eventWriter.add(eventFactory.createEndElement("", "", "albumCollection"));
				eventWriter.add(end);
			}
		}
		eventWriter.add(eventFactory.createEndElement("", "", "FonoTech_"+user.getUsername()));
		eventWriter.add(end);
		eventWriter.close();
	}

	public static void setDatabase(ArrayList<Producer> database) {
		DataStorage.database = database;
	}

	public static void setUsers(ArrayList<User> users) {
		DataStorage.users = users;
	}

}
