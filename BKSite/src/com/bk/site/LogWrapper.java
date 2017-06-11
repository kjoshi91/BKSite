package com.bk.site;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.Level;

public class LogWrapper
{
	private static boolean isLoggingInitialized=false;
	Properties prop;
	String logLocation;
	public static void debug(String logMsg)
	{
		log(Level.DEBUG,logMsg);
	}
	private static void log(Level logLevel, String logMsg)
	{
		if(!isLoggingInitialized)
			initLogger();
		
	}
	private static void initLogger()
	{
		
	}
	public Logger getLogger(Class loggerClass)
	{
		try
		{
			File propertiesFile=new File(System.getenv("TOMCAT_PATH")+File.separator+"lib"+File.separator+"site.properties");
			prop=new Properties();
			prop.load(new FileInputStream(propertiesFile));
			logLocation=prop.getProperty("LOGS");
			//PatternLayout layout=new PatternLayout();
			String conversionPattern = "%-7p %d [%t] %c %x - %m%n";
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
