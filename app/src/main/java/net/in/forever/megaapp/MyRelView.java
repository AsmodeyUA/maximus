package net.in.forever.megaapp;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class MyRelView extends RelativeLayout {

    private int widthX,widthY;
    Paint mPaint;

    public MyRelView(final Context context, final AttributeSet attrs,
                     final int defStyle) {
        super(context, attrs, defStyle);
        setWillNotDraw(false); // разрешаем рисовать в ViewGroup
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(final int width, final int height,
                                 final int oldWidth, final int oldHeight) {
        setWidthX(width);
        setWidthY(height);

        System.out.println("Width - " + width);
        System.out.println("Height - " + height);
    }

    @Override
    public void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        //canvas.drawOval(mBounds, mPaint);
        double alfa = Math.atan(widthY / widthX);
        int a_el = 0;
        int b_el = widthY/4;
        int shift_x = widthX/2;
        int shift_y = widthY/2;
        int temp_x_i;
        int temp_y_i;
        int temp_x_i_alfa;
        int temp_y_i_alfa;
        double sin_alpha = Math.sin(alfa);
        double cos_alpha = Math.cos(alfa);

        int temp_x = a_el ;
        int temp_y = b_el ;


        temp_x_i_alfa = (int)(temp_x*cos_alpha+temp_y*sin_alpha);
        temp_y_i_alfa = (int)(-temp_x*sin_alpha+temp_y*cos_alpha);


        int temp_x_i1 = temp_x_i_alfa +shift_x;
        int temp_y_i1 = shift_y- temp_y_i_alfa;


        canvas.drawLine(0,0, temp_x_i1, temp_y_i1, mPaint);

        temp_x = a_el ;
        temp_y = -b_el ;


        temp_x_i_alfa = (int)(temp_x*cos_alpha+temp_y*sin_alpha);
        temp_y_i_alfa = (int)(-temp_x*sin_alpha+temp_y*cos_alpha);


        temp_x_i = temp_x_i_alfa +shift_x;
        temp_y_i = shift_y- temp_y_i_alfa;


        canvas.drawLine(0,0, temp_x_i, temp_y_i, mPaint);
        canvas.drawLine(temp_x_i, temp_y_i,temp_x_i1, temp_y_i1, mPaint);

        //     canvas.drawLine(0,860, 720, 860, mPaint);
    }

    public int getWidthY() {
        return widthY;
    }

    private void setWidthY(int widthY) {
        this.widthY = widthY;
    }

    public int getWidthX() {
        return widthX;
    }

    private void setWidthX(int widthX) {
        this.widthX = widthX;
    }
}