package di.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import di.annotation.ApplicationContext;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }
}
