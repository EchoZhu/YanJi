/**
 * @(#)ToastUtils.java, 2014-08-01
 *
 * Copyright 2014 Youdao, Inc. All rights reserved.
 * YOUDAO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.echo.utils;

import android.content.Context;
import android.widget.Toast;

import com.echo.activity.XiaoYouApplication;


public class ToastUtils {
	public static final String OP_ERROR ="操作失败";
	public static final String OP_ERROR_NETWORK_OUT ="网络连接失败";
	
	public static final String MOBILE_FORMAT ="手机号输入错误";
	public static final String MOBILE_NULL ="手机号码不能为空！";
	public static final String USERNAME_NULL ="姓名不能为空！";
	
	
	public static final String REG_MOBILE_FORMAT = "手机号输入错误";
	public static final String REG_MOBILE_NULL = "注册手机号码不能为空！";
	public static final String REG_PASSWD_SAME = "密码重复输入不一致";
	public static final String REG_NICKNAME_NULL = "昵称不能为空！";
	public static final String REG_VALID_NULL = "验证码不能为空！";
	public static final String REG_VALID_ILLEGAL = "验证码应为6位纯数字！";
	public static final String REG_PASSWD_NULL = "密码不能为空！";
	public static final String REG_PASSWD_AGREMENT = "请同意注册协议";
	
	public static final String LOGIN_PASSWD_NULL = "密码不能为空！";
	public static final String LOGIN_MOBILE_NULL = "手机号码不能为空！";
	
	public static final String REPASSWD_MOBILE_NULL = "手机号码不能为空！";
	public static final String REPASSWD_MOBILE_FORMAT = "手机号输入错误";
	public static final String REPASSWD_VALID_NULL = "验证码不能为空！";
	public static final String REPASSWD_PASSWD_NULL = "密码不能为空！";
	public static final String REPASSWD_PASSWD_SAME = "密码重复输入不一致";
	
	public static final String ADDADDR_PROVINCE_NULL = "省份不能为空!";
	public static final String ADDADDR_CITY_NULL = "城市不能为空!";
	public static final String ADDADDR_DISTRICT_NULL = "地区不能为空!";
	public static final String ADDADDR_STREET_NULL = "街道地址不能为空!";
	public static final String ADDADDR_ZIPCODE_NULL = "邮编不能为空!";
	
	public static final String NICKNAME_NAME_NULL="昵称不能为空！";
	public static final String GENDER_NULL="性别不能为空！";
	public static final String AGE_NULL="性别不能为空！";

	public static final String NEWROOM_INTRO_NULL="个人简介不能为空！";
	public static final String NEWROOM_LIVEINTRO_NULL="直播简介不能为空！";
	public static final String NEWROOM_BACKDATE_NULL="回国日期不能为空！";
	public static final String NEWROOM_BACKCITY_NULL="回国城市不能为空！";
	public static final String NEWROOM_DESTCITY_NULL="目标城市不能为空！";
	public static final String NEWROOM_STARTDATE_NULL="直播开始日期不能为空！";
	public static final String NEWROOM_ENDDATE_NULL="直播结束日期不能为空！";
	
	public static final String NEWPRODUCT_DESC_NULL="附加信息不能为空！";
	public static final String NEWPRODUCT_PRICE_ERROR="单品价格不正确！";
	public static final String NEWPRODUCT_NUM_ERROR="产品数量不正确！";
	public static final String NEWPRODUCT_AGENTPRICE_ERROR="单品服务费不正确！";
	public static final String NEWPRODUCT_NAME_NULL="单品名称不能为空！";
	
	public static final String CONFRIMORDER_NUM_NULL="请输入购买数量！";
	 

	public static final String INPUT_EMPTY = "未输入任何内容！";
	public static final String INPUT_EMPTY_CHARACTER = "输入不能全为空！";
	public static final String INPUT_EMPTY_QUERY = "请输入搜索关键词";
	public static final String FAIL_ON_SEND_COMMENT = "评论失败！";
	public static final String FAIL_ON_DETAIL_DATA = "获取数据失败！";
	public static final String PRESS_BACK_AGAIN_QUIT = "再按一次退出";
	public static final String URL_NOT_CORRECT = "url地址不正确！\nurl: ";
	public static final String NETWORK_ERROR_163 = "163登录请求失败！";
	public static final String LOG_IN_ERROR_163 = "登录失败！\n请检查是否正确输入账号密码！";
	public static final String SET_PUSH_FAILED = "设置推送失败！";
	public static final String INPUT_FEEDBACK = "请输入反馈意见";
	public static final String SUBMIT_SUCCEED = "您的调查已提交，谢谢";
	public static final String POST_REPLY_SUCCEED = "提交评论成功！";
	public static final String DELETE_COMMENT_SUCCEED = "删除评论成功！";
	public static final String DELETE_COMMENT_FAILED = "删除评论失败！";
	public static final String CHOOSE_CATEGORY = "选择分类：";
	public static final String QUERY_NOT_CORRECT = "未输入查询内容！";
	public static final String SUBSCRIBE_SUCCEED = "收藏成功！";
	public static final String SUBSCRIBE_FAILED = "收藏失败！";
	public static final String UNSUBSCRIBE_SUCCEED = "取消收藏成功！";
	public static final String UNSUBSCRIBE_FAILED = "取消收藏失败！";
	public static final String GET_SINA_WEIBO_ACCESS_TOKEN_FAILED = "获取新浪微博access_token失败！";
	public static final String GET_QQ_ACCESS_TOKEN_FAILED = "获取QQ access_token失败！";
	public static final String SHARE_TO_WEIBO_OUT_OF_TEXT_LIMIT = "字数超出微博分享限制！";
	public static final String NO_MARKET_APP_INSTALLED = "未安装任何应用市场！";
	public static final String VERSION_NOT_SUPPORT_QQ_LOGIN = "当前系统版本不支持网页方式登录！";
	public static final String KEYWORD_SHOULD_NOT_CONTAIN_SPACE = "关键词不能包含空格！";
	public static final String DUPLICATE_KEYWORD = "不能添加相同的关键词！";
	public static final String KEYWORD_COUNT_REACH_LIMIT = "关键词数量达到上限，不能再添加！";
	public static final String KEYWORD_LENGTH_REACH_LIMIT = "您输入的关键词过长！";

	public static final String DEVICE_HAS_NO_CAMERA = "此设备没有相机！";
	public static final String SET_SHOOT_IMAGE_PATH_FAILED = "初始化拍照文件失败！";
	public static final String SET_CROP_IMAGE_PATH_FAILED = "初始化剪裁文件失败！";
	public static final String CHOOSE_IMAGE_PATH_FAILED = "初始化选择文件失败！";
	public static final String NO_SHOOT_APP_FOUND = "未找到拍照应用！";
	public static final String NO_CROP_IMAGE_APP_FOUND = "未找到图像剪裁应用！";
	public static final String CREATE_SHOOT_IMAGE_FAILED = "创建拍摄图像失败！";
	public static final String CREATE_CROP_IMAGE_FAILED = "创建剪裁图像失败！";
	public static final String CANCEL_MODIFY_AVATAR = "取消修改头像！";
	public static final String NO_GALLERY_APP_FOUND = "未找到图像库应用！";

	public static final String MODIFY_USER_ICON_SUCCEED = "修改头像成功！";
	public static final String UPLOAD_USER_ICON_FAILED = "上传头像失败！";

	public static final String MODIFY_NICKNAME_SUCCEED = "修改昵称成功！";
	public static final String MODIFY_NICKNAME_FAILED = "网络故障，修改昵称失败！";
	public static final String MODIFY_NICKNAME_FAILED_REPEAT = "昵称重复，换个不一样的昵称吧～";
	public static final String MODIFY_NICKNAME_FAILED_FORMAT_ERROR = "昵称格式错误，请输入4～16个字符\n输入仅支持中英文、数字和下划线";

	private static Toast toast = null;

	public static void show(String msg) {
		showText(XiaoYouApplication.getInstance(), msg, Toast.LENGTH_SHORT);
	}

	public static void show(Integer stringId) {
		showText(XiaoYouApplication.getInstance(), stringId, Toast.LENGTH_SHORT);
	}

	public static void show(Context context, String msg) {
		showText(context, msg, Toast.LENGTH_SHORT);
	}

	public static void show(Context context, Integer stringId) {
		showText(context, stringId, Toast.LENGTH_SHORT);
	}

	private static void showText(Context context, String msg, int length) {
		if (toast == null) {
			toast = Toast.makeText(context, msg, length);
		} else {
			toast.setText(msg);
		}
		toast.show();
	}

	private static void showText(Context context, Integer stringId, int length) {
		if (toast == null) {
			toast = Toast.makeText(context, stringId, length);
		} else {
			toast.setText(stringId);
		}
		toast.show();
	} 
}