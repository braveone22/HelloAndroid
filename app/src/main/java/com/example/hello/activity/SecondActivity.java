package com.example.hello.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hello.R;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Button btnCloseSecondActivity = findViewById(R.id.btnCloseSecondActivity);
        btnCloseSecondActivity.setTag(1001);
        btnCloseSecondActivity.setOnClickListener((View.OnClickListener)this);

        Button btnCloseSecond = findViewById(R.id.btnCloseSecond);
        btnCloseSecond.setOnClickListener((View.OnClickListener)this);
        btnCloseSecond.setTag(1002);
    }

    @Override
    public void onClick(View v) {
        int nId = (Integer) v.getId();
        if (R.id.btnCloseSecondActivity == nId){
            finish();
        }
        else if (R.id.btnCloseSecond == nId){
//            Toast.makeText(SecondActivity.this, "你来打我啊", Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(SecondActivity.this, MyCreateActivity.class);
             startActivity(intent);
        }
    }

}
