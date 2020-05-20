package com.my.pandaview;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<VideoInfo> mDataset;

    //Provide a reference to the views for each data item
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public VideoView videoView;
        public TextView nickname;
        public TextView descriptiom;

        public MyViewHolder(View v){
            super(v);
            videoView=v.findViewById(R.id.videoView);
            nickname=v.findViewById(R.id.nickname);
            descriptiom=v.findViewById(R.id.description);
        }
    }

    public void setData(List<VideoInfo> myDataset) { mDataset = myDataset; }

    //Create new views (invoked by the layout maneger)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_item, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //get element from my dataset at this position
        //replace the contents of the view with that element
        holder.nickname.setText(mDataset.get(position).nickname);
        holder.descriptiom.setText(mDataset.get(position).description);
        holder.videoView.setVideoPath(mDataset.get(position).feedurl);
        holder.videoView.start();

    }

    // Return the size of your dataset ( invoked by the layout manager)
    @Override
    public int getItemCount() { return mDataset == null ? 0 : mDataset.size();}
}
