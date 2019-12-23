package com.xiaohei.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
/**
 * @author : WiuLuS
 * @version : v1.12.10.2019
 * @discription :
 * @date : 2019-12-10 15:03:07
 * @email : m13886933623@163.com
 */
public class HttpContextUtils {

	public static HttpServletRequest getHttpServletRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static String getDomain(){
		HttpServletRequest request = getHttpServletRequest();
		StringBuffer url = request.getRequestURL();
		return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
	}

	public static String getOrigin(){
		HttpServletRequest request = getHttpServletRequest();
		return request.getHeader("Origin");
	}

	/**
	 * 获取请求的token
	 */
	public static String getRequestToken(HttpServletRequest httpRequest){
		//从header中获取token
		String token = httpRequest.getHeader("token");

		//如果header中不存在token，则从参数中获取token
		if(StringUtils.isBlank(token)){
			token = httpRequest.getParameter("token");
		}
		return token;
	}
	public static String getHeader(ServletRequest request,String key){
		if (request instanceof HttpServletRequest){
			HttpServletRequest req = (HttpServletRequest) request;
			return req.getHeader(key);
		}
		return "" ;
	}

}
