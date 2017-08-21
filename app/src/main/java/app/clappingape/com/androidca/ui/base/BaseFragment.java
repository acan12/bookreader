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
    protected void apiCallbackResponse(MultipleResponse mr) {
    }

    protected void apiCallbackFailure(String message) {
        // --- default callback if not defined on child class --
        Toast.makeText(getActivity(), "Error: " + message, Toast.LENGTH_LONG).show();
        Log.e("Message:", message);
    }


    public void onCallbackResponse(Call<MultipleResponse> call, Response response) {
        DialogComponent.dismissProgressDialog((BaseActivity) getActivity());
        this.apiCallbackResponse((MultipleResponse) response.body());
    }

    public void onCallbackFailure(Throwable t) {
        DialogComponent.dismissProgressDialog((BaseActivity) getActivity());
        this.apiCallbackFailure(t.getMessage());
    }


}
