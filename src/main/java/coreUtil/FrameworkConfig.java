package coreUtil;

import org.aeonbits.owner.Config;

@Config.Sources({ "file:${user.dir}/src/main/resources/properties/config.properties" })

public interface FrameworkConfig extends Config {
	
	String url();

	@DefaultValue("chrome")
	String browser();

	String executionMode();
	
	//Execution screenshots
	boolean passedstepsscreenshots();
	boolean failedstepsscreenshots();
	boolean skippedstepsscreenshots();
	boolean infostepsscreenshots();
	

	int driverStartupWait();
	int pageLoadWait();
	int frameLoadWait();
	int driverExplicitWait();

	String requestType();
	String baseUrl();

}
