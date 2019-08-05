package com.lsh.utils;

import java.util.concurrent.TimeUnit;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;


@Log4j
public class SeleniumUtils {

    public static void main(String [] args){


    }

    public WebDriver dr;
    public WebDriver window;


    /**在指定的时间去查找元素，如果没找到则超时，抛出异常*/
    public void waitForElementToLoad(int timeout,final By by){

        log.info("开始查找元素["+by+"]");
        try{
            (new WebDriverWait(dr, timeout)).until(new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver driver){
                    WebElement element=driver.findElement(by);
                    return element.isDisplayed();
                }
            });


        }catch(TimeoutException e){
            log.error("超时"+timeout+"秒之后还没找到元素["+by+"]");
            Assert.fail("超时"+timeout+"秒之后还没找到元素["+by+"]");
        }
        log.info("找到元素["+by+"]");

    }


    /**包装查找元素的方法*/
    public WebElement findElelementBy(By by){
        return dr.findElement(by);
    }

    /**包装清除输入框的操作*/
    public void clear(By by){
        try {
            findElelementBy(by).clear();
        } catch (Exception e) {

            log.error("清除元素["+by+"]上的内容失败");
        }
        log.info("清除元素["+by+"]的内容");
    }


    /**封装向输入框输入内容的方法*/
    public void input(By by,String text){
        try {
            findElelementBy(by).sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();

            log.error("输入["+text+"]到元素["+by+"]");
            Assert.fail("输入["+text+"]到元素["+by+"]");
        }
        log.info("输入["+text+"]到["+by+"]");

    }
    /**封装表单提交的方法*/
    public void submit(By by){
        findElelementBy(by).submit();
        log.info("点击了元素：["+by+"]");
    }

    /**封装点击操作的方法*/
    public void click(By by){

        try {
            //调用不能点击重试点击方法retryClick()
            retryClick(by, System.currentTimeMillis(), 2000);


            //StaleElementReferenceException:元素过期异常
        } catch (StaleElementReferenceException e) {
            log.error("您点击的元素："+by+"不存在");
            Assert.fail("您点击的"+by+"不存在",e);


        } catch(Exception e){
            log.error("点击元素["+by+"]失败");
            Assert.fail("点击元素["+by+"]失败");

        }
        log.info("点击了元素["+by+"]");

    }

    /**不能点击时重试点击操作*/
    public void retryClick(By by,long startTime,int timeOut) throws Exception{
        //System.currentTimeMillis():获得当前系统时间的毫秒数
        System.out.println(new java.util.Date(System.currentTimeMillis()));

        try {
            findElelementBy(by).click();
        } catch (Exception e) {
            if(System.currentTimeMillis()-startTime>timeOut){
                log.warn(by+"不可点击");
                throw new Exception(e);
            }else{
                Thread.sleep(500);
                log.warn(by+"不可点击，重试");
                retryClick(by, startTime, timeOut);
            }

        }


    }

    /**检查元素是否存在的方法*/
    public boolean isElementExist(By by){

        try {
            findElelementBy(by);
            log.info("找到元素["+by+"]了");
            return true;

        } catch (NoSuchElementException e) {

            log.error("元素["+by+"]不存在");
            Assert.fail("元素["+by+"]不存在");
            return false;
        }

    }

    /**最大化浏览器操作封装*/
    public void maxBrowser(String browserName){
        log.info("最大化"+browserName+"浏览器");
        dr.manage().window().maximize();
    }

    /**启动浏览器并打开页面*/
   public void openBrowser(String browserName,ITestContext context,String testURL,int timeOut){

        SelectBrowser selectBrowser=new SelectBrowser();
        this.dr=selectBrowser.selectBrowserByName(browserName);
        try {

            maxBrowser(browserName);
            waitForPageLoading(timeOut);
            get(testURL);
        } catch (TimeoutException e) {
            log.warn("注意！页面没有完全加载出来，请刷新重试！");
            refresh();
            //用js打印出当前页面加载状态
            JavascriptExecutor js=(JavascriptExecutor)dr;
            String status=(String)js.executeScript("return document.readyState");
            log.info("打印状态："+status);

        }


    }

    /**包装页面刷新的方法*/
    public void refresh(){
        dr.navigate().refresh();
        log.info("页面刷新成功");
    }


    /**输入URL的get方法包装*/
    public void get(String testURL){
        log.info("打开测试页面："+testURL);
        dr.get(testURL);
    }

    /**pageLoadTiemOut:页面加载的超时时间，因为webdriver会等待页面加载完成后再进行后面的操作
     * 所以如果页面在这个超时时间内没有加载完成，那么webdriver就会抛出异常
     * */
    public void waitForPageLoading(int pageLoadTimeout){
        dr.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);

    }

    /**封装退出浏览器的操作*/
    public void quit(){
        dr.quit();
    }

    /**判断文本是否是需求的文本*/
    public void isTextCorrect(String text1,String text2){

        try {
            Assert.assertEquals(text1, text2);
        } catch (AssertionError e) {
            log.error("期望的文字是：["+text2+"]但找到的文字是：["+text1+"]");
            Assert.fail("期望的文字是：["+text2+"]但找到的文字是：["+text1+"]");
        }
        log.info("测试通过，找到了期待的文字["+text2+"]");

    }

    //判断是否博包含
    public void isContainText(String text1,String text2){


    }

    /**获得元素的文本*/
    public String getText(By by){
        return dr.findElement(by).getText().trim();
    }

    /**选择下拉列表框操作封装*/
    //根据index角标选择
    public void selectByIndex(By by,int index){
        Select select=new Select(dr.findElement(by));
        select.selectByIndex(index);
        log.info("选择了下拉列表index为：["+index+"]的选项");
    }
    //根据value值选择
    public void selectByValue(By by,String value){
        new Select(dr.findElement(by)).selectByValue(value);
        log.info("选择了下拉列表value为["+value+"]的选项");

    }
    //根据选项的文本内容选择
    public void selectByText(By by,String text){
        new Select(dr.findElement(by)).selectByVisibleText(text);
        log.info("选择了下拉列表文本内容为["+text+"]的选项");
    }













}

