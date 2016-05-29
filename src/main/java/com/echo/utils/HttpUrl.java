package com.echo.utils;

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
    // 注册地址
    public static String registUrl = baseUrl+"/usermanagement.req?action=register";
    // 登陆地址
    public static String loginUrl = baseUrl+"/usermanagement.req?action=login";
    // 登出地址
    public static String logoutUrl = baseUrl+"/usermanagement.req?action=logout";
    // 上传图像地址
    public static String uploadUrl = baseUrl+"/imagemanagement.req?action=upload";

}
