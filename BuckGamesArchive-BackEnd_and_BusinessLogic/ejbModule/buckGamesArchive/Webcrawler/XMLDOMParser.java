package buckGamesArchive.Webcrawler;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import buckGamesArchive.EJB.Entity.GameEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class XMLDOMParser {

	public static void main(String argv[]) {
		List<ProductInfo> finalPList = new ArrayList<ProductInfo>();
		try {
			List<ProductID> productIDList = new ArrayList<ProductID>();
			productIDList = GetProductIDs();
			Set<String> hashSet = new HashSet<String>();
			for (ProductID prod : productIDList) {
				String productID = prod.ID;
				String pathProduct = "http://open.api.ebay.com/shopping?"
						+ "callname=FindProducts&responseencoding=XML&appid="
						+ "OSU1c4550-b201-46d1-8dde-713decd2c3a"
						+ "&siteid=0&version=525&"
						+ "ProductID.type=Reference&" + "ProductID.Value="
						+ productID + "&IncludeSelector=Items"
						+ "&AvailableItemsOnly=true&MaxEntries=5";
				URL url = new URL(pathProduct);
				URLConnection conn = url.openConnection();

				DocumentBuilderFactory factory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(conn.getInputStream());

				TransformerFactory factory1 = TransformerFactory.newInstance();
				Transformer xform = factory1.newTransformer();
				File myOutput = new File(
						"C:\\Users\\rangaishenvi.1\\workspace\\BuckGamesArchive-BackEnd_and_BusinessLogic\\ejbModule\\buckGamesArchive\\Webcrawler"
								+ "\\ProductInfo.xml");
				xform.transform(new DOMSource((Node) doc), new StreamResult(
						myOutput));
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document docOut = dBuilder.parse(myOutput);

				docOut.getDocumentElement().normalize();
				NodeList nList = docOut.getElementsByTagName("Item");
				List<ProductInfo> pList = new ArrayList<ProductInfo>();
				for (int temp = 0; temp < nList.getLength(); temp++) {

					Node nNode = nList.item(temp);

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;

						String ItemID = eElement.getElementsByTagName("ItemID")
								.item(0).getTextContent();
						String Title = eElement.getElementsByTagName("Title")
								.item(0).getTextContent();
						Double Currency = Double.parseDouble(eElement
								.getElementsByTagName("ConvertedCurrentPrice")
								.item(0).getTextContent());
						ProductInfo p = new ProductInfo(ItemID, Title, Currency);
						pList.add(p);

					}
				}
				String NewPCID = "";
				String NewXBOXID = "";
				String NewPSID = "";
				Double min1 = 100000.0;
				Double min2 = 100000.0;
				Double min3 = 100000.0;
				String psTitle = "";
				String xboxTitle = "";
				String pcTitle="";
				ProductInfo product = null;

				for (ProductInfo pr : pList) {

					if ((pr.Title.toLowerCase().contains("ps")
							|| pr.Title.toLowerCase().contains("playstation") || pr.Title
							.toLowerCase().contains("sony"))
							&& !pr.Title.toLowerCase().contains("used")) {
						if (pr.ConvertedCurrentPrice < min1) {
							min1 = pr.ConvertedCurrentPrice;
							NewPSID = pr.ItemID;
							psTitle = pr.Title;
						}
					}
				}
				product = new ProductInfo(NewPSID, psTitle, min1);
				if (!psTitle.isEmpty() && !NewPSID.isEmpty()) {
					if (!hashSet.contains(psTitle + "ebay")) {
						hashSet.add(psTitle + "ebay");
						finalPList.add(product);
					}
				}

				for (ProductInfo pr : pList) {

					if ((pr.Title.toLowerCase().contains("xbox"))
							&& !pr.Title.toLowerCase().contains("used")) {
						if (pr.ConvertedCurrentPrice < min2) {
							min2 = pr.ConvertedCurrentPrice;
							NewXBOXID = pr.ItemID;
							xboxTitle = pr.Title;

						}
					}
				}
				product = new ProductInfo(NewXBOXID, xboxTitle, min2);
				if (!xboxTitle.isEmpty() && !NewXBOXID.isEmpty()) {
					if (!hashSet.contains(xboxTitle + "ebay")) {
						hashSet.add(xboxTitle + "ebay");
						finalPList.add(product);
					}
				}
				
				for (ProductInfo pr : pList) {

					if ((pr.Title.toLowerCase().contains("pc"))
							&& !pr.Title.toLowerCase().contains("used")) {
						if (pr.ConvertedCurrentPrice < min3) {
							min3 = pr.ConvertedCurrentPrice;
							NewPCID = pr.ItemID;
							pcTitle = pr.Title;

						}
					}
				}
				product = new ProductInfo(NewPCID, pcTitle, min3);
				if (!pcTitle.isEmpty() && !NewPCID.isEmpty()) {
					if (!hashSet.contains(pcTitle + "ebay")) {
						hashSet.add(pcTitle + "ebay");
						finalPList.add(product);
					}
				}
			}
			List<GameEntity> gameList = new ArrayList<GameEntity>();
			for (ProductInfo prInfo : finalPList) {
				GameEntity g = GetItemInfo(prInfo);
				gameList.add(g);
			}
			for (GameEntity game : gameList) {
				System.out.println("GName: " + game.getGname());
				System.out.println("Price: " + game.getPrice());
				System.out.println("Platform: " + game.getPlatform());
				System.out.println("Genre: " + game.getGenre());
				System.out.println("Game Buy URL: " + game.getGameBuyURL());
				System.out.println("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<ProductID> GetProductIDs() {
		List<ProductID> productIDList = new ArrayList<ProductID>();
		try {

			String pathProduct = "http://open.api.ebay.com/shopping?"
					+ "callname=FindProducts&responseencoding=XML&appid="
					+ "OSU1c4550-b201-46d1-8dde-713decd2c3a"
					+ "&siteid=0&version=525&&QueryKeywords"
					+ "=FIFA%2014&IncludeSelector=Items"
					+ "&AvailableItemsOnly=true&MaxEntries=5";
			URL url = new URL(pathProduct);
			URLConnection conn = url.openConnection();

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(conn.getInputStream());

			TransformerFactory factory1 = TransformerFactory.newInstance();
			Transformer xform = factory1.newTransformer();
			File myOutput = new File(
					"C:\\Users\\rangaishenvi.1\\workspace\\BuckGamesArchive-BackEnd_and_BusinessLogic\\ejbModule\\buckGamesArchive\\Webcrawler\\ProductID.xml");
			xform.transform(new DOMSource((Node) doc), new StreamResult(
					myOutput));
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document docOut = dBuilder.parse(myOutput);

			docOut.getDocumentElement().normalize();

			NodeList nList = docOut.getElementsByTagName("Product");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String ID = eElement.getElementsByTagName("ProductID")
							.item(0).getTextContent();
					String DomainName = eElement
							.getElementsByTagName("DomainName").item(0)
							.getTextContent();
					if (!DomainName.isEmpty()
							&& DomainName.toLowerCase().contains("game")) {
						ProductID p = new ProductID(ID, DomainName);
						productIDList.add(p);
					}
				}
			}
			/*for (ProductID pr : productIDList) {
				System.out.println(pr.ID);
			}*/
		} catch (Exception ex) {
			return null;
		}
		return productIDList;
	}

	public static GameEntity GetItemInfo(ProductInfo prodInfo) {
		GameEntity gam = new GameEntity();
		String GName = "";
		String VendorID = "ebay";
		String Developer = "";
		String Genre = "";
		String Platform = "";
		double Price = 0.0;
		double Rating = 0.0;
		String GameBuyURL = "";
		try {
			String ItemID = prodInfo.ItemID;
			String pathProduct = "http://open.api.ebay.com/shopping?callname"
					+ "=GetSingleItem&responseencoding=XML&"
					+ "appid=OSU1c4550-b201-46d1-8dde-713decd2c3a&"
					+ "siteid=0&version=615&ItemID=" + ItemID
					+ "&IncludeSelector=Variations,ItemSpecifics";
			URL url = new URL(pathProduct);
			URLConnection conn = url.openConnection();

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(conn.getInputStream());

			TransformerFactory factory1 = TransformerFactory.newInstance();
			Transformer xform = factory1.newTransformer();
			File myOutput = new File(
					"C:\\Users\\rangaishenvi.1\\workspace\\BuckGamesArchive-BackEnd_and_BusinessLogic\\ejbModule\\buckGamesArchive\\Webcrawler\\ItemID.xml");
			xform.transform(new DOMSource((Node) doc), new StreamResult(
					myOutput));
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document docOut = dBuilder.parse(myOutput);
			docOut.getDocumentElement().normalize();
            
			Price = Double.parseDouble(docOut
					.getElementsByTagName("ConvertedCurrentPrice").item(0)
					.getTextContent());
			gam.setPrice(Price);
			GameBuyURL = docOut
					.getElementsByTagName("ViewItemURLForNaturalSearch")
					.item(0).getTextContent();
			gam.setGameBuyURL(GameBuyURL);
			GName = docOut.getElementsByTagName("Title").item(0)
					.getTextContent();
			gam.setGname(GName);
			NodeList nList = docOut.getElementsByTagName("NameValueList");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					String ID = eElement
								.getElementsByTagName("Name").item(0)
								.getTextContent();
					gam.setGameID(ID);
							if (ID.equalsIgnoreCase("Genre")) {
								Genre = eElement
										.getElementsByTagName("Value").item(0)
										.getTextContent();
						         
							} else if (ID.equalsIgnoreCase("Platform")) {
								Platform = eElement
										.getElementsByTagName("Value").item(0)
										.getTextContent();
						}
					}
				gam.setGenre(Genre);
				gam.setPlatform(Platform);;
				}
			//gam = new Game();
		} catch (Exception ex) {
			return gam;
		}
		return gam;
	}
}
