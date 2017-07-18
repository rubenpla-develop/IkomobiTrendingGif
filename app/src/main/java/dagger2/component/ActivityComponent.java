package dagger2.component;

import com.rubenpla.develop.ikomobitrendinggif.MainActivity;

import dagger.Component;
import dagger2.annotation.PerActivity;
import dagger2.module.ActivityModule;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
