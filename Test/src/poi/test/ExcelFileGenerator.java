package poi.test;

/**
 * 提单数据导出Excel 生成器
 * @version 1.0
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelFileGenerator {

	private final int SPLIT_COUNT = 1500; //Excel每个工作簿的行数

	private ArrayList<Object> fieldName = null; //excel标题数据集

	private ArrayList<Object> fieldData = null; //excel数据内容	

	private HSSFWorkbook workBook = null;

	/**
	 * 构造器
	 * @param fieldName 结果集的字段名
	 * @param data
	 */
	public ExcelFileGenerator(ArrayList<Object> fieldName, ArrayList<Object> fieldData) {

		this.fieldName = fieldName;
		this.fieldData = fieldData;
	}

	/**
	 * 创建HSSFWorkbook对象
	 * @return HSSFWorkbook
	 */
	public HSSFWorkbook createWorkbook() {

		workBook = new HSSFWorkbook();
		int rows = fieldData.size();
		int sheetNum = 0;

		if (rows % SPLIT_COUNT == 0) {
			sheetNum = rows / SPLIT_COUNT;
		} else {
			sheetNum = rows / SPLIT_COUNT + 1;
		}

		for (int i = 1; i <= sheetNum; i++) {
			HSSFSheet sheet = workBook.createSheet("Page " + i);
			HSSFRow headRow = sheet.createRow((short) 0); 
			for (int j = 0; j < fieldName.size(); j++) {
				HSSFCell cell = headRow.createCell((short) j);
				//添加样式
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				//设置表头样式  2016-11-12
				HSSFCellStyle cellStyle = workBook.createCellStyle();
				HSSFFont font = workBook.createFont();
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font.setColor(HSSFColor.RED.index);
				cellStyle.setFont(font);
				cell.setCellStyle(cellStyle);
				sheet.setColumnWidth((short)j, (short)4000);
				if(fieldName.get(j) != null){
					cell.setCellValue((String) fieldName.get(j));
				}else{
					cell.setCellValue("-");
				}
			}

			for (int k = 0; k < (i == sheetNum ? rows % SPLIT_COUNT : SPLIT_COUNT); k++) {
				HSSFRow row = sheet.createRow((short) (k + 1));
				//将数据内容放入excel单元格
				ArrayList<Object> rowList = (ArrayList<Object>) fieldData.get((i - 1) * SPLIT_COUNT + k );
				for (int n = 0; n < rowList.size(); n++) {
					HSSFCell cell = row.createCell((short) n);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					if(rowList.get(n) != null){
						cell.setCellValue((String) rowList.get(n).toString());
					}else{
						cell.setCellValue("");
					}
				}
			}
		}
		return workBook;
	}

	public void expordExcel(OutputStream os) throws Exception {
		workBook = createWorkbook();
		workBook.write(os);
//		os.close();
	}
	
	public static void main(String[] args) throws Exception {
		File file = new File("E:\\poi\\fb.xls");
		OutputStream os = new FileOutputStream(file);
		ArrayList<Object> fieldName = new ArrayList<>();
		fieldName.add("头1");
		fieldName.add("头2");
		fieldName.add("头3");
		fieldName.add("头4");
		fieldName.add("头5");
		
		ArrayList<Object> fieldData = new ArrayList<>();
	/*	List<Object> row1 = new ArrayList<>();
		row1.add("行1列1");
		row1.add("行1列2");
		row1.add("行1列3");
		row1.add(33);
		row1.add("行1列5");
		List<Object> row2 = new ArrayList<>();
		row2.add("行2列1");
		row2.add("行2列2");
		row2.add("行2列3");
		row2.add("行2列4");
		row2.add("2016-05-23");
		List<Object> row3 = new ArrayList<>();
		row3.add("行3列1");
		row3.add("行3列2");
		row3.add("行3列3");
		row3.add("行3列4");
		row3.add("行3列5");
		fieldData.add(row1);
		fieldData.add(row2);
		fieldData.add(row3);*/
		for(int i=0;i<2000;i++) {
			List<Object> row = new ArrayList<>();
			row.add("分页测试" + i);
			row.add("分页测试" + i);
			row.add("分页测试" + i);
			row.add("分页测试" + i);
			row.add("分页测试" + i);
			
			fieldData.add(row);
		}
		
		ExcelFileGenerator generator = new ExcelFileGenerator(fieldName, fieldData);
		generator.expordExcel(os);
	}

}

