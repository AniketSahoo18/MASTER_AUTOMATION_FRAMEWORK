package reports;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelReportUtil {

	DataFormatter formatter = new DataFormatter();

	public static List<Map<String, String>> getData(String inputPath, String sheetName) throws IOException {

		List<Map<String, String>> testDataAllRows = null;

		Map<String, String> testData = null;

		FileInputStream fis = new FileInputStream(inputPath);
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();

		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				if (sheet == null) {

					throw new IllegalArgumentException("Sheet Number " + i + " does not exist...");
				}

				int rows = sheet.getLastRowNum();

				Row firstRow = sheet.getRow(0);

				int columns = firstRow.getLastCellNum();

				List<String> list = new ArrayList<String>();
				for (int c = 0; c < columns; c++) {

					Cell cell = firstRow.getCell(c);
					String rowHeader = cell.getStringCellValue().trim();
					list.add(rowHeader);

				}

				testDataAllRows = new ArrayList<Map<String, String>>();

				for (int j = 1; j <= rows; j++) {

					Row row = sheet.getRow(j);

					testData = new LinkedHashMap<String, String>();

					for (int k = 0; k < columns; k++) {

						Cell cell = row.getCell(k);
						String colValue = cell.getStringCellValue().trim();
						testData.put((String) list.get(k), colValue);
					}

					testDataAllRows.add(testData);
				}

			}
		}

		return testDataAllRows;

	}

	// Using Fillo...........................................

	public static List<Map<Object, Object>> getTestDataMap(String excelFilePath, String sheetName) {

		String query = "select * from " + sheetName + " where Run = 'Yes'";

		Map<Object, Object> testData = null;
		List<Map<Object, Object>> testDataList = null;

		Fillo fillo = new Fillo();
		Connection connection = null;
		Recordset recordset = null;

		try {

			connection = fillo.getConnection(excelFilePath);
			recordset = connection.executeQuery(query);

			testDataList = new ArrayList<Map<Object, Object>>();

			while (recordset.next()) {

				testData = new LinkedHashMap<Object, Object>();

				for (String field : recordset.getFieldNames()) {

					testData.put(field, recordset.getField(field));
				}

				testDataList.add(testData);
			}

		}

		catch (FilloException e) {

			e.printStackTrace();
		}

		return testDataList;
	}
}
