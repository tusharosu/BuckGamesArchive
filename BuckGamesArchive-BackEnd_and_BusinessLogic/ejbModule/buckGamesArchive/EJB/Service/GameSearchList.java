package buckGamesArchive.EJB.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


import javax.ejb.Stateless;

import buckGamesArchive.EJB.Entity.*;

@Stateless
public class GameSearchList {
	@PersistenceContext(unitName = "buckGames-EJB")
	EntityManager em;

	public List<List<String>> findGame(String gname, String condition,
			String platform) {
		GameEntity newEntity = new GameEntity();

		List<List<String>> gameList = new ArrayList<List<String>>();
		List<GameEntity> games;
		List<SellerEntity> usedGames;

		Query query = null;
		boolean flag = false;
		boolean onlyUsed = false;
		if (condition.equalsIgnoreCase("new game")
				|| condition.equalsIgnoreCase("both")) {
			if (condition.equalsIgnoreCase("both")) {
				flag = true;
			}
			query = em.createNativeQuery(
					"select * from GAME where GNAME like '%" + gname
							+ "%' AND PLATFORM= '" + platform + "'",
					GameEntity.class);
		}

		else {
			flag = true;
			onlyUsed = true;
			/*
			 * query = em.createNativeQuery(
			 * "select * from GAME where GNAME like '" + gname +
			 * "' AND PLATFORM LIKE '" + platform + "'", GameEntity.class);
			 */
		}
		if (!onlyUsed) {
			games = query.getResultList();
			if (!games.isEmpty()) {
				int size = games.size();

				for (int i = 0; i < size; i++) {
					GameEntity game = new GameEntity();
					game = games.get(i);

					String gamename = game.getGname();
					String developer = game.getDeveloper();
					String genre = game.getGenre();
					String gameplatform = game.getPlatform();
					String price = String.valueOf(game.getPrice());
					String urlToBuy = game.getGameBuyURL();

					List<String> gameValue = new ArrayList<String>();

					gameValue.add(gamename);
					gameValue.add(developer);
					gameValue.add(genre);
					gameValue.add(gameplatform);
					gameValue.add(price);
					gameValue.add(urlToBuy);

					gameList.add(gameValue);
				}
				if (flag) {
					Query query1 = em.createNativeQuery(
							"select * from SELLER where USEDGNAME like '%"
									+ gname + "%' AND PLATFORM='" + platform
									+ "'", SellerEntity.class);
					usedGames = query1.getResultList();
					if (!usedGames.isEmpty()) {
						int usedGameSize = games.size();

						for (int i = 0; i < usedGameSize; i++) {
							SellerEntity game = new SellerEntity();
							game = usedGames.get(i);
							String gamename = game.getUsedGName();
							String developer = game.getDeveloper();
							String genre = game.getGenre();
							String gameplatform = game.getPlatform();
							String price = String.valueOf(game.getPrice());

							List<String> gameValue = new ArrayList<String>();

							gameValue.add(gamename);
							gameValue.add(developer);
							gameValue.add(genre);
							gameValue.add(gameplatform);
							gameValue.add(price);
							gameValue.add(game.getSellID() + "@osu.edu");

							gameList.add(gameValue);
						}

					}

					// return gameList;

				}
			} else {
				Query query1 = em.createNativeQuery(
						"select * from SELLER where USEDGNAME like '%" + gname
								+ "%' AND PLATFORM '" + platform + "'",
						SellerEntity.class);
				usedGames = query1.getResultList();
				if (!usedGames.isEmpty()) {
					int usedGameSize = games.size();

					for (int i = 0; i < usedGameSize; i++) {
						SellerEntity game = new SellerEntity();
						game = usedGames.get(i);
						String gamename = game.getUsedGName();
						String developer = game.getDeveloper();
						String genre = game.getGenre();
						String gameplatform = game.getPlatform();
						String price = String.valueOf(game.getPrice());

						List<String> gameValue = new ArrayList<String>();

						gameValue.add(gamename);
						gameValue.add(developer);
						gameValue.add(genre);
						gameValue.add(gameplatform);
						gameValue.add(price);
						gameValue.add(game.getSellID() + "@osu.edu");

						gameList.add(gameValue);
					}

				}
			}
			return gameList;
		}
		return gameList;
	}
}
