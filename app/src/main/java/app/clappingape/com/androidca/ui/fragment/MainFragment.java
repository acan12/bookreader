package app.clappingape.com.androidca.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app.clappingape.com.androidca.model.api.response.MultipleResponse;
import app.clappingape.com.androidca.ui.base.BaseFragment;

/**
 * Created by arysuryawan on 8/21/17.
 */

public class MainFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    protected void apiCallbackResponse(MultipleResponse mr) {
        if(mr.getStatus().equals("ok")){
            Toast.makeText(getActivity(), "Status: OK, Size= "+mr.getSources().size(), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getActivity(), "Status: 200, but error", Toast.LENGTH_LONG).show();
        }
    }

}
