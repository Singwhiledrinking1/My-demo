package com.sdbi.a1713640153;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class dingwei extends AppCompatActivity implements View.OnClickListener {
    private Button btn1;
    private Button btn2;
    private Button button3;
    private Button button4;
    private TextView textView;
    private LocationManager locationManager;
    private String time;
    private Location location;
    static LocationListener locationListener;
    private MapView mapView;
    private LocationClient locationClient; // 定位服务器
    private BaiduMap baiduMap;
    private boolean isFirstLocate=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_dingwei);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();




        mapView = (MapView) findViewById(R.id.mapView);

        List<String> permissionList = new ArrayList<>();

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty())
        {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this,permissions,1);
        }else
        {

        }






        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);


        textView = (TextView) findViewById(R.id.textView40);


        button3.setOnClickListener( this);
       button4.setOnClickListener( this);
        //获取location服务
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public class MyLocationListener implements BDLocationListener
    {


        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {  //注册监听器
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder positions = new StringBuilder();
                    positions.append("您的位置信息:\n");
                    positions.append("国家"+bdLocation.getCountry()+"\n");
                    positions.append("省:"+bdLocation.getProvince()+"\n");
                    positions.append("市:"+bdLocation.getCity()+"\n");
                    positions.append("区:"+bdLocation.getDistrict()+"\n");
                    positions.append("街道:"+bdLocation.getStreet()+"\n");
                    if(bdLocation.getLocType()==BDLocation.TypeGpsLocation)
                    {
                       // positions.append("GPS");
                    }else {
                        if (bdLocation.getLocType()==BDLocation.TypeNetWorkLocation)
                        {
                         //   positions.append("Network");
                        }

                    }
                    textView.setText(positions.toString());
                    MyLocationData.Builder locationBuilder= new MyLocationData.Builder();
                    locationBuilder.longitude(bdLocation.getLongitude());
                    locationBuilder.latitude(bdLocation.getLatitude());
                    MyLocationData locationData = locationBuilder.build();
                    baiduMap.setMyLocationData(locationData);

                    if(isFirstLocate) {
                        LatLng latLng = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                        MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(latLng);
                        baiduMap.animateMapStatus(update);
                        update = MapStatusUpdateFactory.zoomBy(14.5f);
                        baiduMap.animateMapStatus(update);
                        isFirstLocate=false;
                    }


                    //设置自定义定位图标
                    BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_geo);
                    MyLocationConfiguration.LocationMode myLocationMode = MyLocationConfiguration.LocationMode.NORMAL;
                    MyLocationConfiguration configuration = new MyLocationConfiguration(myLocationMode,true,bitmapDescriptor);
                    baiduMap.setMyLocationConfiguration(configuration);  //地图显示定位图标

                }
            });



        }
    }



    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button3:
                //   btn3.setText("1");

                locationClient = new LocationClient(getApplicationContext()); //实例化定位服务器，他的参数是获取上下文

                LocationClientOption option = new LocationClientOption();
                option.setScanSpan(5000);
                option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors); //设定定位方式
                option.setIsNeedAddress(true);

                option.setCoorType("bd09ll"); //可选默认gcj02,设置返回的定位结果坐标系，
                locationClient.setLocOption(option);
                locationClient.registerLocationListener(new MyLocationListener());
                locationClient.start();



                break;
            case R.id.button4:
                // btn4.setText("2");

                baiduMap = mapView.getMap();
                baiduMap.setMyLocationEnabled(true);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
       /* if (requestCode == 1) {
            if (!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(this, "未获得精确定位服务的访问权限", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        if (requestCode == 2) {
            if (!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(this, "未获得粗略定位服务的访问权限", Toast.LENGTH_LONG).show();
                finish();
            }
        }*/



        switch (requestCode)
        {
            case 1:
                if(grantResults.length >0)
                {
                    for(int result :grantResults)
                    {
                        if(result !=PackageManager.PERMISSION_GRANTED)
                        {
                            //如果用户拒绝了权限申请

                            Toast.makeText(this,"小兄弟必须同意权限才能使用本程序",Toast.LENGTH_LONG).show();
                            finish();//关闭程序
                            return;
                        }
                    }
                    break;
                }
        }
    }








    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            //关闭程序是将监听器移除
           // locationManager.removeUpdates(locationListener);
        }

        mapView.onDestroy();
        mapView=null;
        locationClient.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }



    // protected void onDestroy() {
    //   super.onDestroy();
    //    if (locationManager != null) {
    //关闭程序是将监听器移除
    //        locationManager.removeUpdates(locationListener);
    //        mapView.onDestroy();
    //    }
    //  }
}