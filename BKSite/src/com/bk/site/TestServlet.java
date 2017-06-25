package com.bk.site;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class TestServlet extends HttpServlet
{
	UserClass uc;
	Properties prop;
	InputStream input=null;
	String logLocation;
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException
	{
		
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		File propertiesFile=new File(System.getenv("TOMCAT_PATH")+File.separator+"lib"+File.separator+"site.properties");
		System.out.println("Properties file: "+propertiesFile.toString());
		prop=new Properties();
		input=new FileInputStream(propertiesFile);
		prop.load(input);
		logLocation=prop.getProperty("LOGS");
		System.out.println("Servlet called");
		BufferedReader br=new BufferedReader(new InputStreamReader(request.getInputStream()));
		String objParam="";
		if(br!=null)
		{
			objParam=br.readLine();
		}
		System.out.println("Json Received: "+objParam);
		try
		{
			Gson gson=new Gson();
			uc=gson.fromJson(objParam, UserClass.class);
			System.out.println("Printing parsed Json below");
			uc.printUser();
			if(uc.action.equals("register"))
			{
				boolean createStatus=createUser();
				response.setContentType("application/json");
				PrintWriter out=response.getWriter();
				String insertResponse="";
				if(createStatus)
				{
					System.out.println("User created successfully.");
					insertResponse="{\"createResult\":\"success\"}";
					out.print(insertResponse);
					out.flush();
				}
				else
				{
					System.out.println("Some error occured in user creation.");
					insertResponse="{\"createResult\":\"failure\"}";
					out.print(insertResponse);
					out.flush();
				}
			}
			else if(uc.action.equals("login"))
			{
				response.setContentType("application/json");
				PrintWriter out=response.getWriter();
				String loginRes=loginUser();
				String jsonResponse="";
				if(loginRes!=null)
				{
					System.out.println("Login successful.");
					jsonResponse="{\"result\":\"success\",\"fname\":\""+loginRes+"}";
					out.print(jsonResponse);
					out.flush();
				}
				else
				{
					System.out.println("Login failed.");
					jsonResponse="{\"result\":\"failure\",\"fname\":\"\"}";
					out.print(jsonResponse);
					out.flush();
				}
			}
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}
	}
	private boolean createUser()
	{
		if(uc!=null)
		{
			DbHelper dh=new DbHelper();
			boolean insertStatus=dh.insertUser(uc);
			uc=null;
			return insertStatus;
		}
		else
			return false;
	}
	private String loginUser()
	{
		DbHelper dh=new DbHelper();
		String result=dh.fetchUser(uc);
		return result;
	}
}
