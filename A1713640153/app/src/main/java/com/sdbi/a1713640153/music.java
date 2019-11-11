package com.sdbi.a1713640153;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class music extends AppCompatActivity {
    private Context mContext;
   private ImageButton imageButton,imageButton2;
   private ImageView imageView1;
    private boolean isplaying;
   private ConstraintLayout constraintLayout;
    static  MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
       imageButton = (ImageButton)findViewById(R.id.imageButton10) ;
        imageButton2 = (ImageButton)findViewById(R.id.imageButton11) ;
        imageButton2.setVisibility(View.GONE);

       imageView1 = (ImageView)findViewById(R.id.iv_needle);
        constraintLayout = (ConstraintLayout)findViewById(R.id.fl_play_music) ;


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) //判断用户是否给我们授权
                != PackageManager.PERMISSION_GRANTED){ //使用返回值与PackageManager.PERMISSION_GRANTED作比较，相等就代表已经授权，不等就代表用户没有授权.
            //如果没有权限，动态申请授权
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);//如果没有授权则需要调用此方法来申请授权，
            //接收三个参数，第一个参数为activity实例，地位个参数为staring数组，我们要把申请的权限名写入数组中，第三个为请求码，要求唯一即可
        }else {
            //如果设置了权限则直接播放sd中的音频文件
            MediPlayerSD();
        }


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   mediaPlayer.start();

                Intent intent =new Intent(music.this,MyService1.class);

                startService(intent);

                isplaying=true;
                Animation animation = AnimationUtils.loadAnimation(music.this,R.anim.play_music_anim);
                constraintLayout.startAnimation(animation);
                if(isplaying==true)
                {
                    Animation animation1 = AnimationUtils.loadAnimation(music.this,R.anim.stop_needle_anim);
                     imageView1.startAnimation(animation1);
                    imageButton.setVisibility(View.GONE);
                    imageButton2.setVisibility(View.VISIBLE);
                }else {

                }
                //Animation animation1 = AnimationUtils.loadAnimation(music.this,R.anim.play_needle_anim);
               // imageView1.startAnimation(animation1);

            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 =new Intent(music.this,MyService1.class);
                stopService(intent2);

                mediaPlayer.pause();
                imageButton.setVisibility(View.VISIBLE);
                imageButton2.setVisibility(View.GONE);

                constraintLayout.clearAnimation();

                Animation animation4 = AnimationUtils.loadAnimation(music.this,R.anim.play_needle_anim);
                imageView1.startAnimation(animation4);
            }
        });


    }



    private void MediPlayerSD()
    {
        try {
            mediaPlayer = new MediaPlayer();
            //   mediaPlayer=MediaPlayer.create(this,R.raw.kcj) ;  //播放本地资源                                      //俩个参数，第一个上下文，第二个可以通过id的形式来应用资源文件     c创建一个mediaplayer对像，并且装载音频文件\
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);//创建声流的类型   系统可以根据类型分配不同的内存空间
            File file  =new File(Environment.getExternalStorageDirectory(),"jiujin.mp3"); //访问手机中的路径，不在是针对不同的目录.
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
            // mediaPlayer.setDataSource("http://www.170mv.com/kw/other.web.rh01.sycdn.kuwo.cn/resource/n2/48/90/4059254706.mp3");   //播放内部资源   播放网络资源
            //准备播放或回放（同步）


            //  mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { //数组中传过来的参数不可以为空，且用户已经点击同意授权.

                MediPlayerSD();
            }else {
                Toast.makeText(this,"未获得sd卡访问权限",Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    protected void onDestroy()
    {
        super.onDestroy();
        //关闭应用时释放MediaPlayer对像占用资源
        if(mediaPlayer!=null)
        {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
}