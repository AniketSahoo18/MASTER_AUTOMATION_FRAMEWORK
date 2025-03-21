package resources.pojo.responses.assertions;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import resources.pojo.responses.AddPlaceResponse;

public class MapAssert extends AbstractAssert<MapAssert, AddPlaceResponse> {


    protected MapAssert(AddPlaceResponse addPlaceResponse, Class<?> selfType) {
        super(addPlaceResponse, selfType);
    }

    public static MapAssert assertThat(AddPlaceResponse response) {

        return new MapAssert(response, MapAssert.class);
    }

    public MapAssert hasStatus_OK(){

        Assertions.assertThat(actual.getStatus())
                .withFailMessage(()->"Status is NOT OK")
                .isEqualTo("OK");

        return this;
    }

    public void hasScope_APP(){

        Assertions.assertThat(actual.getScope())
                .withFailMessage(()->"Scope is NOT APP")
                .isEqualTo("APP");

    }
}
