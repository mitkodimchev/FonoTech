package mainPackage;
import java.util.ArrayList;

public class Artist {
	private String name;
	private ArrayList<Album> albums;
	private Producer producer;
	
	public Artist(String name, Producer producer){
		this.name = name;
		this.albums = new ArrayList<Album>();
		this.producer = producer;
		producer.addArtist(this);
	}
	
	public Artist(){
		this.albums = new ArrayList<Album>();
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setAlbums(ArrayList<Album> albums) {
		this.albums = albums;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public Producer getProducer() {
		return producer;
	}

	public String getName(){
		return name;
	}
	
	public ArrayList<Album> getAlbums() {
		return albums;
	}

	public void addAlbum(Album album){
		albums.add(album);
	}
}
