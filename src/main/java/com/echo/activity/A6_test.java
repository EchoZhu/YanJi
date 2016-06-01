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
import com.echo.service.GetBattery;
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
 *         <p>
 *         2015-10-28
 */
public class A6_test extends BaseActivity {

    private ImageView iv_myinfo_test, iv_pic_a, iv_pic_b, iv_pic_c,
            iv_delete_a, iv_delete_b, iv_delete_c;
    private Button btn_takephoto, btn_getyanzhi;
    private TextView tv_tips, tv_battery;
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
    Bitmap bmp,bmp_a,bmp_b,bmp_c;
    Bitmap bitmapToUpload;
    String TAG = "VideoSocket";
    private int pic_a_show = 0;
    private int pic_b_show = 0;
    private int pic_c_show = 0;
    private static final int UPDATEUI = 0x11;
    private RequestQueue mRequestQueue;
    AlertDialog dialog;
    private AlertDialog showPicDialog;
    private ImageView iv_pic;
    private Thread getVideoThread;

    private boolean isChangedNetwork = false;


    @Override
    protected void onResume() {
        super.onResume();
//        try {
//            if (SocketUtil.socket == null) {
//                SocketUtil.socket = new Socket(SocketUtil.IP, SocketUtil.PORT);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a6_test);
        this.socket = SocketUtil.socket;
        initUI();
//        startGetBattery();
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

    /**
     * 启动获取硬件电池电量服务
     */
    private void startGetBattery() {
        Intent intent = new Intent(this, GetBattery.class);
        startActivity(intent);
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
                            bmp_a = bmp;
                            iv_pic_a.setImageBitmap(bmp_a);
                            pic_a_show = 1;
                            iv_delete_a.setVisibility(View.VISIBLE);
                            return;
                        } else if (pic_b_show == 0) {
                            bmp_b = bmp;
                            iv_pic_b.setImageBitmap(bmp_b);
                            bitmapToUpload = bmp;//采用这里的bitmap作为上传的图像
                            pic_b_show = 1;
                            iv_delete_b.setVisibility(View.VISIBLE);
                            return;
                        } else if (pic_c_show == 0) {
                            bmp_c = bmp;
                            iv_pic_c.setImageBitmap(bmp_c);
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
            case R.id.iv_pic_a:
                if (iv_delete_a.isShown()){
                    shwoPopView(bmp_a);
                }
                break;
            case R.id.iv_pic_b:
                if (iv_delete_b.isShown()){
                    shwoPopView(bmp_b);
                }
                break;
            case R.id.iv_pic_c:
                if (iv_delete_c.isShown()){
                    shwoPopView(bmp_c);
                }
                break;
            default:
                break;
        }
    }

    private void shwoPopView(Bitmap bitmap) {
        showPicDialog.show();
        Window window = showPicDialog.getWindow();
        window.setContentView(R.layout.pic_view);
        iv_pic = (ImageView) window.findViewById(R.id.iv_pic);
        iv_pic.setImageBitmap(bitmap);
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
        tv_battery = (TextView) findViewById(R.id.tv_battery);
        progressDialog = new ProgressDialog(this);
        setListener(iv_myinfo_test, btn_takephoto, btn_getyanzhi, iv_delete_a, iv_delete_b, iv_delete_c, iv_pic_a, iv_pic_b, iv_pic_c);
        //初始化图片显示dialog
        showPicDialog = new AlertDialog.Builder(this).create();


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
