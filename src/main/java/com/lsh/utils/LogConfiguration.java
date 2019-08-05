
package com.lsh.utils;

import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

/*
 * @author lsh
 * @decription 日志配置
 * */

public class LogConfiguration {
	
	public static void main(String args[]){
		
		//LogConfiguration logConfiguration=new LogConfiguration();
		//System.out.println("模块名称为："+getFunctionName("HomePage_001_CheckHomeText_Test"));
	}
	
	/**配置log4j生成日志的各种参数配置*/
	public static void initLog(){
		//获取模块名称
		//String functionName=getFunctionName(fileName);
		
		/**声明日志文件的存储路径及文件名、格式*/
		final String logFilePath="C:\\WebAutoTesting\\WebAutoTesting.log";
		
		Properties properties=new Properties();
		
		properties.setProperty("log4j.rootLogger", "info,A,B");
		properties.setProperty("log4j.appender.file.encoding", "UTF-8");
		properties.setProperty("log4j.appender.A", "org.apache.log4j.ConsoleAppender");
		properties.setProperty("log4j.appender.A.Target", "System.out");
		properties.setProperty("log4j.appender.A.layout", "org.apache.log4j.PatternLayout");
		properties.setProperty("log4j.appender.A.layout.ConversionPattern", "[%d{yyyy-MM-dd HH:mm:ss}] [%p] %m%n");
		
		properties.setProperty("log4j.appender.B", "org.apache.log4j.DailyRollingFileAppender");
		properties.setProperty("log4j.appender.B.file",logFilePath);
		properties.setProperty("log4j.appender.B.append", "false");
		properties.setProperty("log4j.appender.B.Threshold", "info");
		properties.setProperty("log4j.appender.B.layout", "org.apache.log4j.PatternLayout");
		properties.setProperty("log4j.appender.B.layout.ConversionPattern", "[%d{yyyy-MM-dd HH:mm:ss}] [%p] %m%n");
		
		/**使配置生效，读取java的配置文件*/
		PropertyConfigurator.configure(properties);
		
		
		
		
	}
	
	/**取得模块名称*/
	/*public static String getFunctionName(String fileName){
		String functionName=null;
		int firstUndelineIndex=fileName.indexOf("_");
		functionName=fileName.substring(0,firstUndelineIndex-4);
		return functionName;
		
	}*/

}
