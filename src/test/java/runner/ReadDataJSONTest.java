package runner;

import static io.github.sskorol.data.TestDataReader.use;
import java.lang.reflect.Method;
import org.testng.annotations.Test;
import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.data.JsonReader;
import one.util.streamex.StreamEx;
import resources.TestDataJSON;

public class ReadDataJSONTest {
	
	@DataSupplier											//can read data from json, csv, yaml, excel
	public StreamEx<TestDataJSON> getData(Method method) {
		
		return use(JsonReader.class)
				.withTarget(TestDataJSON.class)
				.withSource("testdatas/Library.json")
				.read();
	}

	@Test(dataProvider = "getData")
	public void testcase1(TestDataJSON data) {
		
		System.out.println(data);
	}
}
