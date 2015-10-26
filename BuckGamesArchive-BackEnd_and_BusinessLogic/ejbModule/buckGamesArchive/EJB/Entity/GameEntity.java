package buckGamesArchive.EJB.Entity;
import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "GAME")
public class GameEntity implements Serializable{
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="GNAME")
	private String Gname;
	
	public String getGname(){
		return this.Gname;
	}
	public void setGname(String gamename){
		this.Gname = gamename;
	}
	
	@Id
	@Column(name="GAMEID")
	private String GameID;
	public String getGameID() {return GameID;}

	public void setGameID(String gameID) { this.GameID = gameID;}
	
	
	@Column(name="DEVELOPER")
	private String Developer;
	public void setDeveloper(String name) {
		this.Developer = name;
	}

	public String getDeveloper() {
		return this.Developer;
		}
		
	@Column(name="GENRE")
	private String Genre;

	public void setGenre(String Genre) {
		this.Genre = Genre;
	}

	public String getGenre() {
		return this.Genre;
	}
	
	
	@Column(name="PLATFORM")
	private String Platform;

	public void setPlatform(String Platform) {
		this.Platform = Platform;
	}
	public String getPlatform() {
		return this.Platform;
	}

	@Column(name="PRICE")
	private double Price;
	
	public void setPrice(double price) {
		this.Price = price;
	}
	public double getPrice() {
		return this.Price;
	}
	
	@Column(name="RATING")
	private double Rating;
	
	public void setRating(double rating) {
		this.Rating = rating;
	}
	public double getRating() {
		return this.Rating;
	}


	
	
	@Column(name="GAMEBUYURL")
	private String GameBuyURL;

	public void setGameBuyURL(String gamebuyurl) {
		this.GameBuyURL = gamebuyurl;
	}
	public String getGameBuyURL() {
		return this.GameBuyURL;
	}
	
}
