package com.echo.yanji_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * 项目名称：My Application
 * 类描述：
 * 创建人：zhuyikun
 * 创建时间：15/11/19 下午9:39
 * 修改人：zhuyikun
 * 修改时间：15/11/19 下午9:39
 * 修改备注：
 */
public class ChartView extends View {
    public int XPoint=120;    //原点的X坐标
    public int YPoint=520;     //原点的Y坐标
    public int XScale=110;     //X的刻度长度
    public int YScale=80;     //Y的刻度长度
    public int XLength=760;        //X轴的长度
    public int YLength=480;        //Y轴的长度
    public String[] XLabel;    //X的刻度
    public String[] YLabel;    //Y的刻度
    public String[] Data;      //数据
    public String Title;    //显示的标题
    public ChartView(Context context)
    {
        super(context);
    }
    public void SetInfo(String[] XLabels,String[] YLabels,String[] AllData,String strTitle)
    {
        XLabel=XLabels;
        YLabel=YLabels;
        Data=AllData;
        Title=strTitle;
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);//重写onDraw方法

        //canvas.drawColor(Color.WHITE);//设置背景颜色
        Paint paint= new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setAntiAlias(true);//去锯齿
        paint.setColor(Color.parseColor("#e894da"));//颜色

        //文字
        Paint paint1=new Paint();
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setAntiAlias(true);//去锯齿
        paint1.setColor(Color.DKGRAY);
        paint1.setTextSize(40);  //设置轴文字大小
        paint1.setColor(Color.parseColor("#a78aec"));
        //设置Y轴
        canvas.drawLine(XPoint, YPoint-YLength, XPoint, YPoint, paint);   //轴线
        for(int i=0;i*YScale<YLength ;i++)
        {
            canvas.drawLine(XPoint,YPoint-i*YScale, XPoint+5, YPoint-i*YScale, paint);  //刻度
            try
            {
                canvas.drawText(YLabel[i] , XPoint-50, YPoint-i*YScale+5, paint1);  //文字
            }
            catch(Exception e)
            {
            }
        }
        canvas.drawLine(XPoint,YPoint-YLength,XPoint-3,YPoint-YLength+6,paint);  //箭头
        canvas.drawLine(XPoint,YPoint-YLength,XPoint+3,YPoint-YLength+6,paint);
        //设置X轴
        canvas.drawLine(XPoint,YPoint,XPoint+XLength,YPoint,paint);   //轴线
        for(int i=0;i*XScale<XLength;i++)
        {
            canvas.drawLine(XPoint+i*XScale, YPoint, XPoint+i*XScale, YPoint-5, paint);  //刻度
            try
            {
                canvas.drawText(XLabel[i] , XPoint+i*XScale-10, YPoint+50, paint1);  //文字
                //数据值
                if(i>0&&YCoord(Data[i-1])!=-999&&YCoord(Data[i])!=-999)  //保证有效数据
                    canvas.drawLine(XPoint+(i-1)*XScale, YCoord(Data[i-1]), XPoint+i*XScale, YCoord(Data[i]), paint);
                canvas.drawCircle(XPoint+i*XScale,YCoord(Data[i]), 2, paint);
            }
            catch(Exception e)
            {
            }
        }
        canvas.drawLine(XPoint+XLength,YPoint,XPoint+XLength-6,YPoint-3,paint);    //箭头
        canvas.drawLine(XPoint+XLength,YPoint,XPoint+XLength-6,YPoint+3,paint);
        paint.setTextSize(16);
        canvas.drawText(Title, 150, 50, paint);
    }
    private int YCoord(String y0)  //计算绘制时的Y坐标，无数据时返回-999
    {
        int y;
        try
        {
            y=Integer.parseInt(y0);
        }
        catch(Exception e)
        {
            return -999;    //出错则返回-999
        }
        try
        {
            return YPoint-y*YScale/Integer.parseInt(YLabel[1]);
        }
        catch(Exception e)
        {
        }
        return y;
    }
}
