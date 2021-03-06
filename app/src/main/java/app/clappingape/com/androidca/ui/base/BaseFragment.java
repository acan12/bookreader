package app.clappingape.com.androidca.ui.base;

import android.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import app.clappingape.com.androidca.model.api.response.MultipleResponse;
import retrofit2.Call;
import retrofit2.Response;
import app.clappingape.com.androidca.ui.component.DialogComponent;

/**
 * Created by arysuryawan on 8/16/17.
 */

public abstract class BaseFragment extends Fragment {
    protected void onApiResponseCallback(MultipleResponse mr, String keyCallback) {
    }

    protected void onApiFailureCallback(String message) {
        // --- default callback if not defined on child class --
        Toast.makeText(getActivity(), "Error: " + message, Toast.LENGTH_LONG).show();
        Log.e("Message:", message);
    }


    public static void onResponseCallback(Call<MultipleResponse> call, Response response, BaseFragment fm, String keyCallback) {
        DialogComponent.dismissProgressDialog((BaseActivity) fm.getActivity());
        fm.onApiResponseCallback((MultipleResponse) response.body(), keyCallback);
    }

    public static void onFailureCallback(Throwable t, BaseFragment fm) {
        DialogComponent.dismissProgressDialog((BaseActivity) fm.getActivity());
        fm.onApiFailureCallback(t.getMessage());
    }


}
