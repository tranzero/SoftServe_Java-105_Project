package com.ita.edu.softserve.manager.impl;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import com.ita.edu.softserve.manager.FileManager;

public class FileManagerImplTest {
	
	@Mock
	File file;
	
	@InjectMocks
	FileManager fileManager = new FileManagerImpl();
	
	String fileName = "vrode.kak";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testDeleteFile() {
		
		boolean isDeleted = false;
		isDeleted = fileManager.deleteFile(fileName);
		assertTrue(isDeleted);
		
	}
	
	@Test(expected = Exception.class)
	public final void testDeleteFileExeption() {
		
//		boolean isDeleted = true;
		doThrow(new Exception()).when(file).delete();
		fileManager.deleteFile(fileName);
//		assertFalse(isDeleted);
		
	}

	@Test
	public final void testCreateFile() {
	}

}
