package com.echo.yanji_utils;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.avos.avoscloud.AVUser;
import com.echo.yanji.ForwardHelper;
import com.echo.yanji_user.LoginUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：My Application
 * 类描述：
 * 创建人：zhuyikun
 * 创建时间：15/11/14 上午11:43
 * 修改人：zhuyikun
 * 修改时间：15/11/14 上午11:43
 * 修改备注：
 */
public class VolleyUtil {
    /**
     * 注册接口
     * @param activity
     * @param mRequestQueue
     * @param userName
     * @param password
     */
    public static void toRegist(final Activity activity, final RequestQueue mRequestQueue,String userName,String password) {
//        Log.e("url", HttpUrl.regist());
        Map<String,String> params = new HashMap<>();
        params.put("userName",userName);
        params.put("password", password);
        final String mRequestBody = appendParameter(HttpUrl.regist(),params);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,HttpUrl.regist(),null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("test", response.toString());
                try {
                    String success = response.getString("success");
                    if (success.equals("true")){
                        ToastUtils.show(activity, "验证成功");
                        //验证码验证正确之后跳转到注册的第二页
                        ForwardHelper.toRegist2(activity);
                    }else{
                        String errMsg = response.getString("errMsg");
                        ToastUtils.show(activity,errMsg);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.show("网络连接失败，请检查网络连接");
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
            }

            @Override
            public byte[] getBody() {
                return mRequestBody == null ? null : mRequestBody.getBytes();
            }
        };
        mRequestQueue.add(request);
        mRequestQueue.start();
    }

    /**
     * 登陆接口
     * @param activity
     * @param mRequestQueue
     * @param phone
     * @param pwd
     */
    public static void toLogin(final Activity activity, final RequestQueue mRequestQueue, final String phone,String pwd) {
//        Log.e("url", HttpUrl.regist());
        Map<String,String> params = new HashMap<>();
        params.put("userName",phone);
        params.put("password",pwd);
        final String mRequestBody = appendParameter(HttpUrl.login(),params);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,HttpUrl.login(),null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("test", response.toString());
                try {
                    String success = response.getString("success");
                    if (success.equals("true")){
                        String authToken = response.getString("authToken");
                        LoginUtils.setAuthToken(activity, authToken);
                        LoginUtils.setUserName(activity, phone);
                        LoginUtils.isLogined(activity);
                        //登陆验证正确之后跳转到连接
                        ForwardHelper.toConect(activity);
                    }else{
                        String errMsg = response.getString("errMsg");
                        Log.e("登陆错误",errMsg);
                        ToastUtils.show(activity, "账号或密码错误，请重试");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.show("网络连接失败，请检查网络连接");
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
            }

            @Override
            public byte[] getBody() {
                return mRequestBody == null ? null : mRequestBody.getBytes();
            }
        };
        mRequestQueue.add(request);
        mRequestQueue.start();
    }

    /**
     * 登出接口
     * @param activity
     * @param mRequestQueue
     * @param username
     * @param authToken
     */
    public static void toLogout(final Activity activity, final RequestQueue mRequestQueue,String username,String authToken) {
        Map<String,String> params = new HashMap<>();
        params.put("userName", username);
        params.put("authToken",authToken);
        final String mRequestBody = appendParameter(HttpUrl.logout(),params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,HttpUrl.logout(),null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String success = response.getString("success");
                    if (success.equals("true")){
                        AVUser.logOut();
                        //登陆验证正确之后跳转到登陆页面
                        ToastUtils.show(activity,"您已成功退出");
                        ForwardHelper.toLogin(activity);
                    }else{
                        String errMsg = response.getString("errMsg");
                        Log.e("注销错误",errMsg);
                        ToastUtils.show(activity, "退出登陆失败，请重试");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.show("网络连接失败，请检查网络连接");
            }
        }){
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=" + getParamsEncoding();
            }

            @Override
            public byte[] getBody() {
                return mRequestBody == null ? null : mRequestBody.getBytes();
            }
        };
        mRequestQueue.add(request);
        mRequestQueue.start();
    }



    private static String appendParameter(String url, Map<String, String> params){
        Uri uri = Uri.parse(url);
        Uri.Builder builder = uri.buildUpon();
        for(Map.Entry<String,String> entry:params.entrySet()){
            builder.appendQueryParameter(entry.getKey(),entry.getValue());
        }
        return builder.build().getQuery();
    }


}
