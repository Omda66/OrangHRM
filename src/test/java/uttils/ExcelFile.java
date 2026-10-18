package uttils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelFile {
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;

    public ExcelFile(String FilePath,String sheetName){
        try {
            FileInputStream fileInputStream=new FileInputStream(FilePath);
            workbook=new XSSFWorkbook(fileInputStream);
            sheet=workbook.getSheet(sheetName);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public ExcelFile(String FilePath,int SheetIndex){
        try {
            FileInputStream fileInputStream=new FileInputStream(FilePath);
            workbook=new XSSFWorkbook(fileInputStream);
            sheet=workbook.getSheetAt(SheetIndex);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public int GetRowsCount(){
        return sheet.getPhysicalNumberOfRows();
    }
    public int GetColumCount(){
        return sheet.getRow(0).getPhysicalNumberOfCells();
    }
    public String getFormla(int rowIndex,int colIndex){
        Cell cell=sheet.getRow(rowIndex).getCell(colIndex);
        return cell.getCellFormula();
    }
    public String getSpasifcCelValue(int rowIndex,int colIndex){
        Cell cell=                                                      sheet.getRow(rowIndex).getCell(colIndex);
        DataFormatter dataFormatter=new DataFormatter();
        return dataFormatter.formatCellValue(cell);
    }
}
