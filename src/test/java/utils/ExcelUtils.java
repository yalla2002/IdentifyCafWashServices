package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static List<String> readExcel() throws IOException {
		FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\excelsheet\\inputExcel.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheet("Sheet1");
		XSSFRow row1=sheet.getRow(0);
		XSSFRow row2=sheet.getRow(1);
		XSSFRow row3=sheet.getRow(2);
		
		String location=row1.getCell(0).toString();
		String service=row2.getCell(0).toString();
		String mobile=row3.getCell(0).toString();
		
		List<String> excelInputs=new ArrayList<String>();
		excelInputs.add(location);
		excelInputs.add(service);
		excelInputs.add(mobile);
		
		workbook.close();
		file.close();
		return excelInputs;
	}
	
	public static void writeExcel(List<String> carWashsingServices, List<String> phoneNumbers, List<String> customersRating, List<String> customersVotes, String errorMessageText, List<String> gymSubMenu) throws IOException {
		FileOutputStream file=new FileOutputStream(System.getProperty("user.dir")+"\\excelsheet\\outputExcel.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("CarWashServices");
		//Storing CarWashingServices
		XSSFRow row1=sheet.createRow(0);
		row1.createCell(0).setCellValue("Top 5 Car Washing Services In Chennai");
		for(int i=1;i<6;i++) {
			XSSFRow row=sheet.createRow(i);
			row.createCell(0).setCellValue(carWashsingServices.get(i-1));
			row.createCell(1).setCellValue(phoneNumbers.get(i-1));
			row.createCell(2).setCellValue(customersRating.get(i-1));
			row.createCell(3).setCellValue(customersVotes.get(i-1));
		}
		
		XSSFRow row2=sheet.createRow(7);
		row2.createCell(0).setCellValue("Error Message While Entering Wrong Number");
		row2.createCell(1).setCellValue(errorMessageText);
		
		XSSFRow row3=sheet.createRow(9);
		row3.createCell(0).setCellValue("Submenu of Gym Menu");
		
		for(int i=10;i<gymSubMenu.size()+10;i++) {
			XSSFRow row=sheet.createRow(i);
			row.createCell(0).setCellValue(gymSubMenu.get(i-10));
		}
		
		//Writing into Excel
		workbook.write(file);
		workbook.close();
		file.close();
	}
	
	
}
