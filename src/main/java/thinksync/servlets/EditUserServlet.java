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

@WebServlet("/editaccount")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String about = req.getParameter("about");
			String occupation = req.getParameter("occupation");
			String organization = req.getParameter("organization");
			String facebookurl = req.getParameter("facebookurl");
			String twitterurl = req.getParameter("twitterurl");
			String linkedinurl = req.getParameter("linkedinurl");
			
			// fetch current User from session object
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			
			// Create User object with updated details
			User newUser = new User(name, email, password, about, occupation, organization, facebookurl, twitterurl, linkedinurl);
			
			// Update new details in database and associate updated user object into session object
			new UserDatabase(SessionProvider.getSession()).updateUser(user, newUser);
			User currentUser = new UserDatabase(SessionProvider.getSession()).getUserFromEmail(newUser);
			session.setAttribute("user", currentUser);
			resp.sendRedirect("profile.jsp");
		}
		catch(Exception e) {
			e.printStackTrace();
			String error = "Cannot Process Your Details, Something unexpected happens";
			req.setAttribute("error", error);
			RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
			rd.forward(req, resp);
		}
	}
}