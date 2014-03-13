package com.ita.edu.softserve.devutils;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.ita.edu.softserve.utils.ClasspathXmlParser;
import com.mysql.jdbc.Connection;



public class DbDumpReceiving {

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
	
	private static void receiving() {
		Connection con=null;
		DatabaseMetaData meta = null;
		StringBuilder dump= new StringBuilder();
		try {
			System.out.print("Connecting to database...");
			con=(Connection) DriverManager.	getConnection("jdbc:mysql://"+dbPath+"/"+dbName, dbLogin,dbPass);
			System.out.println("Success!");
			System.out.print("Building dump...");
			dump.append("DROP DATABASE IF EXISTS `")
					.append(dbName)
					.append("`;\nCREATE DATABASE  IF NOT EXISTS `")
					.append(dbName)
					.append("`;\n use `")
					.append(dbName)
					.append("`;");
			meta = con.getMetaData();
			ResultSet rs = meta.getTables(null, null, "%", null);

			while(rs.next()){
				String typeOfTable = rs.getString("TABLE_TYPE");
				String nameOfTable = rs.getString("TABLE_NAME");
                if ("TABLE".equalsIgnoreCase(typeOfTable)) {
                	ResultSet localMeta = meta.getColumns(null, null, nameOfTable, "%");
                	boolean commaRequired =false;
                	dump.append("\n-- \n-- Table structure for table `")
                			.append(nameOfTable)
                			.append("`\n-- \n\n")
                			.append("DROP TABLE IF EXISTS `")
                			.append(nameOfTable)
                			.append("`;\n")
                			.append("CREATE TABLE `")
                			.append(nameOfTable)
                			.append("` (\n");
                	while (localMeta.next()) {
                		if (commaRequired){
                			dump.append(", \n");
                		}
                		else{
                			commaRequired=true;
                		}
                		String sizeOfColumn = "";
                		String nameOfColumn = localMeta.getString("COLUMN_NAME");
                		String typeOfColumn = localMeta.getString("TYPE_NAME")+" (";
                		String typeFinish = ")";
                		if (localMeta.getString("TYPE_NAME").equalsIgnoreCase("ENUM")){
                			ResultSet localEnum = con.createStatement().executeQuery("SHOW COLUMNS FROM `"
                					+nameOfTable +"` LIKE '"+nameOfColumn+"'");
                			if (localEnum.next()){
                				sizeOfColumn = localEnum.getString("Type");
                				typeOfColumn = "";
                				typeFinish = "";
                			}
                		}
                		else{
                			if (localMeta.getInt("DECIMAL_DIGITS")>0){
                				sizeOfColumn = localMeta.getString("COLUMN_SIZE")+", "+
                						localMeta.getString("DECIMAL_DIGITS");
                			}
                			else {
                				sizeOfColumn = localMeta.getString("COLUMN_SIZE");
                			}
                		}
                		String allowNull = 
                				"NO".equalsIgnoreCase(localMeta.getString("IS_NULLABLE"))?" NOT NULL ":" NULL ";
                		if (typeOfColumn.matches("[A-Za-z]+ UNSIGNED..")){
                			typeOfColumn=typeOfColumn.replaceAll(" UNSIGNED", "");
                			allowNull = " unsigned"+allowNull;
                		}
                		if (typeOfColumn.matches("DATE..") 
                				|| typeOfColumn.matches("TIME..")
                				|| typeOfColumn.matches("BIT..")
                				|| typeOfColumn.matches("DATETIME..")
                				|| typeOfColumn.matches("TIMESTAMP..")){
                			typeOfColumn=typeOfColumn.replaceAll(" \\(", "");
                			sizeOfColumn="";
                			typeFinish = "";
                		}
                		String isAutoInc =
                				"NO".equalsIgnoreCase(localMeta.getString("IS_AUTOINCREMENT"))?"":"AUTO_INCREMENT";
                		dump.append("    `")
                				.append(nameOfColumn)
                				.append("` ")
                				.append(typeOfColumn)
                				.append(sizeOfColumn)
                				.append(typeFinish)
                				.append(allowNull)
                				.append(isAutoInc);
                	}
                	localMeta.close();
                	localMeta = meta.getPrimaryKeys(null, null, nameOfTable);
                	if (localMeta.next()){
                		dump.append(",\n    PRIMARY KEY (");
                		boolean commaInlineRequired =false;
                		do{
                			if (commaInlineRequired){
                				dump.append(", ");
                			}
                			else {
                				commaInlineRequired = true;
                			}
    						dump.append(localMeta.getString("COLUMN_NAME"));
                		}while (localMeta.next());
                		dump.append(")");
                	}
                	localMeta.close();
                	
                	localMeta= con.createStatement().executeQuery("SHOW COLUMNS FROM `" 
                			+ nameOfTable+"`;");
                	while (localMeta.next()){
                		if(localMeta.getString("Key").equalsIgnoreCase("UNI")){
                			dump.append(",\n    UNIQUE KEY `")
                					.append(localMeta.getString("Field"))
                					.append("` (`")
                					.append(localMeta.getString("Field"))
                					.append("`)")
                			;
                		}
                	}
                	
                	localMeta.close();
                	               	
                	localMeta= meta.getImportedKeys(null, null, nameOfTable);
                	while(localMeta.next()){
                		dump.append(",\n    CONSTRAINT `")
                				.append(localMeta.getString("FK_NAME"))
                				.append("`\n    FOREIGN KEY (`")
                				.append(localMeta.getString("FKCOLUMN_NAME"))
                				.append("`)\n    REFERENCES `")
                				.append(localMeta.getString("PKTABLE_NAME"))
                				.append("` (`")
                				.append(localMeta.getString("PKCOLUMN_NAME"))
                				.append("`)\n    ON DELETE ")
                				.append(localMeta.getInt("DELETE_RULE")==0?"CASCADE":"RESTRICT")
                				.append("\n    ON UPDATE ")
                				.append(localMeta.getInt("UPDATE_RULE")==0?"CASCADE":"RESTRICT");
                	}
                	localMeta.close();
                	dump.append("\n) ");
                	ResultSet engine = con.createStatement()
                			.executeQuery("select engine from information_schema.tables where table_name='" 
                			+ nameOfTable + "' and table_schema='"+dbName+"';");
                	engine.next();
                	dump.append("ENGINE=")
                			.append(engine.getString(1))
                			.append(" ");
                	
                	ResultSet encoding = con.createStatement()
                			.executeQuery("select table_collation from information_schema.tables where table_name='" 
                			+ nameOfTable + "' and table_schema='"+dbName+"';");
                	encoding.next();
                	String enc =encoding.getString(1).split("_")[0];
                	dump.append(" DEFAULT CHARSET=")
                			.append(enc);
                	dump.append(";\n");
                	dump.append("\n-- \n-- Dumping data for table `")
                			.append(nameOfTable)
                			.append("`\n-- \n");
                	ResultSet inTable = con.createStatement().executeQuery("SELECT * FROM `"+nameOfTable+"`");
                	dump.append("LOCK TABLES `")
                			.append(nameOfTable)
                			.append("` WRITE;\n/*!40000 ALTER TABLE `")
                			.append(nameOfTable)
                			.append("` DISABLE KEYS */;\n")
                			.append("INSERT INTO `")
                			.append(nameOfTable)
                			.append("` VALUES ");
                	boolean insideComma=false;
                	while(inTable.next()){
                		if (insideComma){
                			dump.append(", ");
                		}
                		else{
                			insideComma=true;
                		}
                		dump.append("(");
                		for (int k=1; k<=inTable.getMetaData().getColumnCount();k++){
                			if (k>1){
                				dump.append(", ");
                			}
                			Object rowValue = inTable.getObject(k);
                			if (rowValue==null){
                				dump.append("NULL");
                			}
                			else{
                				String corrected = (rowValue.toString()).replaceAll("'","\\\\'");
                				dump.append("'")
                						.append(corrected)
                						.append("'");
                			}	
                		}
                		dump.append(")");
                	}
                	dump.append(";\n/*!40000 ALTER TABLE `")
                			.append(nameOfTable)
                			.append("` ENABLE KEYS */;\nUNLOCK TABLES;\n");
                }
			}
			System.out.println("Success!");
			System.out.println("Writing dump to the file...");
			String truePath = DbDumpReceiving.class.getResource(DUMP_PATH).getFile();
			String secondPath = new String(truePath);
			secondPath=secondPath.replaceAll("target/classes", "src/main/java");
			File dumpFile1 = new File(truePath);
			File dumpFile2 = new File(secondPath);
			PrintWriter pw1 = new PrintWriter(new FileWriter(dumpFile1));
			pw1.print(dump.toString());
			pw1.close();
			PrintWriter pw2 = new PrintWriter(new FileWriter(dumpFile2));
			pw2.print(dump.toString());
			pw2.close();			
			System.out.print(dump.toString());
			System.out.println("Success!");
			System.out.println(truePath);
			System.out.println(secondPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		parseXml();
		receiving();

	}
}
