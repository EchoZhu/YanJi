package com.echo.utils;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.avos.avoscloud.AVUser;
import com.echo.activity.ForwardHelper;
import com.echo.user.LoginUtils;

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
     *
     * @param activity
     * @param mRequestQueue
     * @param userName
     * @param password
     */
    public static void toRegist(final Activity activity, final RequestQueue mRequestQueue, String userName, String password) {
//        Log.e("url", HttpUrl.regist());
        Map<String, String> params = new HashMap<>();
        params.put("userName", userName);
        params.put("password", password);
        final String mRequestBody = appendParameter(HttpUrl.registUrl, params);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, HttpUrl.registUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("test", response.toString());
                try {
                    String success = response.getString("success");
                    if (success.equals("true")) {
                        ToastUtils.show(activity, "验证成功");
                        //验证码验证正确之后跳转到注册的第二页
                        ForwardHelper.toRegist2(activity);
                    } else {
                        String errMsg = response.getString("errMsg");
                        ToastUtils.show(activity, errMsg);
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
        }) {
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
     *
     * @param activity
     * @param mRequestQueue
     * @param phone
     * @param pwd
     */
    public static void toLogin(final Activity activity, final RequestQueue mRequestQueue, final String phone, String pwd) {
//        Log.e("url", HttpUrl.regist());
        Map<String, String> params = new HashMap<>();
        params.put("userName", phone);
        params.put("password", pwd);
        final String mRequestBody = appendParameter(HttpUrl.loginUrl, params);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, HttpUrl.loginUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("test", response.toString());
                try {
                    String success = response.getString("success");
                    if (success.equals("true")) {
                        String authToken = response.getString("authToken");
                        LoginUtils.setAuthToken(activity, authToken);
                        LoginUtils.setUserName(activity, phone);
                        LoginUtils.isLogined(activity);
                        //登陆验证正确之后跳转到连接
                        ForwardHelper.toConect(activity);
                    } else {
                        String errMsg = response.getString("errMsg");
                        Log.e("登陆错误", errMsg);
                        ToastUtils.show(activity, "账号或密码错误，请重试");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtils.show(activity, "网络连接失败，请检查网络连接");
            }
        }) {
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
     *
     * @param activity
     * @param mRequestQueue
     * @param username
     * @param authToken
     */
    public static void toLogout(final Activity activity, final RequestQueue mRequestQueue, String username, String authToken) {
        Map<String, String> params = new HashMap<>();
        params.put("userName", username);
        params.put("authToken", authToken);
        final String mRequestBody = appendParameter(HttpUrl.logoutUrl, params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, HttpUrl.logoutUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String success = response.getString("success");
                    if (success.equals("true")) {
                        AVUser.logOut();
                        //登陆验证正确之后跳转到登陆页面
                        ToastUtils.show(activity, "您已成功退出");
                        ForwardHelper.toLogin(activity);
                    } else {
                        String errMsg = response.getString("errMsg");
                        Log.e("注销错误", errMsg);
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
        }) {
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



//    /**
//     * //     * 上传图像接口
//     * //     * @param activity
//     * //     * @param mRequestQueue
//     * //     * @param image
//     * //     * @param authToken
//     * //
//     */
//
//    public static void toUploadphoto(final Activity activity, final RequestQueue mRequestQueue, String image, String authToken) {
//        Map<String, String> params = new HashMap<>();
//        Log.e("image00", image);
//        Log.e("image", authToken);
//        try {
//            BitmapUtils.saveToSDCard("/yanji/test.txt", image);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        params.put("image", image);
//        params.put("authToken", authToken);
//        final String mRequestBody = appendParameter(HttpUrl.uploadUrl, params);
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, HttpUrl.uploadUrl, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.e("code", response + "");
//                try {
//                    String success = response.getString("success");
//                    if (success.equals("true")) {
////                        //成功
////                        JSONObject picDataObject =  response.getJSONObject("picData");
////                        JSONObject solutionDataObject =  response.getJSONObject("solutionData");
////                        JSONObject adviceDataObject =  response.getJSONObject("adviceData");
////                        String blood = picDataObject.getString("blood");
////                        String color = picDataObject.getString("color");
////                        String moisten = picDataObject.getString("moisten");
////                        String satin = picDataObject.getString("satin");
////                        String texture = picDataObject.getString("texture");
//
//                        String responseString = response.toString();
//
//                        Intent intent = new Intent(activity, A7_yanzhidetail.class);
//                        intent.putExtra("From", "A6_test");
//                        intent.putExtra("response", responseString);
//                        activity.startActivity(intent);
//                        activity.overridePendingTransition(R.anim.activity_open_in_anim,
//                                R.anim.activity_open_out_anim);
//                    } else {
//                        String errMsg = response.getString("errMsg");
//                        Log.e("errMsg", errMsg);
//                        Toast.makeText(activity, "上传失败，请检查网络连接", Toast.LENGTH_LONG).show();
////                        Intent intent1 = new Intent(activity, A7_yanzhidetail.class);
////                        intent1.putExtra("From", "A6_test");
////                        activity.startActivity(intent1);
////                        activity.overridePendingTransition(R.anim.activity_open_in_anim,
////                                R.anim.activity_open_out_anim);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(activity, "网络连接失败，请检查网络连接", Toast.LENGTH_LONG).show();
//            }
//        }) {
//            @Override
//            public String getBodyContentType() {
//                return "application/x-www-form-urlencoded; charset=" + getParamsEncoding()
//                        +"Accept-Encoding=gzip,deflate";
//            }
//
//            @Override
//            public byte[] getBody() {
//                return mRequestBody == null ? null : mRequestBody.getBytes();
//            }
//        };
//        mRequestQueue.add(request);
//        mRequestQueue.start();
//    }

    /**
     * //     * 上传图像接口
     * //     * @param activity
     * //     * @param mRequestQueue
     * //     * @param image
     * //     * @param authToken
     * //
     */

    public static void toUploadphoto(final Activity activity, final RequestQueue mRequestQueue, String image, String authToken) {

    }
    private static String appendParameter(String url, Map<String, String> params) {
        Uri uri = Uri.parse(url);
        Uri.Builder builder = uri.buildUpon();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        return builder.build().getQuery();
    }



}
