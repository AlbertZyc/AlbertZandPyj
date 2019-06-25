package com.use.zyc.losed.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.use.zyc.losed.R;

public class ColorCicle extends View {
    private Paint paint;//»­±Ê
    private int color=0;

    public ColorCicle(Context context) {
        super(context);
    }

    public ColorCicle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorCicle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ColorCicle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        paint = new Paint();
        if(color == 0){
            color= Color.RED;
        }
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
        canvas.drawCircle(getWidth()/2 , getHeight()/2 ,10,paint);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
