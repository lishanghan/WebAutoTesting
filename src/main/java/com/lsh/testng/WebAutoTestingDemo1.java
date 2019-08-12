package com.lsh.testng;


import com.lsh.pages.BaiduIndex;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * @Author: lish
 * @Description:
 * @Date:2019/8/5
 */

@Log4j
public class WebAutoTestingDemo1 extends BaseCase{

    @Test(description = "第一个用例")
    public void test1(){
        su.input(BaiduIndex.searchBox,"自动化测试教程");
        su.waitForPageLoading(waitPageloadTime);
        su.click(BaiduIndex.searchButton);
        su.waitForElementToLoad(waitElementloadTime,By.xpath("//*[@id=\"1\"]/h3/a/em"));
        String actual = su.getText(By.xpath("//*[@id=\"1\"]/h3/a/em"));
        log.info("找到的文字为：["+actual+"]");
        su.isTextCorrect(actual,"测试教程");





    }


}
