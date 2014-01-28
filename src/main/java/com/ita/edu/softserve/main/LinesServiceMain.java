package com.ita.edu.softserve.main;

import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.service.LinesManager;
import com.ita.edu.softserve.service.impl.LinesManagerImpl;

public class LinesServiceMain {

	public static void main(String[] args) {
		LinesManager lm = (LinesManager) LinesManagerImpl.getInstance();
		
		for(Lines line: lm.getFullLines()){
			System.out.println(line.getLineName());
			
		}

	}

}
