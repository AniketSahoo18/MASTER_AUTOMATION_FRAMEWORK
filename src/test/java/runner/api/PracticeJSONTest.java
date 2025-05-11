package runner.api;

import coreUtil.APIUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PracticeJSONTest {


    public static void main(String[] args) {
        String jsonString = "{\n" +
                "  \"data\": {\n" +
                "    \"groups\": [\n" +
                "      {\n" +
                "        \"group_id\": \"G1\",\n" +
                "        \"group_name\": \"Team Alpha\",\n" +
                "        \"members\": [\n" +
                "          {\n" +
                "            \"member_id\": \"M1\",\n" +
                "            \"member_name\": \"John Doe\",\n" +
                "            \"tasks\": [\n" +
                "              { \"task_id\": \"T1\", \"task_description\": \"Write documentation\" },\n" +
                "              { \"task_id\": \"T2\", \"task_description\": \"Review code\" }\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"member_id\": \"M2\",\n" +
                "            \"member_name\": \"Jane Smith\",\n" +
                "            \"tasks\": [\n" +
                "              { \"task_id\": \"T3\", \"task_description\": \"Design UI\" },\n" +
                "              { \"task_id\": \"T4\", \"task_description\": \"Implement API\" }\n" +
                "            ]\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"group_id\": \"G2\",\n" +
                "        \"group_name\": \"Team Beta\",\n" +
                "        \"members\": [\n" +
                "          {\n" +
                "            \"member_id\": \"M3\",\n" +
                "            \"member_name\": \"David Lee\",\n" +
                "            \"tasks\": [\n" +
                "              { \"task_id\": \"T5\", \"task_description\": \"Database design\" },\n" +
                "              {\"task_id\": \"T6\", \"task_description\": \"Testing\"}\n" +
                "            ]\n" +
                "          },\n" +
                "          {\n" +
                "            \"member_id\": \"M4\",\n" +
                "            \"member_name\": \"Sarah Jones\",\n" +
                "            \"tasks\": [\n" +
                "              {\"task_id\": \"T7\", \"task_description\": \"Deployment\"},\n" +
                "              {\"task_id\": \"T8\", \"task_description\": \"Monitoring\"}\n" +
                "            ]\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";

        List<JSONArray> tasksArrays = APIUtil.extractJSONArrays(jsonString, "tasks");

        for (JSONArray tasks : tasksArrays) {

            String[] strArray = new String[tasks.length()];

            for(int i = 0; i < tasks.length(); i ++) {

                JSONObject a = (JSONObject) tasks.get(i);

                System.out.println(a.get("task_id"));
            }
        }
    }
}
