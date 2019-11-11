package com.sdbi.a1713640153;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private Button button2;
private EditText editText;
private EditText editText2;
private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button2 = (Button)findViewById(R.id.button2);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        checkBox = (CheckBox)findViewById(R.id.checkBox);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
      init();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String str = editText.getText().toString().trim();
                String ste2 = editText2.getText().toString().trim();
                if (str.isEmpty()) {
                    Toast.makeText(MainActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                } else if (ste2.isEmpty()) {
                    Toast.makeText(MainActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }
                else if (ste2!=null) {

                    Intent intent = new Intent(MainActivity.this,main.class);//当输入用户名和密码之后再能实现程序的跳转 并且后面的记住密码等功能
                    startActivity(intent);


                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    SharedPreferences.Editor editor = sp.edit();//获取sp对象的编辑器
                    editor.putString("name", editText.getText().toString());//建值对的方式录入信息
                    editor.putString("pwd", editText2.getText().toString());

                    editor.commit();

                    editor.commit();
                    editText.setText("");
                    editText2.setText("");
                    if (checkBox.isChecked()) {
                        Toast.makeText(MainActivity.this, "记住密码", Toast.LENGTH_SHORT).show();
                        editText.setText(sp.getString("name", ""));
                        editText2.setText(sp.getString("pwd", "")); //回填功能  记住密码
                    }
                }
            }
        });
    }
    protected void init()
    {

    }

}

