package mvp.base;

/**
 * Created by alten on 26/7/17.
 */

public interface MpvPresenter<V extends MpvView> {
    void onCreate();
    void onAttach(V view);
    void onFinish();
    void onStart();
    void onStop();
    void onPause();
    boolean isViewAttached();
    V getPresenterView();

}
