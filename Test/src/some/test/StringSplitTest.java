package some.test;

import java.util.HashSet;

public class StringSplitTest {
	public static void main(String[] args) {
		String str = "820";
		str.toCharArray();
	}
	
	public static void splitText() {
		String location = "/opt/test.txt";
		HashSet<String> set = new HashSet<>();
		String[] locationStrings = location.split("/");
		for (int i = 0; i < locationStrings.length; i++) {
			System.out.println(locationStrings[i]);
			set.add(locationStrings[i]);
		}
		
		for (String string : set) {
			System.out.println(string);
		}
	}
	
	
}
