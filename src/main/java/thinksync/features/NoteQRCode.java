package thinksync.features;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import thinksync.entities.User;

@WebServlet("/qrcode")
public class NoteQRCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String url = "jdbc:mysql://localhost:3306/thinksync";
	private static final String username = "root";
	private static final String password = "sanket@sql";
	
	private static final String ip = "192.168.31.215";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String filename = req.getParameter("filename");
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		try(ByteArrayOutputStream byteStream = new ByteArrayOutputStream()) {
		    // Generate PDF
			Document document = new Document();
		    PdfWriter.getInstance(document, byteStream);
		    document.open();

		    // Fonts
		    Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
		    Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

		    // Adding title into the PDF
		    Paragraph titleParagraph = new Paragraph(title, titleFont);
		    titleParagraph.setAlignment(Element.ALIGN_CENTER);
		    titleParagraph.setSpacingAfter(10f);
		    document.add(titleParagraph);
		    
		    // Adding content into the PDF
		    Paragraph contentParagraph = new Paragraph(content, contentFont);
		    document.add(contentParagraph);
		    
		    document.close();
		    
		    byte[] pdfBytes = byteStream.toByteArray();

		    // Storing PDF in database
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    try(Connection connection = DriverManager.getConnection(url, username, password)) {
		        String query = "INSERT INTO qrfile (title, content, filename, filedata, userid) VALUES (?, ?, ?, ?, ?)";
		        
		        try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
		        	statement.setString(1, title);
		        	statement.setString(2, content);
		        	statement.setString(3, filename);
		        	statement.setBytes(4, pdfBytes);
		        	statement.setInt(5, user.getId());
		        	statement.executeUpdate();

		            int pdfId = 0;
		            try(ResultSet result = statement.getGeneratedKeys()) {
		                if(result.next()) {
		                    pdfId = result.getInt(1);
		                }
		            }

		            // Replace ipAddress with your system IP address
		            String downloadURL = req.getScheme() + "://" + ip + ":" + req.getServerPort() + req.getContextPath() + "/DownloadPDF?id=" + pdfId;

		            // Generate QR code
		            BitMatrix matrix = new MultiFormatWriter().encode(downloadURL, BarcodeFormat.QR_CODE, 200, 200);
		            ByteArrayOutputStream qrStream = new ByteArrayOutputStream();
		            MatrixToImageWriter.writeToStream(matrix, "PNG", qrStream);
		            byte[] qrBytes = qrStream.toByteArray();

		            // Send data to qrcode.jsp
		            String base64Image = Base64.getEncoder().encodeToString(qrBytes);
		            req.setAttribute("qrImage", base64Image);
		            req.setAttribute("downloadLink", downloadURL);
		            
		            RequestDispatcher rd = req.getRequestDispatcher("qrcode.jsp");
		            rd.forward(req, resp);
		        }
		    }
		} 
		catch(Exception e) {
		    e.printStackTrace();
		}
	}
}
