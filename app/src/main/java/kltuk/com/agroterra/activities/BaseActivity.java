package kltuk.com.agroterra.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.arellomobile.mvp.MvpAppCompatActivity;

import kltuk.com.agroterra.R;

public class BaseActivity extends MvpAppCompatActivity {

    public void showLoader() {
        if (loader != null) {
            loader.dismiss();
        }
        loader = ProgressDialog.show(this, getString(R.string.loading), getString(R.string.please_wait));
    }

    public void hideLoader() {
        if (loader != null) {
            loader.dismiss();
        }
    }

    public void showMessage(int title, int message, final DialogInterface.OnClickListener onClickListenerOk) {

        String titleStr = getString(title);
        String messageStr = getString(message);

        showMessage(titleStr, messageStr, onClickListenerOk);

    }

    public void showMessage(String title, String message, final DialogInterface.OnClickListener onClickListenerOk) {

        if (alertDialog != null) {
            alertDialog.dismiss();
        }

        alertDialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    if (onClickListenerOk != null) {
                        onClickListenerOk.onClick(dialogInterface, i);
                    }
                })
                .setNegativeButton(R.string.cancel, (dialogInterface, i) -> dialogInterface.dismiss())
                .show();

    }

    public void showSuccess(int title, int message) {

        if (alertDialog != null) {
            alertDialog.dismiss();
        }

        alertDialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> dialogInterface.dismiss())
                .show();

    }

    private AlertDialog alertDialog;

    private ProgressDialog loader;

}
