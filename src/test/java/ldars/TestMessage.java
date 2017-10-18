package ldars;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ldars.tools.MessageTool;

public class TestMessage {

	public static void main(String[] s){
		
		String tem="尾号9930用户您好，您已获得南京绿地之窗C-5栋智能门禁权限，点击“open”开启智慧办公之旅！荟生活APP，从工作到生活，您的一站式服务管家！下载链接http://dwz.cn/3OJRnK";
		
		System.out.println(MessageTool.sendMessage(tem, "13814179930"));
		
		/*String ss="{\"status\": 0, \"msg\": \"\", \"data\": {}}";
		JSONObject s2= JSON.parseObject(ss);
		System.out.println(s2.get("status"));*/
		/*String httpUrl = "lvdi.dev.aimoge.com/v1/mobile/send/sms";
		String httpArg = "mobile=13205516161&content=%E3%80%90%E5%87%AF%E4%BF%A1%E9%80%9A%E3%80%91%E6%82%A8%E7%9A%84%E9%AA%8C%E8%AF%81%E7%A0%81%EF%BC%9A888888";
		String jsonResult = request(httpUrl, httpArg);
		System.out.println(jsonResult);*/
	}
	
	/**
	 * @param urlAll
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String sendMessage(String httpUrl, String content,String receiver) {
	    BufferedReader reader = null;
	    String result = null;
	    StringBuffer sbf = new StringBuffer();

	    try {
	        URL url = new URL(httpUrl);
	        HttpURLConnection connection = (HttpURLConnection) url
	                .openConnection();
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	        connection.setDoOutput(true);
	        
	        StringBuffer params = new StringBuffer();
		    params.append("timestamp").append("=").append("1479557435487");
		    params.append("content").append("=").append("测试短信");
		    params.append("receiver").append("=").append("13814179930");
		    params.append("token").append("=").append("1479557435487");
		    byte[] bypes = params.toString().getBytes();
		    connection.getOutputStream().write(bypes);
	        connection.connect();
	        
	        InputStream is = connection.getInputStream();
	        reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        String strRead = null;
	        while ((strRead = reader.readLine()) != null) {
	            sbf.append(strRead);
	            sbf.append("\r\n");
	        }
	        reader.close();
	        result = sbf.toString();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

}
