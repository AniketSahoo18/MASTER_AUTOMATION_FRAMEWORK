package coreUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class APIUtil {

	private APIUtil(){}

//	public static JSONObject parseJsonAndGetKeyValue(String filePath, String paramName) throws IOException {
//
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.readTree()
//
//	}

	public static String readJsonAndGetAsString(String filePath) throws IOException {

		return new String(Files.readAllBytes(Paths.get(filePath)));
	}

	public static void storeStringAsJsonFile(String filePath, Response response) throws IOException {

		Files.write(Paths.get(filePath), response.asByteArray());
	}

	public static String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}

	public static Map<String, Object> getAllKeys(Response response) throws JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		String json = response.asString();

//		List<String> keys = new ArrayList<>();
//		List<Object> values = new ArrayList<>();
		Map<String, Object> keyValueMap = new LinkedHashMap<String, Object>();
		JsonNode jsonNode = mapper.readTree(json);

		getAllKeysUsingJsonNodeFields(jsonNode, keyValueMap);

//		for (int i = 0; i < keys.size(); i++) {
//
//			keyValueMap.get(keys.get(i));
//		}

		return keyValueMap;
	}

	private static void getAllKeysUsingJsonNodeFields(JsonNode jsonNode, Map<String, Object> keyValueMap) {

		if(jsonNode.isObject()) {

			Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();

			fields.forEachRemaining(field -> {

				keyValueMap.put(field.getKey(), field.getValue());

				getAllKeysUsingJsonNodeFields((JsonNode) field.getValue(), keyValueMap);
			});
		}

		else if(jsonNode.isArray()) {

			ArrayNode arrayField = (ArrayNode) jsonNode;
			arrayField.forEach(node -> {

				getAllKeysUsingJsonNodeFields(node, keyValueMap);
			});
		}
	}

	public static List<JSONArray> extractJSONArrays(String jsonData, String jsonArrayKey) {
		List<JSONArray> arrays = new ArrayList<>();
		try {
			JSONObject data = new JSONObject(jsonData);
			findJsonArray(data, jsonArrayKey, arrays);
		} catch (JSONException e) {
			// Handle invalid JSON, return empty list
			return arrays;
		}
		return arrays;
	}

	private static void findJsonArray(Object obj, String jsonArrayKey, List<JSONArray> arrays) {
		if (obj instanceof JSONObject) {
			JSONObject jsonObject = (JSONObject) obj;
			for (String key : jsonObject.keySet()) {
				Object value = jsonObject.get(key);
				if (jsonArrayKey.equals(key) && value instanceof JSONArray) {
					arrays.add((JSONArray) value);
				} else {
					findJsonArray(value, jsonArrayKey, arrays);
				}
			}
		} else if (obj instanceof JSONArray) {
			JSONArray jsonArray = (JSONArray) obj;
			for (int i = 0; i < jsonArray.length(); i++) {
				findJsonArray(jsonArray.get(i), jsonArrayKey, arrays);
			}
		}
	}
}
