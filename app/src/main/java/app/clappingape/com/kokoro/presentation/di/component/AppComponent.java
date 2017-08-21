package app.clappingape.com.kokoro.presentation.di.component;


import javax.inject.Singleton;

import app.clappingape.com.kokoro.presentation.di.IApi;
import app.clappingape.com.kokoro.presentation.di.IProgress;
import app.clappingape.com.kokoro.presentation.di.module.ApiServiceModule;
import app.clappingape.com.kokoro.presentation.di.module.AppModule;
import dagger.Component;

/**
 * Created by arysuryawan on 11/20/16.
 */

@Singleton
@Component(modules = {AppModule.class, ApiServiceModule.class})
public interface AppComponent {

    IApi getApi();
    IProgress getProgressStatus();

}
