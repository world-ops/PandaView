package com.my.pandaview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRecyclerView extends AppCompatActivity {

    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MyAdapter myAdapter;
    private List<VideoInfo> mVideoInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycler_view);
        recyclerView = (androidx.recyclerview.widget.RecyclerView)findViewById(R.id.recycler_view);

        //use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        mVideoInfos = new ArrayList<>();
        myAdapter = new MyAdapter(this);

        recyclerView.setAdapter(myAdapter);

        getData();
    }

    private void getData() {
        //创建Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://beiyou.bytedance.com/")//设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create())//设置数据解析器
                .build();

        //创建网络请求接口的实例
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getVideoInfos().enqueue(new Callback<List<VideoInfo>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<List<VideoInfo>> call, Response<List<VideoInfo>> response) {
                if (response.body() != null){
                    List<VideoInfo> videoInfos = response.body();
                    Log.d("retrofit", String.valueOf(videoInfos.size()));
                    if (videoInfos.size() != 0){
                        myAdapter.setData(videoInfos);
                        myAdapter.notifyDataSetChanged();
                    }
                }
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<List<VideoInfo>> call, Throwable t) {
                Log.d("retrofit fail", t.getMessage());
            }
        });
    }
}
