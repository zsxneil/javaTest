package poi.test;

import java.util.*;
import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadExcel {

	public static void main(String[] args) {
		List<String[]> list = readByPoi("E:" + File.separator + "poi" + File.separator + "vip.xls", 0);
		for (String[] strings : list) {
			for (String string : strings) {
				System.out.print(string + "---");
			}
			System.out.println();
		}
		
	}

	 /**
	     *  Description:用poi解析excel，支持xls
	     *  
	     *  @param fileName
	     *  @param sheetNumber
	     *  @return
	     */
	    public static List<String[]> readByPoi(String fileName,int sheetNumber) {
	    	HSSFWorkbook workbook = null;
			List<String[]> list = new ArrayList<String[]>();
			try {
				String fileType=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
			        if (fileType.equals("xls")) {  
			        	workbook = new HSSFWorkbook(new FileInputStream(fileName));  
			        }  
			        else  
			        {  
			            System.err.println("您的文档格式不正确！");  
			        }  
			        //创建sheet对象  
				HSSFSheet sheet = workbook.getSheetAt(sheetNumber);
				 int firstRowIndex = sheet.getFirstRowNum();  
		            int lastRowIndex = sheet.getLastRowNum(); 
		            int coloumNum=sheet.getRow(0).getPhysicalNumberOfCells();//获得总列数
		            for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex ++){  
		                HSSFRow row = sheet.getRow(rIndex);  
		                if(row != null){  
		                    int firstCellIndex = row.getFirstCellNum();  
		                    int lastCellIndex = row.getLastCellNum();  
		                    String[] s = new String[coloumNum];
		                    for(int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex ++){  
		                        HSSFCell cell = row.getCell((short) cIndex);  
		                        String value = "";  
		                        int type = cell.getCellType();
		                        
		                        if(cell != null){  
		                        	value = cell.toString();  
		                        	if (HSSFCell.CELL_TYPE_NUMERIC == type) { 
//		                        		 value = String.valueOf(cell.getNumericCellValue()); 
		                        		  Double val = cell.getNumericCellValue();
		                                  if(val == val.longValue()){
		                                      value= "" + val.longValue();
		                                  } 
		                        	} else if (HSSFCell.CELL_TYPE_BLANK == type) {
										value = null;
									}
		                        	if (value == null || "".equals(value.trim())) {
										value = "    ";
									}
		                            s[cIndex]=value;  
		                        }  
		                    }  
		                    list.add(s);  
		                }  
		            }  

			} catch (Exception e) {
//				Log.error(e.getMessage());
				e.printStackTrace();
				
			} 
			return list;
		}
}
