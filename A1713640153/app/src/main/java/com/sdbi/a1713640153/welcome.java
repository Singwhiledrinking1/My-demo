package com.sdbi.a1713640153;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class welcome extends AppCompatActivity {
  private List<Fragment> fragmentList;
private Button button11,button12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        button11=(Button)findViewById(R.id.button11);
        button12=(Button)findViewById(R.id.button12);
        //构造适配器
        List<Fragment> fragments=new ArrayList<Fragment>();
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        fragments.add(new Fragment5());
        FragAdapter adapter=new FragAdapter(getSupportFragmentManager(),fragments);
        ViewPager vp=(ViewPager)findViewById(R.id.viewPager);
        vp.setAdapter(adapter);
        init();
         android.support.v7.app.ActionBar actionBar = getSupportActionBar();
         actionBar.hide();

         gai();

    }
    public  class FragAdapter extends FragmentPagerAdapter{
        public FragAdapter(FragmentManager fm,List<Fragment>fragments){
            super(fm);
            fragmentList=fragments;
        }
        @Override
        public Fragment getItem(int e){
            return fragmentList.get(e);
        }
        @Override
        public int getCount(){
            return fragmentList.size();
        }
    }
    protected  void  init()
    {

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcome.this, MainActivity.class);//当输入用户名和密码之后再能实现程序的跳转 并且后面的记住密码等功能
                startActivity(intent);
            }
        });

        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcome.this, MainActivity.class);//当输入用户名和密码之后再能实现程序的跳转 并且后面的记住密码等功能
                startActivity(intent);
            }
        });

    }


    protected  void gai()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }



}
