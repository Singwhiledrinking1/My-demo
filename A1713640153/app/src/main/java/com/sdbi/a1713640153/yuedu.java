package com.sdbi.a1713640153;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class yuedu extends AppCompatActivity {
private Button btn1,btn2;
    private TextView textView,textView4,textView6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuedu);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);

        textView = (TextView)findViewById(R.id.text1);
        textView4 = (TextView)findViewById(R.id.textView20);
        textView6 = (TextView)findViewById(R.id.textView27);


        sa();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {



                    String jsonData = textView6.getText().toString();
                    JSONObject jsonObject = new JSONObject(jsonData);
                    String result = "";
                    String name = jsonObject.optString("name");


                    String sex = jsonObject.optString("sex");
                    String age = jsonObject.optString("age");
                    result = "name="+name+"\n"+"sex"+sex+"\n"+"age"+age;
                    textView4.setText(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            send();
            }
        });
    }

    private  void  send()
    //开启线程来发送请求
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection  = null;
                BufferedReader reader = null;
                try{
                    URL url = new URL("http://10.0.2.2:8888/user.txt");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    //下面对获取到输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String Line;
                    while ((Line = reader.readLine())!=null)
                    {
                        response.append(Line);
                    }

                    showResponse(response.toString());
                }catch (Exception e)
                {
                    e.printStackTrace();
                }finally {
                    if(reader !=null)
                    {
                        try{
                            reader.close();
                        }catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    if(connection !=null)
                    {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }


    private void  sa()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //利用消息传递信息
                try {

                    OkHttpClient okClient = new OkHttpClient();
                    Request.Builder builder = new Request.Builder();  //如果想要发起一条http请求，就需要创建一个Request对像
                    //   builder.url("http://10.0.2.2:8888/user.txt");
                    builder.url("http://10.0.2.2:8888/user3.json");
                    //builder.url("http://10.11.135.99:8081/user2.xml");
                    Request request = builder.build();


                    //采用post方式发起一条请求，并向web服务器传递数据
                         /*   Request.Builder builder=new Request.Builder();
                            builder.url("http://10.0.2.2:8888/post.jsp");
                            RequestBody requestBody=new FormBody.Builder()
                                    .add("username","zhangsan")
                                    .add("password","123")
                                    .build();
                            builder.post(requestBody);
                            Request request=builder.build();
                            builder.post(requestBody);*/



                    Response response = okClient.newCall(request).execute();  //之后调用okH的newcall方法，来创建一个call对像，并调用他的excute方法来发送请求并获取服务器的数据
                    String result  = response.body().string();

                    Message message = Message.obtain();
                    message.what = 0x7;
                    message.obj= result;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(response);
            }
        });
    }


    private Handler handler = new Handler()
    {

        @Override
        public void handleMessage(Message msg) {      //通过消息的形式实现ui更新
            super.handleMessage(msg);
            if(msg.what==0x7)
            {
                textView6.setText(msg.obj.toString());
            }
        }
    };
}

