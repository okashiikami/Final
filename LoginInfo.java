
public class LoginInfo{
	
	private String user;// holds username to site
	private String pass;//holds password for site
	private String site;//holds site 
	
	
	public LoginInfo(String user, String pass, String site){
		this.user = user;
		this.pass = pass;
		this.site = site;	
	}
	public String getSite(){
		return site;
	}
	public String getUser(){
		return user;
	}
	public String getPass(){
		return pass;
	}
}