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

@WebServlet("/deletecategory")
public class DeleteCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer category_id = Integer.parseInt(req.getParameter("id"));
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		Category category = new CategoryDatabase(SessionProvider.getSession()).getCategory(category_id);
		new CategoryDatabase(SessionProvider.getSession()).deleteCategory(category, user);
		
		user = new UserDatabase(SessionProvider.getSession()).getUserFromEmail(user);
		session.removeAttribute("user");
		session.setAttribute("user", user);
		resp.sendRedirect("category.jsp");
	}
}
