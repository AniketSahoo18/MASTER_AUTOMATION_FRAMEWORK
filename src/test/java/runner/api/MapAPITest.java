package runner.api;

import static io.github.sskorol.data.TestDataReader.use;
import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import constants.FrameworkConstants;
import coreUtil.APIUtil;
import coreUtil.ValidationUtil.ResponseAssert;
import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.data.XlsxReader;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import one.util.streamex.StreamEx;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//import utils.DBConnection;
import resources.*;
import resources.pojo.responses.AddPlaceResponse;
import baseFactory.APIFactory;
import resources.pojo.responses.assertions.MapAssert;

@Listeners(listeners.TestListener.class)

public class MapAPITest {

	Response response;

	AddPlaceResponse responseMap;

//	dataProvider = "placeAPIData", dataProviderClass = DataProvidersAPI.class, enabled = true

	@DataSupplier
	public StreamEx<TestDataMapExcel> getData(Method method) {

		return use(XlsxReader.class)
				.withTarget(TestDataMapExcel.class)
				.withSource("testdatas/EXCEL/InputData.xlsx")
				.read()
				.filter(data -> data.getRun().equalsIgnoreCase("Yes"));
	}

	@Test(dataProvider = "getData", priority = 1)
	public void testMapPOST(TestDataMapExcel data, Method method, ITestContext context) throws IOException {

		try {

			response = APIFactory.buildPostRequest()
					.queryParam("key", "qaclick123")
					.body(TestDataBuild.addPlacePayLoad(data))
					.when().post(APIEndpoints.valueOf("AddPlaceAPI").getResource());

			ResponseAssert.assertThat(response)
					.isSuccessfulPostResponse()
					.hasHeaderApplicationJson();

			responseMap = response.as(AddPlaceResponse.class);		//Deserialization --> JSON --> Java obj

			MapAssert.assertThat(responseMap)
					.hasStatus_OK()
					.hasScope_APP();

			//Schema Validation
			response.then().body(JsonSchemaValidator.matchesJsonSchema(new File(FrameworkConstants.getSchemaFolderPath() + "schema.json")));

			//Store the output in JSON File
			APIUtil.storeStringAsJsonFile(FrameworkConstants.getResponseJsonFolderPath() + method.getName() + ".json", response);

			context.setAttribute("ResponseMap", responseMap);
			context.setAttribute("PlaceID", responseMap.getPlace_id());

		}

		catch (Exception e) {

			System.out.println("Failed reason : " + e.getMessage());
		}

	}

	@Test(dataProvider = "getData", priority = 2)
	public void testMapGET(TestDataMapExcel data, Method method, ITestContext context) throws IOException {

		try {

			response = APIFactory.buildGetRequest()
					.queryParam("key", "qaclick123")
					.queryParam("place_id", (String) context.getAttribute("PlaceID"))
					.when().get(APIEndpoints.valueOf("GetPlaceAPI").getResource());

			ResponseAssert.assertThat(response)
					.isSuccessfulGetResponse();

			Map<String, Object> keyValueMap = APIUtil.getAllKeys(response);

			System.out.println("keyValueMap = " + keyValueMap);

			keyValueMap.get("");

		}

		catch (Exception e) {

			System.out.println("Failed reason : " + e.getMessage());
		}

	}
}
