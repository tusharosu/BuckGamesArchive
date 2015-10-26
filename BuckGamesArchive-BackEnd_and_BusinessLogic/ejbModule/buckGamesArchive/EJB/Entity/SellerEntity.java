package buckGamesArchive.EJB.Entity;
import java.io.*;

import javax.persistence.*;

@Entity
@Table(name = "SELLER")
public class SellerEntity implements Serializable{
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="SELLID")
	private String SellID = "";
	
	public String getSellID(){
		return this.SellID;
	}
	public void setSellID(String sellID){
		this.SellID = sellID;
	}
	
	@Id
	@Column(name="USEDGNAME")
	private String UsedGName = "";
	
	public String getUsedGName() {return UsedGName;}

	public void setUsedGName(String UsedGName) { this.UsedGName = UsedGName;}
	
	
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
	
	@Column(name="PASSWORD")
	private String Password="";
	
	public String getPassword(){
		return this.Password;
	}
	public void setPassword(String password){
		this.Password = password;
	}
	
	@Column(name="YEAROFPURCHASE")
	private int YearofPurchase;
	
	public void setYearofPurchase(int YearofPurchase) {
		this.YearofPurchase = YearofPurchase;
	}
	public int getYearofPurchase() {
		return this.YearofPurchase;
	}


	
	
	@Column(name="ITEMQTY")
	private int ItemQty;

	public void setItemQty(int ItemQty) {
		this.ItemQty = ItemQty;
	}
	public int getItemQty() {
		return this.ItemQty;
	}
	
}
