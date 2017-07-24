package com.rubenpla.develop.ikomobitrendinggif;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rubenpla.develop.ikomobitrendinggif.adapter.GifGalleryAdapter;
import com.rubenpla.develop.ikomobitrendinggif.callback.OnGifsRetrievedListener;
import com.rubenpla.develop.ikomobitrendinggif.image.ImageLoader;
import com.rubenpla.develop.ikomobitrendinggif.model.GiphyModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnGifsRetrievedListener,
        SwipeRefreshLayout.OnRefreshListener {

    private final int COLUMNS_COUNT = 2;

    @BindView(R.id.rv_images)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh_gif_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ImageLoader gifLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, COLUMNS_COUNT);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        gifLoader = new ImageLoader(this, new GiphyModel());
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void setGiphyList(List<String> urlGifList) {
        if (urlGifList != null && urlGifList.size() > 0) {
            GifGalleryAdapter adapter = new GifGalleryAdapter(this, urlGifList);
            recyclerView.setAdapter(adapter);
        }

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onRefresh() {
        if (gifLoader != null) {
            gifLoader.getGiphy();
        }
    }
}
