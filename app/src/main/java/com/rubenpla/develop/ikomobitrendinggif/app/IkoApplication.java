package com.rubenpla.develop.ikomobitrendinggif.app;

import android.app.Application;

import com.rubenpla.develop.ikomobitrendinggif.BuildConfig;

/**
 * Created by alten on 18/7/17.
 */

public class IkoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
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
}
