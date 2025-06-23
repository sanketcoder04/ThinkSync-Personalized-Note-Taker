package thinksync.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thinksync.database.CategoryDatabase;
import thinksync.database.UserDatabase;
import thinksync.entities.Category;
import thinksync.entities.User;
import thinksync.helper.SessionProvider;

@WebServlet("/addcategory")
public class AddCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("category");
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		Category category = new Category(title, new Date(), user);
		new CategoryDatabase(SessionProvider.getSession()).saveCategory(category, user);
		session.removeAttribute("user");
		user = new UserDatabase(SessionProvider.getSession()).getUserFromEmail(user);
		session.setAttribute("user", user);
		resp.sendRedirect("category.jsp");
	}
}
