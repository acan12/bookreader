package app.clappingape.com.androidca.ui.base;

import android.content.ComponentCallbacks2;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import app.clappingape.com.androidca.model.api.response.MultipleResponse;
import retrofit2.Call;
import retrofit2.Response;
import support.component.DialogComponent;

/**
 * Created by arysuryawan on 8/16/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements ComponentCallbacks2 {
    protected void apiCallbackResponse(MultipleResponse mr) {
    }

    protected void apiCallbackFailure(String message) {
        // --- default callback if not defined on child class --
        Toast.makeText(this, "Error: " + message, Toast.LENGTH_LONG).show();
        Log.e("Message:", message);
    }


    public static void onCallbackResponse(Call<MultipleResponse> call, Response response, BaseActivity ac) {
        DialogComponent.dismissProgressDialog(ac);
        ac.apiCallbackResponse((MultipleResponse) response.body());
    }

    public static void onCallbackFailure(Throwable t, BaseActivity ac) {
        DialogComponent.dismissProgressDialog(ac);
        ac.apiCallbackFailure(t.getMessage());
    }


    // --- optimize VM, Memory, GC usage ---
    @Override
    public void onTrimMemory(int level) {
        switch (level) {
            case ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN:
                /*
                   Release any UI objects that currently hold memory.

                   The user interface has moved to the background.
                */
                break;
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE:
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW:
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL:
                /*
                   Release any memory that your app doesn't need to run.

                   The device is running low on memory while the app is running.
                   The event raised indicates the severity of the memory-related event.
                   If the event is TRIM_MEMORY_RUNNING_CRITICAL, then the system will
                   begin killing background processes.
                */

                break;

            case ComponentCallbacks2.TRIM_MEMORY_BACKGROUND:
            case ComponentCallbacks2.TRIM_MEMORY_MODERATE:
            case ComponentCallbacks2.TRIM_MEMORY_COMPLETE:
                /*
                   Release as much memory as the process can.

                   The app is on the LRU list and the system is running low on memory.
                   The event raised indicates where the app sits within the LRU list.
                   If the event is TRIM_MEMORY_COMPLETE, the process will be one of
                   the first to be terminated.
                */

                break;

            default:
                /*
                  Release any non-critical data structures.

                  The app received an unrecognized memory level value
                  from the system. Treat this as a generic low-memory message.
                */
                break;
        }
    }

}
