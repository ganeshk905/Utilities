package automation.utils.io;

import lombok.Getter;
import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by shantonu on 6/8/16.
 * todo, planned to complete ASAP
 */
public class ExcelUtils {
    private static HSSFWorkbook workbook;
    private @Getter static HSSFSheet sheet;
    private static HSSFCell cell;
    private static HSSFCellStyle cellStypeDefault;
    private static StringBuilder fileName = new StringBuilder();
    private static String currentSheet;
    private static String fontDefault = "Calibri-Regular";

    public static String getFileName(){return fileName.toString();}
    private ExcelUtils(){}

    public static void write(String fullFileNameAndPath){

    }
    public static void write(FileOutputStream outputStream){

    }
    public static File createXLfile(String filename){
        return null;
    }
    public static void createSheet(HSSFWorkbook book, String sheetName){}
    public static void createSheet(HSSFWorkbook book, String sheetName, String... headers){}
    public static void createRow(boolean isHeader , String... values){
        
    }
    public static void createCol(boolean isHeader , String... values){}

    private static String generateFileName(){return null;}

    private static HSSFCellStyle createStyle(HSSFWorkbook book){
        HSSFCellStyle style = book.createCellStyle();
        HSSFFont font = book.createFont();
        font.setFontName(fontDefault);
        style.setFont(font);
        style.setWrapText(true);
        style.setBorderLeft((short) 1);
        style.setBorderRight((short) 1);
        style.setBorderTop((short) 1);
        style.setBorderBottom((short) 1);
        return style;
    }
}
