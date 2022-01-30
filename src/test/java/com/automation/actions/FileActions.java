package com.automation.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;

public class FileActions {

    private static Logger logger = LogManager.getLogger(FileActions.class);

    public static void readExcelFile(String fileName) throws IOException {
        Workbook workbook = null;
        try {
            String filePath = "NewFile.xlsx";

            // Creating a Workbook from an Excel file (.xls or .xlsx)
             workbook = WorkbookFactory.create(new File(filePath));
            // Retrieving the number of sheets in the Workbook
            System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
            // Getting the Sheet at index zero
            Sheet sheet = workbook.getSheetAt(0);
            // Create a DataFormatter to format and get each cell's value as String
            DataFormatter dataFormatter = new DataFormatter();

            for (Row row : sheet) {
                for (Cell cell : row) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    System.out.print(cellValue + "\t");
                }
                System.out.println();
            }

        } catch (Exception e) {
            System.err.println("Exception occurred." + e.getMessage());
            e.printStackTrace();
        }finally {
            if(workbook!=null)
                workbook.close();
        }
    }

    public static void main(String[] args) throws IOException {
        readExcelFile("");
    }
}
