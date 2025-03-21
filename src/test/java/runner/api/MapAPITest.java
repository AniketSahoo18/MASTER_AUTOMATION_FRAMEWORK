package runner.api;

import static io.github.sskorol.data.TestDataReader.use;
import static io.restassured.RestAssured.given;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import constants.FrameworkConstants;
import coreUtil.APIUtil;
import coreUtil.ValidationUtil.ResponseAssert;
import io.github.sskorol.core.DataSupplier;
import io.github.sskorol.data.XlsxReader;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import static org.assertj.core.api.Assertions.*;

import one.util.streamex.StreamEx;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.restassured.specification.RequestSpecification;
//import utils.DBConnection;
import resources.*;
import resources.pojo.responses.AddPlaceResponse;
import baseUtil.APIFactory;
import dataUtil.DataProvidersAPI;
import resources.pojo.responses.assertions.MapAssert;

@Listeners(listeners.TestListener.class)

public class MapAPITest {

	RequestSpecification res;
	Response response;
	RequestSpec reqSpec = new RequestSpec();

//	dataProvider = "placeAPIData", dataProviderClass = DataProvidersAPI.class, enabled = true

	@DataSupplier
	public StreamEx<TestDataMapExcel> getData(Method method) {

		return use(XlsxReader.class)
				.withTarget(TestDataMapExcel.class)
				.withSource("testdatas/EXCEL/InputData.xlsx")
				.read()
				.filter(data -> data.getRun().equalsIgnoreCase("Yes"));
	}

	@Test(dataProvider = "getData")
	public void testMap(TestDataMapExcel data, Method method) throws IOException {

		try {

			res = APIFactory.buildPostRequest().body(TestDataBuild.addPlacePayLoad(data));

			response = res.when().post(APIEndpoints.valueOf("AddPlaceAPI").getResource());

//			response.prettyPrint();

			ResponseAssert.assertThat(response)
					.isSuccessfulPostResponse()
					.hasHeaderApplicationJson();

			AddPlaceResponse responseMap = response.as(AddPlaceResponse.class);		//Deserialization --> JSON --> Java obj

			MapAssert.assertThat(responseMap)
					.hasStatus_OK()
					.hasScope_APP();

			//Schema Validation
			response.then().body(JsonSchemaValidator.matchesJsonSchema(new File(FrameworkConstants.getSchemaFolderPath())));

			//Store the output in JSON File
			APIUtil.storeStringAsJsonFile(FrameworkConstants.getResponseJsonFolderPath() + method.getName() + ".json", response);

//			// Connection to DB and fetch data
//			List<Object> data = DBConnection.getDBDataMap();
//
//			ValidationUtil.stepInfo("Status Validation");
//
//			ValidationUtil.validationCheck("soft", status, data.get(0).toString(),
//					"Validation : Expected Value from API and Actual Value from DB");

		}

		catch (Exception e) {

//			Assert.assertTrue(false,
//					"User is not able to perform the below steps : <br>"
//							+ "Validation : Expected Value from API and Actual Value from DB"
//
//							+ "<br>Technical Error Message is as below : <br/>" + e.getMessage());
		}

	}
}
