package app.clappingape.com.androidca;

import android.app.Application;
import android.content.Context;

import app.clappingape.com.androidca.presenter.di.component.AppComponent;
import app.clappingape.com.androidca.presenter.di.component.DaggerAppComponent;
import app.clappingape.com.androidca.presenter.di.module.ApiServiceModule;
import app.clappingape.com.androidca.presenter.di.module.AppModule;

/**
 * Created by arysuryawan on 8/17/17.
 */

public class App extends Application {
    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule(this))
                .build();
    }

    public static AppComponent getComponent(Context context) {
        return ((App) context.getApplicationContext()).component;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
