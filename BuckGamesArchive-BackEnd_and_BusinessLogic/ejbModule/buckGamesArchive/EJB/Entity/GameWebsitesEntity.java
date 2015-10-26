package buckGamesArchive.EJB.Entity;
import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "GAMEWEBSITES")
public class GameWebsitesEntity implements Serializable{
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	
	
	@Id
	@Column(name="URL")
	private String URL;
	
	public void setURL(String name) {
		this.URL = name;
	}

	public String getURL() {
		return this.URL;
	}
	
	
	@Column(name="VENDORNAME")
	private String VendorName;
	
	public void setVendorName(String VendorName) {
		this.VendorName = VendorName;
	}

	public String getVendorName() {
		return this.VendorName;
	}

		
}
