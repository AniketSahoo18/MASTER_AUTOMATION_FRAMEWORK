package resources;

import resources.pojo.requests.AddBook;
import resources.pojo.requests.AddPlace;
import resources.pojo.requests.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestDataBuild {

    // Place Data

    public static AddPlace addPlacePayLoad(TestDataMapExcel data) {

        return AddPlace.builder()
                .location(Location.builder().lat(data.getLatitude()).lng(data.getLongitude()).build())
                .accuracy(data.getAccuracy())
                .name(data.getName())
                .phone_number(data.getPhoneNumber())
                .address(data.getAddress())
                .types(Arrays.asList(data.getTypes().split(",")))
                .website(data.getWebsite())
                .language(data.getLanguage()).build();
    }

    public String deletePlacePayload(String placeId) {
        return "{\r\n    \"place_id\":\"" + placeId + "\"\r\n}";
    }

    // Book Data

    public static AddBook addBookPayLoad(Map<String, String> mapData) {
        AddBook b = new AddBook();

        b.setName(mapData.get("BookName"));
        b.setIsbn(mapData.get("Isbn"));
        b.setAisle(mapData.get("Aisle"));
        b.setAuthor(mapData.get("Author"));

        return b;
    }
}
