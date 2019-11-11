package com.sdbi.a1713640153;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {
private TextView textView19;
private Button button9;
private Button button10;
private ListView listView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        textView19 = (TextView)findViewById(R.id.textView19);
        button9 = (Button)findViewById(R.id.button9);
        button10= (Button)findViewById(R.id.button10);
        listView2 = (ListView)findViewById(R.id.listview2) ;

        //接收 Main4Activity中传递过来的用户名的信息
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String name1=bundle.getString("name1");
        textView19.setText(name1);



        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db= Main4Activity.myhelper1.getReadableDatabase();
                Cursor cursor= db.rawQuery("select * from user",null);
                SimpleCursorAdapter myAdapter=new SimpleCursorAdapter(Main5Activity.this,R.layout.listview3,cursor,new String[]{"d"},new int[]{R.id.textView18});
               listView2.setAdapter(myAdapter);
                db.close();
            }
        });
    }
}
