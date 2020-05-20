package com.my.pandaview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button btn_enter;//首页跳转至视频列表页
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_enter=findViewById(R.id.btn_enter);

        //预留登录界面设计

        //点击按钮后跳转至视频播放页
        btn_enter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, MyRecyclerView.class);
                startActivity(intent);
            }
        });



    }
}
