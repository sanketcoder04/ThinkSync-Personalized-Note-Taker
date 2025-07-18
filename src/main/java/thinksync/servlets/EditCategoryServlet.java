package thinksync.servlets;

import java.io.IOException;

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

@WebServlet("/editcategory")
public class EditCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		Category category = new CategoryDatabase(SessionProvider.getSession()).getCategory(id);
		new CategoryDatabase(SessionProvider.getSession()).editCategory(category, user, title);
		
		user = new UserDatabase(SessionProvider.getSession()).getUserFromEmail(user);
		session.removeAttribute("user");
		session.setAttribute("user", user);
		resp.sendRedirect("category.jsp");
	}
}
