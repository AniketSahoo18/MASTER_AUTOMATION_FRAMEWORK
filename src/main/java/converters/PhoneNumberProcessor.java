package converters;

import com.creditdatamw.zerocell.converter.Converter;

public class PhoneNumberProcessor implements Converter<String>{
	
	@Override
	public String convert(String value, String columnName, int rowNumber) {

		return value.startsWith("+91") ? value : "+91" + value;
	}
}
