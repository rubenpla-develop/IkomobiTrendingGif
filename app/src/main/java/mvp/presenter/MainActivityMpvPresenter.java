package mvp.presenter;

import android.app.Activity;

import mvp.base.MpvPresenter;

/**
 * Created by alten on 27/7/17.
 */

public interface MainActivityMpvPresenter extends MpvPresenter {
    void getViewList(Activity context);
}
