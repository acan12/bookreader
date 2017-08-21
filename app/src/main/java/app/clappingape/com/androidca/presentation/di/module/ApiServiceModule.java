package app.clappingape.com.androidca.presentation.di.module;

import android.content.Context;

import javax.inject.Singleton;

import app.clappingape.com.androidca.presentation.di.IApi;
import app.clappingape.com.androidca.presentation.di.manager.ApiManager;
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
