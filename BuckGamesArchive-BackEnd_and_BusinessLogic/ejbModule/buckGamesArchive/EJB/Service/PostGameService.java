package buckGamesArchive.EJB.Service;

import javax.persistence.*;
import javax.validation.ConstraintViolationException;
import javax.ejb.Stateless;

import buckGamesArchive.EJB.Entity.*;

@Stateless
public class PostGameService {
	@PersistenceContext(unitName = "buckGames-EJB")
	EntityManager em;

	public String postGame(String gname, String developer, String genre,
			String platform, double price, int yearOfPurchase, int itemQty,
			String sellerID) {
		SellerEntity sellerEntity = new SellerEntity();

		if (!gname.isEmpty() && !sellerID.isEmpty() && (itemQty > 0)) {
			sellerEntity.setUsedGName(gname);
			sellerEntity.setDeveloper(developer);
			sellerEntity.setGenre(genre);
			sellerEntity.setPlatform(platform);
			sellerEntity.setPrice(price);
			sellerEntity.setYearofPurchase(yearOfPurchase);
			sellerEntity.setItemQty(itemQty);
			sellerEntity.setSellID(sellerID);

			try {
				em.persist(sellerEntity);
				em.flush();
			} catch (EntityExistsException e) {
				return "Exists";
			}

			catch (ConstraintViolationException e) {
				return "Exists";
			}

			catch (Exception e) {
				return "Exists";
			}
			return "true";
		}
		else
			return "false";
	}
}
