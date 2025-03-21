package coreUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import constants.FrameworkConstants;

public final class PropertyUtil {

	private PropertyUtil() {

	}

	private static Properties prop = new Properties();

	static {

		try {

			FileInputStream fis = new FileInputStream(FrameworkConstants.getConfigFilePath());

			prop.load(fis);

		}

		catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static String getValue(String key) throws Exception {

		if (Objects.isNull(prop.getProperty(key)) || Objects.isNull(key)) {

			throw new Exception("Property Name " + key + " is not found. Please check config.properties");
		}

		return prop.getProperty(key);

	}

}
