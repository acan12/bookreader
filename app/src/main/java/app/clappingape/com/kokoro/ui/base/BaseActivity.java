package app.clappingape.com.kokoro.ui.base;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import app.clappingape.com.kokoro.model.api.response.MultipleResource;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by arysuryawan on 8/16/17.
 */

public class BaseActivity extends AppCompatActivity {
    protected void apiResponseCallback(MultipleResource mr) {
    }

    protected void apiFailureCallback(String message) {
        Toast.makeText(this, "Error: " + message, Toast.LENGTH_LONG).show();
        Log.e("Message:", message);
    }


    public void onCallbackResponse(Call<MultipleResource> call, Response response) {
        this.apiResponseCallback((MultipleResource) response.body());
    }

    public void onCallbackFailure(Throwable t) {
        this.apiFailureCallback(t.getMessage());
    }


}
