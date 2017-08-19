package app.clappingape.com.kokoro;

import android.app.Application;
import android.content.Context;

import app.clappingape.com.kokoro.presentation.di.component.AppComponent;

import app.clappingape.com.kokoro.presentation.di.component.DaggerAppComponent;
import app.clappingape.com.kokoro.presentation.di.module.AppModule;

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
