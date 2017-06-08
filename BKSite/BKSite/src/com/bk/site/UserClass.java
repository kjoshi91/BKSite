package com.bk.site;

public class UserClass
{
	public String uname;
	public String fname;
	public String pass;
	public String email;
	public String action;
	
	
	public UserClass(String name,String fname,String pass, String email, String action)
	{
		this.uname=name;
		this.fname=fname;
		this.pass=pass;
		this.email=email;
		this.action=action;
	}
//	@Override
//	public String toString()
//	{
//		System.out.println("To String of UserClass called");
//		StringBuilder sb=new StringBuilder();
//		sb.append("Name: "+name+"\n");
//		sb.append("Password: "+pass);
//		return sb.toString();
//	}
	
	public void printUser()
	{
		System.out.println("User name: "+uname);
		System.out.println("Full name: "+fname);
		System.out.println("User pass: "+pass);
		System.out.println("User email: "+email);
		System.out.println("Action: "+action);
	}
}
