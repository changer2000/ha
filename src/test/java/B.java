import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;


public class B {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		parsePath("a.b[i].c.d[j].e[");

	}
	
	public static String parsePath(String path) throws JspException {
		int i=0;
		String regex = "\\[.*?\\]";
		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher = pattern.matcher(path);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
		    System.out.println(matcher.group());  
		    matcher.appendReplacement(sb, "["+new Integer(i++).toString()+"]");
		}
		System.out.println(sb.toString());
		return null;
	}

}
