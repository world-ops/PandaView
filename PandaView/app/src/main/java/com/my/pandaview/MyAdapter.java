package com.my.pandaview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<VideoInfo> mDataset;
    private Context mContext;
    private static final int UPDATE_UI = 1;
    private int oldProgress = 0;

    public MyAdapter(Context context){
        mContext = context;
    }



    //Provide a reference to the views for each data item
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public myVideoView videoView;
        public TextView nickname;
        public TextView descriptiom;
        public ImageButton btn_play;
        public Button btn_pause;
        public ImageView vedioViewHead;


        public MyViewHolder(View v){
            super(v);
            videoView=v.findViewById(R.id.videoView);
            nickname=v.findViewById(R.id.nickname);
            descriptiom=v.findViewById(R.id.description);
            btn_pause=v.findViewById(R.id.btn_pause);
            btn_play=v.findViewById(R.id.btn_play);
            vedioViewHead=v.findViewById(R.id.videoViewHead);

        }
    }

    // get data
    public void setData(List<VideoInfo> myDataset) { mDataset = myDataset; }

    //Create new views (invoked by the layout maneger)
@Override
public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.my_item, parent, false);
    final MyViewHolder myViewHolder = new MyViewHolder(v);
    return myViewHolder;
}

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //get element from my dataset at this position
        //replace the contents of the view with that element
        holder.nickname.setText(mDataset.get(position).nickname);
        holder.descriptiom.setText(mDataset.get(position).description);
        holder.videoView.setVideoPath(mDataset.get(position).feedurl);
        holder.videoView.setMediaController(new MediaController(mContext));


//        final Target<Bitmap> into = Glide.with(mContext)
//                .load(mDataset.get(position).feedurl)
//                .asBitmap()
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(holder.vedioViewHead);

        Glide.with(mContext).load(mDataset.get(position).avatar).into(holder.vedioViewHead);

        //设置点击事件，OnClickListener不好用
        holder.videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (holder.videoView.isPlaying()){
                    holder.videoView.pause();
                    //holder.btn_play.setVisibility(View.VISIBLE);
                }else {
                    holder.videoView.start();
                    holder.vedioViewHead.setVisibility(View.GONE);
                    //holder.btn_play.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });

        /*
        holder.btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.videoView.isPlaying()){
                    holder.videoView.pause();
                    //holder.btn_play.setVisibility(View.VISIBLE);
                }else {
                    holder.videoView.start();
                    //holder.btn_play.setVisibility(View.INVISIBLE);
                }
            }
        });

         */





        //holder.videoView.start();

    }



    // Return the size of your dataset ( invoked by the layout manager)
    @Override
    public int getItemCount() { return mDataset == null ? 0 : mDataset.size();}
/*
    public static void loadVideoScreenshot(final Context context, String uri, ImageView imageView, long frameTimeMicros) {

        RequestOptions requestOptions = RequestOptions.frameOf(frameTimeMicros);
        requestOptions.set(FRAME_OPTION, MediaMetadataRetriever.OPTION_CLOSEST);
        requestOptions.transform(new BitmapTransformation() {
            @Override
            protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
                return toTransform;
            }
            @Override
            public void updateDiskCacheKey(MessageDigest messageDigest) {
                try {
                    messageDigest.update((context.getPackageName() + "RotateTransform").getBytes("utf-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Glide.with(context).load(uri).apply(requestOptions).into(imageView);
    }
*/
}

