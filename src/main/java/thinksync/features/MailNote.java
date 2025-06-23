package thinksync.features;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import thinksync.entities.User;

@WebServlet("/mailnote")
public class MailNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String systemEmail = "thinksync.mail.sender@gmail.com";
	private static final String systemPassword = "zzcvlyzhrcvhrpuw";
	
	private static final String host = "smtp.gmail.com";
	private static final String port = "587";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String filename = req.getParameter("filename");
		String content = req.getParameter("content");
		
		String receipent = req.getParameter("reciepent");
		String format = req.getParameter("format");
		String subject = req.getParameter("subject");
		String body = req.getParameter("body");
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		String senderName = user.getName();
		
		body = "Sender : " + senderName + "\n\n" + body;
		
		File file = null;
        switch (format) {
            case "txt":
                file = generateTxtFile(filename, title, content);
                break;
            case "pdf":
                file = generatePdfFile(filename, title, content);
                break;
            case "docx":
                file = generateDocxFile(filename, title, content);
                break;
        }
        sendEmailWithAttachment(receipent, subject, body, file);
        file.delete();

        resp.getWriter().println("Email sent successfully with attachment.");
        resp.sendRedirect("notes.jsp");
	}
	
	private void sendEmailWithAttachment(String receipent, String subject, String body, File fileToSend) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(systemEmail, systemPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(systemEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receipent));
            message.setSubject(subject);

            MimeBodyPart messageBody = new MimeBodyPart();
            messageBody.setText(body);

            MimeBodyPart attachment = new MimeBodyPart();
            attachment.attachFile(fileToSend);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBody);
            multipart.addBodyPart(attachment);

            message.setContent(multipart);

            Transport.send(message);
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	private File generateTxtFile(String filename, String title, String content) throws IOException {
	    File file = new File(filename + ".txt");
	    try(FileWriter writer = new FileWriter(file)) {
	    	writer.write(title + "\n\n" + content);
	    }
	    return file;
	}
	
	private File generatePdfFile(String filename, String title, String content) throws IOException {
        File file = new File(filename + ".pdf");
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            
            Paragraph titlePara = new Paragraph(title, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
            titlePara.setAlignment(Element.ALIGN_CENTER);
            document.add(titlePara);
            
            document.add(Chunk.NEWLINE);
            
            document.add(new Paragraph(content, FontFactory.getFont(FontFactory.HELVETICA, 12)));
            document.close();
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
        return file;
    }
	
	private File generateDocxFile(String filename, String title, String content) throws IOException {
        File file = new File(filename + ".docx");
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph para1 = document.createParagraph();
        para1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run1 = para1.createRun();
        run1.setBold(true);
        run1.setFontSize(16);
        run1.setText(title);

        XWPFParagraph para2 = document.createParagraph();
        XWPFRun run2 = para2.createRun();
        run2.setFontSize(12);
        run2.setText(content);

        try(FileOutputStream out = new FileOutputStream(file)) {
            document.write(out);
        }
        document.close();
        return file;
    }
}
