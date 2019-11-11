package com.sdbi.a1713640153;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    private Button button5,btn,btn2;
    private Button button4;
    private TextView textView;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private EditText editText6;
    static EditText editText7;
    private SQLiteDatabase db;
    private SQLiteDatabase db1;
    static MyHelper myhelper;

    private LocationManager locationManager;
    private String time;
    private Location location;
    private LocationListener locationListener;
    private MapView mapView;
    private LocationClient locationClient; // 定位服务器
    private BaiduMap baiduMap;
    private boolean isFirstLocate=true;

    private ContentValues values;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main3);













        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);















        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        btn = (Button)findViewById(R.id.button13);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);
        editText5 = (EditText)findViewById(R.id.editText5);
        editText6 = (EditText)findViewById(R.id.editText6);
        editText7 = (EditText)findViewById(R.id.editText7);
        myhelper=new MyHelper(Main3Activity.this,"myDb.db",null,1);

        db =myhelper.getReadableDatabase();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5= new Intent(Main3Activity.this, dingwei.class);

                startActivity(intent5);
            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if("myDb.db"==null)//判断表是否为空
                {
                    return;
                }
                db = myhelper.getWritableDatabase();//创建打开一个数据库
                values = new ContentValues();
                values.put("phone",editText6.getText().toString());
                values.put("book",editText4.getText().toString());
                values.put("number",editText5.getText().toString());

                db.insert("user",null,values);
                db.close();


                Intent intent1= new Intent(Main3Activity.this, Main4Activity.class);//当输入用户名和密码之后再能实现程序的跳转 并且后面的记住密码等功能
                //使用数据传递功能把editText3所写的用户信息传递到下一个活动中
                Bundle bundle = new Bundle();
                 bundle.putString("name1",editText3.getText().toString().trim());
                 intent1.putExtras(bundle);
                Toast.makeText(Main3Activity.this, "付款成功", Toast.LENGTH_SHORT).show();

                startActivity(intent1);




            }
        });
    }
    public class MyHelper extends SQLiteOpenHelper{
        //public MyHelper(Context context)
        //{
        //   super(context,"myDB.db",null,1);
        //  }
        public MyHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version)
        {
            super(context,name,factory,version);
        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL("create table user(_id integer primary key  autoincrement,phone text not null,book text,number text)");
           Toast.makeText(Main3Activity.this,"创建数据库成功",Toast.LENGTH_LONG).show();
        }
        @Override
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
        {

        }

    }








    }



















