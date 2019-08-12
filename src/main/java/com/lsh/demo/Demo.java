package com.lsh.demo;


import com.lsh.utils.LogConfiguration;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

@Log4j
public class Demo {

    public static void main(String args[]){
        LogConfiguration.initLog();

        //设置浏览器驱动路径
        System.setProperty("webdriver.chrome.driver","src/main/resources/dirver/chrome/win/chromedriver.exe");
        //打开chrome浏览器
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //进入百度
        driver.get("http://www.baidu.com");
        //设置等待页面加载时间
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        //搜索框输入关键字
        driver.findElement(By.id("kw")).sendKeys("自动化测试教程");
        //点击“百度一下”按钮进行搜索
        driver.findElement(By.id("su")).click();
        //等待搜索结果加载
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //获得实际结果 //*[@id="1"]/h3/a/em
        String actual = driver.findElement(By.xpath("//*[@id=\"1\"]/h3/a/em")).getText().trim();
        //判断实际结果与预期结果是否相等
        if(actual.equals("测试教程")){
            log.info("测试通过");
        }else{
            log.info("测试失败");
        }
        //关闭
        driver.quit();


        /*ITestContext context=null;
        SeleniumUtils seleniumUtils = new SeleniumUtils();
        seleniumUtils.openBrowser("chrome",context,"http://www.baidu.com",20);
        seleniumUtils.waitForPageLoading(20);
        seleniumUtils.findElelementBy(By.id("kw")).sendKeys("selenium3教程");
        seleniumUtils.findElelementBy(By.id("su")).click();
        //seleniumUtils.quit();*/







    }




}
