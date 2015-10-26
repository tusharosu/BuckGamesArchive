/**
 * 
 */
package edu.osu;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import buckGamesArchive.EJB.Service.GameSearchList;


/**
 * @author Vignesh
 *
 */
public class WelcomeBean {

	/*@EJB
	private ProfileEmployer profileEmployer;
	private LoginBean logBean;

	public LoginBean getLogBean() {
		return logBean;
	}

	public void setLogBean(LoginBean logBean) {
		this.logBean = logBean;
	}

	private ProfileEmployerBean profileEmployerBean;

	public ProfileEmployerBean getProfileEmployerBean() {
		return profileEmployerBean;
	}

	public void setProfileEmployerBean(ProfileEmployerBean profileEmployerBean) {
		this.profileEmployerBean = profileEmployerBean;
	}

	private ProfileBean profileBean;

	public ProfileBean getProfileBean() {
		return profileBean;
	}

	public void setProfileBean(ProfileBean profileBean) {
		this.profileBean = profileBean;
	}*/

	private List<List<String>> games = new ArrayList<List<String>>();

	public List<List<String>> getgames() {
		return games;
	}

	public void setgames(List<List<String>> games) {
		this.games = games;
	}

	/*@EJB
	private HelloService helloService;*/
	@EJB
	private GameSearchList gameSearchList;

	/*@EJB
	private JobApply jobApply;*/
	private String gameName;
	private String gameCondition = "new game";
	private String platform;

	public String getplatform() {
		return platform;
	}

	public void setplatform(String platform) {
		this.platform = platform;
	}

	public String getgameName() {
		return gameName;
	}

	public void setgameName(String gname) {
		this.gameName = gname;
	}

	public String getgameType() {
		return gameCondition;
	}

	public void setgameType(String type) {
		this.gameCondition = type;
	}

	public String search() { 
		// TODO: Put crawler code here
		games = gameSearchList.findGame(gameName, gameCondition, platform);

		if (games.isEmpty())
			return "false";

		else {
			return "true";
		}
	}

	/*
	 * public String profile() {
	 * 
	 * String[] results = helloService.search(logBean.getName());
	 * 
	 * if(results[0].equalsIgnoreCase("no")) return "false";
	 * 
	 * else { profileBean.setTitle(results[0]);
	 * profileBean.setLocation(results[1]);
	 * profileBean.setTechnology(results[2]); profileBean.setPay(results[3]);
	 * 
	 * profileBean.setUser(logBean.getName()); return "profile";
	 * 
	 * } }
	 */

	/*public String profileEmployer() {

		String[] results = profileEmployer.firstProfile(logBean.getName());

		if (!results[0].equalsIgnoreCase("no")) {
			profileEmployerBean.setCompany(results[0]);
			profileEmployerBean.setLocation(results[1]);
			profileEmployerBean.setTechnology(results[2]);
			profileEmployerBean.setDomain(results[3]);
			profileEmployerBean.setUser(logBean.getName());
			return "profile";
		} else {
			profileEmployerBean.setUser(logBean.getName());
			return "profile";
		}
	}*/
	

}
