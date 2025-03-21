package converters;

import com.creditdatamw.zerocell.converter.Converter;

import enums.BrowserTypes;

public class StringToBrowserTypeConverter implements Converter<BrowserTypes>{

	@Override
	public BrowserTypes convert(String value, String columnName, int row) {
		
		return BrowserTypes.valueOf(value.toUpperCase());
	}
	
	
}
