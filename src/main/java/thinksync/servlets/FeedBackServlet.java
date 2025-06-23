package thinksync.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import thinksync.database.FeedbackDatabase;
import thinksync.entities.Feedback;
import thinksync.entities.User;
import thinksync.helper.SessionProvider;

@WebServlet("/feedback")
public class FeedBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String feed = req.getParameter("feedback").trim();
			if(!feed.equals("")) {
				HttpSession session = req.getSession();
				User user = (User) session.getAttribute("user");
				Feedback feedback = new Feedback(feed, user, new Date());
				new FeedbackDatabase(SessionProvider.getSession()).uploadFeedback(feedback);
			}
			resp.sendRedirect("feedback.jsp");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}