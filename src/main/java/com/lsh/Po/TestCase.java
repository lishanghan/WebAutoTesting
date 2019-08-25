package com.lsh.Po;

import lombok.Data;

import java.util.Map;

/**
 * @Author: lish
 * @Description:
 * @Date:2019/8/5
 */
@Data
public class TestCase {

    //用例编号
    private Integer caseNo;

    //接口地址
    private String testUrl;

    //接口请求方式
    private String method;

    //数据格式
    private String contentType;

    //请求参数
    private String parameter;

    //预期结果
    private String expected;


}
