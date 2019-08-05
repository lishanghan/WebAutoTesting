package com.lsh.testng;


import com.lsh.pages.BaiduIndex;
import com.lsh.utils.SeleniumUtils;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.annotations.Test;

/**
 * @Author: lish
 * @Description:
 * @Date:2019/8/5
 */

@Log4j
public class WebAutoTestingDemo1 extends BaseCase{

    @Test
    public void test1(){
        su.input(BaiduIndex.searchBox,"自动化测试教程");
        su.waitForPageLoading(waitPageloadTime);
        su.click(BaiduIndex.searchButton);
        su.waitForElementToLoad(waitPageloadTime,By.className("AMUHan"));
        String actual = su.getText(By.className("AMUHan"));
        log.info("找到的文字为：["+actual+"]");
        su.isTextCorrect(actual,"自动化测试教程_初学者必看秘笈");





    }


}
