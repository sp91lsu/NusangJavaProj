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
			// sheet�� ���
			int sheetCn = workbook.getNumberOfSheets();
			System.out.println("sheet�� : " + sheetCn);
			for (int cn = 0; cn < sheetCn; cn++) {
				// System.out.println("����ϴ� sheet �̸� : " + workbook.getSheetName(cn));
				// System.out.println(workbook.getSheetName(cn) + " sheet ������ ��� ����");
				// 0��° sheet ���� ���
				XSSFSheet sheet = workbook.getSheetAt(cn);
				// ���� sheet���� rows�� ���
				int rows = sheet.getPhysicalNumberOfRows();

				// System.out.println(workbook.getSheetName(cn) + " sheet�� row�� : " + rows);

				// ���� row���� ����� cell�� ���

				cells = sheet.getRow(cn).getPhysicalNumberOfCells(); //

				// System.out.println(workbook.getSheetName(cn) + " sheet�� row�� ����� cell�� : " +
				// cells);

				for (int r = 0; r < rows; r++) {
					row = sheet.getRow(r); // row ��������
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

									value = "[null �ƴ� ����]";

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

						} // for(c) ��
						System.out.print("\n");
					}
				} // for(r) ��
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
}
