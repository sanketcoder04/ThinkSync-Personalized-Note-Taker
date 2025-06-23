package thinksync.features;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/downloadnotepdf")
public class DownloadPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String filename = req.getParameter("filename");

		// Normalize content line endings
		content = content.replaceAll("\r\n", "\n").replaceAll("\r", "\n");

		resp.setContentType("application/pdf");
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + ".pdf\"");

		try {
		    Document document = new Document();
		    PdfWriter.getInstance(document, resp.getOutputStream());
		    document.open();

		    // Fonts
		    Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
		    Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

		    // Title
		    Paragraph titlePara = new Paragraph(title, titleFont);
		    titlePara.setAlignment(Element.ALIGN_CENTER);
		    document.add(titlePara);
		    document.add(Chunk.NEWLINE);

		    // Split by lines to preserve spacing
		    String[] lines = content.split("\n");
		    
		    for(String line : lines) {
		        if(line.trim().isEmpty()) {
		            document.add(Chunk.NEWLINE); // for blank lines
		        } 
		        else {
		            Paragraph p = new Paragraph(line, contentFont);
		            p.setLeading(14f); // line height spacing
		            document.add(p);
		        }
		    }
		    document.close();
		} 
		catch(Exception e) {
		    e.printStackTrace();
		}
	}
}
