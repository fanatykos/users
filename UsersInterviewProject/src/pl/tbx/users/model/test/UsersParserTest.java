package pl.tbx.users.model.test;

import org.junit.Assert;
import org.junit.Test;

import pl.tbx.users.model.Users;
import pl.tbx.users.model.parser.UsersParser;

public class UsersParserTest {

	private final static int NUMBER_OF_USERS = 100;

	@Test
	public void testParseUsersFromString() {
		String stringToTest = prepareUsersString(NUMBER_OF_USERS);

		UsersParser parser = new UsersParser();
		Users users = parser.parseUsersFromString(stringToTest);

		Assert.assertEquals(users.getUsers().size(), NUMBER_OF_USERS);
	}

	private String prepareUsersString(int numberOfUsersToTest) {
		StringBuilder sb = new StringBuilder();
		sb.append("<users>");
		sb.append("\n");

		for (int i = 1; i < numberOfUsersToTest + 1; i++) {
			sb.append("<user><name>name");
			sb.append(String.valueOf(i));
			sb.append("</name><surname>surname");
			sb.append(String.valueOf(i));
			sb.append("</surname><login>login");
			sb.append(String.valueOf(i));
			sb.append("</login></user>");
			sb.append("\n");
		}
		sb.append("</users>");

		return sb.toString();
	}
}
