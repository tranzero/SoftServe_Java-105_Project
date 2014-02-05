package com.ita.edu.softserve.devutils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import com.mysql.jdbc.Connection;

public class DbRecreation {
	
	private static String dbPath;
	private static String dbName;
	private static String dbLogin;
	private static String dbPass;
	private static final String hibernateCfgPath = "/hibernate.cfg.xml";
	private static final String dumpPath = "/dump.sql";
	private static final String propertyTagName = "property";
	private static final String urlAttribute = "connection.url";
	private static final String usernameAttribute = "connection.username";
	private static final String passwordAttribute = "connection.password";
	private static final String searchingAttribute = "name";
	private static void parseXml() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document dom = null;
        Element e;
        Element tmp;
        InputStream is  = DbRecreation.class.getResourceAsStream(hibernateCfgPath);
        DocumentBuilder builder = null;
        try {
			builder = factory.newDocumentBuilder();
			dom = builder.parse(is);
			is.close();
		} catch (Exception ec) {
			ec.printStackTrace();
		}
        e = dom.getDocumentElement();
        NodeList properties = e.getElementsByTagName(propertyTagName);
		for (int j=0; j<properties.getLength(); j++){
			tmp = (Element) properties.item(j);
			if (tmp.getAttribute(searchingAttribute).equals(urlAttribute)){
				String connection = tmp.getTextContent();
				String csplit[] = connection.split("/");
				dbName = csplit[csplit.length-1];
				dbPath = csplit[csplit.length-2];
				System.out.println("Db name is "+dbName);
				System.out.println("DB path is "+dbPath);
			} else if (tmp.getAttribute(searchingAttribute).equals(usernameAttribute)){
				dbLogin = tmp.getTextContent();
				System.out.println("DB login is "+dbLogin);
				
			} else if (tmp.getAttribute(searchingAttribute).equals(passwordAttribute)){
				dbPass = tmp.getTextContent();
				System.out.println("DB password is "+dbPass);
			}
		}
	}
	
	private static void recreation(){
		String rQry=null;
		Connection con=null;
		try {
			rQry=new String(ByteBuffer.wrap(Files.readAllBytes(Paths.get(DbRecreation.class.getResource(dumpPath).toURI()))).array());
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
			String qsplit[] = rQry.split(";");
			for (String q:qsplit){
				con.createStatement().executeUpdate(q);
				System.out.println(q+";");
			}
			System.out.println("Success!");
			System.out.print("Closing connection...");
			con.close();
			System.out.println("Success!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		parseXml();
		recreation();

	}

}
