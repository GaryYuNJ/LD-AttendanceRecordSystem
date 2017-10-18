package ldars;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMobile {

	public static void main(String[] s){
/*		String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147))\\d{8}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher("189123125411");  
        if(m.matches()){
        	System.out.println("--");
        }
*/        
		String ss="13814179930";
		System.out.println(ss.substring(7, 11));
	}
}
