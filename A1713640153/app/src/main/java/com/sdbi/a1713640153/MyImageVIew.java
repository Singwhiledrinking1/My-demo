package com.sdbi.a1713640153;

import android.content.Context;

public class MyImageVIew extends android.support.v7.widget.AppCompatImageView {
    public MyImageVIew(Context context) {

        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(1070,800); //设置自定义view的大小
    }
}
