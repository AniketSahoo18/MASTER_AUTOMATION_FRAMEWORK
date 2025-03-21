package converters;

import com.creditdatamw.zerocell.converter.Converter;

public class StringToBooleanConverter implements Converter<Boolean> {

	@Override
	public Boolean convert(String value, String columnName, int rowNumber) {

		return value.equalsIgnoreCase("true");
	}
}
