package com.ita.edu.softserve.old_service;

import java.util.List;

import com.ita.edu.softserve.entity.Lines;

public interface LinesService {
	
	public List<Lines> getLinesTwoStationsCertainOrder(); 		

	public List<Lines> getFullLines(); 

	public List<Lines> getLinesByStation(); 	
	
}
