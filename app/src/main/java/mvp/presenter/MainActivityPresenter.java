package mvp.presenter;

import android.util.Log;

import com.rubenpla.develop.ikomobitrendinggif.R;
import com.rubenpla.develop.ikomobitrendinggif.util.Utils;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;

import java.io.IOException;

import mvp.base.BasePresenter;
import mvp.model.GiphyModel;
import mvp.view.MainActivityView;

public class MainActivityPresenter extends BasePresenter implements MainActivityMpvPresenter {

    private int num_results = 35;
    private GiphyModel giphyData;
    private Utils utils;
    private String key;

    public MainActivityPresenter(GiphyModel giphyData, Utils utils) {
        this.giphyData = giphyData;
        this.utils = utils;
    }

    @Override
    public void getViewList(String key) {
        this.key = key;
        getGiphy();
    }

    public void getGiphy() {
        String url = "https://api.giphy.com/v1/gifs/trending?" +
                "api_key=" + key
                + "&limit=" + num_results
                + "&rating=G";

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
                            giphyData.setGifList(utils.getUrlGifList(jsonData));
                            ((MainActivityView) view).loadGifList(giphyData.getGifList());
                        } else {
                            view.showToastMessage(R.string.img_loader_dialog_warning_message_body);
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
    }
}
