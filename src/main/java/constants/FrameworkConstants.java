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

	private static final String EXTENTREPORTBASEPATH = System.getProperty("user.dir") + "/reports/ExtentReports/";
	private static final String ALLUREREPORTBASEPATH = System.getProperty("user.dir") + "/reports/AllureReports/";

	private static String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss").format(new Date());
	private static final String EXTENTREPORTFOLDERNAME = "ExtentReports_" + timeStamp;
	private static final String ALLUREREPORTFOLDERNAME = "AllureReports_" + timeStamp;

	static {

		try {

			File extentReportFile = new File((EXTENTREPORTBASEPATH + coreUtil.ConfigFactory.getConfig().executionMode() + "_Reports/" + EXTENTREPORTFOLDERNAME));

			extentReportFile.mkdir();

			File allureReportFile = new File((ALLUREREPORTBASEPATH + coreUtil.ConfigFactory.getConfig().executionMode() + "_Reports/" + ALLUREREPORTFOLDERNAME));

			allureReportFile.mkdir();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static String getExtentReportpath() throws Exception {

		return EXTENTREPORTBASEPATH + coreUtil.ConfigFactory.getConfig().executionMode() + "_Reports/" + EXTENTREPORTFOLDERNAME + "/"
				+ coreUtil.ConfigFactory.getConfig().executionMode() + ".html";

	}

	public static String getAllureReportpath() throws Exception {

		return ALLUREREPORTBASEPATH + coreUtil.ConfigFactory.getConfig().executionMode() + "_Reports/" + ALLUREREPORTFOLDERNAME;

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
