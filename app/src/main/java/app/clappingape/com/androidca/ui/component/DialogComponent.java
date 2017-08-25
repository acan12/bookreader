package app.clappingape.com.androidca.ui.component;


import android.app.ProgressDialog;
import android.content.Context;

import app.clappingape.com.androidca.ui.base.BaseActivity;


/**
 * Created by arysuryawan on 8/19/17.
 */

public class DialogComponent {
    private static ProgressDialog dialog;

    synchronized public static ProgressDialog showProgressDialog(Context context) {
        if (dialog == null) {
            dialog = new ProgressDialog(context);
            dialog.setMessage("...Loading");
            dialog.show();
        }

        return dialog;
    }

    public static void dismissProgressDialog(BaseActivity ac) {
        if (!ac.isFinishing()) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
