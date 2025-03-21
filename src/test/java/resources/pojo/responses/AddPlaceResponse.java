package resources.pojo.responses;

import lombok.Getter;
import lombok.ToString;

@Getter
//@ToString
public class AddPlaceResponse {

	private String status;
	private String place_id;
	private String scope;
	private String reference;
	private String id;
}
