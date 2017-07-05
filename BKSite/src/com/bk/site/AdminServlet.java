package com.bk.site;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class adminServlet
 */
@WebServlet("/adminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(request.getInputStream()));
		String objParam="";
		if(br!=null)
		{
			objParam=br.readLine();
		}
		System.out.println("Json Received: "+objParam);
		try
		{
			Gson g=new Gson();
			TextWrapper tw=g.fromJson(objParam, TextWrapper.class);
			System.out.println(tw.toString());
			boolean status=insertTextStatus(tw);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private boolean insertTextStatus(TextWrapper tw)
	{
		DbHelper dh=new DbHelper();
		boolean result=dh.insertText(tw);
		if(result)
			System.out.println("Text inserted successfully!");
		else
			System.out.println("Some error occurred in inserting the text!");
		return result;
	}

}
