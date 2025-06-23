package thinksync.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thinksync.database.NoteDatabase;
import thinksync.database.UserDatabase;
import thinksync.entities.Note;
import thinksync.entities.User;
import thinksync.helper.SessionProvider;

@WebServlet("/savenote")
public class AddNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String filename = req.getParameter("filename");
		String[] categoriesName = req.getParameterValues("categories");
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		Note note = new Note(title, content, filename, new Date(), user);
		new NoteDatabase(SessionProvider.getSession()).saveNote(categoriesName, note, user);
		
		session.removeAttribute("user");
		user = new UserDatabase(SessionProvider.getSession()).getUserFromEmail(user);
		session.setAttribute("user", user);
		resp.sendRedirect("notes.jsp");
	}
}
