package buckGamesArchive.EJB.Entity;
import java.io.*;
import javax.persistence.*;

@Entity
@Table(name = "USER")
public class UserEntity implements Serializable{
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="OSUID")
	private String OSUID;
	
	public String getOSUID(){
		return this.OSUID;
	}
	public void setOSUID(String OSUId){
		this.OSUID = OSUId;
	}
	
	@Column(name="PASSWORD")
	private String Password;
	
	public String getPassword(){
		return this.Password;
	}
	public void setPassword(String password){
		this.Password = password;
	}
	
	
	
	
	@Column(name="FNAME")
	private String FName;
	
	public String getFName() {
		return FName;
	}
	public void setFName(String FName) {
		this.FName = FName;
	}
	


	@Column(name="LNAME")
	private String LName;


	public String getLName() {
		return LName;
	}
	public void setLName(String LName) {
		this.LName = LName;
	}


	@Column(name="EMAIL")
	private String EMail;
	
	public String getEMail() {
		return EMail;
	}
	public void setEMail(String EMail) {
		this.EMail = EMail;
	}
	
	@Column(name="PHNO")
	private String PhNo;
	
	
	public String getPhNo() {
		return PhNo;
	}
	public void setPhNo(String PhNo) {
		this.PhNo = PhNo;
	}

	
}
