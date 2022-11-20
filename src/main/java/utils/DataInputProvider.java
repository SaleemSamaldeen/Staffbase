package utils;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.lang.reflect.Method;

public class DataInputProvider {

    static String[][] getSheet(String dataSheetName) {

        String[][] data = null;
        FileInputStream fis;
        XSSFWorkbook workbook;
        XSSFSheet sheet;

        try {
            fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/" + dataSheetName + ".xlsx");
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);

            // get the number of rows
            int rowCount = sheet.getLastRowNum();

            // get the number of columns
            int columnCount = sheet.getRow(0).getLastCellNum();
            data = new String[rowCount][columnCount];

            // loop through the rows
            for (int i = 1; i < rowCount + 1; i++) {
                try {
                    XSSFRow row = sheet.getRow(i);
                    for (int j = 0; j < columnCount; j++) {
                        try {
                            String cellValue = "";
                            try {
                                cellValue = row.getCell(j).getStringCellValue();
                            } catch (NullPointerException e) {

                            }

                            data[i - 1][j] = cellValue;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            fis.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @DataProvider(name = "GenericDataProvider")
    static Object[][] getExcelData(Method m) {
        String testSheet = (m.getAnnotation(Test.class)).testName();
        return getSheet(testSheet);
    }
}