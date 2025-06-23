package thinksync.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thinksync.entities.CategoryFilter;

@WebServlet("/filtercategory")
public class CategoryFilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String isSort = req.getParameter("sort");
		String createdDate = req.getParameter("createdDate");
		String search = req.getParameter("name");
		
		CategoryFilter categoryFilter = new CategoryFilter(isSort, createdDate, search);
		HttpSession session = req.getSession();
		session.setAttribute("category_filter", categoryFilter);
		resp.sendRedirect("category.jsp");
	}
}
