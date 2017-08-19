package app.clappingape.com.kokoro.presentation.di.component;


import app.clappingape.com.kokoro.presentation.di.IProgress;
import app.clappingape.com.kokoro.presentation.di.module.AppModule;
import dagger.Component;

/**
 * Created by arysuryawan on 11/20/16.
 */


@Component(modules = AppModule.class)
public interface AppComponent {
    IProgress getProgressStatus();

}
