package com.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriterUtility {


	public static void writeToExcel(List<Map<String, Object>> lisOfMap) {

		String projectPath = System.getProperty("user.dir");	
		//Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook(); 

		//Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("weatherData");

		System.out.println(" lisOfMap size "+lisOfMap.size());
		String[] arrObj = new String[5];

		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("1", new Object[] {"Expected Temp", "Actual Temp", "Expected temp_min","Actual temp_min", "Expected grnd_level", "ACtual grnd_level", "Expected humidity","Actual humidity", "Expected pressure", "Actual pressure"});
		int flag =2;
		String[][] expectedData = new String[5][5];
		List<Map<String, String>> expectedList = new ArrayList<Map<String,String>>();

		boolean marker= false;
		for (Map<String, Object> map : lisOfMap) {
			int count =0;
			for (Map.Entry<String, Object> entry : map.entrySet()) {

				String key = entry.getKey();
				Object value = entry.getValue();
				System.out.print(key +": "+value);
				arrObj[count++] = value.toString();
				marker = true;
			}
			System.out.println();
			if(flag != 3 && flag != 4) {
				data.put(flag+"", new Object[] {"279.946", arrObj[0], "279.946", arrObj[1], "279.946", arrObj[2],"100",  arrObj[3], "1016.76",arrObj[4]});
			}
			if(flag != 2 && flag != 4) {
				data.put(flag+"", new Object[] {"282.597", arrObj[0], "282.597", arrObj[1], "282.597", arrObj[2],"98",  arrObj[3], "1012.12",arrObj[4]});
			}
			if(flag != 2 && flag != 3) {
				data.put(flag+"", new Object[] {"279.38", arrObj[0], "278.15", arrObj[1], "280.15", arrObj[2],"93",  arrObj[3], "1011",arrObj[4]});
			}
			flag++;
		}

		//Iterate over data and write to sheet
		Set<String> keyset = data.keySet();

		int rownum = 0;
		for (String key : keyset)
		{
			Row row = sheet.createRow(rownum++);
			Object [] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr)
			{
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof String)
					cell.setCellValue((String)obj);
				else if(obj instanceof Integer)
					cell.setCellValue((Integer)obj);
			}
		}
		try
		{
			//Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File(projectPath+"\\outputRecords.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}
