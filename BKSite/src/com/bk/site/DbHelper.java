package com.bk.site;

import java.sql.*;



public class DbHelper
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/nvc";
	Connection con=null;
	PreparedStatement pstmt=null;
	public DbHelper()
	{
		
	}
	
	public boolean insertUser(UserClass uc)
	{
		String query="insert into user_table (user_name,user_fname,user_email,user_password) values (?,?,?,?)";
		try
		{
			Class.forName(JDBC_DRIVER);
			con=DriverManager.getConnection(DB_URL, "nvcuser", "nvcuser");
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
		String query="select * from user_table where user_name='"+uc.uname+"'";
		String uname="",email="",passwd="",fname="";
		try
		{
			Class.forName(JDBC_DRIVER);
			con=DriverManager.getConnection(DB_URL, "root", "bhaumeen98");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(query);
			if(rs!=null)
			while(rs.next())
			{
				uname=rs.getString("user_name");
				fname=rs.getString("user_fname");
				email=rs.getString("user_email");
				passwd=rs.getString("user_password");
			}
			if(uc.uname.equals(uname) && uc.pass.equals(passwd))
			{
				System.out.println("It's a match!\nFull Name: "+fname);
				return fname;
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
}
