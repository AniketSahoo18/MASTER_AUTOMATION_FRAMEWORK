package dataUtil;

public final class TestDataMapper {

	private TestDataMapper() {

	}

	private static final String TESTDATA_BASE_PATH = "./src/test/resources/testdatas/EXCEL/";
	
	public static String excelPath = "";

	public static String getAPIData() {
		
		excelPath = TESTDATA_BASE_PATH + "InputData.xlsx";

		return excelPath;
	}

	public static String getEcommerceData() {
		
		excelPath = TESTDATA_BASE_PATH + "purchaseOrder.xlsx";

		return excelPath;
	}

}
