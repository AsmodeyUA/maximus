package net.in.forever.megaapp;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class MyRelView extends RelativeLayout {

    private int widthX,widthY;

    public MyRelView(final Context context, final AttributeSet attrs,
                     final int defStyle) {
        super(context, attrs, defStyle);
        setWillNotDraw(false); // разрешаем рисовать в ViewGroup
        Paint mPaint = new Paint();
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

        //     canvas.drawLine(0,280, 720, 280, mPaint);
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