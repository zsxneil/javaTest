package reg.test;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegTest {

	public static void main(String[] args) {
		
		charText();
		
		// 简单认识正则表达式的概念
		
		 /*p("abc".matches("...")); p("a8729a".replaceAll("\\d", "-")); 
		 Pattern p = Pattern.compile("[a-z]{3}"); 
		 Matcher m = p.matcher("fgh");
		 p(m.matches()); 
		 p("fgh".matches("[a-z]{3}"));*/
		 

		// 初步认识 . * +
		
		 /*p("a".matches(".")); 
		 p("aa".matches("aa")); 
		 p("aaaa".matches("a*"));
		 p("aaaa".matches("a+")); 
		 p("".matches("a*"));
		 p("aaaa".matches("a?")); 
		 p("".matches("a?")); 
		 p("a".matches("a?"));
		 p("214523145234532".matches("\\d{3,100}"));
		 p("192.168.0.aaa".matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")); 
		 p("192".matches("[0-2][0-9][0-9]"));*/
		 

		// 范围
		/*
		 * p("a".matches("[abc]")); p("a".matches("[^abc]"));
		 * p("A".matches("[a-zA-Z]")); p("A".matches("[a-z]|[A-Z]"));
		 * p("A".matches("[a-z[A-Z]]")); p("R".matches("[A-Z&&[RFG]]"));
		 */

		// 认识 \s \d \w
		/*
		 * p("\n\r\t".matches("\\s{4}")); p("".matches("\\S"));
		 * p("a_8".matches("\\w{3}"));
		 * p("abc888&^%".matches("[a-z]{1,3}\\d+[&^#%]+"));
		 * p("\\".matches("\\\\"));
		 */

		// POSIX Style
		// p("a".matches("\\p{Lower}"));

		// boundary

		/*
		 * p("hello sir".matches("^h.*")); p("hello sir".matches(".*ir$")); p(
		 * "hello sir".matches("^h[a-z]{1,3}o\\b.*"));
		 * p("hellosir".matches("^h[a-z]{1,3}o\\b.*")); //whilte lines p(" \n"
		 * .matches("^[\\s&&[^\\n]]*\\n$"));
		 */
		/*
		 * p("aaa 8888c".matches(".*\\d{4}.")); p("aaa 8888c"
		 * .matches(".*\\b\\d{4}.")); p("aaa8888c".matches(".*\\d{4}."));
		 * p("aaa8888c".matches(".*\\b\\d{4}."));
		 */
		// email
		// p("asdfasdfsafsf@dsdfsdf.com".matches("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+"));

		// matches find lookingAt

		/*
		 * Pattern p = Pattern.compile("\\d{3,5}"); String s =
		 * "123-34345-234-00"; Matcher m = p.matcher(s); p(m.matches());
		 * m.reset(); p(m.find()); p(m.start() + "-" + m.end()); p(m.find());
		 * p(m.start() + "-" + m.end()); p(m.find()); p(m.start() + "-" +
		 * m.end()); p(m.find()); // p(m.start() + "-" + m.end());
		 * p(m.lookingAt()); p(m.lookingAt()); p(m.lookingAt());
		 * p(m.lookingAt());
		 */

		// replacement

		/*
		 * Pattern p = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
		 * Matcher m = p.matcher(
		 * "java Java JAVa JaVa IloveJAVA you hateJava afasdfasdf");
		 * StringBuffer buf = new StringBuffer(); int i = 0; while (m.find()) {
		 * i++; if (i % 2 == 0) { m.appendReplacement(buf, "java"); } else {
		 * m.appendReplacement(buf, "JAVA"); } } m.appendTail(buf); p(buf);
		 */

		// group

		/*Pattern p = Pattern.compile("(\\d{3,5})([a-z]{2})");
		String s = "123aa-34345bb-234cc-00";
		Matcher m = p.matcher(s);
		while (m.find()) {
			p(m.group());
		}*/

	}

	public static void p(Object o) {
		//System.out.println(o);
		
	}
	
	public static void charText() {
		String regEx="^[A-Z,a-z,0-9,-]*$";
		String str = "按--";
		boolean result=Pattern.compile(regEx).matcher(str).find();
		System.out.println(result);
	}

}
