package com.sdbi.a1713640153;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Myguangbo extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving

        Toast.makeText(context,"请确认您的信息",Toast.LENGTH_LONG).show();//onReceive方法中第二个就是上下文
        System.out.println("Myreceivwe接收到一条广播");
    }
}
