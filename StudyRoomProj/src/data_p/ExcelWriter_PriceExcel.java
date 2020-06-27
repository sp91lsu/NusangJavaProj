package data_p;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter_PriceExcel {
	double roomID;
	int pirce;

	public ExcelWriter_PriceExcel(int roomID, int price) {
		super();
		this.roomID = roomID;
		this.pirce = price;
		ee();
	}
		
	void ee() {
        try {
        	File file = new File("DataTable/RoomData.xlsx");
        	
        	// ���� ���� ����
        	XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
        	
//        	Cell cell = null;
            
            // ù���� sheet ���� �б�
            for (Row row : wb.getSheetAt(0)) { 
//                // ��°�ٺ���..
//                if (row.getRowNum() < 2) {
//                    continue;
//                }
        	   if (row.getRowNum() == 1) {
	                 continue;
        	   }
//                
//                // �ι�° ���� ��������� for���� �����.
                if(row.getCell(1) == null){
                    break;
                }
                
//                // �ܼ� ���
//                System.out.println("[row] �̸� : " + row.getCell(1) + ", ����: " + row.getCell(2)
//                                + ", ����: " + row.getCell(3) + ", ���: " + row.getCell(4));
                
//                // 4��° �� ���� ����
//                cell = row.createCell(4);
//                cell.setCellValue(input);
            	   
        	   	XSSFCell cell = (XSSFCell) row.getCell(0);
        	   	System.out.println(cell);
        	   	String value = null;
        	   	if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
        	   		value = cell.getNumericCellValue()+"";
        	   	}
    	   		if(cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
    	   			value = cell.getStringCellValue()+"";
//            	if( (""+cel.getNumericCellValue()).equals(""+roomID)) {
            	}
    	   		if(value.equals(roomID+"")) {
    	   			row.getCell(2).setCellValue(pirce);
    	   			System.out.println(row.getCell(2));
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
		new ExcelWriter_PriceExcel(1001, 2000);
		
	}

}
