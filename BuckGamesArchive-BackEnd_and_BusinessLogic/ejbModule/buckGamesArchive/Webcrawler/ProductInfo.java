package buckGamesArchive.Webcrawler;

public class ProductInfo {

	public String ItemID;
	public String Title;
	public Double ConvertedCurrentPrice;


	public ProductInfo(String ItemID, String Title, Double ConvertedCurrentPrice) {
		this.ItemID = ItemID;
		this.Title = Title;
		this.ConvertedCurrentPrice = ConvertedCurrentPrice;
		
	}

	

	public void setItemID(String name) {
		this.ItemID = name;
	}

	public String getItemID() {
		return this.ItemID;
	}

	public void setTitle(String name) {
		this.Title = name;
	}

	public String getTitle() {
		return this.Title;
	}

	public void setConvertedCurrentPrice(Double ConvertedCurrentPrice) {
		this.ConvertedCurrentPrice = ConvertedCurrentPrice;
	}

	public Double getConvertedCurrentPrice() {
		return this.ConvertedCurrentPrice;
	}



	
	
}
