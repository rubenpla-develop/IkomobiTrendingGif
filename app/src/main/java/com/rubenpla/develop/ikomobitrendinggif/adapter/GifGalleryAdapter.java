package com.rubenpla.develop.ikomobitrendinggif.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
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
        ImageView gifImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindGif(int position) {
            Glide.with(context)
                    .load(trendingGifsList.get(position))
                    .thumbnail(0.1f)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.drawable.ic_cloud_off_red)
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(new GlideDrawableImageViewTarget(gifImageView) {
                        @Override
                        public void onResourceReady(GlideDrawable resource,
                                                    GlideAnimation<? super GlideDrawable> animation) {
                            super.onResourceReady(resource, animation);
                        }
                    });
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "Awesome Gif!", Toast.LENGTH_SHORT).show();
        }
    }
}
