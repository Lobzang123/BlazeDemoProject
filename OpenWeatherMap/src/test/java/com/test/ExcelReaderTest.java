package com.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.util.ExcelReaderUtility;

public class ExcelReaderTest {
	
	@Test
	public void testExcelReader() {
		
	try {
		ExcelReaderUtility.ReadExcel();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}
}
