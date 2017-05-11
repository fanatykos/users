package pl.tbx.users.model;

public class UserDTO {

	private String name;

	private String surnameMD5;

	private String login;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurnameMD5() {
		return surnameMD5;
	}

	public void setSurnameMD5(String surnameMD5) {
		this.surnameMD5 = surnameMD5;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
