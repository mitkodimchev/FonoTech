package mainPackage;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DataReader extends DefaultHandler {
	ArrayList<Producer> producerList;
	String temp;
	Producer producerTemp;
	Artist artistTemp;
	Album albumTemp;
	Song songTemp;
<<<<<<< HEAD
	String albumArtist;
=======
>>>>>>> origin/master

	public DataReader(String XmlFileName) {
		producerList = new ArrayList<Producer>();
		parseDocument(XmlFileName);
		DataStorage.setDatabase(producerList);
	}

	private void parseDocument(String XmlFileName) {
		// parse
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(XmlFileName, this);
		} catch (ParserConfigurationException e) {
			System.out.println("ParserConfig error");
		} catch (SAXException e) {
			System.out.println("Xml not well formed");
		} catch (IOException e) {
			System.out.println("IO error");
		}
	}

	@Override
	public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {
<<<<<<< HEAD
=======
		// if current element is book , create new book
		// clear tmpValue on start of element

>>>>>>> origin/master
		if (elementName.equalsIgnoreCase("producer")) {
			producerTemp = new Producer();
			producerTemp.setName(attributes.getValue("producerName"));
		}
		if (elementName.equalsIgnoreCase("artist")) {
			artistTemp = new Artist();
		}
		if (elementName.equalsIgnoreCase("album")) {
			albumTemp = new Album();
		}
		if (elementName.equalsIgnoreCase("song")) {
			songTemp = new Song();
		}
	}

	@Override
	public void endElement(String s, String s1, String element) throws SAXException {
<<<<<<< HEAD
=======
		// if end of book element add to list
>>>>>>> origin/master
		if (element.equalsIgnoreCase("producer")) {
			producerList.add(producerTemp);
		}
		if (element.equalsIgnoreCase("producerOwner")) {
			producerTemp.setOwner(temp);
		}
		if (element.equalsIgnoreCase("artist")) {
<<<<<<< HEAD
			artistTemp.setProducer(producerTemp);
		}
		if (element.equalsIgnoreCase("artistName")) {
			artistTemp.setName(temp);
			producerTemp.addArtist(artistTemp);
=======
			if (artistTemp.getName() != null) {
				artistTemp.setProducer(producerTemp);
				producerTemp.addArtist(artistTemp);
			}
		}
		if (element.equalsIgnoreCase("artistName")) {
			artistTemp.setName(temp);
>>>>>>> origin/master
		}
		if (element.equalsIgnoreCase("albumName")) {
			albumTemp.setAlbumName(temp);
			albumTemp.setArtist(artistTemp);
			albumTemp.setProducer(producerTemp);
<<<<<<< HEAD
=======
			producerTemp.addArtist(artistTemp);
>>>>>>> origin/master
			artistTemp.setProducer(producerTemp);
			artistTemp.addAlbum(albumTemp);
		}
		if (element.equalsIgnoreCase("albumYear")) {
			albumTemp.setYear(Integer.parseInt(temp));
		}
		if (element.equalsIgnoreCase("songTitle")) {
			songTemp.setSongTitle(temp);
		}
		if (element.equalsIgnoreCase("songArtist")) {
			songTemp.setSongArtist(Search.searchArtist(temp));
		}
		if (element.equalsIgnoreCase("albumOfSong")) {
			songTemp.setAlbum(albumTemp);
		}
		if (element.equalsIgnoreCase("songArtist")) {
			songTemp.setSongArtist(Search.searchArtist(temp));
		}
		if (element.equalsIgnoreCase("length")) {
<<<<<<< HEAD
			songTemp.setLength(DurationFono.toDuration(temp));
=======
			songTemp.setLength(DurationFono.stringToDuration(temp));
>>>>>>> origin/master
		}
		if (element.equalsIgnoreCase("year")) {
			songTemp.setYear(Integer.parseInt(temp));
		}
		if (element.equalsIgnoreCase("arrangementBy")) {
			songTemp.setArrangementBy(temp);
		}
		if (element.equalsIgnoreCase("musicBy")) {
			songTemp.setMusicBy(temp);
		}
		if (element.equalsIgnoreCase("lyricsBy")) {
			songTemp.setLyricsBy(temp);
		}
		if (element.equalsIgnoreCase("hasVideo")) {
			songTemp.setHasVideo(Boolean.parseBoolean(temp));
		}
		if (element.equalsIgnoreCase("song")) {
			if (songTemp.getSongArtist() != null) {
				albumTemp.addSong(songTemp);
				albumTemp.addToDuration(songTemp.getLength());
			}
		}
		if (element.equalsIgnoreCase("album")) {
			albumTemp.setId(albumTemp.idGenerator(albumTemp));
		}
	}

	@Override
	public void characters(char[] ac, int i, int j) throws SAXException {
		temp = new String(ac, i, j);
	}

}
