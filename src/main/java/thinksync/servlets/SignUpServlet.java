package thinksync.servlets;

import java.io.IOException;
import java.util.Date;

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

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Fetching User Details from SignUp form
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String about = req.getParameter("about");
			String occupation = req.getParameter("occupation");
			String organization = req.getParameter("organization");
			String facebookurl = req.getParameter("facebookurl");
			String twitterurl = req.getParameter("twitterurl");
			String linkedinurl = req.getParameter("linkedinurl");
			
			// Creating Object of new User and save it to Database
			User user = new User(name, email, password, about, occupation, organization, facebookurl, twitterurl, linkedinurl, new Date());
			new UserDatabase(SessionProvider.getSession()).signupUser(user);
			
			// Now go through the user record and then fetch all data and create a new User and associate the user in session object
			User newUser = new UserDatabase(SessionProvider.getSession()).getUserFromEmail(user);
			System.out.println(newUser);
			
			HttpSession session = req.getSession();
			session.setAttribute("user", newUser);
			resp.sendRedirect("index.jsp");
		}
		catch(Exception e) {
			e.printStackTrace();
			String error = "User already exists - Please Login";
			req.setAttribute("error", error);
			RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
			rd.forward(req, resp);
		}
 	}
}