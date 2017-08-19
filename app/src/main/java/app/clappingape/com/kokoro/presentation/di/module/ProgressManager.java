package app.clappingape.com.kokoro.presentation.di.module;


import app.clappingape.com.kokoro.presentation.di.IProgress;

/**
 * Created by ary on 5/28/17.
 */

public class ProgressManager implements IProgress {
    @Override
    public String showProgressText(String text) {
        return text;
    }
}
