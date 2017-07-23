package cc.metapro.nfc;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Boollean on 2017/7/22.
 */

public class MyDialogFragment extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.dialog_simulate,null);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("模拟中..")
                .setPositiveButton(android.R.string.cancel,null)
                .create();
    }
}
