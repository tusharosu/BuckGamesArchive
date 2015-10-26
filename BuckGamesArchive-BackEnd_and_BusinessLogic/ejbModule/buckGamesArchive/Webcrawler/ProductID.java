package buckGamesArchive.Webcrawler;

public class ProductID {
	public String ID;
	public String DomainName;
	public ProductID(String ID,String DomainName)
	{
		this.ID=ID;
		this.DomainName=DomainName;
	}
	public void setID(String ID) {
		this.ID = ID;
	}

	public String getID() {
		return this.ID;
	}
	public void setDomainName(String name) {
		this.DomainName = name;
	}

	public String getDomainName() {
		return this.DomainName;
	}
}
