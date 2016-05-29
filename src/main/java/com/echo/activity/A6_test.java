package com.echo.activity;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.echo.base.BaseActivity;
import com.echo.user.LoginUtils;
import com.echo.utils.BitmapUtils;
import com.echo.utils.HttpUrl;
import com.echo.utils.SocketUtil;
import com.echo.utils.ToastUtils;
import com.echo.view.RoundImageView;
import com.echo.yanji.R;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 检测图像页面
 *
 * @author zhuyikun
 *         <p/>
 *         2015-10-28
 */
public class A6_test extends BaseActivity {

    private ImageView iv_myinfo_test, iv_pic_a, iv_pic_b, iv_pic_c,
            iv_delete_a, iv_delete_b, iv_delete_c;
    private Button btn_takephoto, btn_getyanzhi;
    private TextView tv_tips;
    private long firstTime = 0;
    private RoundImageView iv_mirror;

    private boolean PHOTOISTAKED = false;
    private Handler mHandle;
    private ProgressDialog progressDialog;
    private Socket socket;
    private DataInputStream dataInputStream;
    protected byte[] buffer;
    protected byte[] img_buffer;

    protected int img_buff_cnt;
    boolean img_buffer_lock = false;
    Bitmap bmp;
    Bitmap bitmapToUpload;
    String TAG = "VideoSocket";
    private int pic_a_show = 0;
    private int pic_b_show = 0;
    private int pic_c_show = 0;
    private static final int UPDATEUI = 0x11;
    private RequestQueue mRequestQueue;
    AlertDialog dialog;
    private Thread getVideoThread;

    private boolean isChangedNetwork = false;


    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (SocketUtil.socket == null) {
                SocketUtil.socket = new Socket(SocketUtil.IP, SocketUtil.PORT);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a6_test);
        this.socket = SocketUtil.socket;
        initUI();
        mRequestQueue = Volley.newRequestQueue(A6_test.this);
        img_buff_cnt = 0;
        buffer = new byte[1024 * 1024];
        img_buffer = new byte[1024 * 1024];
        mHandle = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
//                int[] color = (int[]) msg.obj;
//				iv_mirror1.setBackgroundColor(Color.argb(color[3],color[0], color[1], color[2]));
                switch (msg.what) {
                    case UPDATEUI://接收子线程发过来的图像
//                        progressDialog.dismiss();
                        iv_mirror.setImageBitmap(bmp);
                        img_buffer_lock = false;
                        break;
                    default:
                        break;
                }

            }
        };
        /**
         * 图像处理线程
         */

//		new myThread().run();
        getVideoThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (SocketUtil.socket == null) {
                        SocketUtil.socket = new Socket(SocketUtil.IP, SocketUtil.PORT);
                        socket = SocketUtil.socket;
                        Log.e(TAG, "socket is null");
                    } else {
                        socket = SocketUtil.socket;
                    }
                    dataInputStream = new DataInputStream(socket.getInputStream());

                    getVideo(socket);


//                    OutputStream outputStream = socket.getOutputStream();
//                    byte[] code = SocketUtil.HexUtils.getBytes(SocketUtil.OPENTVIDEO);
//                    Log.e("code", SocketUtil.HexUtils.getString(code));
//                    outputStream.write(code);
//                    outputStream.flush();//向下位机发送打开视频指令

//                    Looper.prepare();
//                    progressDialog.setTitle("正在获取视频，请稍后");
//                    progressDialog.show();
//                    Looper.loop();
                    Log.e(TAG, "is going to while()");

                    try {
                        while (true) {
                            Log.e(TAG, "in while()");

                            if (socket.isConnected()) {
                                Log.e(TAG, "socket.isConnected()");
                                if (dataInputStream == null) {
                                    dataInputStream = new DataInputStream(socket.getInputStream());
                                }
                                int len = dataInputStream.read(buffer);

                                Log.d(TAG, "recv:" + len + " " + buffer.length);
                                if (len > 0) {
                                    //recv_pkg(buffer, len);
//                                    //贞检测
//                                    for(int i = 0; i < len ;i++){
//                                        //贞头
//                                        if (i>0 && (byte)buffer[i-1] == 0x5A && (byte)buffer[i] == 0xA5){
//                                            Log.e("frameNum",buffer[i+1]+" "+buffer[i+2]);
//
//                                            if (i > 0 && buffer[i - 1] == (byte) 0xff && buffer[i] == (byte) 0xd8) {
//                                                Log.d(TAG, "start img");
//                                                img_buffer[0] = (byte) 0xff;
//                                                img_buff_cnt = 1;
//                                            }
//
//                                            if (i > 0 && buffer[i - 1] == (byte) 0xff && buffer[i] == (byte) 0xd9) {
//                                                Log.d(TAG, "end img1");
//                                                //img_buffer[0] = (byte) 0xff;
//                                                //img_buff_cnt = 1;
//                                            }
//
//                                            if (img_buff_cnt < 1024 * 1024) {
//                                                img_buffer[img_buff_cnt] = buffer[i];
//
//                                                if (img_buff_cnt > 0 && img_buffer[img_buff_cnt - 1] == (byte) 0xff && img_buffer[img_buff_cnt] == (byte) 0xd9) {
//                                                    Log.d(TAG, "end img");
//                                                    bmp = BitmapFactory.decodeByteArray(img_buffer, 0, img_buff_cnt + 1);
//                                                    if (bmp != null) {
//                                                        img_buffer_lock = true;
//                                                        Message message = new Message();
//                                                        message.what = UPDATEUI;
//                                                        mHandle.sendMessage(message);
//                                                        while (img_buffer_lock == true) ;
//                                                    }
//                                                    img_buff_cnt = -1;
//
//                                                }
//
//                                                img_buff_cnt++;
//                                            } else {
//                                                Log.e(TAG, "img_buff_cnt" + img_buff_cnt);
//                                                img_buff_cnt = 0;
//                                            }
//
//
//
//                                        }
//                                    }
                                    //贞检测

                                    for (int i = 0; i < len; i++) {
//                                        Log.e("image0",buffer[i]+" ");
                                        //调试：将接收到的图片信息输出到text文件
//                                        FileWriter fw =  new FileWriter(Environment.getExternalStorageDirectory()+"aaaaaa.txt");
//                                        fw.flush();
//                                        fw.write(buffer[i]);
//                                        fw.close();
//                                        Log.e("image0",buffer[i]+" ");

                                        if (i > 0 && buffer[i - 1] == (byte) 0xff && buffer[i] == (byte) 0xd8) {
                                            Log.d(TAG, "start img");
                                            img_buffer[0] = (byte) 0xff;
                                            img_buff_cnt = 1;
                                        }

                                        if (i > 0 && buffer[i - 1] == (byte) 0xff && buffer[i] == (byte) 0xd9) {
                                            Log.d(TAG, "end img1");
                                            //img_buffer[0] = (byte) 0xff;
                                            //img_buff_cnt = 1;
                                        }

                                        if (img_buff_cnt < 1024 * 1024) {
                                            img_buffer[img_buff_cnt] = buffer[i];

                                            if (img_buff_cnt > 0 && img_buffer[img_buff_cnt - 1] == (byte) 0xff && img_buffer[img_buff_cnt] == (byte) 0xd9) {
                                                Log.d(TAG, "end img");
                                                bmp = BitmapFactory.decodeByteArray(img_buffer, 0, img_buff_cnt + 1);

//                                                System.arraycopy(img_buffer,0,img_data,0,img_buff_cnt + 1);

                                                if (bmp != null) {
                                                    img_buffer_lock = true;
                                                    Message message = new Message();
                                                    message.what = UPDATEUI;
                                                    mHandle.sendMessage(message);
                                                    while (img_buffer_lock == true) ;
                                                }
                                                img_buff_cnt = -1;

                                            }

                                            img_buff_cnt++;
                                        } else {
                                            Log.e(TAG, "img_buff_cnt" + img_buff_cnt);
                                            img_buff_cnt = 0;
                                        }
                                    }

                                } else {
                                    //重新发送一遍视频请求命令
                                    Log.e(TAG, "close");
                                    getVideo(socket);
                                }
                            } else {
//                                wait(1000);
                                Log.d(TAG, "wait for socket connect");
                                SocketUtil.socket = new Socket(SocketUtil.IP, SocketUtil.PORT);
                                socket = SocketUtil.socket;
                            }
                        }
                    } catch (Exception e) {
                        //code
                        e.printStackTrace();
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        getVideoThread.start();
    }

    //    	/**
//	 * 图像处理线程
//	 */
//	class myThread implements Runnable {
//		public void run() {
//				BitmapDrawable db = (BitmapDrawable) getResources().getDrawable(
//						R.mipmap.mirror);
//				Bitmap bitmap = db.getBitmap();
//				int width = bitmap.getWidth();
//				int hight = bitmap.getHeight();
//				int[] pixs = new int[width*hight];
//			    int sum_r = 0;
//			    int sum_g = 0;
//			    int sum_b = 0;
//			    int sum_a = 0;
//			    int cunt = 0;
//				bitmap.getPixels(pixs, 0, width, 0, 0, width, hight);
//				for (int i =0;i<width*hight;i++){
//					int r = Color.red(pixs[i]);
//					int g = Color.green(pixs[i]);
//					int b = Color.blue(pixs[i]);
//					int a = Color.alpha(pixs[i]);
//
//					//去除黑色点
//					if (r!=0 && g!=0 && b!=0){
//						sum_r+=r;
//						sum_g+=g;
//						sum_b+=b;
//						sum_a+=a;
//						cunt++;
//					}
//				}
//				int[] color = new int[4];
//				color[0] = sum_r/cunt;
//				color[1] = sum_g/cunt;
//				color[2] = sum_b/cunt;
//				color[3] = sum_a/cunt;
//
//				Message message = new Message();
//				message.obj = color;
//				mHandle.sendMessage(message);
//		}
//	}
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.iv_myinfo_test:
                ForwardHelper.toMyinfo(A6_test.this);
                break;
            case R.id.btn_takephoto:

                btn_getyanzhi.setVisibility(View.VISIBLE);
                tv_tips.setVisibility(View.GONE);
                //模拟数据，取背景图片
//                bmp = BitmapFactory.decodeResource(getResources(),R.mipmap.mirror);
                if (bmp != null) {
                    PHOTOISTAKED = true;
                    if (PHOTOISTAKED) {
//                        btn_getyanzhi.setVisibility(View.VISIBLE);
//                        tv_tips.setVisibility(View.GONE);
                        if (pic_a_show == 0) {
                            iv_pic_a.setImageBitmap(bmp);
                            pic_a_show = 1;
                            iv_delete_a.setVisibility(View.VISIBLE);
                            return;
                        } else if (pic_b_show == 0) {
                            iv_pic_b.setImageBitmap(bmp);
                            bitmapToUpload = bmp;//采用这里的bitmap作为上传的图像
                            pic_b_show = 1;
                            iv_delete_b.setVisibility(View.VISIBLE);
                            return;
                        } else if (pic_c_show == 0) {
                            iv_pic_c.setImageBitmap(bmp);
                            pic_c_show = 1;
                            iv_delete_c.setVisibility(View.VISIBLE);
                            return;
                        }

                    }
                } else {
                    //模拟数据，取背景图片
                    bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.mirror);
//                    Toast.makeText(A6_test.this, "无法获取视频信号，请连接到硬件稍后重试", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.iv_delete_a:
                iv_pic_a.setImageResource(R.mipmap.photo);
                iv_delete_a.setVisibility(View.GONE);
                pic_a_show = 0;
                break;
            case R.id.iv_delete_b:
                iv_pic_b.setImageResource(R.mipmap.photo);
                iv_delete_b.setVisibility(View.GONE);
                pic_b_show = 0;
                break;
            case R.id.iv_delete_c:
                iv_pic_c.setImageResource(R.mipmap.photo);
                iv_delete_c.setVisibility(View.GONE);
                pic_c_show = 0;
                break;
            //获取颜值>
            case R.id.btn_getyanzhi:
                if (isChangedNetwork == false) {
//                ForwardHelper.toYanzhiDetail(this);
                    //先判断目前的网络情况，如果不是连接到外网（数据或者wifi连接），就弹出dialog进行提示网络选择
//                if (NetworkUtils.isWifi(this)) {
//                    Log.e("code","wifi");
//                    String authToken = LoginUtils.getAuthToken(A6_test.this);
//                    if (bitmapToUpload != null) {
//                        String image = BitmapUtils.bitmapToBase64(bitmapToUpload);
////                        VolleyUtil.toUploadphoto(this, mRequestQueue, image, authToken);
//                    }else {
//                        Toast.makeText(this,"请拍照后再点击获取颜",Toast.LENGTH_LONG).show();
//                    }
//                } else {
                    dialog = new AlertDialog.Builder(this).create();
                    dialog.show();
                    Window window = dialog.getWindow();
                    window.setContentView(R.layout.dialog_notic);
                    TextView tv_tip = (TextView) window.findViewById(R.id.tv_tips);
                    tv_tip.setText("请切换您的网络链接，您需要断开与硬件的网络链接，选择可以链接到互联网的网络");
                    Button btn_yes = (Button) window.findViewById(R.id.btn_yes);
                    Button btn_no = (Button) window.findViewById(R.id.btn_no);
                    btn_no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    //跳到wifi设置界面，切换其他可联网wifi
                    btn_yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                            startActivity(intent);
                            isChangedNetwork = true;
                            dialog.dismiss();
                        }
                    });
//                }
                }
                //已经切换到可以联网的网络
                else {
                    //测试上传图像接口
//                    bitmapToUpload = BitmapFactory.decodeResource(getResources(), R.mipmap.mirror);

                    //上传图片到服务器
                    String authToken = LoginUtils.getAuthToken(A6_test.this);
                    Log.e("authToken", authToken);
                    if (bitmapToUpload != null) {

                        String image = null;
                        try {
                            image = BitmapUtils.bitmapToBase64(bitmapToUpload);
//                            image = MyBase64Encoder.encode(img_data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

//                        String image = Base64.encodeToString(img_data, Base64.DEFAULT);
//                        String path = Environment.getExternalStorageDirectory()+"/yanji/test.jpg";
//                        data_temp = BitmapUtils.getBytes(path);
//                        String image = Base64.encodeToString(data_temp, Base64.DEFAULT);

//                        String image = null;
//                        try {
//                            image = MyBase64Encoder.encode(data_temp);
////                            image = BitmapUtils.bitmapToBase64(bitmapToUpload);
////                            BitmapUtils.saveToSDCard("/yanji/test.txt",image);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }

//                        String image = "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";
                        Log.e("image", image);
                        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
                        //创建okHttpClient对象
                        OkHttpClient mOkHttpClient = new OkHttpClient();
                        FormEncodingBuilder builder = new FormEncodingBuilder();
                        builder.add("image", image);
                        builder.add("authToken", authToken);
                        //创建一个Request
                        final Request request = new Request.Builder()
                                .url(HttpUrl.uploadUrl)
                                .post(builder.build())
                                .build();
                        //new call
                        Call call = mOkHttpClient.newCall(request);
                        //请求加入调度
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Request request, IOException e) {
                                Toast.makeText(A6_test.this, "上传图像失败，请检查您的手机是否网络畅通", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onResponse(Response response) throws IOException {
                                Log.e("okhttp", response.toString());
                                Intent intent = new Intent(A6_test.this, A7_yanzhidetail.class);
                                intent.putExtra("From", "A6_test");
//                                intent.putExtra("response", responseString);
                                startActivity(intent);
                                overridePendingTransition(R.anim.activity_open_in_anim,
                                        R.anim.activity_open_out_anim);

                            }
                        });

                        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& Log.e("okhttp", response.toString());

//                        UpLoadPicTask upLoadPicTask = new UpLoadPicTask();
//                        upLoadPicTask.execute(image, authToken);

//                        VolleyUtil.toUploadphoto(this, mRequestQueue, image, authToken);
//                        Intent intent = new Intent(this, A7_yanzhidetail.class);
//                        intent.putExtra("From", "A6_test");
//                        this.startActivity(intent);
//                        this.overridePendingTransition(R.anim.activity_open_in_anim,
//                                R.anim.activity_open_out_anim);
                    } else {
                        Toast.makeText(this, "请拍照后再点击获取颜", Toast.LENGTH_LONG).show();
                    }
                }

                break;
            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                ToastUtils.show(getApplicationContext(), "再按一次退出程序");
                firstTime = secondTime;
                return true;
            }
            ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE)).restartPackage(getPackageName());
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initUI() {
        // TODO Auto-generated method stub
        iv_mirror = (RoundImageView) findViewById(R.id.iv_mirror);
//        iv_mirror1 = (RoundImageView) findViewById(R.id.iv_mirror1);
//		iv_mirror1.bringToFront();
        iv_myinfo_test = (ImageView) findViewById(R.id.iv_myinfo_test);
        iv_pic_a = (ImageView) findViewById(R.id.iv_pic_a);
        iv_pic_b = (ImageView) findViewById(R.id.iv_pic_b);
        iv_pic_c = (ImageView) findViewById(R.id.iv_pic_c);
        iv_delete_a = (ImageView) findViewById(R.id.iv_delete_a);
        iv_delete_b = (ImageView) findViewById(R.id.iv_delete_b);
        iv_delete_c = (ImageView) findViewById(R.id.iv_delete_c);

        btn_takephoto = (Button) findViewById(R.id.btn_takephoto);
        btn_getyanzhi = (Button) findViewById(R.id.btn_getyanzhi);
        tv_tips = (TextView) findViewById(R.id.tv_tips);
        progressDialog = new ProgressDialog(this);
        setListener(iv_myinfo_test, btn_takephoto, btn_getyanzhi, iv_delete_a, iv_delete_b, iv_delete_c);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (socket.isConnected()) {
//                    OutputStream os = null;
//                    try {
//                        os = SocketUtil.socket.getOutputStream();
//                        os.write(SocketUtil.HexUtils.getBytes(SocketUtil.TURNOFF));
//                        os.flush();
//                        Thread.sleep(1000);
//                        SocketUtil.socket.close();
//                        Intent stopIntent = new Intent(A6_test.this, HeartbeatService.class);
//                        stopService(stopIntent);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//            }
//        }).start();
    }

    private void getVideo(Socket socket) {
        OutputStream outputStream = null;
        try {
            outputStream = socket.getOutputStream();
            byte[] code = SocketUtil.HexUtils.getBytes(SocketUtil.OPENTVIDEO);
            outputStream.write(code);
            outputStream.flush();//向下位机发送打开视频指令
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
