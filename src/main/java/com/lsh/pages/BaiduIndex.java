package com.lsh.pages;

import lombok.Data;
import org.openqa.selenium.By;

/**
 * @Author: lish
 * @Description:
 * @Date:2019/8/5
 */
@Data
public class BaiduIndex {

    //搜索框
    public static final By searchBox = By.id("kw");
    //百度一下按钮
    public static final By searchButton = By.id("su");
    //搜索结果文字
    public static final By row = By.xpath("//*[@id=\"1\"]/h3/a/em");



}
