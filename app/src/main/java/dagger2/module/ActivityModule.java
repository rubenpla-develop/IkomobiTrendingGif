package dagger2.module;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rubenpla.develop.ikomobitrendinggif.callback.OnGifsRetrievedListener;
import com.rubenpla.develop.ikomobitrendinggif.image.ImageLoader;

import dagger.Module;
import dagger.Provides;
import dagger2.annotation.ActivityContext;

@Module
public class ActivityModule {

    private final Activity activity;
    private OnGifsRetrievedListener listener;

    public ActivityModule(final Activity activity, OnGifsRetrievedListener onGifsRetrievedListener) {
        this.activity = activity;
        this.listener = onGifsRetrievedListener;
    }

    @Provides
    @ActivityContext
    Context provideContext() { return this.activity; }

    @Provides
    Activity provideActivity() { return this.activity; }

    @Provides
    RecyclerView.LayoutManager provideLayoutManager() { return  new GridLayoutManager(activity, 2);}

    @Provides
    ImageLoader provideImageLoaderController() { return new ImageLoader(activity, listener); }
}
