package thinksync.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thinksync.entities.NoteFilter;

@WebServlet("/filternote")
public class NoteFilterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String isSort = req.getParameter("sort");
		String createdDate = req.getParameter("createdDate");
		String searchTitle = req.getParameter("title");
		String searchCategory = req.getParameter("category");
		
		NoteFilter noteFilter = new NoteFilter(isSort, createdDate, searchTitle, searchCategory);
		HttpSession session = req.getSession();
		session.setAttribute("note_filter", noteFilter);
		resp.sendRedirect("notes.jsp");
	}
}