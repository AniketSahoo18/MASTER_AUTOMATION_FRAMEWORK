package coreUtil.ValidationUtil;

import io.restassured.response.Response;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

public class ResponseAssert extends AbstractAssert<ResponseAssert, Response>{
    private ResponseAssert(Response response, Class<?> selfType) {
        super(response, selfType);
    }

    public static ResponseAssert assertThat(Response response) {

        return new ResponseAssert(response, ResponseAssert.class);
    }

    public ResponseAssert isSuccessfulPostResponse(){

        Assertions.assertThat(actual.getStatusCode())
                .withFailMessage(()->"Status Code is NEITHER 200 NOR 201")
                .isBetween(200,201);

        return this;
    }

    public void isSuccessfulGetResponse(){

        Assertions.assertThat(actual.getStatusCode())
                .withFailMessage(()->"Status Code is NOT 200")
                .isEqualTo(200);
    }

    public void hasHeaderApplicationJson() {

        Assertions.assertThat(actual.header("Content-Type"))
                .withFailMessage(()-> "Header with Content-Type as Application/Json is not present")
                .contains("application/json");
    }
}
