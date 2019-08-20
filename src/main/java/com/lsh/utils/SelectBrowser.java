package com.lsh.utils;
/**
 * @author lsh
 * @decrition 在不同的平台上选择对应的浏览器，系统平台程序自动判断是什么平台
 * */

import java.util.Properties;
import com.lsh.Po.YmlConfig;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;

@Log4j

public class SelectBrowser {

	/**
	 * @description:选择浏览器的方法封装
	 * @param browserName ie/chrome/firefox/ghost
	 */
	public WebDriver selectBrowserByName(String browserName){
		
		//获得系统属性集
		Properties properties=System.getProperties();
		//获得当前操作系统名称
		String currentPlateform=properties.getProperty("os.name");
		log.info("当前操作系统是["+currentPlateform+"]");
		log.info("启动的浏览器是["+browserName+"]");
		Reporter.log(("启动的浏览器:["+browserName+"]"));
		
		//从driverPaht.yml 配置文件读取各驱动路径
		YmlConfig yml = YamlUtil.getYml("src/main/resources/driverPath.yml");
		//声明好驱动路径
		String chromedriver_win=yml.getChromeDriver_win();
		String chromedriver_linux=yml.getChromeDriver_linux();
		String firefoxdriver_win=yml.getFirefoxDriver_win();
		String firefoxdriver_linux=yml.getFirefoxDriver_linux();
		String iedriver=yml.getIeDriver_win();
		String ghostdriver=yml.getPhantomjsDriver_win();

		
		//String.toLowerCase():将所有的英文字符转化成小写；String.contains("要找的字符串"):指定的字符串是否包含要寻找的字符串
		if(currentPlateform.toLowerCase().contains("windows")){//判断如果是windows平台
			
			//String.equalsIgnoreCase("字符串")：这个方法是不区分大小写来比较两个字符串是否相等
			if(browserName.equalsIgnoreCase("ie")){//判断是那个浏览器，如果是IE浏览器
				//设置IE浏览器的driver路径
				System.setProperty("webdriver.ie.driver", iedriver);
				
				/**
				 * 当采用 InterenetExplorerDriver时，可能会遇到一个安全问题提示："Protected Mode must be set to the 
				 * same value (enabled or disabled) for all zones"。想要解决这一问题，需要设置特定的功能
				 * 为 Internet Explorer 设置安全性功能：ieCapabilities.setCapability
				 * (InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				 * */
				DesiredCapabilities ieCapabilities=DesiredCapabilities.internetExplorer();
				//ie常规设置，方便执行自动化测试
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				WebDriver driver=new InternetExplorerDriver(ieCapabilities);
				//返回IE浏览器对象
				return driver;	
				
			}else if(browserName.equalsIgnoreCase("chrome")){//如果是chrome浏览器
				//设置chromedriver的路径
				System.setProperty("webdriver.chrome.driver", chromedriver_win);
				log.info("chromedriver_win="+chromedriver_win);
				//返回chrome浏览器对象
				WebDriver dr=new ChromeDriver();
				return dr;
				
			}else if(browserName.equalsIgnoreCase("firefox")){//如果是Firefox浏览器
				//返回Firefox浏览器对象
				System.setProperty("webdriver.gecko.driver",firefoxdriver_win);
				WebDriver driver=new FirefoxDriver();
				return driver;
				
				/**
				 * 其实selenium也是支持无界面浏览器操作的。比如说HtmlUnit和PhantomJs。他们都不是真正的浏览器，
				 * 运行时不会渲染页面显示内容，但是支持页面元素查找，js的执行等；由于不进行css和gui渲染，运行效率要比真实的浏览器要快很多。
				 * 要使用PhantomJS需要在maven下添加引入jar包代码如下：
				 * <dependency>
				 * <groupId>com.codeborne</groupId>
				 * <artifactId>phantomjsdriver</artifactId>
				 * <version>1.2.1</version>
				 * </dependency>
				 * */
			}else if(browserName.equalsIgnoreCase("ghost")){//如果是ghost浏览器
				DesiredCapabilities ghostCapability=new DesiredCapabilities();
				ghostCapability.setJavascriptEnabled(true);
				ghostCapability.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,ghostdriver);
				//返回ghost对象
				return new PhantomJSDriver(ghostCapability);
				/*System.setProperty("phantomjs.binary.path",phantomjsdriver_win);
				WebDriver driver = new PhantomJSDriver();
				return driver;*/
				
			}else{//如果以上4中浏览器都不是
				
				log.error("浏览器：["+browserName+"]不适用用于["+currentPlateform+"]操作系统");
				Assert.fail("浏览器："+browserName+"不适用用于"+currentPlateform+"操作系统");
			}
			
		}else if(currentPlateform.toLowerCase().contains("linux")){//如果是Linux平台
			
			if(browserName.equalsIgnoreCase("chrome")){//如果是chrome浏览器
				//设置chromedriver的路径
				System.setProperty("webdriver.chrome.driver", chromedriver_linux);
				return new ChromeDriver();
				
			}else if(browserName.equalsIgnoreCase("firefox")){//如果是Firefox浏览器
				System.setProperty("webdriver.gecko.driver",firefoxdriver_linux);
				WebDriver driver=new FirefoxDriver();
				return driver;
				
			}else{//如果以上两种浏览器都不是
				log.error("["+browserName+"]浏览器不适用["+currentPlateform+"]操作系统");
				Assert.fail("["+browserName+"]浏览器不适用["+currentPlateform+"]操作系统");
			}
			
		}/*else if(currentPlateform.toLowerCase().contains("mac")){//如果是mac平台
			if(browserName.equalsIgnoreCase("chrome")){//如果是chrome浏览器
				System.setProperty("webdriver.chrome.driver", chromedriver_mac);
				return new ChromeDriver();
				
			}else if(browserName.equalsIgnoreCase("firefox")){//如果是Firefox浏览器
				return new FirefoxDriver();
				
			}else{//如果以上两种浏览器都不是
				log.error("["+browserName+"]浏览器不适用["+currentPlateform+"]操作系统");
				Assert.fail("["+browserName+"]浏览器不适用["+currentPlateform+"]操作系统");
			}
			
			
		}*/else//以上三种平台都不是
			
			log.error("操作系统["+currentPlateform+"]不支持自动化测试，请更换操作系统（Windows或Linux或Mac）");
			Assert.fail("操作系统["+currentPlateform+"]不支持自动化测试，请更换操作系统（Windows或Linux或Mac）");
			return null;
		
		
	}

	
	
}
