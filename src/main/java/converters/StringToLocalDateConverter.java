package converters;

import java.time.LocalDate;

import com.creditdatamw.zerocell.converter.Converter;

public class StringToLocalDateConverter implements Converter<LocalDate>{
	
	@Override
	public LocalDate convert(String value, String columnName, int rowNumber) {

		return LocalDate.now();
	}
}
