package buckGamesArchive.EJB.Service;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.ConstraintViolationException;


import javax.ejb.Stateless;

import buckGamesArchive.EJB.Entity.*;

@Stateless
public class LoginSession {
	@PersistenceContext(unitName = "buckGames-EJB")
	EntityManager em;

	public String userName(String osuID, String password, String type) { // method
																			// called
																			// from
																			// LoginBean
																			// Backing
																			// Bean

		List<UserEntity> loginUserList;
		List<SellerEntity> loginSellerList;

		if (type.equals("Buyer")) {
			try {
				Query query = em.createNativeQuery // create query using this
													// syntax
						("select * from USER where OSUID =  '" + osuID + "'"
								+ " AND PASSWORD = '" + password + "'",
								UserEntity.class);

				loginUserList = query.getResultList(); // gets results from
														// query
														// and puts into a list
														// of
														// that object type

				if (!loginUserList.isEmpty()) // if user actually exists
				{
					return "success";
				}
			}

			catch (Exception e) {
				return "fail";
			}

			return "fail";
		} else {
			try {
				Query query = em.createNativeQuery // create query using this
													// syntax
						("select * from SELLER where SELLID =  '" + osuID + "'"
								+ " AND PASSWORD = '" + password + "'"
								+ "AND USEDGNAME=''", SellerEntity.class);

				loginSellerList = query.getResultList(); // gets results from
															// query
															// and puts into a
															// list
															// of
															// that object type

				if (!loginSellerList.isEmpty()) // if user actually exists
				{
					return "success";
				}
			}

			catch (Exception e) {
				return "fail";
			}

			return "fail";
		}
	}

}
