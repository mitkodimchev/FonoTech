package mainPackage;
import java.time.Duration;

public class Song {
	private Artist songArtist;
	private String songTitle;
	private Album album;
	private Duration length;
	private int year;
	private String arrangementBy;
	private String musicBy;
	private String lyricsBy;
	private boolean hasVideo;
	private Producer producer;
	
	
	
	public void setSongArtist(Artist songArtist) {
		this.songArtist = songArtist;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public void setLength(Duration length) {
		this.length = length;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setArrangementBy(String arrangementBy) {
		this.arrangementBy = arrangementBy;
	}

	public void setMusicBy(String musicBy) {
		this.musicBy = musicBy;
	}

	public void setLyricsBy(String lyricsBy) {
		this.lyricsBy = lyricsBy;
	}

	public void setHasVideo(boolean hasVideo) {
		this.hasVideo = hasVideo;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public Duration getLength() {
		return length;
	}
	
	public Artist getSongArtist() {
		return songArtist;
	}

	public Album getAlbum() {
		return album;
	}

	public int getYear() {
		return year;
	}

	public String getArrangementBy() {
		return arrangementBy;
	}

	public String getMusicBy() {
		return musicBy;
	}

	public String getLyricsBy() {
		return lyricsBy;
	}

	public boolean isHasVideo() {
		return hasVideo;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	public String getSongTitle() {
		return songTitle;
	}

	public Song (Artist songArtist, String songTitle, Album album, Duration length, int year, 
				String arrangementBy, String lyricsBy, String musicBy, boolean hasVideo, Producer producer){
		this.songArtist = songArtist;
		this.songTitle = songTitle;
		this.album = album;
		this.length = length; 
		this.year = year;
		this.arrangementBy = arrangementBy;
		this.lyricsBy = lyricsBy;
		this.musicBy = musicBy;
		this.hasVideo = hasVideo;
		this.producer = producer;
		album.addSong(this);
		album.addToDuration(length);
	}

	public Song() {
		// TODO Auto-generated constructor stub
	}
}
