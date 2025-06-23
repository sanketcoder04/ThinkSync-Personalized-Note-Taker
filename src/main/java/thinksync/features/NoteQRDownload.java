package thinksync.features;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadPDF")
public class NoteQRDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String url = "jdbc:mysql://localhost:3306/thinksync";
	private static final String username = "root";
	private static final String password = "sanket@sql";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT filename, filedata FROM qrfile WHERE id = ?";
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if(result.next()) {
                String filename = result.getString("filename");
                byte[] pdfBytes = result.getBytes("filedata");

                resp.setContentType("application/pdf");
                resp.setHeader("Content-Disposition", "attachment;filename=" + filename + ".pdf");

                OutputStream outputStream = resp.getOutputStream();
                outputStream.write(pdfBytes);
                outputStream.flush();
                outputStream.close();
            } 
            else {
                resp.getWriter().println("File not found");
            }
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
	}
}
