package thinksync.features;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

@WebServlet("/downloadnoteword")
public class DownloadDOCX extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String filename = req.getParameter("filename");

		// Normalize line endings
		content = content.replaceAll("\r\n", "\n").replaceAll("\r", "\n");

		resp.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + ".docx\"");

		try(XWPFDocument document = new XWPFDocument()) {
		    // Setting Title
		    XWPFParagraph titleParagraph = document.createParagraph();
		    titleParagraph.setAlignment(ParagraphAlignment.CENTER);
		    XWPFRun titleRun = titleParagraph.createRun();
		    titleRun.setBold(true);
		    titleRun.setFontSize(16);
		    titleRun.setText(title);

		    // Add empty line
		    document.createParagraph();

		    // Setting Content with proper alignment
		    String[] lines = content.split("\n");
		    for(String line : lines) {
		        XWPFParagraph paragraph = document.createParagraph();
		        XWPFRun run = paragraph.createRun();
		        run.setFontFamily("Helvetica"); 
		        run.setFontSize(12);
		        run.setText(line);
		    }
		    document.write(resp.getOutputStream());
		} 
		catch(Exception e) {
		    e.printStackTrace();
		}
	}
}
