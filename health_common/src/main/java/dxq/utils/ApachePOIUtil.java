package dxq.utils;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ApachePOIUtil {
    private final static String XLSX = "xlsx";
    private final static String XLS = "xls";
    private final static String DATE_FORMAT ="yyyy-MM-dd";
    /**
     *
     */
    public static List<String[]> readExcel(MultipartFile file) throws IOException {
       checkFile(file);
        Workbook workbook =getWorkbook(file);
        List<String[]> list = new ArrayList<String[]>();
        Sheet sheet = workbook.getSheetAt(0);
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        for(int i=firstRowNum+1;i<=lastRowNum;i++)
        {
            Row row = sheet.getRow(i);
            if(row==null)
            {
                continue;
            }
            String[] cells = new String[row.getPhysicalNumberOfCells()];
            short firstCellNum = row.getFirstCellNum();
            short lastCellNum = row.getLastCellNum();
            for(short j=firstCellNum;j<lastCellNum;j++)
            {
                cells[j]=getCellValue(row.getCell(j));

            }
            list.add(cells);
        }
        workbook.close();
        return list;
    }


    public static void checkFile(MultipartFile file) throws IOException {
        if(file==null)
        {
            throw new FileNotFoundException("文件为空");
        }
            String originalFilename = file.getOriginalFilename();
            if (!originalFilename.endsWith(XLS) && !originalFilename.endsWith(XLSX)) {
                throw new IOException("传入的文件不是excel文件");
            }
    }
    public static  Workbook getWorkbook(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        Workbook workbook=null;
        if(originalFilename.endsWith(XLS))
        {
            workbook = new HSSFWorkbook(file.getInputStream());
        }
        if(originalFilename.endsWith(XLSX))
        {
            workbook = new XSSFWorkbook(file.getInputStream());
        }
        return workbook;
    }
    public static String getCellValue(Cell cell)
    {
        String str = "";
        if(cell==null)
        {
            return str;
        }
            String dataFormatString = cell.getCellStyle().getDataFormatString();
        //此处是固定的写法
        if(dataFormatString.equals("m/d/yy"))
        {
            str = new SimpleDateFormat(DATE_FORMAT).format(cell.getDateCellValue());
            return str;
        }
        if(cell.getCellType()==0)
        {
            cell.setCellType(1);
        }
        switch (cell.getCellType())
        {
            case 0:
                str=String.valueOf(cell.getNumericCellValue());
                break;
            case 1:
                str=cell.getStringCellValue();
                break;
            case 2:
                str=String.valueOf(cell.getCellFormula());
            case 3:
                str="";
                break;
            case 4:
                str = String.valueOf(cell.getBooleanCellValue());
            case 5:
                str="非法字符";
                break;
        }
        return str;
    }
}
