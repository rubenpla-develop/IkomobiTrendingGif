package com.rubenpla.develop.ikomobitrendinggif;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.rubenpla.develop.ikomobitrendinggif.adapter.GifGalleryAdapter;
import com.rubenpla.develop.ikomobitrendinggif.model.GiphyModel;
import com.rubenpla.develop.ikomobitrendinggif.util.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mvp.presenter.MainActivityPresenter;
import mvp.view.MainActivityView;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        MainActivityView {

    private final int COLUMNS_COUNT = 2;

    private MainActivityPresenter mainPresenter;
    private GifGalleryAdapter adapter;

    @BindView(R.id.rv_images)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh_gif_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new MainActivityPresenter(new GiphyModel(), new Utils()); //TODO DEPENDENCY
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, COLUMNS_COUNT);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout.setOnRefreshListener(this);
        mainPresenter.onAttach(this);
        mainPresenter.getViewList(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mainPresenter.onFinish();
        finish();
    }

    @Override
    public void onRefresh() {
        mainPresenter.getViewList(this);
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public void loadGifList(final List<String> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (adapter != null) {
                    adapter.setData(getApplicationContext(), list);
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    adapter = new GifGalleryAdapter(getApplicationContext(), list);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }

    @Override
    public void updateGifList(final List<String> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (adapter != null) {
                    adapter.setData(getApplicationContext(), list);
                }

            }
        });
    }

    @Override
    public void showErrorDialog(@StringRes int titleMessage, @StringRes int bodyMessage) {
        final Resources resources = this.getResources();
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(resources.getString(titleMessage))
                .setMessage(resources.getString(bodyMessage))
                .setIcon(R.mipmap.ic_launcher_round)
                .setNeutralButton(resources.getString(R.string.dialog_button_accept),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(),
                                        resources.getString(R.string.img_loader_dialog_warning_toast_message),
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                .setCancelable(false);

        builder.show();
    }

    @Override
    public void showToastMessage(@StringRes int message) {
        Toast.makeText(this, getResources().getString(message), Toast.LENGTH_LONG).show();
    }
}
