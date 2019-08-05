package com.lsh.demo;

import com.lsh.Po.YmlConfig;
import com.lsh.utils.LogConfiguration;
import com.lsh.utils.SelectBrowser;
import com.lsh.utils.SeleniumUtils;
import com.lsh.utils.YamlUtil;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestContext;

import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Log4j
public class Demo {

    public static void main(String args[]){
        LogConfiguration.initLog();

        //设置浏览器驱动路径
        System.setProperty("webdriver.chrome.driver","src/main/resources/dirver/chrome/win/chromedriver.exe");
        //打开chrome浏览器
        WebDriver driver = new ChromeDriver();
        //进入百度
        driver.get("http://www.baidu.com");
        //设置等待页面加载时间
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        //搜索框输入关键字
        driver.findElement(By.id("kw")).sendKeys("自动化测试教程");
        //点击“百度一下”按钮进行搜索
        driver.findElement(By.id("su")).click();

        //关闭
        //driver.quit();


        /*ITestContext context=null;
        SeleniumUtils seleniumUtils = new SeleniumUtils();
        seleniumUtils.openBrowser("chrome",context,"http://www.baidu.com",20);
        seleniumUtils.waitForPageLoading(20);
        seleniumUtils.findElelementBy(By.id("kw")).sendKeys("selenium3教程");
        seleniumUtils.findElelementBy(By.id("su")).click();
        //seleniumUtils.quit();*/







    }




}
