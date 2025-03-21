package reports;

import java.awt.Desktop;
import java.io.File;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import constants.FrameworkConstants;

public final class ExtentReportUtil {

	public static ExtentReports getReport() {

		ExtentReports extentReport = new ExtentReports();

		try {

			ExtentSparkReporter sparkReport = new ExtentSparkReporter(FrameworkConstants.getExtentreportpath());

			sparkReport.config().setDocumentTitle("Test Results");
			sparkReport.config().setReportName(coreUtil.ConfigFactory.getConfig().executionMode() + " Automation Result");
			sparkReport.config().setTheme(Theme.DARK);

			extentReport.attachReporter(sparkReport);

		}

		catch (Exception e) {

			e.printStackTrace();
		}

		return extentReport;
	}

	public static void flushReports(ExtentReports extentReport) {

		try {

			extentReport.flush();
			ExtentManager.unload();
			Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentreportpath()).toURI());

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
