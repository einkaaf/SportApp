package com.erfankazemi.drtarmast.Util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Excel {

  public void createExcel(int state,String bmi) {

    HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

    HSSFSheet hssfSheet = hssfWorkbook.createSheet("BMI");

    HSSFRow row = hssfSheet.createRow(0);

    HSSFCell cell = row.createCell(0);

    cell.setCellValue("128");

  }
}
