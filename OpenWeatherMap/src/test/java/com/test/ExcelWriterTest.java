package com.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.util.ExcelWriterUtility;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ExcelWriterTest {

	@Test
	public void testExcelWriter() {

		Response response = RestAssured.given()
				.when()
				.get("https://samples.openweathermap.org/data/2.5/history/city?q=London,UK&appid=b1b1%205%20e88fa797225412429c1c50c122a1")
				.thenReturn()
				;
		
		List<Map<String, Object>> listOfMap = new ArrayList<Map<String,Object>>();
		byte[] compressed;
		try {
			compressed = compress(response.asString());
			String decompressed = decompress(compressed);
			System.out.println("================================");
			//System.out.println(decompressed);
			JSONObject jsnobject = new JSONObject(decompressed);
			JSONArray jsonArray = jsnobject.getJSONArray("list");
			System.out.println(jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				JSONObject explrObject = jsonArray.getJSONObject(i);
				System.out.println(explrObject.get("main"));
				JSONObject jsonObj = (JSONObject) explrObject.get("main");
				System.out.println("*************************");
				System.out.println(jsonObj.get("temp"));
				map.put("temp", jsonObj.get("temp").toString());
				map.put("temp_min", jsonObj.get("temp_min").toString());
				map.put("temp_max", jsonObj.get("temp_max").toString());
				map.put("humidity", jsonObj.get("humidity").toString());
				map.put("pressure", jsonObj.get("pressure").toString());
				listOfMap.add(map);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("listOfMap=== "+listOfMap.size());
		ExcelWriterUtility.writeToExcel(listOfMap);

	}
	public static byte[] compress(String data) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length());
		GZIPOutputStream gzip = new GZIPOutputStream(bos);
		gzip.write(data.getBytes());
		gzip.close();
		byte[] compressed = bos.toByteArray();
		bos.close();
		return compressed;
	}

	public static String decompress(byte[] compressed) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(compressed);
		GZIPInputStream gis = new GZIPInputStream(bis);
		BufferedReader br = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		gis.close();
		bis.close();
		return sb.toString();
	}

}
