package dagger2.module;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rubenpla.develop.ikomobitrendinggif.image.ImageLoader;
import com.rubenpla.develop.ikomobitrendinggif.model.GiphyModel;

import dagger.Module;
import dagger.Provides;
import dagger2.annotation.ActivityContext;

@Module
public class ActivityModule {

    private final Activity activity;
    private GiphyModel giphyModel;

    public ActivityModule(final Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() { return this.activity; }

    @Provides
    GiphyModel provideGihpyModel() { return new GiphyModel(); }

    @Provides
    Activity provideActivity() { return this.activity; }

    @Provides
    RecyclerView.LayoutManager provideLayoutManager() { return  new GridLayoutManager(activity, 2);}

    @Provides

    ImageLoader provideImageLoaderController() { return new ImageLoader(activity, new GiphyModel()); }
}
