package data_p;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead_PriceExcel {
	
	public ArrayList<String> rowDataArr(int column,String dataPath){
		ArrayList<String> arr = new ArrayList<String>();
		FileInputStream fis;
		XSSFWorkbook workbook;
		XSSFSheet sheet;
		try {
			fis = new FileInputStream(dataPath);
			workbook=new XSSFWorkbook(fis);
		
		int rowindex=0;
		sheet=workbook.getSheetAt(0);
		//행의 수
		int rows=sheet.getPhysicalNumberOfRows();
		for(rowindex=1;rowindex<rows;rowindex++){
		    //행을읽는다
		    XSSFRow row=sheet.getRow(rowindex);
		    if(row !=null){
		        //셀의 수
		        int cells=row.getPhysicalNumberOfCells();
		            //셀값을 읽는다
		            XSSFCell cell=row.getCell(column);
		            String value="";
		            //셀이 빈값일경우를 위한 널체크
		            if(cell==null){
		                continue;
		            }else{
		                //타입별로 내용 읽기
		                switch (cell.getCellType()){
		                case XSSFCell.CELL_TYPE_FORMULA:
		                    value=cell.getCellFormula();
		                    break;
		                case XSSFCell.CELL_TYPE_NUMERIC:
		                    value=cell.getNumericCellValue()+"";
		                    break;
		                case XSSFCell.CELL_TYPE_STRING:
		                    value=cell.getStringCellValue()+"";
		                    break;
		                case XSSFCell.CELL_TYPE_BLANK:
		                    value=cell.getBooleanCellValue()+"";
		                    break;
		                case XSSFCell.CELL_TYPE_ERROR:
		                    value=cell.getErrorCellValue()+"";
		                    break;
		                }
		            }
		            arr.add(value);
//		            System.out.println(value);
		        }
		    }
		} catch (FileNotFoundException fe) {
            System.out.println("FileNotFoundException >> " + fe.toString());
        } catch (IOException ie) {
            System.out.println("IOException >> " + ie.toString());
        }
	
		return arr;
	}


	public static void main(String[] args) {
		
		ArrayList<String> s = new ExcelRead_PriceExcel().rowDataArr(2,"DataTable/RoomData.xlsx");
		System.out.println(s.size());
		System.out.println(s.get(0));
	}

}
