package com.lsh.testng;


import com.lsh.pages.BaiduIndex;
import com.lsh.utils.ExcelDataProvider;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: lish
 * @Description:
 * @Date:2019/8/5
 */

@Log4j
public class WebAutoTestingDemo1 extends BaseCase{

    @Test(dataProvider = "testData",description = "第一个用例")
    public void test1(Map<String,String> data){
        su.input(BaiduIndex.searchBox,data.get("keys"));
        su.waitForPageLoading(waitPageloadTime);
        su.click(BaiduIndex.searchButton);
        su.waitForElementToLoad(waitElementloadTime,BaiduIndex.row);
        String actual = su.getText(BaiduIndex.row);
        su.isTextCorrect(actual,data.get("expected"));

    }

    @DataProvider(name="testData")
    public Iterator<Object[]> dataFortestMethod() throws IOException {
        return new ExcelDataProvider("baiduIndex", "001");
    }


}
