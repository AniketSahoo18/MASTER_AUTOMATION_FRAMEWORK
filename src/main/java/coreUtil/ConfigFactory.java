package coreUtil;

import org.aeonbits.owner.ConfigCache;

public final class ConfigFactory {
	
	private ConfigFactory() {}
	
	public static FrameworkConfig getConfig() {
		
		//Singleton pattern implementation to make sure there is only one instance of the class
		return ConfigCache.getOrCreate(FrameworkConfig.class);
	}
}
