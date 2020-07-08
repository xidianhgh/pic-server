package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 读取Excel
 */
@Slf4j
public class ReadExcelUtil {
    private Workbook wb;
    private Sheet sheet;
    private Row row;

    public static Workbook ReadExcel(String filepath) {
        if (filepath == null) {
            return null;
        }
        String ext = filepath.substring(filepath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filepath);
            if (".xls".equals(ext)) {
                return new HSSFWorkbook(is);
            } else if (".xlsx".equals(ext)) {
                return new XSSFWorkbook(is);
            }
        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException", e);
        } catch (IOException e) {
            log.error("IOException", e);
        } finally {
            IOUtils.closeQuietly(is);
        }
        return null;
    }

    /**
     * 获取Excel内容，正式
     */
    public static List<List<String>> getSheet(Workbook wb, int sheetNum) {
        List<List<String>> excel = new ArrayList<>();
        Sheet sheet = wb.getSheetAt(sheetNum);
        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            List<String> rowList = new ArrayList<>();
            // 不可以 j<=row.getLastCellNum();
            for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                rowList.add(cellStringValue(cell));
            }
            excel.add(rowList);
        }
        return excel;
    }

    public static void createExcel(String filepath, List<List<String>> content) throws Exception {
        Workbook workbook = null;
        if (filepath.endsWith(".xls")) {
            workbook = new HSSFWorkbook();
        } else if (filepath.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook();
        } else {
            throw new Exception("文件类型错误！");
        }
        // 创建第一个Sheet页
        Sheet sheet = workbook.createSheet("第一个Sheet页");

        for (int i = 0; i < content.size(); i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < content.get(i).size(); j++) {
                String value = content.get(i).get(j);
                Cell cell = row.createCell(j);
                cell.setCellValue(value);
            }
        }
        FileOutputStream os = new FileOutputStream(filepath);
        workbook.write(os);
    }

    public static String cellStringValue(Cell cell) {
        return String.valueOf(cellValue(cell));
    }

    public static Object cellValue(Cell cell) {
        Object val = new Object();
        if (cell != null) {
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                // 当excel 中的数据为数值或日期是需要特殊处理
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    double d = cell.getNumericCellValue();
                    Date date = HSSFDateUtil.getJavaDate(d);
                    SimpleDateFormat dformat = new SimpleDateFormat(
                            "yyyy-MM-dd");
                    val = dformat.format(date);
                } else {
                    NumberFormat nf = NumberFormat.getInstance();
                    nf.setGroupingUsed(false);// true时的格式：1,234,567,890
                    val = nf.format(cell.getNumericCellValue());// 数值类型的数据为double，所以需要转换一下
                }
            } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                val = cell.getStringCellValue();
            } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                val = cell.getCellFormula();
            } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                val = cell.getBooleanCellValue();
            } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
                val = cell.getErrorCellValue();
            }
        }
        return val;
    }

    public static String getValue(String filepath, int sheetNum, int row, int column) {
        Workbook wb = ReadExcel(filepath);
        List<List<String>> content = getSheet(wb, sheetNum);
        List<String> rows = content.get(row);
        String value = rows.get(column);
        return value;
    }


    public static void main(String[] args) throws Exception {
        createExcel();
        readExcelTest();
    }

    ////以下为测试方法
    public static void readExcelTest() {
        String filepath = "C:\\Users\\MI\\Desktop\\测试poi.xlsx";
        String value = getValue(filepath, 0, 1, 7);
        System.out.println(value);
    }

    public static void createExcel() throws Exception {

        int num = 0;
        List<List<String>> content = new ArrayList<>();
        // i为行，j为列
        for (int i = 0; i < 2; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                row.add(String.valueOf(++num));
            }
            content.add(row);
        }

        String filepath = "C:\\Users\\MI\\Desktop\\测试poi.xlsx";
        createExcel(filepath, content);
    }


}