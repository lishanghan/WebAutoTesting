
package com.lsh.utils;

import java.io.IOException;

import lombok.extern.log4j.Log4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.Reporter;


/**
 * @author lish
 * @description:HttpClient 常用方法封装
 * */
@Log4j
public class HttpClientUtils {
	

	/**HttpClient对象*/
	public static CloseableHttpClient httpClient;
	/**HttpResponse对象*/
	public static CloseableHttpResponse response;
	/**http 响应状态码*/
	public static String httpResponseCode;
	/**http 响应实体*/
	public static HttpEntity httpResponseEntity;

	
	
	/**
	 * 封装post请求方法,请求与响应都是Json格式字符串
	 * @param  requestURL 接口地址
	 * @param  requestParameters 接口请求参数
	 * @return String responseBody 返回请求响应
	 * */
	public static String doHttpJsonPost(String requestURL,String requestParameters){
		
		httpClient=HttpClients.createDefault();
		
		String responseBody=null;
		//创建HttpPost对象
		HttpPost httpPost=new HttpPost(requestURL);
		
		
		
		try {
			//给httpPost设置JSON格式的参数
			StringEntity stringEntity=new StringEntity(requestParameters,"utf-8");
			//设置请求头
			httpPost.setHeader("Content-type", "application/json");
			httpPost.setHeader("Accept", "application/json");
			httpPost.setEntity(stringEntity); 
			
			//String beforeTime=DateFormat.getCurrentTime("yyyy-MM-dd HH:mm:ss:SSS");
            long startTime = System.currentTimeMillis();
			log.info("***********************************************开始调用接口！******************************************************");
			log.info("接口请求为："+requestParameters);
			response=httpClient.execute(httpPost);
            long endTime = System.currentTimeMillis();
			log.info("***********************************************接口调用结束！******************************************************");
			double takeUpTime = (endTime - startTime) / 1000d;
			log.info("接口调用耗时(s)："+takeUpTime);
			httpResponseCode=response.getStatusLine().getStatusCode()+"";
			if(httpResponseCode.equals("200")){
				log.info("发送Http请求成功！Http响应状态为：【"+httpResponseCode+"】");
				
				httpResponseEntity=response.getEntity();
				responseBody=EntityUtils.toString(httpResponseEntity,"UTF-8");
				
				log.info("http请求响应为："+response);
				log.info("接口响应为："+responseBody);
				
			}else{
				log.error("发送Http请求失败！http响应状态码为【"+httpResponseCode+"】");
			}
			Reporter.log("请求URL："+requestURL);
			Reporter.log("请求方式：post");
			Reporter.log("接口调用耗时(s)："+ takeUpTime);
			Reporter.log("接口请求参数："+requestParameters);
			
			
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
			log.error("ClientProtocolException",e);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			log.error("IOException",e);
		}
		
		return responseBody;
		
	}
	
	
	
	/**
	 * https-post-json请求方法封装
	 * @param 
	 * */
	public static void doHttpsPostJson(){
		
		
	}
	
	/**关闭链接，释放资源 */
	public static void close(){		
		
			try {
				//关闭连接，释放资源
				response.close();
				httpClient.close();
				
			} catch (IOException e) {
				
				e.printStackTrace();
				log.error("IOException",e);
			}
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
