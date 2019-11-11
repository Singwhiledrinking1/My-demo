package com.sdbi.a1713640153;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService1 extends Service {
    public MyService1() {
    }
    //Service被创建时回调

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public void onCreate()
    {
        Log.i("MyService1","onCreate()");

    }
    //Service被启动时回调

    public int onStartCommand(Intent intent,int flags,int startId)
    {
        Log.i("MyService1"," onStartCommand");

        //  stopSelf();
        music.mediaPlayer.start();
        return super.onStartCommand(intent,flags,startId);
    }
    //Service被关闭之前回调，通常用于清理资源

    public void onDestroy()
    {
        music.mediaPlayer.pause();
        super.onDestroy();
        Log.i("MyService1","onDestroy");
    }
}
