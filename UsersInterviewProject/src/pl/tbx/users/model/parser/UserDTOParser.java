package pl.tbx.users.model.parser;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pl.tbx.users.model.UserDTO;

public class UserDTOParser {
	
	public JSONObject parseUsersToJSON(List<UserDTO> users) {
		JSONObject jObject = new JSONObject();
		try {
			JSONArray jArray = new JSONArray();
			for (UserDTO user : users) {
				JSONObject userJSON = new JSONObject();
				userJSON.put("name", user.getName());
				userJSON.put("surname", user.getSurnameMD5());
				userJSON.put("login", user.getLogin());
				jArray.put(userJSON);
			}
			jObject.put("users", jArray);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return jObject;
	}
}
