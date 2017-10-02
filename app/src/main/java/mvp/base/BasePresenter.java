package mvp.base;

public class BasePresenter<V extends MpvView> implements MpvPresenter<V> {

    protected final String TAG = this.getClass().getSimpleName();

    protected V view;

    @Override
    public void onCreate() {

    }

    @Override
    public void onAttach(V view) {
        this.view = view;
    }

    @Override
    public void onFinish() {
        view = null;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public boolean isViewAttached() {
        return (view != null);
    }

    @Override
    public V getPresenterView() {
        return view;
    }
}
