package com.ethan;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * HSSFWorkbook     03版本的excel
 * SXSSFWorkbook    07版本的excel加强版
 * XSSFWorkbook     07版本的excel
 */
public class ExcelWriteTest {

    private static final String EXCEL_FILE_PATH = "D:\\upload\\";

    /**
     * 03版本的excel测试
     * @throws IOException
     */
    @Test
    public void testExcelWrite01() throws IOException {
        //1.创建一个工作簿
        Workbook wb = new HSSFWorkbook();
        //2.通过工作簿创建一个工作表sheet
        Sheet sheet = wb.createSheet("员工信息表");
        //3.在工作表sheet中创建对应的行row，比如第0行
        Row row0 = sheet.createRow(0);
        //4.在row这一行创建对应的单元格
        Cell cell00 = row0.createCell(0);
        cell00.setCellValue("员工id");
        //多设置两个单元格数据
        Cell cell01 = row0.createCell(1);
        cell01.setCellValue("员工姓名");
        Cell cell02 = row0.createCell(2);
        cell02.setCellValue("入职时间");
        //创建第二行
        Row row1 = sheet.createRow(1);
        Cell cell10 = row1.createCell(0);
        cell10.setCellValue(23);
        Cell cell11 = row1.createCell(1);
        cell11.setCellValue("ethan");
        Cell cell12 = row1.createCell(2);
        cell12.setCellValue(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        //生成一张表，并将表写出out流中输出
        FileOutputStream outExcelFile = new FileOutputStream(EXCEL_FILE_PATH + "03版excel操作员工信息表.xls");
        wb.write(outExcelFile);
        outExcelFile.close();
        System.out.println("03版本excel表生成!");
    }

    /**
     * 03版本的excel测试
     * @throws IOException
     */
    @Test
    public void testExcelWrite02() throws IOException {
        //1.创建一个工作簿
        Workbook wb = new XSSFWorkbook();
        //2.通过工作簿创建一个工作表sheet
        Sheet sheet = wb.createSheet("员工信息表");
        //3.在工作表sheet中创建对应的行row，比如第0行
        Row row0 = sheet.createRow(0);
        //4.在row这一行创建对应的单元格
        Cell cell00 = row0.createCell(0);
        cell00.setCellValue("员工id");
        //多设置两个单元格数据
        Cell cell01 = row0.createCell(1);
        cell01.setCellValue("员工姓名");
        Cell cell02 = row0.createCell(2);
        cell02.setCellValue("入职时间");
        //创建第二行
        Row row1 = sheet.createRow(1);
        Cell cell10 = row1.createCell(0);
        cell10.setCellValue(23);
        Cell cell11 = row1.createCell(1);
        cell11.setCellValue("ethan");
        Cell cell12 = row1.createCell(2);
        cell12.setCellValue(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        //生成一张表，并将表写出out流中输出
        FileOutputStream outExcelFile = new FileOutputStream(EXCEL_FILE_PATH + "07版excel操作员工信息表.xlsx");
        wb.write(outExcelFile);
        outExcelFile.close();
        System.out.println("07版本excel表生成!");
    }

    /**
     * 03版excel处理一个大数据量的表
     * @throws IOException
     */
    @Test
    public void testWrite03BigData() throws IOException {
        //时间
        long begin = System.currentTimeMillis();
        //创建一个薄
        Workbook workbook = new HSSFWorkbook();
        //创建表
        Sheet sheet = workbook.createSheet();
        //写入数据
        for (int rowNum = 0; rowNum < 65536; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        FileOutputStream fos = new FileOutputStream(EXCEL_FILE_PATH + "03版本Excel大量数据测试.xls");
        workbook.write(fos);
        fos.close();
        System.out.println("over");
        long end = System.currentTimeMillis();
        System.out.println((double) (end - begin) / 1000);
    }

    /**
     * 07版excel处理一个大数据量的表
     * @throws IOException
     */
    @Test
    public void testWrite04BigData() throws IOException {
        //时间
        long begin = System.currentTimeMillis();
        //创建一个薄
        Workbook workbook = new XSSFWorkbook();
        //创建表
        Sheet sheet = workbook.createSheet();
        //写入数据
        for (int rowNum = 0; rowNum < 65536; rowNum++) {
            Row row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 10; cellNum++) {
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        FileOutputStream fos = new FileOutputStream(EXCEL_FILE_PATH + "07版本Excel大量数据测试.xlsx");
        workbook.write(fos);
        fos.close();
        System.out.println("over");
        long end = System.currentTimeMillis();
        System.out.println((double) (end - begin) / 1000);
    }
}
