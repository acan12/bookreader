package app.clappingape.com.kokoro.ui.base;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import app.clappingape.com.kokoro.model.api.response.MultipleResponse;
import retrofit2.Call;
import retrofit2.Response;
import support.component.DialogComponent;

/**
 * Created by arysuryawan on 8/16/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected void apiCallbackResponse(MultipleResponse mr) {
    }

    protected void apiCallbackFailure(String message) {
        // --- default callback if not defined on child class --
        Toast.makeText(this, "Error: " + message, Toast.LENGTH_LONG).show();
        Log.e("Message:", message);
    }


    public void onCallbackResponse(Call<MultipleResponse> call, Response response) {
        DialogComponent.dismissProgressDialog(this);
        this.apiCallbackResponse((MultipleResponse) response.body());
    }

    public void onCallbackFailure(Throwable t) {
        DialogComponent.dismissProgressDialog(this);
        this.apiCallbackFailure(t.getMessage());
    }


}
