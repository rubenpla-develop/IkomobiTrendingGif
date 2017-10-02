package com.rubenpla.develop.ikomobitrendinggif.app;

import android.app.Application;
import android.content.Context;

import com.rubenpla.develop.ikomobitrendinggif.BuildConfig;

import di.component.ApplicationComponent;
import di.component.DaggerApplicationComponent;
import di.module.ApplicationModule;

public class IkoApplication extends Application {

    protected ApplicationComponent applicationComponent;
    private final String API_KEY = IkoApplication.getGiphyApiKey();

    public static IkoApplication get(Context context) {
        return (IkoApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public static String getGiphyApiKey() {
        String giphyId = "";
        if (BuildConfig.BUILD_TYPE.equals(ReleaseType.DEBUG)) {
            giphyId = BuildConfig.GIPHY_API_KEY_DEBUG;
        } else if (BuildConfig.BUILD_TYPE.equals(ReleaseType.RELEASE)) {
            giphyId = BuildConfig.GIPHY_API_KEY_RELEASE;
        }
        return giphyId;
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
