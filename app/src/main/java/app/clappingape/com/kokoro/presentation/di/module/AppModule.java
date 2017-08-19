package app.clappingape.com.kokoro.presentation.di.module;


import app.clappingape.com.kokoro.App;
import app.clappingape.com.kokoro.presentation.di.IProgress;
import dagger.Module;
import dagger.Provides;

/**
 * Created by arysuryawan on 11/20/16.
 */

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }


    @Provides
    protected IProgress provideProgressStatus() {
        return new ProgressManager();
    }

}
