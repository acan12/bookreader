package app.clappingape.com.androidca.ui.base;

import android.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import app.clappingape.com.androidca.model.api.response.MultipleResponse;
import retrofit2.Call;
import retrofit2.Response;
import support.component.DialogComponent;

/**
 * Created by arysuryawan on 8/16/17.
 */

public abstract class BaseFragment extends Fragment {
    protected void onApiCallbackResponse(MultipleResponse mr) {
    }

    protected void onApiCallbackFailure(String message) {
        // --- default callback if not defined on child class --
        Toast.makeText(getActivity(), "Error: " + message, Toast.LENGTH_LONG).show();
        Log.e("Message:", message);
    }


    public static void onCallbackResponse(Call<MultipleResponse> call, Response response, BaseFragment fm) {
        DialogComponent.dismissProgressDialog((BaseActivity) fm.getActivity());
        fm.onApiCallbackResponse((MultipleResponse) response.body());
    }

    public static void onCallbackFailure(Throwable t, BaseFragment fm) {
        DialogComponent.dismissProgressDialog((BaseActivity) fm.getActivity());
        fm.onApiCallbackFailure(t.getMessage());
    }


}
