package reg.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegFeedback {
	
	public static void main(String[] args) {
		String msg = "已提交金蝶处理  FEEDBACK_NUMBER=R20160331-0385 FEEDBACK_ID=bdb4dc85054d429fbbbe70991caec9bf";
		Pattern p = Pattern.compile("(?<=FEEDBACK_NUMBER=)(R\\d{8}-\\d{4})");
		Matcher matcher = p.matcher(msg);
		if(matcher.find()){
			System.out.println(matcher.group());
		}
		p = Pattern.compile("(?<=FEEDBACK_ID=)([a-fA-F0-9]+)");
		matcher = p.matcher(msg);
		if(matcher.find()){
			System.out.println(matcher.group());
		}
	}
}