package com.echo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * 项目名称：My Application
 * 类描述：
 * 创建人：zhuyikun
 * 创建时间：15/11/18 下午7:38
 * 修改人：zhuyikun
 * 修改时间：15/11/18 下午7:38
 * 修改备注：
 */
public class DrawView extends View {
    private int x = 0;
    private int y = 0;
    public DrawView(Context context) {
        super(context);
    }
    public DrawView(Context context,int x,int y) {
        super(context);
        this.x = x;
        this.y = y;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
         * 方法 说明 drawRect 绘制矩形 drawCircle 绘制圆形 drawOval 绘制椭圆 drawPath 绘制任意多边形
         * drawLine 绘制直线 drawPoin 绘制点
         */
        // 创建画笔
        Paint p = new Paint();
        p.setColor(Color.RED);// 设置红色
        p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
        canvas.drawCircle(x, y, 100, p);// 大圆
    }
}
