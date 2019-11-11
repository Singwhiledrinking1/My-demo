package com.sdbi.a1713640153;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    private ListView listView;
    private Button button;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = (ListView)findViewById(R.id.listview);
        button  =(Button)findViewById(R.id.button);
        button3  =(Button)findViewById(R.id.button3);

        init();


        //定义String数组，存储对应名称集合
        String[] titles=new String[]{"java","linux","web","android","斗破苍穹","武动乾坤"};
        //定义int数组，存储对应图片集合
        String[] danjias=new String[]{"34Rmb","44Rmb","24Rmb","84Rmb","44Rmb","35Rmb"};
        int[] images = new int[]{R.drawable.java,R.drawable.linux1,R.drawable.web,R.drawable.android1,R.drawable.doupo,R.drawable.wudong};
        // ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice,titles);//创建适配器，第三个所要传达的参数,第二个后缀是调整列表的外观，第一个上下文对像

        List<Map<String,Object>> listItem =new ArrayList<Map<String,Object>>();

        for(int i=0;i<images.length;i++)
        {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("title",titles[i]);
            map.put("image",images[i]);
            map.put("danjia",danjias[i]);
            listItem.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,listItem,R.layout.listview,new String[]{"title","image","danjia"},new int[]{R.id.textView,R.id.imageView,R.id.textView2});
        listView.setAdapter(adapter);//调用适配器


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Main2Activity.this,Myguangbo.class);//显示意图启动广播 这不能直接写this
                sendBroadcast(intent);

                Intent intent1= new Intent(Main2Activity.this, Main3Activity.class);//当输入用户名和密码之后再能实现程序的跳转 并且后面的记住密码等功能
                startActivity(intent1);

            }
        });
    }
    protected void init(){
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}

