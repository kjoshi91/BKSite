package com.bk.site;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;



public class DbHelper
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/nvc";
	Connection con=null;
	PreparedStatement pstmt=null;
	String username,password;
	Properties prop;
	public DbHelper()
	{
		fetchProperties();		
	}
	
	private void fetchProperties()
	{
		try
		{
			File propertiesFile=new File(System.getenv("TOMCAT_PATH")+File.separator+"lib"+File.separator+"site.properties");
			prop=new Properties();
			prop.load(new FileInputStream(propertiesFile));
			username=prop.getProperty("DBUSER");
			password=prop.getProperty("DBPASS");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean insertUser(UserClass uc)
	{
		String query="insert into user_table (user_name,user_fname,user_email,user_password) values (?,?,?,?)";
		try
		{
			Class.forName(JDBC_DRIVER);
			con=DriverManager.getConnection(DB_URL, username, password);
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, uc.uname);
			pstmt.setString(2, uc.fname);
			pstmt.setString(3, uc.email);
			pstmt.setString(4, uc.pass);
			pstmt.execute();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
	
	public String fetchUser(UserClass uc)
	{
		String query="select * from user_table where user_email='"+uc.email+"'";
		String uname="",email="",passwd="",fname="";
		int isAdmin=0;
		try
		{
			Class.forName(JDBC_DRIVER);
			con=DriverManager.getConnection(DB_URL, username, password);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			if(rs!=null)
			while(rs.next())
			{
				uname=rs.getString("user_name");
				fname=rs.getString("user_fname");
				email=rs.getString("user_email");
				passwd=rs.getString("user_password");
				isAdmin=rs.getInt("user_isAdmin");
			}
			if(uc.email.equals(email) && uc.pass.equals(passwd))
			{
				System.out.println("It's a match!\nFull Name: "+fname);
				return fname+"\",\"isAdmin\":"+isAdmin;
			}
			else
				return null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public boolean insertText(TextWrapper tw)
	{
		String query="insert into scroll_details (scroll_text,text_visible) values (?,?)";
		String deleQuery="delete from scroll_details";
		try
		{
			Class.forName(JDBC_DRIVER);
			con=DriverManager.getConnection(DB_URL, username, password);
			pstmt=con.prepareStatement(deleQuery);
			pstmt.executeUpdate();
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, tw.text);
			if(tw.isVisible)
				pstmt.setInt(2,1);
			else
				pstmt.setInt(2,0);
			pstmt.execute();
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean fetchTextVisible()
	{
		String query="select text_visible from scroll_details";
		boolean isVisible=false;
		try
		{
			Class.forName(JDBC_DRIVER);
			con=DriverManager.getConnection(DB_URL, username, password);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			if(rs!=null)
			{
				while(rs.next())
				{
					if(rs.getInt("text_visible")==1)
						isVisible=true;
					else
						isVisible=false;
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return isVisible;
	}

	public String fetchText() 
	{
		String query="select scroll_text from scroll_details";
		String text="";
		try
		{
			Class.forName(JDBC_DRIVER);
			con=DriverManager.getConnection(DB_URL, username, password);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			if(rs!=null)
			{
				while(rs.next())
				{
					text=rs.getString("scroll_text");	
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return text;
	}
}
