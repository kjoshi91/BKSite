package com.bk.site;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.output.*;

public class UploadFile extends HttpServlet
{
	
	private boolean isMultipart;
	private String filePath="D:\\BKSite\\uploaded";
	private int maxFileSize = 500 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file ;

	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException
	{
		
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		isMultipart = ServletFileUpload.isMultipartContent(request);
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter( );
		
		if( !isMultipart ) {
	         out.println("<html>");
	         out.println("<head>");
	         out.println("<title>Servlet upload</title>");  
	         out.println("</head>");
	         out.println("<body>");
	         out.println("<p>No file uploaded</p>"); 
	         out.println("</body>");
	         out.println("</html>");
	         return;
	      }
		
		DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setSizeThreshold(maxMemSize);
		factory.setRepository(new File("D:\\BKSite\\temp"));
		ServletFileUpload upload=new ServletFileUpload(factory);
		upload.setSizeMax(maxFileSize);
		try
		{
			List fileItems=upload.parseRequest(request);
			Iterator i=fileItems.iterator();
			int count=0;
			while(i.hasNext())
			{
				count++;
				FileItem fi=(FileItem)i.next();
				if(!fi.isFormField())
				{
					String fieldName=fi.getFieldName();
					String fileName=fi.getName();
					String contentType=fi.getContentType();
					boolean isInMemory=fi.isInMemory();
					long sizeInBytes=fi.getSize();
					/*if( fileName.lastIndexOf("\\") >= 0 )
					{
		                  file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
		            }
					else
					{
		                  file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		            }*/
					System.out.println("File name: "+fi.getName());
					//fi.write(file);
					byte[] fileByte=fi.get();
					
					//File outputFi=new File(filePath+File.separator+fileName);
					File outputFi=new File(filePath+File.separator+count+".jpg");
					System.out.println(outputFi.getName());
					FileOutputStream fos=new FileOutputStream(outputFi);
//					FileInputStream fis=new FileInputStream(file);
					/*fis.read(fileByte);
					fis.close();*/
					fos.write(fileByte);
					fos.flush();
					fos.close();
					/*BufferedImage image=null;
					//image=ImageIO.read();
					ImageIO.write(image, "jpg", new File(filePath+File.separator+fileName));*/
					out.println("Uploaded Filename: " + fileName + "<br>");
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
//		String ajaxUpdateResult="";
//		try {
//            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);   
//            for (FileItem item : items) {
//                if (item.isFormField()) {
//                    ajaxUpdateResult += "Field " + item.getFieldName() + 
//                    " with value: " + item.getString() + " is successfully read\n\r";
//                } else {
//                    String fileName = item.getName();
//                    InputStream content = item.getInputStream();
//                    response.setContentType("text/plain");
//                    response.setCharacterEncoding("UTF-8");
//                    // Do whatever with the content InputStream.
//                    System.out.println(Streams.asString(content));
//                    ajaxUpdateResult += "File " + fileName + " is successfully uploaded\n\r";
//                }
//            }
//        } catch (FileUploadException e) {
//            throw new ServletException("Parsing file upload failed.", e);
//        }
//        response.getWriter().print(ajaxUpdateResult);
	}
}
