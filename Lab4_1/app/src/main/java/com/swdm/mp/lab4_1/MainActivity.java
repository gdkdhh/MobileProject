package com.swdm.mp.lab4_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View mv = new MyView(this);
        setContentView(mv);
    }

    protected class MyView extends View {

        Path pt = new Path();
        Paint pnt = new Paint();

        public MyView(Context context){
            super(context);
        }

        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
            pnt.setColor(Color.BLUE);
            pnt.setStyle(Paint.Style.STROKE);
            pnt.setStrokeCap(Paint.Cap.ROUND);
            pnt.setStrokeWidth(15);
            canvas.drawPath(pt,pnt);
        }

        public boolean onTouchEvent(MotionEvent event)
        {
            super.onTouchEvent(event);
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                 pt.moveTo((int)event.getX(),(int)event.getY());
            }

            else if(event.getAction()==MotionEvent.ACTION_MOVE)
            {
                pt.lineTo((int)event.getX(),(int)event.getY());
            }

            invalidate();
            return true;
        }

    }
}
