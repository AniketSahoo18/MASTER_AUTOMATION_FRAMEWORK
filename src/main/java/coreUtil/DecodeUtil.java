package coreUtil;

import java.util.Base64;

public final class DecodeUtil {

	private DecodeUtil() {}
	
	
	public static String getDecodedString(String encodedString) {
		
		return new String(Base64.getDecoder().decode(encodedString.getBytes()));
	}
}
