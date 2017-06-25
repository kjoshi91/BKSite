package com.bk.site;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

public class UploadFile extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException
	{
		
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		String ajaxUpdateResult="";
		try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);            
            for (FileItem item : items) {
                if (item.isFormField()) {
                    ajaxUpdateResult += "Field " + item.getFieldName() + 
                    " with value: " + item.getString() + " is successfully read\n\r";
                } else {
                    String fileName = item.getName();
                    InputStream content = item.getInputStream();
                    response.setContentType("text/plain");
                    response.setCharacterEncoding("UTF-8");
                    // Do whatever with the content InputStream.
                    System.out.println(Streams.asString(content));
                    ajaxUpdateResult += "File " + fileName + " is successfully uploaded\n\r";
                }
            }
        } catch (FileUploadException e) {
            throw new ServletException("Parsing file upload failed.", e);
        }
        response.getWriter().print(ajaxUpdateResult);
	}
}
