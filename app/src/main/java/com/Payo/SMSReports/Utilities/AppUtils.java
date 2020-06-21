package com.Payo.SMSReports.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.Payo.SMSReports.R;

public class AppUtils {

    private static final AppUtils ourInstance = new AppUtils();


    public static AppUtils getInstance() {
        return ourInstance;
    }

    public EditText addTagAlert(Context context, final AppUtils.AlertInterface listener) {
        EditText addTag_et = null;
        android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(context).create();
        alertDialog.setTitle("Add Tag");
        alertDialog.setCancelable(false);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", (dialog, which) -> listener.onPositiveButtonClick());
        View view = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_add_tag, null);
        alertDialog.setView(view);
        addTag_et = view.findViewById(R.id.add_tag);
        alertDialog.show();
        return addTag_et;
    }

    public interface AlertInterface {
        void onPositiveButtonClick();
    }
}
