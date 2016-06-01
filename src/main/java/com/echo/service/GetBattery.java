package com.echo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhuyikun on 5/29/16.
 */
public class GetBattery extends Service {
    private Timer timer;
    private TimerTask timerTask;
    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);


    }
}
