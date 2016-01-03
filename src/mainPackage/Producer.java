package mainPackage;
import java.util.ArrayList;

public class Producer {
	private String name;
	private String owner;
	private ArrayList<Artist> artistCatalog;
	
	public Producer(String name, String owner){
		this.name = name;
		this.owner = owner;
		this.artistCatalog = new ArrayList<Artist>();
		DataStorage.storeProducer(this);
	}
	
	public Producer(){
		this.artistCatalog = new ArrayList<Artist>();
		DataStorage.storeProducer(this);
	}
	
	public String getName() {
		return name;
	}

	public String getOwner() {
		return owner;
	}

	public void addArtist(Artist artist){
		artistCatalog.add(artist);
	}
	
	public void printProducerInfo(){
		System.out.format("%s %s", name,owner);
	}

	public ArrayList<Artist> getArtistList() {
		return artistCatalog;
	}

	public void setArtistCatalog(ArrayList<Artist> artistCatalog) {
		this.artistCatalog = artistCatalog;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}
