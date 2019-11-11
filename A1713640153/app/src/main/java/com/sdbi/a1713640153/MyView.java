package com.sdbi.a1713640153;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {
    public float bitmapX,bitmapY;
    public MyView(Context context) {

        super(context);
        bitmapX=600;
        bitmapY=130;
    }
//绘制图片
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       Paint paint = new Paint(); //利用无参的构造方法定义一个画笔对象
      Bitmap bitmap  = BitmapFactory.decodeResource(this.getResources(),R.drawable.d1)  ;           //获取系统资源  知名数据，
   //  canvas.drawBitmap(bitmap,bitmapX,bitmapY,paint);    //第一个什么图片  第二个什么位置，第三个什么画笔   用来绘制view
        Matrix matrix = new Matrix();
        matrix.setRotate(60);
       // canvas.drawColor(Color.RED); //设置画布的颜色
     // canvas.drawBitmap(bitmap,matrix,paint);
      if(bitmap.isRecycled())
     {
          bitmap.recycle();
     }

     canvas.drawLine(10,40,50,50,paint);
       /* Paint paint = new Paint();
        paint.setColor(Color.RED);
        Rect rect = new Rect(40,40,200,100);
        canvas.drawRect(rect,paint);*/ //绘制区域，参数一为rectF的区域



  }
}
