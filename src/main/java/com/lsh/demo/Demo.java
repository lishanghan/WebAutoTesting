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

import java.io.FileNotFoundException;
import java.util.Properties;

@Log4j
public class Demo {

    public static void main(String args[]){

        /*System.setProperty("webdriver.chrome.driver","src/main/resources/dirver/firefox/win/geckodriver.exe");
        WebDriver webDriver = new ChromeDriver();*/

        /*System.setProperty("phantomjs.binary.path","src/main/resources/dirver/phantomjs/win/phantomjs.exe");
        WebDriver driver = new PhantomJSDriver();
        driver.get("http://www.baidu.com");
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();*/
        /*YmlConfig yml = YamlUtil.getYml("src/main/resources/driverPath.yml");
        System.out.println(yml.getFirefoxDriver_win());*/
        LogConfiguration.initLog();

        SelectBrowser selectBrowser = new SelectBrowser();
        //设置打开那个浏览器
        WebDriver driver = selectBrowser.selectBrowserByName("chrome");
        //打开百度
        driver.get("http://www.baidu.com");
        driver.findElement(By.id("kw")).sendKeys("selenium3教程");
        driver.findElement(By.id("su")).click();
        driver.quit();




    }




}
