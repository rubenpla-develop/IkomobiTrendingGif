package di.component;

import com.rubenpla.develop.ikomobitrendinggif.MainActivity;

import dagger.Component;
import di.annotation.PerActivity;
import di.module.ActivityModule;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
