package dataUtil;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import reports.ExcelReportUtil;

public class DataProvidersAPI {

	@DataProvider(name = "libraryAPIData")

	public Object[] libraryAPI() {

		List<Map<Object, Object>> listMapData = ExcelReportUtil.getTestDataMap(TestDataMapper.getAPIData(), "AddBook");

		return listMapData.toArray();
	}

	@DataProvider(name = "placeAPIData")

	public Object[] getTestData() {

		List<Map<Object, Object>> listMapData = ExcelReportUtil.getTestDataMap(TestDataMapper.getAPIData(), "AddPlace");

		return listMapData.toArray();

	}
}
