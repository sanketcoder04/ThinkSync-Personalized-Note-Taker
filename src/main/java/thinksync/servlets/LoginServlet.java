package thinksync.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thinksync.database.UserDatabase;
import thinksync.entities.User;
import thinksync.helper.SessionProvider;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Fetching User Details from Login form
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		// Creating Object of old User and fetch all data from database
		User user = new User(name, email, password);
		User currUser = new UserDatabase(SessionProvider.getSession()).loginUser(user);
		System.out.println(currUser);
		
		if (currUser == null) {
			String error = "Invalid Credentials / User doesn't exists - Please SignUp";
			req.setAttribute("error", error);
			RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
			rd.forward(req, resp);
			return;
		}
		HttpSession session = req.getSession();
		session.setAttribute("user", currUser);
		resp.sendRedirect("index.jsp");
	}
}
