package runner;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import resources.pojo.requests.Employee;
import resources.pojo.requests.FavFoods;
import resources.pojo.requests.Marks;

import static io.restassured.RestAssured.*;

import java.util.Arrays;

public class ReadDataPojoTest {

	@Test
	public void test() {

		Marks marks1 = new Marks(95, 98);
		Marks marks2 = new Marks(86, 96);
		FavFoods favFoods = new FavFoods("bread", "rice", Arrays.asList("Roti", "Sabji"));
		Employee emp = new Employee("Aniket", "Sahoo", 123, "xyz@gmail.com", Arrays.asList("Tester", "Developer"),
				Arrays.asList(marks1, marks2), favFoods);

		Response response = given().header("Content-Type", ContentType.JSON).log().all().body(emp)
				.post("http://localhost:3000/employees");

		response.prettyPrint();

		Assert.assertEquals(response.getStatusCode(), 201);

	}
}
