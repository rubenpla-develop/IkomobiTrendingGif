package com.rubenpla.develop.ikomobitrendinggif.app;

import android.app.Application;
import android.content.Context;

import com.rubenpla.develop.ikomobitrendinggif.BuildConfig;

import dagger2.component.ApplicationComponent;
import dagger2.component.DaggerApplicationComponent;
import dagger2.module.ApplicationModule;

public class IkoApplication extends Application {

    protected ApplicationComponent applicationComponent;

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
        String hockeyappId = "";
        if (BuildConfig.BUILD_TYPE.equals(ReleaseType.DEBUG)) {
            hockeyappId = BuildConfig.GIPHY_API_KEY_DEBUG;
        } else if (BuildConfig.BUILD_TYPE.equals(ReleaseType.RELEASE)) {
            hockeyappId = BuildConfig.GIPHY_API_KEY_RELEASE;
        }
        return hockeyappId;
    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}
