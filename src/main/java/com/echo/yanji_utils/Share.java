package com.echo.yanji_utils;

import android.app.Activity;

import com.echo.yanji.R;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.SmsHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.WeiXinShareContent;


/**
 * 调用步骤：
 * 1.调用invite函数，传入Activity，String ShareContent,String ShareTitle,String ShareUrl
 * @author zhuyikun
 *
 */
public class Share {
	private static UMSocialService mController;
	public Share(Activity mContext, String ShareContent, String ShareTitle, String ShareUrl) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				// 首先在您的Activity中添加如下成员变量
				mController = UMServiceFactory.getUMSocialService("com.umeng.share");

				//设置新浪SSO handler
				mController.getConfig().setSsoHandler(new SinaSsoHandler());
				// 设置分享内容
				mController.setShareContent(ShareContent);
				// 设置分享图片, 参数2为图片的url地址
				mController.setShareMedia(new UMImage(mContext, 
				                                     R.mipmap.ic_launcher));
				// 添加短信
				SmsHandler smsHandler = new SmsHandler();
				smsHandler.addToSocialSDK();
				

				// 添加微信平台
				String appId = "wx265008cc352f2d3c";
				String appSecret = "990e51a92e97e668da68513b4542dd59";
				UMWXHandler wxHandler = new UMWXHandler(mContext,appId,appSecret);
				wxHandler.addToSocialSDK();
				//设置微信好友分享内容
				WeiXinShareContent weixinContent = new WeiXinShareContent();
				//设置分享文字
				weixinContent.setShareContent(ShareContent);
				//设置title
				weixinContent.setTitle(ShareTitle);
				//设置分享内容跳转URL
				weixinContent.setTargetUrl(ShareUrl);
				//设置分享图片
				weixinContent.setShareImage(new UMImage(mContext, 
		                R.mipmap.ic_launcher));
				mController.setShareMedia(weixinContent);
				
				// 添加qq
				//参数1为当前Activity， 参数2为开发者在QQ互联申请的APP ID，参数3为开发者在QQ互联申请的APP kEY.
				UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(mContext, "100424468","c7394704798a158208a74ab60104f0ba");
				qqSsoHandler.addToSocialSDK(); 
				
				QQShareContent qqShareContent = new QQShareContent();
				//设置分享文字
				qqShareContent.setShareContent(ShareContent);
				//设置分享title
				qqShareContent.setTitle(ShareTitle);
				//设置分享图片
				qqShareContent.setShareImage(new UMImage(mContext, R.mipmap.ic_launcher));
				//设置点击分享内容的跳转链接
				qqShareContent.setTargetUrl(ShareUrl);
				mController.setShareMedia(qqShareContent);
				
				mController.getConfig().removePlatform( SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QZONE,
						SHARE_MEDIA.TENCENT);
				mController.getConfig().setPlatformOrder(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.QQ, SHARE_MEDIA.SINA,SHARE_MEDIA.SMS);
				mController.openShare(mContext, false);
	}

}
