package pl.tbx.users.model.test;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import pl.tbx.users.model.UserDTO;
import pl.tbx.users.model.parser.UserDTOParser;

public class UserDTOParserTest {
	private final static int NUMBER_OF_USERS = 50000;
	
	@Test
	public void testParseUsersToJSONObject() {
		List<UserDTO> users = prapareUsers(NUMBER_OF_USERS);

		UserDTOParser parser = new UserDTOParser();
		JSONObject jSONObject = parser.parseUsersToJSON(users);

		JSONArray jSONArray = null;
		try {
			jSONArray = jSONObject.getJSONArray("users");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		int jSONArrayCount = jSONArray.length();

		Assert.assertEquals(jSONArrayCount, NUMBER_OF_USERS);
	}

	private List<UserDTO> prapareUsers(int numberOfUsers) {
		List<UserDTO> usersList = new ArrayList<>();

		for (int i = 1; i < numberOfUsers + 1; i++) {
			UserDTO user = new UserDTO();
			user.setName("name" + i);
			user.setSurnameMD5("surname" + i + "_84386738476457654");
			user.setLogin("login" + i);

			usersList.add(user);
		}
		return usersList;
	}
}
