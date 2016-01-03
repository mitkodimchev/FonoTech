package mainPackage;
import java.io.IOException;
	import java.util.ArrayList;

	import javax.xml.parsers.ParserConfigurationException;
	import javax.xml.parsers.SAXParser;
	import javax.xml.parsers.SAXParserFactory;

	import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
	import org.xml.sax.helpers.DefaultHandler;
	
public class UserDataReader extends DefaultHandler{

		ArrayList<Integer> collection;
		int id=0;
		String temp;

		public UserDataReader(String XmlFileName, Member user) {
			collection = new ArrayList<Integer>();
			parseDocument(XmlFileName);
			user.setAlbumCollection(collection);
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
			// if current element is book , create new book
			// clear tmpValue on start of element

			if (elementName.equals("albumCollection")) {
			}
		}

		@Override
		public void endElement(String s, String s1, String element) throws SAXException {
			// if end of book element add to list
			if (element.equals("albumCollection")) {
				collection.add(id);
			}
			if (element.equals("id")) {
				id = Integer.parseInt(temp);
			}

		}

		@Override
		public void characters(char[] ac, int i, int j) throws SAXException {
			temp = new String(ac, i, j);
		}

	}
