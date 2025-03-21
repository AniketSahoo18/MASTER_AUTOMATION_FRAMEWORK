package runner;

import static io.github.sskorol.data.TestDataReader.use;
import java.lang.reflect.Method;
import org.testng.annotations.Test;
import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.data.XlsxReader;
import one.util.streamex.StreamEx;
import resources.TestDataExcel;

public class ReadDataXLTest {
	
	@DataSupplier											//can read data from json, csv, yaml, excel
	public StreamEx<TestDataExcel> getData(Method method) {
		
		return use(XlsxReader.class)
				.withTarget(TestDataExcel.class)
				.withSource("testdatas/EXCEL/InputData.xlsx")
				.read()
				.filter(data -> data.getRun().equalsIgnoreCase("yes"));
	}

	@Test(dataProvider = "getData")
	public void testcase1(TestDataExcel data) {

		/*
		 * WebDriverManager.chromedriver().setup(); DriverManager.setDriver(new
		 * ChromeDriver());
		 * 
		 * DriverManager.getDriver().get("https://www.google.co.in/");
		 * DriverManager.getDriver().findElement(By.name("q")).sendKeys(data.getBookName
		 * ());
		 * 
		 * DriverManager.getDriver().quit();
		 */
		
		System.out.println(data);
	}
}
