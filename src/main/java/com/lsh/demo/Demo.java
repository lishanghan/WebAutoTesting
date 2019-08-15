package com.lsh.demo;


import com.alibaba.fastjson.JSONObject;
import com.lsh.utils.HttpClientUtils;
import com.lsh.utils.LogConfiguration;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

@Log4j
public class Demo {

    public static void main(String args[]){
        LogConfiguration.initLog();

        interfaceDemo();


    }


    public static void webUiDemo(){

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
        /*WebElement element = driver.findElement(By.id("kw"));
        element.sendKeys("自动化测试教程");*/
        driver.findElement(By.id("kw")).sendKeys("自动化测试教程");
        //点击“百度一下”按钮进行搜索
        driver.findElement(By.id("su")).click();
        //等待搜索结果加载
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //获得实际结果
        String actual = driver.findElement(By.xpath("//*[@id=\"1\"]/h3/a/em")).getText().trim();
        //判断实际结果与预期结果是否相等
        if(actual.equals("测试教程")){
            log.info("测试通过");
        }else{
            log.info("测试失败");
        }
        //关闭
        driver.quit();

    }

    public static void interfaceDemo(){

        String url = "http://192.168.52.26:8011/main/customer/unencryptdetail";
        String param = "{\n" +
                "    \"user\":{\n" +
                "        \"userid\":\"ae5b49ab-3604-e911-814b-00505696cb23\",\n" +
                "        \"brokerCode\":\"18836\",\n" +
                "        \"username\":\"Liujie\",\n" +
                "        \"fullName\":\"刘杰\",\n" +
                "        \"deviceId\":\"1123123123132132\",\n" +
                "        \"images\":\"\"\n" +
                "    },\n" +
                "    \"dataId\":\"2faa47f3-b1b7-4224-93ef-f6484af89c73\",\n" +
                "    \"pageSize\":10,\n" +
                "    \"brokerCode\":\"18836\",\n" +
                "    \"userId\":\"ae5b49ab-3604-e911-814b-00505696cb23\",\n" +
                "    \"page\":1\n" +
                "}";

        String response = HttpClientUtils.doHttpJsonPost(url,param);
        JSONObject json = JSONObject.parseObject(response);
        Integer code = json.getInteger("code");
        if(code == 0){
            log.info("接口测试通过");
        }else{
            log.error("接口测试失败");
        }



    }




}
