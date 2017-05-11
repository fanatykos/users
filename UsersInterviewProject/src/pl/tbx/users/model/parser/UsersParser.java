package pl.tbx.users.model.parser;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.mysql.fabric.xmlrpc.base.Array;

import pl.tbx.users.model.User;
import pl.tbx.users.model.UserDTO;
import pl.tbx.users.model.Users;

public class UsersParser {

	public Users parseUsersFromString(String xmlAsString) {
		Document xml = null;
		try {
			xml = parseXMLFromString(xmlAsString);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		Users users = null;
		try {
			users = parseUsersFromXML(xml);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return users;
	}

	private Users parseUsersFromXML(Document xml) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		return (Users) jaxbUnmarshaller.unmarshal(xml);
	}

	private Document parseXMLFromString(String xmlAsString)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory fctr = DocumentBuilderFactory.newInstance();
		DocumentBuilder bldr = fctr.newDocumentBuilder();
		InputSource insrc = new InputSource(new StringReader(xmlAsString));

		return bldr.parse(insrc);
	}

	public List<UserDTO> parseUsersToUserDTOList(Users users) {
		List<UserDTO> userDTOList = new ArrayList<>();
		
		for(User user : users.getUsers()){
			UserDTO userDTO = new UserDTO();
			userDTO.setName(user.getName());
			String nameMD5hash = generateMD5Hash(user.getName());
			userDTO.setSurnameMD5(user.getSurname() + "_" + nameMD5hash);
			userDTO.setLogin(user.getLogin());
			
			userDTOList.add(userDTO);
		}
		
		return userDTOList;
	}
	
	private String generateMD5Hash(String name){
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		m.reset();
		m.update(name.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		
		return hashtext;
	}

}
