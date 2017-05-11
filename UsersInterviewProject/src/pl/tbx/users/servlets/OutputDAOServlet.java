package pl.tbx.users.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import pl.tbx.users.dao.UserDAO;
import pl.tbx.users.model.UserDTO;
import pl.tbx.users.model.Users;
import pl.tbx.users.model.parser.UserDTOParser;
import pl.tbx.users.model.parser.UsersParser;

/**
 * Servlet implementation class OutputDAOServlet
 */
@WebServlet("/output")
public class OutputDAOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UserDAO uDAO = new UserDAO();
	UsersParser parser = new UsersParser();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users users = uDAO.getAll();
		List<UserDTO> userDTOLis = parser.parseUsersToUserDTOList(users);
		UserDTOParser parserDTO = new UserDTOParser();
		JSONObject jSONObject  = parserDTO.parseUsersToJSON(userDTOLis);
		
		PrintWriter out = response.getWriter();
		out.print(jSONObject.toString());
		out.flush();
	}

}
