package resources.pojo.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.util.List;
@Getter
@AllArgsConstructor
@Builder
//@JsonInclude(value = JsonInclude.Include.NON_NULL)
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
//@JsonPropertyOrder(alphabetic = true)
//@JsonPropertyOrder(value = {})
public class AddPlace {

	private int accuracy;
	private String name;
	private String phone_number;
	private String address;
	private String website;
	private String language;
	private Location location;
	private List<String> types;
}