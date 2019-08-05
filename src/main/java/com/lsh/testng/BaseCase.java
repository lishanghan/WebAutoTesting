package com.lsh.testng;

import com.lsh.Po.TestCase;
import com.lsh.utils.LogConfiguration;
import com.lsh.utils.SeleniumUtils;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * @Author: lish
 * @Description:
 * @Date:2019/8/5
 */
@Log4j
public class BaseCase {


    public SeleniumUtils su;
    public String url;
    public int loadTime;
    public int waitPageloadTime;
    public int sleeptime;
    public ITestContext context;

    @BeforeClass
    public void sartTest(ITestContext context){
        LogConfiguration.initLog();
        this.context=context;
        //打开浏览器
        su = new SeleniumUtils();
        String browserName=context.getCurrentXmlTest().getParameter("browserName");
        url = context.getCurrentXmlTest().getParameter("testURL");
        loadTime = Integer.valueOf(context.getCurrentXmlTest().getParameter("loadTime"));
        waitPageloadTime = Integer.valueOf(context.getCurrentXmlTest().getParameter("waitPageloadTime"));
        sleeptime = Integer.valueOf(context.getCurrentXmlTest().getParameter("sleepTime"));
        su.openBrowser(browserName,context,url,loadTime);



    }

    @AfterClass
    /**结束测试并关闭浏览器*/
    public void endTest() {
        if(su.dr!=null){
            //退出浏览器
            su.quit();
        }else{
            log.error("浏览器driver没有获得对象，退出失败！");
            Assert.fail("浏览器driver没有获得对象，退出失败！");
        }

    }


}
