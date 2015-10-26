/**
 * 
 */
package edu.osu;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import buckGamesArchive.EJB.Service.RegisterService;


/**
 * @author Vignesh
 *
 */
public class RegisterBean {
	@EJB
	private RegisterService registerService;

	// Buyer
	private String osuID;
	private String password;
	private String valid;
	private String selection = "Buyer";
	private String fname;
	private String lname;
	private String email;
	private String phoneno;

	// Buyer

	// Seller
	private String sellID;

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public String getfname() {
		return fname;
	}

	public void setfname(String name) {
		this.fname = name;
	}

	public String getlname() {
		return lname;
	}

	public void setlname(String name) {
		this.lname = name;
	}

	public String getemail() {
		return email;
	}

	public void setemail(String mail) {
		this.email = mail;
	}

	public String getphoneno() {
		return phoneno;
	}

	public void setphoneno(String phno) {
		this.phoneno = phno;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getosuID() {
		return osuID;
	}

	public void setosuID(final String osuid) {
		if (osuid.contains(".")
				&& (osuid.contains("!") || osuid.contains("@")
						|| osuid.contains("#") || osuid.contains("$")
						|| osuid.contains("%") || osuid.contains("^")
						|| osuid.contains("&") || osuid.contains("*")
						|| osuid.contains("(") || osuid.contains(")")
						|| osuid.contains("_") || osuid.contains("+") || osuid
							.equals("")))
			setValid("Not a Valid User Name");
		else
			setValid("Valid User Name");

		this.osuID = osuid;
		this.sellID = osuid;
	}

	public String getsellID() {
		return sellID;
	}

	public void setSellID(String sellID) {
		if (sellID.contains(".")
				&& (sellID.contains("!") || sellID.contains("@")
						|| sellID.contains("#") || sellID.contains("$")
						|| sellID.contains("%") || sellID.contains("^")
						|| sellID.contains("&") || sellID.contains("*")
						|| sellID.contains("(") || sellID.contains(")")
						|| sellID.contains("_") || sellID.contains("+") || sellID
							.equals("")))
			setValid("Not a Valid User Name");
		else
			setValid("Valid User Name");

		this.sellID = sellID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String userName() {
		if (selection.equals("Buyer")) {
			if ((osuID.isEmpty() != true) && (password.isEmpty() != true)
					&& valid.equals("Valid User Name")) {
				String ret = registerService.userName(osuID, password,
						selection);
				if (!ret.equals("Exists"))
					return "true";
				else
					return "Exists";
			} else
				return "false";
		} else {
			if ((sellID.isEmpty() != true) && (password.isEmpty() != true)
					&& valid.equals("Valid User Name")) {
				String ret = registerService.userName(sellID, password,
						selection);
				if (!ret.equals("Exists"))
					return "true seller";
				else
					return "Exists";
			} else
				return "false";
		}

	}

	public String fillCredentials() {
		if (!fname.isEmpty() && !lname.isEmpty() && !email.isEmpty()
				&& !phoneno.isEmpty()) {
			String ret = registerService.update(osuID, fname, lname, email,
					phoneno);
			if (!ret.equals("fail")) {
				return "true";
			} else {
				return "false";
			}
		} else {
			return "false";
		}
	}
}
