package data_p;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter_UpdateColumn {
	double roomID;
	int columnIdx;
	ArrayList<String> columnValueArr = new ArrayList<String>();

	public ExcelWriter_UpdateColumn(ArrayList<String> columnValueArr, int columnIdx) {
		super();
		this.columnValueArr = columnValueArr;
		this.columnIdx = columnIdx;
		ee();
	}

	void ee() {
		try {
			File file = new File("DataTable/RoomData2.xlsx");

			// ���� ���� ����
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));

			int cnt = 0;
			// ù���� sheet ���� �б�
			for (Row row : wb.getSheetAt(0)) {
				//ù��°�� �н�
				if (row.getRowNum() == 0) {
					continue;
				}

				// for�� ���� ����
				if (columnValueArr.size() == cnt) {
					break;
				}

				XSSFCell cell = (XSSFCell) row.getCell(columnIdx);
				if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					int cv = Integer.parseInt(columnValueArr.get(cnt++));
					row.getCell(columnIdx).setCellValue(cv);
				} else {
					row.getCell(columnIdx).setCellValue(columnValueArr.get(cnt++)); 
				}
			}

			// ���� ���� ����
			FileOutputStream fileOut = new FileOutputStream(file);
			wb.write(fileOut);
		} catch (FileNotFoundException fe) {
			System.out.println("FileNotFoundException >> " + fe.toString());
		} catch (IOException ie) {
			System.out.println("IOException >> " + ie.toString());
		}

	}

	public static void main(String[] args) {
		ArrayList<String> co = new ArrayList<String>();
		for (int i = 0; i < 23; i++) {
			co.add(""+(i+1000));
		}
		new ExcelWriter_UpdateColumn(co, 0);

	}

}
