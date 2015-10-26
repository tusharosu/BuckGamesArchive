/**
 * 
 */
package edu.osu;

import javax.ejb.EJB;

import buckGamesArchive.EJB.Service.PostGameService;


/**
 * @author Vignesh
 *
 */
public class PostGameBean {
	private LoginBean logBean;

	public LoginBean getLogBean() {
		return logBean;
	}

	public void setLogBean(LoginBean logBean) {
		this.logBean = logBean;
	}

	@EJB
	private PostGameService postGameService;
	
	private String gamename;
	private String developer;
	private String genre;
	private String platform;
	private double price;
	private int yearOfPurchase;
	private int itemQty;

	public String getgamename() {
		return gamename;
	}

	public void setgamename(String gname) {
		this.gamename = gname;
	}

	public String getdeveloper() {
		return developer;
	}

	public void setdeveloper(String developer) {
		this.developer = developer;
	}

	public String getgenre() {
		return genre;
	}

	public void setgenre(String genre) {
		this.genre = genre;
	}

	public String getplatform() {
		return platform;
	}

	public void setplatform(String platform) {
		this.platform = platform;
	}

	public String getprice() {
		return String.valueOf(this.price);
	}

	public void setprice(String price) {
		this.price = Double.parseDouble(price);
	}

	public String getyearOfPurchase() {
		return String.valueOf(this.yearOfPurchase);
	}

	public void setyearOfPurchase(String year) {
		this.yearOfPurchase = Integer.parseInt(year);
	}

	public String getitemQty() {
		return String.valueOf(this.itemQty);
	}

	public void setitemQty(String qty) {
		this.itemQty = Integer.parseInt(qty);
	}

	public String add() {
		String ret = postGameService.postGame(gamename, developer, genre,
				platform, price, yearOfPurchase, itemQty, logBean.getosuID());
		if (!ret.equalsIgnoreCase("Exists") && !ret.equalsIgnoreCase("false"))
			return "true";
		else
			return "false";
	}

}
