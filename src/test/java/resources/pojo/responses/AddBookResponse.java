package resources.pojo.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AddBookResponse {
	@JsonProperty
	private String Msg;
	@JsonProperty
	private String ID;
}
