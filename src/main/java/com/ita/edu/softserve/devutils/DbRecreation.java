package com.ita.edu.softserve.devutils;

import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.util.Scanner;

import com.ita.edu.softserve.utils.ClasspathXmlParser;
import com.mysql.jdbc.Connection;

public class DbRecreation {
	
	private static String dbPath;
	private static String dbName;
	private static String dbLogin;
	private static String dbPass;
	private static final String HIBERNATE_CFG_PATH = "/hibernate.cfg.xml";
	private static final String DUMP_PATH = "/dump.sql";
	private static final String PROPERTY_TAG_NAME = "property";
	private static final String URL_ATTRIBUTE = "connection.url";
	private static final String USERNAME_ATTRIBUTE = "connection.username";
	private static final String PASSWORD_ATTRIBUTE = "connection.password";
	private static final String SEARCHING_ATTRIBUTE = "name";
	
	private static void parseXml() {
		ClasspathXmlParser p = new ClasspathXmlParser(HIBERNATE_CFG_PATH);
		String values[] = p.findValues(PROPERTY_TAG_NAME, SEARCHING_ATTRIBUTE, 
				URL_ATTRIBUTE, USERNAME_ATTRIBUTE, PASSWORD_ATTRIBUTE);
		String csplit[] = values[0].split("/");
		dbName = csplit[csplit.length-1];
		dbPath = csplit[csplit.length-2];
		System.out.println("Db name is "+dbName);
		System.out.println("DB path is "+dbPath);
		dbLogin = values[1];
		System.out.println("DB login is "+dbLogin);		
		dbPass = values[values.length-1];
		System.out.println("DB password is "+dbPass);		
	}
	
	private static void recreation(){
		String recreationQuery=null;
		Connection con=null;
		try {
			recreationQuery=new String(ByteBuffer.wrap(Files.readAllBytes(
					Paths.get(DbRecreation.class.getResource(DUMP_PATH).toURI()))).array());
			System.out.print("Connecting to database...");
			con=(Connection) DriverManager.getConnection("jdbc:mysql://"+dbPath+"/",dbLogin,dbPass);
			System.out.println("Success!");
			System.out.print("Dropping database...");
			con.createStatement().executeUpdate("DROP DATABASE IF EXISTS "+dbName+";");
			System.out.println("Success!");
			System.out.print("Creating and selecting new database...");
			con.createStatement().executeUpdate("CREATE DATABASE  IF NOT EXISTS `"+dbName+"`;");
			con.createStatement().executeUpdate("USE `"+dbName+"`;");
			System.out.println("Success!");
			System.out.print("Loading dump...");
			Scanner sc=new Scanner(recreationQuery);
			StringBuilder query = new StringBuilder();
			while (sc.hasNextLine()){
				query.append(sc.nextLine());
				if (query.length()>0){
					if (query.charAt(query.length()-1)==';'){
						con.createStatement().executeUpdate(query.toString());
						System.out.println(query.toString());
						query=new StringBuilder();
					}
				}
				if (query.length()>1){
					if (query.charAt(0)=='-' && query.charAt(1)=='-'){
						query=new StringBuilder();
					}
				}
			}
			
			System.out.println("Success!");
			System.out.print("Closing connection...");
			con.close();
			System.out.println("Success!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		parseXml();
		recreation();

	}

}
