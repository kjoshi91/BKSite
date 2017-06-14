package com.bk.site;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;

public class LogWrapper
{
	private static boolean isLoggingInitialized=false;
	static Properties prop;
	static String logLocation;
	public static void debug(String logMsg)
	{
		log(Level.DEBUG,logMsg);
	}
	private static void log(Level logLevel, String logMsg)
	{
		if(!isLoggingInitialized)
			initLogger(logLevel);
		org.slf4j.Logger slf4jLogger = org.slf4j.LoggerFactory.getLogger("com.bk.site.DebugLogger");
		slf4jLogger.debug(logMsg);
	}
	private static void initLogger(Level logLevel)
	{
		isLoggingInitialized=true;
		Logger debugLogger=LogManager.getLogger("DebugLogger");
		String conversionPattern = "%-7p %d [%t] %c %x - %m%n";
		final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
        final Configuration config = ctx.getConfiguration();
        Layout layout=PatternLayout.createLayout(conversionPattern, null, null, null, Charset.defaultCharset(),false,false,null,null);
        File propertiesFile=new File(System.getenv("TOMCAT_PATH")+File.separator+"lib"+File.separator+"site.properties");
		prop=new Properties();
		try {
			prop.load(new FileInputStream(propertiesFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logLocation=prop.getProperty("LOGS");
		
		FileAppender fileAppender=FileAppender.createAppender(logLocation+File.separator+"siteLog.log", "True", "False", "FileApp", "true", "true", "false", "8192", layout, null, "false", null, config);
		fileAppender.start();
		config.addAppender(fileAppender);
		AppenderRef ref = AppenderRef.createAppenderRef("FileApp", null, null);
        AppenderRef[] refs = new AppenderRef[] {ref};
		LoggerConfig loggerConfig = LoggerConfig.createLogger(false, logLevel, "com.bk.site", "true", refs, null, config, null);
		loggerConfig.addAppender(fileAppender, null, null);
		config.addLogger("com.bk.site", loggerConfig);
        ctx.updateLoggers();
        
	}
	/*public Logger getLogger(Class loggerClass)
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
	}*/
}
