package com.ita.edu.softserve.utils;

import java.io.InputStream;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;





import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 
 * @author dnycktc
 *
 *Stores DOM structure of given file and allows to search using attribute values in it
 */

public class ClasspathXmlParser {
	/**
	 * Stores the DOM structure of given file
	 */
	private Element domElement;
	
	/**
	 * 	  @param filePath path to file inside Java classpath
	 * 	Stores DOM structure of given file inside domElement field  
	 */
	public ClasspathXmlParser(String filePath){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document dom = null;
        InputStream is  = ClasspathXmlParser.class.getResourceAsStream(filePath);
        DocumentBuilder builder = null;
        try {
			builder = factory.newDocumentBuilder();
			dom = builder.parse(is);
			is.close();
		} catch (Exception ec) {
			ec.printStackTrace();
		}
        domElement = dom.getDocumentElement();        
	}
	
	/**
	 * Searches for values in appropriate tags with appropriate attribute values
	 * @param tagName tag to search the value
	 * @param searchingAttribute name of tag attribute to check
	 * @param attributes Array of appropriate values of given attribute of tag
	 * @return Array of found text contents inside tags that are matching given criteria
	 */
	
	public String[] findValues(String tagName,String searchingAttribute, String... attributes){
		 NodeList properties = domElement.getElementsByTagName(tagName);
		 String[] results= new String[attributes.length];
			for (int j=0; j<properties.getLength(); j++){
				Element tmp = (Element) properties.item(j);
				boolean contin = true;
				for (int i=0; (i<attributes.length) && contin;i++){
					String currentAtribute=attributes[i];
					if(tmp.getAttribute(searchingAttribute).equals(currentAtribute)){
						results[i] = tmp.getTextContent();
						contin=false;						
					} 
				}
			}
		 
		return results;
		
	}

}
