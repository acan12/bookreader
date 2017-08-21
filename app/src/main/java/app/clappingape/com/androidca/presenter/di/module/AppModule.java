package app.clappingape.com.androidca.presenter.di.module;


import app.clappingape.com.androidca.App;
import app.clappingape.com.androidca.presenter.di.IProgress;
import app.clappingape.com.androidca.presenter.di.manager.ProgressManager;
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
