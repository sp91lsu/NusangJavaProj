package data_p;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

//	private static ExcelReader instance;
//
//	public static ExcelReader getInstance() {
//		if (instance == null) {
//			instance = new ExcelReader();
//		}
//
//		return instance;
//	}

	String basePath = "DataTable/";
	XSSFWorkbook workbook;
	FileInputStream fis;

	public ArrayList<String> titleList = new ArrayList<String>();
	public HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

	public ArrayList<String> getList(String key) {
		return map.get(key);
	}

	public HashMap<String, ArrayList<String>> read(String path) {
		XSSFRow row;

		XSSFCell cell;

		int cnt = 0;

		int cells = 0;

		try {
			FileInputStream inputStream = new FileInputStream(basePath + path);
			workbook = new XSSFWorkbook(inputStream);
			// sheet수 취득
			int sheetCn = workbook.getNumberOfSheets();
			System.out.println("sheet수 : " + sheetCn);
			for (int cn = 0; cn < sheetCn; cn++) {
				// System.out.println("취득하는 sheet 이름 : " + workbook.getSheetName(cn));
				// System.out.println(workbook.getSheetName(cn) + " sheet 데이터 취득 시작");
				// 0번째 sheet 정보 취득
				XSSFSheet sheet = workbook.getSheetAt(cn);
				// 취득된 sheet에서 rows수 취득
				int rows = sheet.getPhysicalNumberOfRows();

				// System.out.println(workbook.getSheetName(cn) + " sheet의 row수 : " + rows);

				// 취득된 row에서 취득대상 cell수 취득

				cells = sheet.getRow(cn).getPhysicalNumberOfCells(); //

				// System.out.println(workbook.getSheetName(cn) + " sheet의 row에 취득대상 cell수 : " +
				// cells);

				for (int r = 0; r < rows; r++) {
					row = sheet.getRow(r); // row 가져오기
					if (row != null) {
						for (int c = 0; c < cells; c++) {
							cell = row.getCell(c);
							if (cell != null) {
								String value = null;
								switch (cell.getCellType()) {
								case XSSFCell.CELL_TYPE_FORMULA:

									value = cell.getCellFormula();

									break;

								case XSSFCell.CELL_TYPE_NUMERIC:

									value = "" + cell.getNumericCellValue();
									value = value.substring(0, value.indexOf("."));
									break;

								case XSSFCell.CELL_TYPE_STRING:

									value = "" + cell.getStringCellValue();

									break;

								case XSSFCell.CELL_TYPE_BLANK:

									value = "[null 아닌 공백]";

									break;

								case XSSFCell.CELL_TYPE_ERROR:

									value = "" + cell.getErrorCellValue();

									break;

								default:

								}

								if (cnt < cells) {
									titleList.add(value);
									map.put(value, new ArrayList<String>());
								} else {

									String title = titleList.get(cnt % cells);
									map.get(title).add(value);
								}

								cnt++;

								System.out.print(value + "\t");

							} else {

								System.out.print("[null]\t");

							}

						} // for(c) 문
						System.out.print("\n");
					}
				} // for(r) 문
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
}
