package models;

public class LoginInfo {
	private String login;
	
	public LoginInfo(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public LoginInfo() {
		
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	private String password;
}
