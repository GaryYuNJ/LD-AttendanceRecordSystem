package ldars;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.ldars.data.APIMessage;
import com.ldars.interceptor.APPInterceptor;

public class TestAPIToken {

	public static String TOKEN_KEY = "123qweASDzxc";
	private static Logger logger = Logger
			.getLogger(APPInterceptor.class);

	public static void main(String[] s){

		String token = "0cec59536c0fa720df4b249c358a131a";
		// token is not needed when debug
		//if(token == null) return true;  // !! remember to comment this when deploy on server !!
		
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		StringBuffer encodeStr =  new StringBuffer();
		//finalpackage.put("mobile","18001587683");
		finalpackage.put("fromMobile","18001587683");
		finalpackage.put("startDate","2016-11-28 00:00:01");
		finalpackage.put("endDate","2016-11-28 23:59:59");
		finalpackage.put("buildingId","1");
		finalpackage.put("floor","1");
		finalpackage.put("toName", "测试");
		finalpackage.put("toMobile","18705172915");
		//获取要加密的string
		Set es = finalpackage.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) 
					) {
				encodeStr.append(k + "=" + v + "&");
			}
		}
		encodeStr.append(TOKEN_KEY);
		String ss="lvdisendcontent1477897008";
		//System.out.println(encodeStr);
		System.out.println(ss);
		System.out.println(DigestUtils.md5Hex(encodeStr.toString()));
	}

}
