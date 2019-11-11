package com.sdbi.a1713640153;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
private Button button6;
private Button button7;
private Button button8;
private ListView listView1;
private ListView listView2;
private TextView textView17;

    private SQLiteDatabase db;
    private SQLiteDatabase db1;
    static MyHelper myhelper1;
    private ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        button6 = (Button)findViewById(R.id.button6);
        button7 = (Button)findViewById(R.id.button7);
        button8 = (Button)findViewById(R.id.button8);
        listView1 = (ListView)findViewById(R.id.listview1) ;

        textView17 = (TextView)findViewById(R.id.textView17) ;

        //接收 Main3Activity中传递过来的用户名的信息
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String name1=bundle.getString("name1");
        textView17.setText(name1);

       myhelper1 = new MyHelper(Main4Activity.this,"my.db",null,1);
        db =myhelper1.getReadableDatabase();



        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(Main4Activity.this, Main5Activity.class);//当输入用户名和密码之后再能实现程序的跳转 并且后面的记住密码等功能

                db = myhelper1.getWritableDatabase();//创建打开一个数据库
                values = new ContentValues();
                values.put("d",Main3Activity.editText7.getText().toString());


                db.insert("user",null,values);
                db.close();



                //使用数据传递功能把textView17所写的用户信息传递到下一个活动中
                Bundle bundle = new Bundle();
                bundle.putString("name1",textView17.getText().toString().trim());
                intent1.putExtras(bundle);
                Toast.makeText(Main4Activity.this, "地址页面", Toast.LENGTH_SHORT).show();

                startActivity(intent1);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              SQLiteDatabase db= Main3Activity.myhelper.getReadableDatabase();
               //把数据库中 Main3Activity 电话  书籍  数量 三个属性的值  显示到Main4Activity中
                Cursor cursor= db.rawQuery("select * from user",null);
                SimpleCursorAdapter myAdapter=new SimpleCursorAdapter(Main4Activity.this,R.layout.listview2,cursor,new String[]{"phone","book","number"},new int[]{R.id.textView12,R.id.textView10,R.id.textView16});
                listView1.setAdapter(myAdapter);
                db.close();
            }
        });
    }
    public class MyHelper extends SQLiteOpenHelper {
        //public MyHelper(Context context)
        //{
        //   super(context,"myDB.db",null,1);
        //  }
        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
        {
            super(context,name,factory,version);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL("create table user(_id integer primary key  autoincrement,d text not null)");
            Toast.makeText(Main4Activity.this,"创建数据库成功",Toast.LENGTH_LONG).show();
        }
        @Override
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
        {

        }

    }
}











