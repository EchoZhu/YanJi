package com.echo.base;

import android.app.TabActivity;
import android.view.KeyEvent;

import com.echo.utils.ToastUtils;

/**
 * 项目名称：My Application
 * 类描述：
 * 创建人：zhuyikun
 * 创建时间：15/11/4 下午1:08
 * 修改人：zhuyikun
 * 修改时间：15/11/4 下午1:08
 * 修改备注：
 */
public class BaseTabActivity extends TabActivity{
    private long firstTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){
            long secondTime = System.currentTimeMillis();
            if(secondTime - firstTime > 2000){
                ToastUtils.show(this,"再按一次退出");
                firstTime = secondTime;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
