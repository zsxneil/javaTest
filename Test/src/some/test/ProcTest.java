package some.test;

import java.util.Properties;

public class ProcTest {
	public static void main(String[] args) {
		Properties properties = System.getProperties();
		String osName = properties.getProperty("os.name");
		String osArch = properties.getProperty("os.arch");
		String osVersion = properties.getProperty("os.version");
		System.out.println(osName + "/" + osArch +"/" + osVersion);
	}
}
