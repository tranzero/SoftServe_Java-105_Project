package com.ita.edu.softserve.components;

import org.springframework.stereotype.Component;

@Component
public interface Encoder {
	
	String encode(String subject);
}
