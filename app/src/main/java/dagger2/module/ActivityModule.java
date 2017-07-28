package dagger2.module;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rubenpla.develop.ikomobitrendinggif.callback.OnGifsRetrievedListener;
import com.rubenpla.develop.ikomobitrendinggif.image.ImageLoader;
import com.rubenpla.develop.ikomobitrendinggif.model.GiphyModel;
import com.rubenpla.develop.ikomobitrendinggif.util.Utils;

import dagger.Module;
import dagger.Provides;
import dagger2.annotation.ActivityContext;
import mvp.presenter.MainActivityPresenter;

@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(final Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() { return this.activity; }

    @Provides
    Activity provideActivity() { return this.activity; }

    @Provides
    RecyclerView.LayoutManager provideLayoutManager() { return  new GridLayoutManager(activity, 2);}

    @Provides
    MainActivityPresenter provideMainActivityPresenter() {
        return new MainActivityPresenter(new GiphyModel(), new Utils());
    }
}
