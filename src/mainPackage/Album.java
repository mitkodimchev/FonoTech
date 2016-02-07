package mainPackage;
import java.time.Duration;
import java.util.ArrayList;

public class Album {
	private String albumName;
	private Duration length;
	private Artist artist;
	private int year;
	private Producer producer;
	ArrayList<Song> songs = new ArrayList<Song>() ;
	int id;

	public Artist getArtist() {
		return artist;
	}
	
	public int getYear() {
		return year;
	}

	public String getAlbumName() {
		return albumName;
	}
	public Producer getProducer() {
		return producer;
	}

	public Duration getLength() {
		return length;
	}
	
	public Album(String albumName, Artist artist, int year, Producer producer){
		this.albumName = albumName;
		this.artist = artist;
		this.year = year;
		this.producer = producer;
		this.length = DurationFono.durationOfSong(0, 0);
		artist.addAlbum(this);
		this.id=idGenerator(this);
	}
	
	public Album(){
		this.length = DurationFono.durationOfSong(0, 0);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public void setLength(Duration length) {
		this.length = length;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public void setSongs(ArrayList<Song> songs) {
		this.songs = songs;
	}

	public void addSong(Song song){
		this.songs.add(song);
		song.setAlbum(this);
	}
	
	public ArrayList<Song> getSongs() {
		return songs;
	}
	
	public void addToDuration(Duration duration){
		if(duration!=null)
			this.length = this.length.plus(duration);
	}
	
	public int idGenerator(Album album){
		int id = (album.getAlbumName().hashCode())+(album.getArtist().getName().hashCode());
		return id;
	}
}
