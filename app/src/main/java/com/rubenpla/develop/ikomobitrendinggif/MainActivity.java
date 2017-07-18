package com.rubenpla.develop.ikomobitrendinggif;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.rubenpla.develop.ikomobitrendinggif.adapter.GifGalleryAdapter;
import com.rubenpla.develop.ikomobitrendinggif.app.IkoApplication;
import com.rubenpla.develop.ikomobitrendinggif.callback.OnGifsRetrievedListener;
import com.rubenpla.develop.ikomobitrendinggif.image.ImageLoader;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger2.component.ActivityComponent;
import dagger2.component.DaggerActivityComponent;
import dagger2.module.ActivityModule;

public class MainActivity extends AppCompatActivity implements OnGifsRetrievedListener {


    private ActivityComponent activityComponent;

    @Inject ImageLoader imageLoader;
    @Inject RecyclerView.LayoutManager layoutManager;

    @BindView(R.id.rv_images)
    RecyclerView recyclerView;

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this, this))
                    .applicationComponent(IkoApplication.get(this).getComponent())
                    .build();
        }

        return activityComponent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        imageLoader.getGiphy();
    }

    @Override
    public void setGiphyList(List<String> urlGifList) {
        if (urlGifList != null && urlGifList.size() > 0) {
            GifGalleryAdapter adapter = new GifGalleryAdapter(this, urlGifList);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
