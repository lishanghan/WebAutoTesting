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

    private String testUrl;

    private String browserName;

    private Map<String,Object> action;

    private Map<String,Object> data;

    private  String expect;

}
