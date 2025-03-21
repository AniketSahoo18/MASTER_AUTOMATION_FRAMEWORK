package runner;

import org.aeonbits.owner.ConfigFactory;

import coreUtil.FrameworkConfig;

public class RunnerWithOwner {
	
	public static void main(String[] args) {
		
		FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);

		System.out.println(config.browser());
	}
}
