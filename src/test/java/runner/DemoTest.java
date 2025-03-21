package runner;

import org.testng.annotations.Test;

public class DemoTest {
	
	@Test
	public void testLogin() {
		
		System.out.println(coreUtil.ConfigFactory.getConfig().url());
		System.out.println(coreUtil.ConfigFactory.getConfig().browser());
		System.out.println(coreUtil.ConfigFactory.getConfig().executionMode());
		
	}
}
