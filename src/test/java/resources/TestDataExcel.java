package resources;

import java.time.LocalDate;
import com.creditdatamw.zerocell.annotation.Column;
import converters.PhoneNumberProcessor;
import converters.StringToIntegerConverter;
import converters.StringToLocalDateConverter;
import io.github.sskorol.data.Sheet;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Sheet(name = "Sheet1")
public class TestDataExcel {

	// POJO

	@Column(name = "Run", index = 0)
	private String run;

	@Column(name = "Book Name", index = 1)
	private String bookName;

	@Column(name = "Isbn", index = 2)
	private String isbn;

	@Column(name = "Aisle", index = 3, converterClass = StringToIntegerConverter.class)
	private int aisle;

	@Column(name = "Author Name", index = 4)
	private String author;

	@Column(name = "Phone Number", index = 5, converterClass = PhoneNumberProcessor.class)
	private String PhoneNumber;

	@Column(name = "Published Date", index = 6, converterClass = StringToLocalDateConverter.class)
	private LocalDate publishedDate;

}
