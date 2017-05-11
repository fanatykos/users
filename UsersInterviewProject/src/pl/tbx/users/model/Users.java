package pl.tbx.users.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "users")
@XmlAccessorType (XmlAccessType.FIELD)
public class Users {

	@XmlElement(name = "user")
	private List<User> users = new ArrayList<>();
	
	public Users(){
	}
	
	public Users(List<User> users){
		this.users = users;
	}

	public boolean addUser(User user) {
		this.users.add(user);

		return true;
	}
	
	public boolean addUsers(List<User> users) {
		this.users.addAll(users);

		return true;
	}
	
	public List<User> getUsers(){
		return this.users;
	}
}