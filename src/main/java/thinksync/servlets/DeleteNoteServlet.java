package thinksync.servlets;

import java.io.IOException;

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

@WebServlet("/deletenote")
public class DeleteNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		Note note = new NoteDatabase(SessionProvider.getSession()).getNote(id);
		new NoteDatabase(SessionProvider.getSession()).deleteNote(note, user);
		
		user = new UserDatabase(SessionProvider.getSession()).getUserFromEmail(user);
		session.removeAttribute("user");
		session.setAttribute("user", user);
		resp.sendRedirect("notes.jsp");
	}
}
