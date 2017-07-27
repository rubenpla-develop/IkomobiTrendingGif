package mvp.view;

import android.support.annotation.NonNull;

import java.util.List;

import mvp.base.MpvView;

/**
 * Created by alten on 26/7/17.
 */

public interface MainActivityView extends MpvView {
    void loadGifList(@NonNull List<String> list);
    void updateGifList(@NonNull List<String> list);
}
