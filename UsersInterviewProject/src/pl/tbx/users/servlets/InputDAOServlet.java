package pl.tbx.users.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.tbx.users.dao.UserDAO;
import pl.tbx.users.model.Users;
import pl.tbx.users.model.parser.UsersParser;

@WebServlet("/input")
public class InputDAOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserDAO uDAO = new UserDAO();
	UsersParser parser = new UsersParser();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = reader.readLine()) != null){
	    sb.append(line);
		}
		
		Users users = parser.parseUsersFromString(sb.toString());
		int usersCount = uDAO.save(users);
		
		PrintWriter out = response.getWriter();
		out.print(usersCount);
		out.flush();
	}

}
