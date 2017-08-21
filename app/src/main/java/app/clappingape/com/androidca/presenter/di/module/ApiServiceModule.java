package app.clappingape.com.androidca.presenter.di.module;

import android.content.Context;

import javax.inject.Singleton;

import app.clappingape.com.androidca.presenter.di.IApi;
import app.clappingape.com.androidca.presenter.di.IProgress;
import app.clappingape.com.androidca.presenter.di.manager.ApiManager;
import app.clappingape.com.androidca.presenter.di.manager.ProgressManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by arysuryawan on 8/20/17.
 */

@Module
public class ApiServiceModule {

    private final Context context;

    public ApiServiceModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    protected IApi provideApi(){
        return new ApiManager();
    }
}
