/**
 * 
 */
package edu.osu;


import javax.ejb.EJB;

import buckGamesArchive.EJB.Service.LoginSession;


/**
 * @author Vignesh
 *
 */
public class LoginBean {
	private String osuID;
	private String password;
	private String selection;

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	@EJB
	private LoginSession loginSession;

	public String userName() {

		String[] params = new String[3];
		params[0] = osuID;
		params[1] = password;
		params[2] = selection;
		String res = loginSession.userName(osuID, password, selection);
		if (res.equalsIgnoreCase("success")) {

			return selection;
		} else if (res.equalsIgnoreCase("fail"))
			return "false";

		return "false";
	}

	public String getMessage() {
		return "lol";
	}

	public String getosuID() {
		return osuID;
	}

	public void setosuID(final String osuID) {
		this.osuID = osuID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
