package resources.pojo.requests;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FavFoods {
	
	private String breakfast;
	private String lunch;
	private List<String> dinner;
	
}
