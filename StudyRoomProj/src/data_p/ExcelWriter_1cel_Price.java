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
        	
        	// 엑셀 파일 오픈
        	XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
        	
//        	Cell cell = null;
            
            // 첫번재 sheet 내용 읽기
            for (Row row : wb.getSheetAt(0)) { 
//                // 셋째줄부터..
//                if (row.getRowNum() < 2) {
//                    continue;
//                }
        	   if (row.getRowNum() == 1) {
	                 continue;
        	   }
//                
//                // 두번째 셀이 비어있으면 for문을 멈춘다.
                if(row.getCell(1) == null){
                    break;
                }
                
//                // 콘솔 출력
//                System.out.println("[row] 이름 : " + row.getCell(1) + ", 나이: " + row.getCell(2)
//                                + ", 성별: " + row.getCell(3) + ", 비고: " + row.getCell(4));
                
//                // 4번째 셀 값을 변경
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
            
            // 엑셀 파일 저장
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
