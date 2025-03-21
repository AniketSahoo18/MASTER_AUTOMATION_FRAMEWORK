package resources.pojo.requests;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
//@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
//@JsonPropertyOrder(alphabetic = true)
//@JsonPropertyOrder(value = {})

public class Employee {

	private String firstName;
	private String lastName;
	private int id;
	private String email;
	private List<String> jobs;
	private List<Marks> boardMarks;
	private FavFoods favfoods;

}
