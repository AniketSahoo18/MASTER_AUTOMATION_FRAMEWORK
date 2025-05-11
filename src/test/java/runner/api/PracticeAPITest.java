package runner.api;

import constants.FrameworkConstants;
import coreUtil.APIUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PracticeAPITest {


    @Test
    public void coverPostTest(Method method, ITestContext context) throws IOException {

        try {

            Response response = given()
                    .baseUri("http://localhost:3000/")
                    .contentType(ContentType.JSON)
                    .log().all()
                    .body("{\n" +
                            "  \"product\": \"XYZ\",\n" +
                            "  \"covers\": [\n" +
                            "    {\n" +
                            "      \"StartDate\": \"14/02/2025\",\n" +
                            "      \"expiryDate\": \"19/02/2025\",\n" +
                            "      \"coverCode\": \"ABC\",\n" +
                            "      \"coverName\": \"Apple Ball Cat\",\n" +
                            "      \"premium\": \"\",\n" +
                            "      \"attributes\": [\n" +
                            "        {\n" +
                            "          \"name\": \"Cover Duration\",\n" +
                            "          \"value\": \"6\"\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"name\": \"Duration Unit\",\n" +
                            "          \"value\": \"Days\"\n" +
                            "        },\n" +
                            "        {\n" +
                            "          \"name\": \"Cover SI\",\n" +
                            "          \"value\": \"100000\"\n" +
                            "        }\n" +
                            "      ],\n" +
                            "      \"deleteIndicator\": null\n" +
                            "    }\n" +
                            "  ]\n" +
                            "}")
                    .when().post("/practice");


            //Store the output in JSON File
            APIUtil.storeStringAsJsonFile(FrameworkConstants.getResponseJsonFolderPath() + method.getName() + ".json", response);

        }

        catch (Exception e) {

            System.out.println("Failed reason : " + e.getMessage());
        }

    }

    @Test
    public void coverGetTest(Method method, ITestContext context) throws IOException {

        try {

            Response response = given()
                    .baseUri("http://localhost:3000/")
                    .log().all()
                    .when().get("/practice");

            response.prettyPrint();

            Map<String, Object> keyValueMap = APIUtil.getAllKeys(response);

            String getResponse = response.asString();

            List<JSONArray> attributesArrays = APIUtil.extractJSONArrays(getResponse, "attributes");

            Map<String, Object> attriKeyValue = new LinkedHashMap<String, Object>();

            for(int j = 0; j < attributesArrays.size(); j ++) {

                for (int i = 0; i < attributesArrays.get(j).length(); i++) {

                    JSONObject jsonObj = (JSONObject) attributesArrays.get(j).get(i);

                    attriKeyValue.put("Cover " + (j + 1) + " : " + String.valueOf(jsonObj.get("name")), jsonObj.get("value"));
                }

            }

            System.out.println("attriMap = " + attriKeyValue);
        }

        catch (Exception e) {

            System.out.println("Failed reason : " + e.getMessage());
        }
    }
}
