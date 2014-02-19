package com.ita.edu.softserve.utils;



import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.stereotype.Component;

@Component("loggerProgrammaticalConfigurator")
public class LoggerProgrammaticalConfigurator {

	private ConsoleAppender consoleAppender = null;
	private String consoleAppenderPatternString = "%-5p: %c - %m%n";
	private Level consoleAppenderLevel = Level.INFO;
	
	private ConsoleAppender consoleErrorAppender = null;
	private Level consoleErrorAppenderLevel = Level.ERROR;

	private DailyRollingFileAppender fileAppender = null;
	private String fileNameString = "info.log";
	private String datePatternString = "'.'yyyy-MM-dd";
	private String fileAppenderPatternString = "%d [%t] %-5p (%F:%L:%M)  %c{1}  - %m%n";
	private Level fileAppenderLevel = Level.INFO;
	
	
	private DailyRollingFileAppender fileErorAppender = null;
	private String fileErrorNameString = "error.log";
	private Level fileErrorAppenderLevel = Level.ERROR;

	public LoggerProgrammaticalConfigurator() {
	}

	public void init(String logsPath) {
		// Preparing the console appender
		consoleAppender = new ConsoleAppender();
		consoleAppender.setLayout(new PatternLayout(
				consoleAppenderPatternString));
		consoleAppender.setThreshold(consoleAppenderLevel);
		consoleAppender.activateOptions();

		Logger.getRootLogger().addAppender(consoleAppender);

		// Preparing file appender
		fileAppender = new DailyRollingFileAppender();
		fileAppender.setName("FileLogger");
		fileAppender.setFile(logsPath + fileNameString);
		fileAppender.setLayout(new PatternLayout(fileAppenderPatternString));
		fileAppender.setThreshold(fileAppenderLevel);
		fileAppender.setAppend(true);
		fileAppender.setDatePattern(datePatternString);
		fileAppender.activateOptions();

		Logger.getRootLogger().addAppender(fileAppender);
		
		
		// Preparing the error console appender
		consoleErrorAppender = new ConsoleAppender();
		consoleErrorAppender.setLayout(new PatternLayout(
				consoleAppenderPatternString));
		consoleErrorAppender.setThreshold(consoleErrorAppenderLevel);
		consoleAppender.activateOptions();

		Logger.getRootLogger().addAppender(consoleErrorAppender);
		
		// Preparing error file appender
		fileErorAppender = new DailyRollingFileAppender();
		fileErorAppender.setName("FileLogger");
		fileErorAppender.setFile(logsPath + fileErrorNameString);
		fileErorAppender.setLayout(new PatternLayout(fileAppenderPatternString));
		fileErorAppender.setThreshold(fileErrorAppenderLevel);
		fileErorAppender.setAppend(true);
		fileErorAppender.setDatePattern(datePatternString);
		fileErorAppender.activateOptions();

		Logger.getRootLogger().addAppender(fileErorAppender);
				
	}
}