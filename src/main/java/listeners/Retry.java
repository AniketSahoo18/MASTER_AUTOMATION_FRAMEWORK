package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int counter = 0;
	int limit = 2;

	@Override
	public boolean retry(ITestResult result) {

		if (counter < limit) {

			System.out.println("Something Wrong Trying Again...");
			
			counter++;

			return true;
		}
		
		System.out.println("Sorry its failing...");
		
		return false;
	}

}
