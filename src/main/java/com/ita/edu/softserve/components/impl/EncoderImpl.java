package com.ita.edu.softserve.components.impl;

import java.io.UnsupportedEncodingException;



import org.springframework.stereotype.Component;

import com.ita.edu.softserve.components.Encoder;

/**
 * Class for encoding strings to be part of the url
 * 
 * @author dnycktc
 * 
 */
@Component
public class EncoderImpl implements Encoder{
	/**
	 * String that represents encoding used to format strings in url
	 */
	private static final String ENCODING = "ISO-8859-1";
	
	public EncoderImpl(){
		
	}
	
	/**
	 * Method encodes given string to be part of ulr
	 * @param subject String to encode
	 * @return result of encoding
	 */
	public String encode(String subject){
		try {
			return java.net.URLEncoder.encode(subject, ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return subject;
		}
	}

}
