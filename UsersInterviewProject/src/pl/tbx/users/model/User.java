package pl.tbx.users.model;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "user")
@XmlAccessorType (XmlAccessType.FIELD)
public class User {

	@XmlElement(name = "name")
	private String name;

	@XmlElement(name = "surname")
	private String surname;

	@XmlElement(name = "login")
	private String login;
	
	public User(){
	}

	public User(String name, String surname, String login) {
		setName(name);
		setSurname(surname);
		setLogin(login);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "User: " + this.name + " " + this.surname + ", login: "
				+ this.login;
	}
}
