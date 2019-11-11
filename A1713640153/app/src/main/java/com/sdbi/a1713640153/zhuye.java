package com.sdbi.a1713640153;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class zhuye extends AppCompatActivity {
private ImageButton imageButton6,imageButton7,imageButton8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuye);
        imageButton6 = (ImageButton)findViewById(R.id.imageButton6) ;

        init();

        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(zhuye.this,Main7Activity.class);//当输入用户名和密码之后再能实现程序的跳转 并且后面的记住密码等功能
                startActivity(intent);
            }
        });

    }
    private void init()
    {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}
