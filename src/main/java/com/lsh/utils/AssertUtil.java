package com.lsh.utils;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

/**
 * @Author: lish
 * @Description:
 * @Date:2019/8/20
 */
@Log4j
public class AssertUtil extends Assert {

    /**
     * @description:实际结果是否包含预期结果
     * @param:expected 预期结果
     * @param:actual 实际结果
     * */
    public static void assertContain(String expected,String actual){

        if (!StringUtils.containsIgnoreCase(actual, expected)) {
            throw new AssertionError("断言失败:实际值中不包含期望值(忽略大小写)");
        }


    }


}
