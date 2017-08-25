package app.clappingape.com.androidca.presenter.di.manager;


import android.content.Context;

import app.clappingape.com.androidca.presenter.di.IProgress;
import app.clappingape.com.androidca.ui.component.DialogComponent;

/**
 * Created by ary on 5/28/17.
 */

public class ProgressManager implements IProgress {
    @Override
    public void showProgressDialog(Context context) {
        DialogComponent.showProgressDialog(context);
    }
}
