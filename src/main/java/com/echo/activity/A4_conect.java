package com.echo.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.echo.base.BaseActivity;
import com.echo.service.HeartbeatService;
import com.echo.utils.SocketUtil;
import com.echo.yanji.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.net.Socket;


/**
 * 连接设备页面
 *
 * @author zhuyikun
 *         <p/>
 *         2015-10-27
 */
public class A4_conect extends BaseActivity {

    private ImageView iv_headtag;
    private Button btn_conect, btn_setwifi, btn_start_service;
    private ProgressDialog pd;
    private Socket socket;
    private DataInputStream dataInputStream;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a4_conect);
        initUI();
        mRequestQueue = Volley.newRequestQueue(A4_conect.this);
//        if (SocketUtil.socket == null) {
//            initService();//初始化服务，发送心跳包
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//		if (!SocketUtil.socket.isConnected()){
//			initService();//初始化服务，发送心跳包
//		}

    }

    private void initService() {
        Intent startIntent = new Intent(this, HeartbeatService.class);
        startService(startIntent);
    }

    /**
     * 检测心跳包的服务是否正在运行
     *
     * @return
     */
    private boolean checkHeartBeatService() {
        boolean isRunning = false;
//		ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//		List<ActivityManager.RunningServiceInfo> serviceInfoList = manager.getRunningServices(30);
//		for (int i = 0; i < serviceInfoList.size();i++){
//			if (serviceInfoList.get(i).service.getClassName().equals("com.echo.service.HeartbeatService")){
//				isRunning = true;
//			}
//			Log.e("serviceList",serviceInfoList.get(i).service.getClassName());
//
//		}
        return isRunning;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_headtag:
//			ForwardHelper.toMyinfo(this);
                ForwardHelper.toTable(this);
                break;

            case R.id.btn_setwifi:
                Intent intent1 = new Intent(Settings.ACTION_WIFI_SETTINGS);
                startActivity(intent1);
                break;

            case R.id.btn_start_service:
                if (SocketUtil.socket == null) {
                    initService();//初始化服务，发送心跳包
                }
                break;
            case R.id.btn_conect:

                if (SocketUtil.socket != null) {
                    ForwardHelper.toTable(this);
                } else {
                    Toast.makeText(this, "未连接硬件，请连接硬件后重试", Toast.LENGTH_LONG).show();
                }


//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					//测试图片上传
//					Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.testupload2);
////					Bitmap compressBitmap = compressImage(bitmap);
//					String image = BitmapUtils.bitmapToBase64(bitmap);
//
//					Log.e("image1", image.toString());
//					VolleyUtil.toUploadphoto(A4_conect.this, mRequestQueue, image.trim(), LoginUtils.getAuthToken(A4_conect.this));
//				}
//			}).start();

//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.testupload2);
//                        try {
//                            BitmapUtils.saveFile(bitmap,"test.jpg");
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();

                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initUI() {
        // TODO Auto-generated method stub
        iv_headtag = (ImageView) findViewById(R.id.iv_headtag);
        btn_conect = (Button) findViewById(R.id.btn_conect);
        btn_setwifi = (Button) findViewById(R.id.btn_setwifi);
        btn_start_service = (Button) findViewById(R.id.btn_start_service);
        pd = new ProgressDialog(this);
        pd.setTitle("正在连接硬件设备，请稍后");
//		pd.show();
        setListener(iv_headtag, btn_conect, btn_setwifi, btn_start_service);
    }

    private Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 20;
        while (baos.toByteArray().length / 1024 > 20) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }
}
