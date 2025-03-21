package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;

import coreUtil.EmailSender;
import reports.ExtentManager;
import reports.ExtentReportUtil;

public class TestListener implements ITestListener {

	ExtentReports extentReports = ExtentReportUtil.getReport();

	public void onTestStart(ITestResult result) {

		ExtentManager.setExtentTest(extentReports.createTest(result.getMethod().getMethodName()));

	}

	public void onFinish(ITestContext context) {

		ExtentReportUtil.flushReports(extentReports);
		
//		EmailSender.sendEmail();

	}
	
//	public void onTestFailure(ITestResult result) {
//		
//		ExtentLogger.
//	}

}
