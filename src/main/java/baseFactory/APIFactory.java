package baseFactory;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static io.restassured.RestAssured.*;

public class APIFactory {

    private APIFactory() {}

    public static RequestSpecification buildPostRequest() throws FileNotFoundException {

        PrintStream log = new PrintStream(new FileOutputStream("POSTLog.txt"));

        return given()
                .baseUri(coreUtil.ConfigFactory.getConfig().baseUrl())
                .filters(RequestLoggingFilter.logRequestTo(log), ResponseLoggingFilter.logResponseTo(log))
                .contentType(ContentType.JSON)
                .log().all();
    }

    public static RequestSpecification buildGetRequest() throws FileNotFoundException {

        PrintStream log = new PrintStream(new FileOutputStream("GETLog.txt"));

        return given()
                .baseUri(coreUtil.ConfigFactory.getConfig().baseUrl())
                .filters(RequestLoggingFilter.logRequestTo(log), ResponseLoggingFilter.logResponseTo(log))
                .log().all();
    }

}
