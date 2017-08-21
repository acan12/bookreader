package app.clappingape.com.androidca.presenter.di.manager;


import app.clappingape.com.androidca.presenter.di.IProgress;

/**
 * Created by ary on 5/28/17.
 */

public class ProgressManager implements IProgress {
    @Override
    public String showProgressText(String text) {
        return text;
    }
}
