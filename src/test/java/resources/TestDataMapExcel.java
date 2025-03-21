package resources;

import com.creditdatamw.zerocell.annotation.Column;
import converters.PhoneNumberProcessor;
import converters.StringToDoubleConverter;
import converters.StringToIntegerConverter;
import io.github.sskorol.data.Sheet;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Sheet(name = "Map")
public class TestDataMapExcel {

	// POJO

	@Column(name = "Run", index = 0)
	private String Run;

	@Column(name = "Latitude", index = 1, converterClass = StringToDoubleConverter.class)
	private double Latitude;

	@Column(name = "Longitude", index = 2, converterClass = StringToDoubleConverter.class)
	private double Longitude;

	@Column(name = "Accuracy", index = 3, converterClass = StringToIntegerConverter.class)
	private int Accuracy;

	@Column(name = "Name", index = 4)
	private String Name;

	@Column(name = "Phone Number", index = 5, converterClass = PhoneNumberProcessor.class)
	private String PhoneNumber;

	@Column(name = "Address", index = 6)
	private String Address;

	@Column(name = "Types", index = 7)
	private String Types;

	@Column(name = "Website", index = 8)
	private String Website;

	@Column(name = "Language", index = 9)
	private String Language;

}
