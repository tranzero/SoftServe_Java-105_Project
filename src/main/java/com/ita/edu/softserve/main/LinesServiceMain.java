package com.ita.edu.softserve.main;

import com.ita.edu.softserve.entity.Lines;
import com.ita.edu.softserve.manager.LinesManager;
import com.ita.edu.softserve.manager.impl.LinesManagerImpl;

public class LinesServiceMain {

	public static void main(String[] args) {
		LinesManager lm = (LinesManager) LinesManagerImpl.getInstance();
		
		for(Lines line: lm.getFullLines()){
			System.out.println(line.getLineName());
		}
		System.out.println("Поверув всі лінії");
		
		lm.createLine("MyNewLine1");
		for(Lines line: lm.getFullLines()){
			System.out.println(line.getLineName());			
		}
		System.out.println("Створив лінію MyNewLine1 і поверув всі лінії");	
		
		lm.createLine("MyNewLine2");
		for(Lines line: lm.getFullLines()){
			System.out.println(line.getLineName());			
		}
		lm.createLine("MyNewLine4");
		for(Lines line: lm.getFullLines()){
			System.out.println(line.getLineName());			
		}
		System.out.println("Створив лінію MyNewLine1 і поверув всі лінії");	
		
		lm.createLine("MyNewLine5");
		for(Lines line: lm.getFullLines()){
			System.out.println(line.getLineName());			
		}
		lm.createLine("MyNewLine6");
		for(Lines line: lm.getFullLines()){
			System.out.println(line.getLineName());			
		}
		System.out.println("Створив лінію MyNewLine1 і поверув всі лінії");	
		
		lm.createLine("MyNewLine7");
		for(Lines line: lm.getFullLines()){
			System.out.println(line.getLineName());			
		}
		System.out.println("Створив лінію MyNewLine2 і поверув всі лінії");
		
		lm.updateLine("MyNewLine2", "MyNewLine3");
		for(Lines line: lm.getFullLines()){
			System.out.println(line.getLineName());			
		}
		System.out.println("Переіменував MyNewLine2 в MyNewLine3 і повернув всі лінії");
		
		lm.updateLine("MyNewLine3", "MyNewLine1");
		for(Lines line: lm.getFullLines()){
			System.out.println(line.getLineName());			
		}
		System.out.println("Переіменував MyNewLine3 в MyNewLine1 і повернув всі лінії");
		
		lm.deleteLine("MyNewLine1");
		for(Lines line: lm.getFullLines()){
			System.out.println(line.getLineName());			
		}
		System.out.println("");
	}
}
