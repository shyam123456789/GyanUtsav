package com.gyanutsav.gyan.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.models.MainModel;
import com.gyanutsav.gyan.ui.server.Api;
import com.google.gson.Gson;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterOtpActivity extends BaseActiviy {

    public static final String INTENT_PHONENUMBER = "phonenumber";
    public static final String INTENT_COUNTRY_CODE = "code";

    public static final int FORGOTPASSWORD = 1218;

    private EditText mPhoneNumber;
    private TextView mSmsButton;
    private String mCountryIso;
    private TextWatcher mNumberTextWatcher;
    private boolean forgotpassord = false;

    AppCompatTextView tv_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_new);
        mPhoneNumber = findViewById(R.id.phoneNumber);
        mSmsButton = findViewById(R.id.smsVerificationButton);
        tv_title = findViewById(R.id.tv_title);

        tv_title.setText("Signup");

        setButtonsEnabled(true);
    }


    private void setButtonsEnabled(boolean enabled) {
        mSmsButton.setEnabled(enabled);
    }

    public void onButtonClicked(View view) {
        String phoneno = mPhoneNumber.getText().toString().trim();
        if (phoneno != null && !phoneno.isEmpty() && phoneno.length() > 9) {
            if (forgotpassord) {
                forgotpassword(phoneno);
            } else {
                sendtoserver(phoneno);
            }

        } else {
            mPhoneNumber.setError("Please Enter Mobile Number.");
        }

    }

    private void sendtoserver(String phoneno) {
        getDialog().show();
        Call<MainModel> modelCall = Api.getClient().userregistration(phoneno);
        modelCall.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                Log.e("TAG", ">>>  " + new Gson().toJson(response.body()));
                if (response.body() != null && response.body().getStatus()) {
                    Intent verification = new Intent(EnterOtpActivity.this, VerificationActivity.class);
                    verification.putExtra(INTENT_PHONENUMBER, phoneno);
                    startActivity(verification);
                    finish();
                } else if (response.body()!=null){
                    Toast.makeText(EnterOtpActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                getDialog().dismiss();
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                getDialog().dismiss();
            }
        });
    }

    private void forgotpassword(String phoneno) {
        getDialog().show();
        Call<MainModel> modelCall = Api.getClient().Forgotpassword(phoneno);
        modelCall.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                Log.e("TAG", ">>>  " + new Gson().toJson(response.body()));
                if (response.body() != null && response.body().getStatus()) {
                    Intent verification = new Intent(EnterOtpActivity.this, VerificationActivity.class);
                    verification.putExtra(INTENT_PHONENUMBER, phoneno);
                    startActivity(verification);
                    finish();
                } else if (response.body() != null && response.body().getType() == 3) {
                    Toast.makeText(EnterOtpActivity.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    startActivityForResult(new Intent(EnterOtpActivity.this, LogInActivity.class), FORGOTPASSWORD);

                }
                getDialog().dismiss();
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                getDialog().dismiss();
            }
        });
    }

    public void Login(View view) {
        startActivityForResult(new Intent(this, LogInActivity.class), FORGOTPASSWORD);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("TAG", ">>>requestCode   " + requestCode);
        if (data != null && requestCode == FORGOTPASSWORD) {
            if (data.getBooleanExtra("FORGOT", false)) {
                forgotpassord = true;
                tv_title.setText("Forget Password");
            } else {
                forgotpassord = false;
                tv_title.setText("Signup");
            }
        }
    }
}
