package Helpers.ExcelHelper;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import Common.Log;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Helpers {

    private FileInputStream fileInput;
    private FileOutputStream fileOut;
    private Workbook workbook;
    private Sheet sheet;
    private Cell cell;
    private Row row;
    private CellStyle cellstyle;
    private Color mycolor;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();

    public void setExcelFile(String ExcelPath, String SheetName) throws Exception {
        try {
            File f = new File(ExcelPath);

            if (!f.exists()) {
                f.createNewFile();
                System.out.println("File doesn't exist, so created!");
            }

            fileInput = new FileInputStream(ExcelPath);
            workbook = WorkbookFactory.create(fileInput);
            sheet = workbook.getSheet(SheetName);
            //sh = wb.getSheetAt(0); //0 - index of 1st sheet
            if (sheet == null) {
                sheet = workbook.createSheet(SheetName);
            }

            this.excelFilePath = ExcelPath;

            //adding all the column header names to the map 'columns'
            sheet.getRow(0).forEach(cell -> {
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getCellData(int rownum, int colnum) throws Exception {
        try {
            cell = sheet.getRow(rownum).getCell(colnum);
            String CellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        CellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        CellData = String.valueOf((long) cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    //Gọi ra hàm này nè
    public String getCellData(String columnName, int rownum) throws Exception {
        return getCellData(rownum, columns.get(columnName));
    }

    public void setCellData(String text, int rownum, int colnum) throws Exception {
        try {
            row = sheet.getRow(rownum);
            if (row == null) {
                row = sheet.createRow(rownum);
            }
            cell = row.getCell(colnum);

            if (cell == null) {
                cell = row.createCell(colnum);
            }
            cell.setCellValue(text);

            fileOut = new FileOutputStream(excelFilePath);
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            throw (e);
        }
    }

    public void SetExcelFile(String FileName, String SheetName) throws Exception {
//        setExcelFile("src/test/resources/DataTest/" + FileName, SheetName);
        setExcelFile("resources/DataTest/" + FileName, SheetName);
    }


    public Object[][] getDataHashMap(String excelPath, String sheetName, int startRow, int endRow) {
        Log.info("Excel File: " + excelPath);
        Log.info("Selected Sheet: " + sheetName);

        Object[][] data = null;

        try {

            File f = new File(excelPath);

            if (!f.exists()) {
                try {
                    Log.info("File Excel path not found.");
                    throw new RuntimeException("File Excel path not found.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            fileInput = new FileInputStream(excelPath);
            workbook = new XSSFWorkbook(fileInput);
            sheet = workbook.getSheet(sheetName);

            int rows = getRows();
            int columns = getColumns();

            Log.info("Row: " + rows + " - Column: " + columns);
            Log.info("StartRow: " + startRow + " - EndRow: " + endRow);

            data = new Object[(endRow - startRow) + 1][1];
            Map<String, String> Data = null;
            for (int rowNums = startRow; rowNums <= endRow; rowNums++) {
                Data = new HashMap<>();
                for (int colNum = 0; colNum < columns; colNum++) {
                    Data.put(getCellData(0, colNum), getCellData(rowNums, colNum));
                }
                data[rowNums - startRow][0] = Data;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.error(e.getMessage());
        }

        return data;


    }


    public int getRows() {
        try {
            return sheet.getLastRowNum();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }
    }

    public int getColumns() {
        try {
            row = sheet.getRow(0);
            return row.getLastCellNum();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }
    }

}



