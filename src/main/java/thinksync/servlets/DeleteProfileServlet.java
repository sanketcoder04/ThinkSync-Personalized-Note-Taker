package thinksync.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thinksync.database.UserDatabase;
import thinksync.entities.User;
import thinksync.helper.SessionProvider;

@WebServlet("/deleteaccount")
public class DeleteProfileServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("user");
			new UserDatabase(SessionProvider.getSession()).deleteUser(user);
			session.removeAttribute("user");
			resp.sendRedirect("delete.jsp");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
