package com.sdbi.a1713640153;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class main extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager viewpager;
    private RadioGroup radiogroup;
    private TextView txtv_buttom1,txtv_buttom2,txtv_buttom3;
    private List<TextView> txtvs;
    private List<View> views;
    private mynewAdapter pagerAdapter;
    LocalActivityManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        manager = new LocalActivityManager(this , true);
        //重新获取或存储activity状态
        manager.dispatchCreate(savedInstanceState);
        findView();



        init();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }
    protected void init()
    {


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manmenu, menu);
        SearchView searchView = (SearchView)menu.findItem(R.id.item4).getActionView();
        return super.onCreateOptionsMenu(menu);

    }

    //选中菜单
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // 选择添加程序图标按键逻辑
            case android.R.id.home:
                Intent in = new Intent(this, Main2Activity.class);
                // 清空Activity栈中栈顶元素
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(in);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }






    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private void findView(){
        txtvs=new ArrayList<TextView>();
        viewpager=(ViewPager) findViewById(R.id.viewpager);
        viewpager.setOnPageChangeListener(this);
        radiogroup=(RadioGroup) findViewById(R.id.radioGroup);
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                for(int i=0;i<group.getChildCount();i++)
                {
                    if(group.getChildAt(i).getId()==checkedId)
                    {
                        //设定viewpager当前的获取焦点的view
                        viewpager.setCurrentItem(i);
                        whiteLineVisibility(i);
                        break;
                    }
                }
            }

            private void whiteLineVisibility(int position){
                for(int i=0;i<txtvs.size();i++){
                    if(i==position){
                        txtvs.get(i).setVisibility(View.VISIBLE);
                    }else{
                        txtvs.get(i).setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        txtv_buttom1=(TextView) findViewById(R.id.txtv_buttom1);
        txtv_buttom2=(TextView) findViewById(R.id.txtv_buttom2);
        txtv_buttom3=(TextView) findViewById(R.id.txtv_buttom3);
        txtvs.add(txtv_buttom1);
        txtvs.add(txtv_buttom2);
        txtvs.add(txtv_buttom3);
        setPage();
    }


    private void setPage(){
        views=new ArrayList<View>();

        Intent in1 = new Intent(getApplicationContext(),zhuye.class);
        views.add(getView("first", in1));
        Intent in2 = new Intent(getApplicationContext(), Main3Activity.class);
        views.add(getView("second", in2));
        Intent in3 = new Intent(getApplicationContext(), me.class);
        views.add(getView("third", in3));
        pagerAdapter=new mynewAdapter(views);
        viewpager.setAdapter(pagerAdapter);
        viewpager.setCurrentItem(0);
    }

    @SuppressWarnings({ "deprecation", "unused" })
    private View getView(String id, Intent intent) {
        return manager.startActivity(id, intent).getDecorView();
    }
}
