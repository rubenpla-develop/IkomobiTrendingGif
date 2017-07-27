package mvp.base;

import android.content.Context;
import android.support.annotation.StringRes;

/**
 * Created by alten on 26/7/17.
 */

public interface MpvView {
    Context getViewContext();
    void showErrorDialog(@StringRes int titleMessage, @StringRes int bodyMessage);
    void showToastMessage(@StringRes int message);


}
