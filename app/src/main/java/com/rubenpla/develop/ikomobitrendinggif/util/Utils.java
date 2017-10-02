package com.rubenpla.develop.ikomobitrendinggif.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Utils {

    public ArrayList<String> getUrlGifList(String jsonData) throws JSONException {
        JSONObject giphy = new JSONObject(jsonData);
        JSONArray array  = giphy.getJSONArray("data");
        ArrayList<String> gifURLs = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject o = (JSONObject) array.get(i);
            JSONObject imagesList  = (JSONObject) o.get("images");
            JSONObject previewGif  = (JSONObject) imagesList.get("fixed_width_downsampled");

            String url = previewGif.get("url").toString();
            gifURLs.add(url);
        }

        return gifURLs;
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        return isAvailable;
    }
}
