package com.echo.data;

import com.google.gson.annotations.Expose;

/**
 * 项目名称：My Application
 * 类描述：
 * 创建人：zhuyikun
 * 创建时间：15/11/3 上午11:05
 * 修改人：zhuyikun
 * 修改时间：15/11/3 上午11:05
 * 修改备注：
 */
public class YanJiUser {
    @Expose
    private Long userId = 1l;
    @Expose
    private String cellphone;
    @Expose
    private String nickName;

    private String passwd;

    // transient
    private String validCode;
    @Expose
    private String photoUrl;

    // 登录code
    private String loginCode;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return cellphone;
    }

    public void setMobile(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }
}
