package com.sdbi.a1713640153;

import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
public class chuanggan extends AppCompatActivity implements SensorEventListener{
private TextView textView;
private Sensor sensor;
private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuanggan);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        textView = (TextView)findViewById(R.id.textView26);
        sensorManager =(SensorManager) getSystemService(SENSOR_SERVICE);

        //实现textview滑动观看
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);  //定义传感器对象，指明使用的传感器  这里指光线传感器

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;   //或许当前 对象的光线信息，获取x，y，z三轴的输出信息
        int sensorType  = event.sensor.getType();  //获取传感器类型
        switch (sensorType)
        {
            case Sensor.TYPE_LIGHT :

                //textView.setText("当前光线为:" +values[0]);
                if(values[0]<50)
                {
                    textView.setText("您当前的环境不适合观书");
                }
                else
                {
                    textView.setText("您当前的图书观看环境良好");
                }

                break;



        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if(sensorManager!=null)
        {
            sensorManager.unregisterListener(this);  //取消注册监听器
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        if(sensorManager!=null)
        {
            sensorManager.unregisterListener(this);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        //  sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);//注册监听器

        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_GAME);//注册监听器游戏评率
    }
}
