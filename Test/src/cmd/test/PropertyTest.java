package cmd.test;

import java.util.Properties;

public class PropertyTest {
	
	public static String test(String str) {
		Properties properties = System.getProperties();
		String osName = properties.getProperty("os.name");
		String osVersion = properties.getProperty("os.version");
		String osArch = properties.getProperty("os.arch");
		
		return osName + "/" + osVersion + "/" + osArch;
	}
}
