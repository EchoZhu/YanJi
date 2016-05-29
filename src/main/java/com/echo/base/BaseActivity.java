package com.echo.base;

import android.app.Activity;
import android.view.View;

/**
 * Created by zhuyikun on 15/10/30.
 */
public class BaseActivity extends Activity implements View.OnClickListener {
    public void setListener(View...views){
        for (int i = 0; i < views.length; i++) {
            views[i].setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {

    }
}
