package com.ita.edu.SoftServeUniversity.service;

import java.util.List;

import com.ita.edu.SoftServeUniversity.entity.Lines;

public interface LinesService {
	
	public List<Lines> getLinesTwoStationsCertainOrder(); 		

	public List<Lines> getFullLines(); 

	public List<Lines> getLinesByStation(); 	
	
}
