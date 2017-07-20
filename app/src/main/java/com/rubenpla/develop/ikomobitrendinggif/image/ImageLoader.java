package com.rubenpla.develop.ikomobitrendinggif.image;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.rubenpla.develop.ikomobitrendinggif.R;
import com.rubenpla.develop.ikomobitrendinggif.app.IkoApplication;
import com.rubenpla.develop.ikomobitrendinggif.callback.OnGifsRetrievedListener;
import com.rubenpla.develop.ikomobitrendinggif.model.GiphyModel;
import com.rubenpla.develop.ikomobitrendinggif.util.ConnectivityUtil;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class ImageLoader {

    private final String TAG = ImageLoader.class.getSimpleName();

    private GiphyModel giphyData;
    private Activity activity;
    private OnGifsRetrievedListener gifListener;

    public ImageLoader(Activity activity, final OnGifsRetrievedListener gifListener) {
        this.activity = activity;
        this.gifListener = gifListener;
        giphyData = new GiphyModel();

        getGiphy();
    }

    public void getGiphy() {
        final String API_KEY = IkoApplication.getGiphyApiKey();
        final Resources resources = activity.getResources();

        String url = "https://api.giphy.com/v1/gifs/trending?" +
                "api_key=" + API_KEY
                + "&limit=20"
                + "&rating=G";

         if (API_KEY.length() == 0) {
             AlertDialog.Builder builder = new AlertDialog.Builder(activity)
                     .setTitle(resources.getString(R.string.img_loader_dialog_warning_title))
                     .setMessage(resources.getString(R.string.img_loader_dialog_warning_message_body))
                     .setIcon(R.mipmap.ic_launcher_round)
                     .setNeutralButton(resources.getString(R.string.dialog_button_accept),
                             new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                             Toast.makeText(activity.getApplicationContext(),
                                     resources.getString(R.string.img_loader_dialog_warning_toast_message),
                                     Toast.LENGTH_SHORT).show();
                         }
                     })
                     .setCancelable(false);

             builder.show();
             return;
         }

        if (ConnectivityUtil.isNetworkAvailable(activity)) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Log.i(TAG, "Request Failure");
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    try {
                        String jsonData = response.body().string();
                        if (response.isSuccessful()) {
                            giphyData.setGifList(getGif(jsonData));
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    gifListener.setGiphyList(giphyData.getGifList());
                                }
                            });
                        } else {
                            Toast.makeText(activity.getApplicationContext(),
                                    resources.getString(R.string.img_loader_dialog_warning_message_body),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (IOException e) {
                        Log.e(TAG, "Exception Caught: " + e.getMessage());
                    }
                    catch (JSONException e) {
                        Log.e(TAG, "JsonException : " + e.getMessage());
                    }
                }
            });
        } else {
            Toast.makeText(activity.getApplicationContext(),
                    resources.getString(R.string.img_loader_dialog_warning_toast_message),
                    Toast.LENGTH_LONG).show();
        }
    }

    private ArrayList<String> getGif(String jsonData) throws JSONException {
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
}
