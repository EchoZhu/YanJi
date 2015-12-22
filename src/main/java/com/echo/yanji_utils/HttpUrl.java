package com.echo.yanji_utils;

/**
 * 项目名称：My Application
 * 类描述：
 * 创建人：zhuyikun
 * 创建时间：15/11/12 下午2:52
 * 修改人：zhuyikun
 * 修改时间：15/11/12 下午2:52
 * 修改备注：
 */
public class HttpUrl {
    private static String baseUrl = "http://123.57.221.17:8080/unifyum";

    /**
     * 注册地址
     * @return
     */
    public static String regist(){
        return baseUrl+"/usermanagement.req?action=register";
    };

    /**
     * 登陆地址
     * @return
     */
    public static String login(){
        return baseUrl+"/usermanagement.req?action=login";
    };
    /**
     * 登出地址
     * @return
     */
    public static String logout(){
        return baseUrl+"/usermanagement.req?action=logout";
    };
}
