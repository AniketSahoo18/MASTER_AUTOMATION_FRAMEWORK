package dataUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import reports.ExcelReportUtil;

public class DataProvidersUI {

	@DataProvider(name = "testData_Fashion", parallel = false)

	public Object[] ecommerceFashion() throws IOException {

		List<Map<Object, Object>> listMapData = ExcelReportUtil.getTestDataMap(TestDataMapper.getEcommerceData(),
				"Fashion");

		return listMapData.toArray();
	}

	@DataProvider(name = "testData_Electronics", parallel = false)

	public Object[] ecommerceElectronics() throws IOException {

		List<Map<Object, Object>> listMapData = ExcelReportUtil.getTestDataMap(TestDataMapper.getEcommerceData(),
				"Electronics");

		return listMapData.toArray();
	}

}
