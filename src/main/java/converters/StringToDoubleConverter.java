package converters;

import com.creditdatamw.zerocell.converter.Converter;

public class StringToDoubleConverter implements Converter<Double> {

	@Override
	public Double convert(String value, String columnName, int rowNumber) {

		return Double.parseDouble(value);
	}
}
