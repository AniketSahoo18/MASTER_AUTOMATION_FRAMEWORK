package constants;

import lombok.Getter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FrameworkConstants {

	private FrameworkConstants() {}

	private static @Getter final String requestJsonFolderPath = System.getProperty("user.dir") + "/src/test/resources/testdatas/JSON/";
	private static @Getter final String responseJsonFolderPath = System.getProperty("user.dir") + "/outputs/";
	private static @Getter final String schemaFolderPath = System.getProperty("user.dir") + "/src/test/resources/jsonschemas/";

	private static final String CONFIGFILEPATH = System.getProperty("user.dir")
			+ "/src/main/resources/properties/config.properties";

	private static final int IMPLICITWAIT = 10;
	private static final int EXPLICITWAIT = 10;

	private static final String REPORTBASEPATH = System.getProperty("user.dir") + "/reports/";

	private static String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss").format(new Date());
	private static final String FOLDERNAME = "ExtentReports_" + timeStamp;

	static {

		try {

			File file = new File((REPORTBASEPATH + coreUtil.ConfigFactory.getConfig().executionMode() + "_Reports/" + FOLDERNAME));

			file.mkdir();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static String getExtentreportpath() throws Exception {

		return REPORTBASEPATH + coreUtil.ConfigFactory.getConfig().executionMode() + "_Reports/" + FOLDERNAME + "/"
				+ coreUtil.ConfigFactory.getConfig().executionMode() + ".html";

	}

	public static String getConfigFilePath() {
		return CONFIGFILEPATH;
	}

	public static int getImplicitWait() {
		return IMPLICITWAIT;
	}

	public static int getExplicitWait() {
		return EXPLICITWAIT;
	}
}
