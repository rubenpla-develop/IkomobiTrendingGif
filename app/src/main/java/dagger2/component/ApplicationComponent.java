package dagger2.component;

import android.app.Application;
import android.content.Context;

import com.rubenpla.develop.ikomobitrendinggif.app.IkoApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger2.annotation.ApplicationContext;
import dagger2.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(IkoApplication application);

    @ApplicationContext
    Context getContext();

    Application getApplication();
}
