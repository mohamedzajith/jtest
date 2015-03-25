package test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class TestServlet extends HttpServlet {
	
	private static final long	serialVersionUID	= 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
	
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		boolean isMultipartContent = ServletFileUpload
				.isMultipartContent(request);
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> fields = upload.parseRequest(request);
			
			Iterator<FileItem> it = fields.iterator();
			while (it.hasNext()) {
				
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				if (isFormField==false) {
					out.println("NAME: " + fileItem.getName()
						+ "<br/>SIZE (BYTES): " + fileItem.getSize());
					
					}
				/*else {
					out.println("NAME: " + fileItem.getName()
							+ "<br/>SIZE (BYTES): " + fileItem.getSize());
					
				}*/
				
			}
			
		}
		catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
}
