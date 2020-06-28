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
		//���� ��
		int rows=sheet.getPhysicalNumberOfRows();
		for(rowindex=1;rowindex<rows;rowindex++){
		    //�����д´�
		    XSSFRow row=sheet.getRow(rowindex);
		    if(row !=null){
		        //���� ��
		        int cells=row.getPhysicalNumberOfCells();
		            //������ �д´�
		            XSSFCell cell=row.getCell(column);
		            String value="";
		            //���� ���ϰ�츦 ���� ��üũ
		            if(cell==null){
		                continue;
		            }else{
		                //Ÿ�Ժ��� ���� �б�
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
