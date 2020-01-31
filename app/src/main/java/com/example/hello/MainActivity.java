package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.ConversationActions;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hello.activity.ImageActivity;
import com.example.hello.activity.ManualActivity;
import com.example.hello.activity.RecyclerActivity;
import com.example.hello.activity.SecondActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import kotlin.jvm.internal.TypeReference;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private final OkHttpClient client;

    public MainActivity() {
        //创建okhttpclient对象
        client = new OkHttpClient();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivity", "MainActivity::onCreate Debug start");
        Log.i("MainActivity", "MainActivity::onCreate Info start");
        Log.e("MainActivity", "MainActivity::onCreate Error start");
        Log.w("MainActivity", "MainActivity::onCreate Warn start");

        final View viewById = findViewById(R.id.btn_Get);

        TextView tv = findViewById(R.id.textView);
        tv.setText("琳琳是傻子");

        Button btn = findViewById(R.id.bt);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "可以啊小伙子", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, ManualActivity.class);
                startActivity(intent);
            }
        });

        Button btnOpenActivity = findViewById(R.id.btnOpenActivity);
        btnOpenActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建意图  打开SecondActivity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                //开启意图的activity
                startActivity(intent);
            }
        });

        Button btn_Login = findViewById(R.id.btn_Login);
        final EditText et_username = findViewById(R.id.et_username);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入的用户名
                String strUserName = et_username.getText().toString().trim();
                if (TextUtils.isEmpty(strUserName)) {
                    Toast.makeText(MainActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Toast.makeText(MainActivity.this, "您输入用户名为 " + strUserName, Toast.LENGTH_SHORT).show();
                }
            }
        });


        View btn_Get = findViewById(R.id.btn_Get);
        btn_Get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Request requst = new Request.Builder()
                                .url("https://www.baidu.com").build();
                        Call call = client.newCall(requst);
                        try {
                            Response response = call.execute();
                            if (response.isSuccessful()) {
                                Log.d("MainActivity", response.body().string());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });



        View btn_GetEnqueue = findViewById(R.id.btn_GetEnqueue);
        btn_GetEnqueue.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                GetEnqueue();
            }
        });
    }

    /**
     * 此时在非ui线程，需要在ui线程设置返回结果到界面
     */
    public void GetEnqueue() {
        final Request request = new Request.Builder().url("https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_9491955703412149112%22%7D&n_type=0&p_from=1")
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            private String strRes;

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                strRes = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("MainActivity_GetEnqueue", strRes);
                    }
                });
            }
        });
    }

    /**
     *  发送一个post请求
     * @
     */
    public void PostTest() throws IOException {
        //构建post的body
        final FormBody formBody = new FormBody.Builder().add("username", "zhangjinfu")
                .add("password", "halou").build();
        //构建请求
        Request request = new Request.Builder().post(formBody).url("http://baidu.com").build();

        //构建调用者
        Call call = client.newCall(request);
        try {
            String strRes = call.execute().body().string();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void ShowImage(View view) {
        Intent intent = new Intent(MainActivity.this, ImageActivity.class);
        startActivity(intent);
    }

    public void ShowRecyclerView(View view) {
        Intent intent = new Intent(this, RecyclerActivity.class);
        startActivity(intent);

    }
}
