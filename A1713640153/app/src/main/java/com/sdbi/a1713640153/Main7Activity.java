package com.sdbi.a1713640153;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class Main7Activity extends AppCompatActivity {
    private Button button14,button16,button15,button18,button17;
    private ImageView imageView;
    private ConstraintLayout constraintLayout;
    private MyView myView;
    private MyImageVIew myImageVIew;
    private TextView textView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        button16 = (Button)findViewById(R.id.button16);
        button15 = (Button)findViewById(R.id.button15);
        button18 = (Button)findViewById(R.id.button18);
        button17 = (Button)findViewById(R.id.button17);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shiping();
            }
        });
        textView4 = (TextView)findViewById(R.id.textView20) ;

        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(Main7Activity.this, music.class);
                startActivity(intent1);
            }
        });

        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent3= new Intent(Main7Activity.this, yuedu.class);
                startActivity(intent3);
            }
        });

        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4= new Intent(Main7Activity.this, chuanggan.class);
                startActivity(intent4);
            }
        });


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) //判断用户是否给我们授权
                != PackageManager.PERMISSION_GRANTED){ //使用返回值与PackageManager.PERMISSION_GRANTED作比较，相等就代表已经授权，不等就代表用户没有授权.
            //如果没有权限，动态申请授权
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);//如果没有授权则需要调用此方法来申请授权，
            //接收三个参数，第一个参数为activity实例，地位个参数为staring数组，我们要把申请的权限名写入数组中，第三个为请求码，要求唯一即可
        }else {
            //如果设置了权限则直接播放sd中的音频文件

        }

        button14 = (Button)findViewById(R.id.button14);

        constraintLayout = (ConstraintLayout)findViewById(R.id.mylayout1);//获取布局实力

        //对myview进行实例化
        myView = new MyView(this);


        myImageVIew = new MyImageVIew(this);

        myImageVIew.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                myImageVIew.setX(event.getX());
                myImageVIew.setY(event.getY());

                return true;
            }
        });
        constraintLayout.addView(myImageVIew);

      //  constraintLayout.addView(myImageVIew);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imageView.setImageResource(R.drawable.x15);
              //  imageView.setWillNotDraw(false);

                myImageVIew.setImageResource(R.drawable.x15);
                   myImageVIew.setWillNotDraw(false);
            }
        });



    }
    protected void shiping()
    {
        VideoView videoView = (VideoView)findViewById(R.id.videoView);
        //videoView.setVideoURI(Uri.parse("android.resource"));
        videoView.setVideoPath("mnt/sdcard/dididi.mp4");
        //   videoView.setVideoURI(Uri.parse(""));   查看网络中的视频
        //  MediaController controller = new MediaController(this);
        // videoView.setMediaController(controller);
        videoView.setMediaController(new MediaController(this));
        videoView.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { //数组中传过来的参数不可以为空，且用户已经点击同意授权.

               shiping();
            }else {
                Toast.makeText(this,"未获得sd卡访问权限",Toast.LENGTH_LONG).show();

            }
        }
    }
}
