package com.lsh.testng.test;

import com.alibaba.fastjson.JSONObject;
import com.lsh.utils.ExcelDataProvider;
import com.lsh.utils.HttpClientUtils;
import com.lsh.utils.LogConfiguration;
import lombok.extern.log4j.Log4j;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: lish
 * @Description:
 * @Date:2019/8/16
 */
@Log4j
public class InterfaceAutoTestingDemo {

    @BeforeClass
    public void sartTest(ITestContext context){
        LogConfiguration.initLog();


    }

    @Test(dataProvider = "testData",description = "第一个接口自动化用例")
    public void test1(Map<String,String> data){
        String response = HttpClientUtils.doHttpJsonPost(data.get("testUrl"),data.get("parameter"));
        JSONObject json = JSONObject.parseObject(response);
        Integer code = json.getInteger("code");
        Reporter.log("预期结果："+ data.get("expected"));
        Reporter.log("实际结果："+ response);
        Assert.assertEquals(code,Integer.valueOf(data.get("expected")));

    }

    @DataProvider(name="testData")
    public Iterator<Object[]> dataFortestMethod() throws IOException {
        return new ExcelDataProvider("interface1", "001");
    }


}
