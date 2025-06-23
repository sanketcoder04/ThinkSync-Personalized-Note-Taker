package thinksync.features;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/downloadnotetext")
public class DownloadTXT extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String filename = req.getParameter("filename");
		
		req.setCharacterEncoding("UTF-8");
		content = content.replaceAll("\r\n", "\n").replaceAll("\r", "\n");
		content = content.replaceAll("\n", "\r\n");

		// Prepare note text
		String note = "**" + title.toUpperCase() + "**\r\n\r\n" + content;

		// Set file download headers
		resp.setContentType("text/plain; charset=UTF-8");
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + ".txt\"");

		// Output as bytes to preserve raw line breaks and formatting
		ServletOutputStream out = resp.getOutputStream();
		out.write(note.getBytes(StandardCharsets.UTF_8));
		out.flush();
		out.close();
	}
}