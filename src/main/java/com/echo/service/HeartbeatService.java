package com.echo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.echo.utils.SocketUtil;
import com.echo.utils.ToastUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by zhuyikun on 12/22/15.
 */
public class HeartbeatService extends Service {
    public static final String TAG = "HeartbeatService";
    private Socket socket;
    private int cunt = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() executed");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(SocketUtil.IP, SocketUtil.PORT);
                    if(socket.isConnected()){
                        Log.e("conn","OK");
                        SocketUtil.socket = socket;
                        Thread.sleep(2000);//连接硬件后增加2秒钟延时，再发送心跳包
                    }else{
                        Log.e("conn","no");
                        ToastUtils.show(getApplicationContext(),"connect fail,please try again");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true) {

                    try {
                        if (socket != null && socket.isConnected()) {

                            String cuntString = null;
                            OutputStream outputStream = socket.getOutputStream();
                            if (cunt <= 65535) {
                                if (cunt < 16) {
                                    cuntString = "000" + Integer.toHexString(cunt);
                                } else if (cunt < 256) {
                                    cuntString = "00" + Integer.toHexString(cunt);
                                } else if (cunt < 4096) {
                                    cuntString = "0" + Integer.toHexString(cunt);
                                } else {
                                    cuntString = Integer.toHexString(cunt);
                                }
                                byte[] arrayOfByte = SocketUtil.HexUtils.getBytes("5AA5" + cuntString + "0101ffff0d0a");

                                Log.e("beat:",cuntString+"");
                                outputStream.write(arrayOfByte);
                                outputStream.flush();
                                cunt++;
                                Thread.sleep(5000);

                            } else {
                                cunt = 0;
                            }


                        } else {
//                            socket = new Socket(SocketUtil.IP, SocketUtil.PORT);
//                            SocketUtil.socket = socket;

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }).start();

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
