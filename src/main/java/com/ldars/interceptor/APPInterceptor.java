package com.ldars.interceptor;

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
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.ldars.data.APIMessage;

public class APPInterceptor extends HandlerInterceptorAdapter {
	
	public static String TOKEN_KEY = "123qweASDzxc";
	private static Logger logger = Logger
			.getLogger(APPInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("request: "+request);
		
		String token = request.getParameter("token");
		// token is not needed when debug
		//if(token == null) return true;  // !! remember to comment this when deploy on server !!
		
		SortedMap<String, String> finalpackage = new TreeMap<String, String>();
		
		Enumeration paraKeys = request.getParameterNames();
		StringBuffer encodeStr =  new StringBuffer();;
//		while (paraKeys.hasMoreElements()) {
//			String paraKey = (String) paraKeys.nextElement();
//			if(paraKey.equals("token")) 
//				continue;
//			String paraValue = request.getParameter(paraKey);
//			encodeStr += paraValue;
//		}
		//排序
		while (paraKeys.hasMoreElements()) {
			String paraKey = (String) paraKeys.nextElement();
			if(paraKey.equals("token")) 
				continue;
			finalpackage.put(paraKey, request.getParameter(paraKey));
		}
		//获取要加密的string
		Set es = finalpackage.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) 
					//&& !"sign".equals(k)
					//&& !"key".equals(k)
					) {
				encodeStr.append(k + "=" + v + "&");
			}
		}
		encodeStr.append(TOKEN_KEY);
		logger.info("encodeStr: "+encodeStr);
		System.out.println(DigestUtils.md5Hex(encodeStr.toString()));
		System.out.println(encodeStr);
		
		if ( token==null||! token.equals(DigestUtils.md5Hex(encodeStr.toString()))) {
			response.setStatus(200);
			response.setContentType("application/json;charset=utf-8");
			response.setCharacterEncoding("UTF-8");  
			PrintWriter out = null;  
		    try {
		    	APIMessage apiMessage = new APIMessage();
				apiMessage.setStatus(-10);
				if(token==null){
					apiMessage.setMessage("token不能为空");
				}else{
					apiMessage.setMessage("token验证失败");
				}
		        out = response.getWriter();  
		        out.append(JSON.toJSONString(apiMessage));  
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    } finally {  
		        if (out != null) {  
		            out.close();  
		        }  
		    }  
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info(request);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
}
