package buckGamesArchive.EJB.Service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import buckGamesArchive.EJB.Entity.SellerEntity;
import buckGamesArchive.EJB.Entity.UserEntity;

@Stateless
public class RegisterService {
	@PersistenceContext(unitName = "buckGames-EJB")
	EntityManager em;

	public String userName(String osuID, String password, String type) {

		if (type.equals("Buyer")) {
			UserEntity userEntity = new UserEntity();
			userEntity.setOSUID(osuID);
			userEntity.setPassword(password);
			try {
				em.persist(userEntity);
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
			return "BuckGamesArchive" + "OSUID =" + osuID;
		}

		else {
			SellerEntity sellerEntity = new SellerEntity();
			sellerEntity.setSellID(osuID);
			sellerEntity.setPassword(password);
			sellerEntity.setUsedGName("");
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
			return "BuckGamesArchive" + "Seller OSUID =" + osuID;
		}

	}

	

	// public String update(String user, String title, String location,
	// String pay, String technology) {
	//
	// List<HelloServiceEntity> ann;
	// Query query = em.createNativeQuery("update MessageTable set title = "
	// + "'" + title + "'" + " ," + " location = " + "'" + location
	// + "'" + " ," + " pay = " + "'" + pay + "'" + " ,"
	// + " technology = " + "'" + technology + "'"
	// + " where username like " + "'" + user + "'",
	// HelloServiceEntity.class);
	//
	// try {
	// query.executeUpdate();
	// em.flush();
	//
	// } catch (Exception e) {
	// return "fail";
	// }
	//
	// return "success";
	//
	// }



}
