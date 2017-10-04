package com.rubenpla.develop.ikomobitrendinggif.adapter;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.rubenpla.develop.ikomobitrendinggif.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GifGalleryAdapter extends RecyclerView.Adapter<GifGalleryAdapter.MyViewHolder>  {

    private List<String> trendingGifsList;
    private Context context;

    public GifGalleryAdapter(Context context, List<String> trendingGifsList) {
        this.context = context;
        this.trendingGifsList = trendingGifsList;
    }

    public synchronized void setData(Context context, List<String> updatedList) {
        if (updatedList != null & updatedList.size() > 0) {
            this.context = context;
            this.trendingGifsList = updatedList;
        }

        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View gifView = inflater.inflate(R.layout.item_photo, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(gifView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindGif(position);
    }

    @Override
    public int getItemCount() {
        return trendingGifsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_photo)
        SimpleDraweeView gifImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindGif(int position) {
            ControllerListener<ImageInfo> imageInfoControllerListener = new BaseControllerListener<ImageInfo>() {
                @Override
                public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                    super.onFinalImageSet(id, imageInfo, animatable);
                    if (animatable != null) {
                        animatable.start();
                    }
                }
            };

            DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                    .setUri(trendingGifsList.get(position))
                    .setControllerListener(imageInfoControllerListener)
                    .setAutoPlayAnimations(true)
                    .build();

            gifImageView.setController(draweeController);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "Awesome Gif!", Toast.LENGTH_SHORT).show();
        }
    }
}
