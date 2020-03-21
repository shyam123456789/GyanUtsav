package com.gyanutsav.gyan.ui.activities;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.Utils.InternetConnectionDialog;

import static com.gyanutsav.gyan.ui.ApplicationClass.getAppContext;

public class BaseActiviy extends AppCompatActivity {
    InternetConnectionDialog internetConnectionError;
    ProgressDialog dialog;
    private BroadcastReceiver networkreceiver = new InternetConnectionReciever();

    private void iniDialog() {
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
    }

    public ProgressDialog getDialog() {
        if (dialog == null) {
            iniDialog();
        }
        return dialog;

    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        internetConnectionError = new InternetConnectionDialog(this);
        getDialog().setOnShowListener(dialog -> {
            startTimer();
        });
    }

    private void startTimer() {
        try {
            new CountDownTimer(5000, 1000 * 40) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    if (getDialog().isShowing()) {
                        getDialog().dismiss();
                    }
                }
            }.start();

        } catch (Exception r) {
        }
    }


    public void setToolbar(androidx.appcompat.widget.Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }


    public void showBackButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.vc_return);
    }


    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return true;

    }

    /*
     * launch activity withous inent data
     * */
    public void launchActivity(Class classToLaunch) {
        startActivity(new Intent(this, classToLaunch));
    }


    public void loadFragment(Fragment fragment, int containerId) {
        getSupportFragmentManager().beginTransaction().replace(containerId, fragment).commit();
    }

    public void loadFragment(Fragment fragment, int containerId, String stack) {
        getSupportFragmentManager().beginTransaction().replace(containerId, fragment).addToBackStack(stack).commit();

    }


    public void isInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            internetConnectionError.dismiss();
        } else {
            internetConnectionError.show();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (networkreceiver != null)
            unregisterReceiver(networkreceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkreceiver, intentFilter);
    }

    public class InternetConnectionReciever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            final ConnectivityManager connMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            final android.net.NetworkInfo wifi = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            final android.net.NetworkInfo mobile = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            isInternetConnected();
        }


    }

}
