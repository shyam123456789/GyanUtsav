package com.gyanutsav.gyan.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.Utils.PrefrenshesManager;
import com.gyanutsav.gyan.ui.models.MainModel;
import com.gyanutsav.gyan.ui.otp.OtpEditText;
import com.gyanutsav.gyan.ui.server.Api;
import com.google.gson.Gson;

import androidx.core.content.ContextCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.gyanutsav.gyan.ui.activities.EnterOtpActivity.INTENT_PHONENUMBER;


public class VerificationActivity extends BaseActiviy {
    private static final String TAG = "VerificationActivity";
    private static final int OTP_LNGTH = 4;
    TextView resend_timer;
    private OtpEditText mOtpEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        resend_timer = (TextView) findViewById(R.id.resend_timer);
        resend_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResendCode();
            }
        });
        startTimer();
        mOtpEditText = findViewById(R.id.inputCode);
        mOtpEditText.setMaxLength(OTP_LNGTH);
        enableInputField(true);
    }


    public void ResendCode() {
        startTimer();
        if (getIntent() != null && getIntent().getStringExtra(INTENT_PHONENUMBER) != null) {
            String mobile = getIntent().getStringExtra(INTENT_PHONENUMBER);
            getDialog().show();
            Call<MainModel> call = Api.getClient().Resendotp(mobile);
            call.enqueue(new Callback<MainModel>() {
                @Override
                public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                    getDialog().dismiss();
                }

                @Override
                public void onFailure(Call<MainModel> call, Throwable t) {
                    getDialog().dismiss();
                    Toast.makeText(VerificationActivity.this, "Please try again!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Please try Again!", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    public void Login(View view) {
        startActivity(new Intent(this, LogInActivity.class));
    }

    public void onSubmitClicked(View view) {
        String code = mOtpEditText.getText().toString();
        if (!code.isEmpty()) {
            hideKeypad();
            varifyOtp(code);
          /*  Intent intent = new Intent(VerificationActivity.this, SignUpActivity.class);
            intent.putExtra(INTENT_PHONENUMBER, "7067144471");
            startActivity(intent);
            finish();*/
        }

    }

    private void varifyOtp(String code) {
        if (getIntent() != null && getIntent().getStringExtra(INTENT_PHONENUMBER) != null) {
            String mobile = getIntent().getStringExtra(INTENT_PHONENUMBER);
            getDialog().show();
            Call<MainModel> call = Api.getClient().user_otp(mobile, code);
            call.enqueue(new Callback<MainModel>() {
                @Override
                public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                    if (response.body() != null && response.body().getStatus()) {
                        if (response.body().getType() == 1) {
                            PrefrenshesManager.saveUserProfile(response.body().getUserProfile().get(0));
                            Intent intent = new Intent(VerificationActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            PrefrenshesManager.saveUserProfile(response.body().getUserProfile().get(0));
                            Intent intent = new Intent(VerificationActivity.this, SignUpActivity.class);
                            intent.putExtra(INTENT_PHONENUMBER, mobile);
                            startActivity(intent);
                            finish();

                        }

                    }
                    getDialog().dismiss();
                }

                @Override
                public void onFailure(Call<MainModel> call, Throwable t) {
                    getDialog().dismiss();

                    Toast.makeText(VerificationActivity.this, "Please try again!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Please try Again!", Toast.LENGTH_SHORT).show();
            return;
        }
    }


    void enableInputField(final boolean enable) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View container = findViewById(R.id.inputContainer);
                if (enable) {
                    container.setVisibility(View.VISIBLE);
                    mOtpEditText.requestFocus();
                } else {
                    container.setVisibility(View.GONE);
                }
                TextView resend_timer = (TextView) findViewById(R.id.resend_timer);
                resend_timer.setClickable(false);
            }
        });

    }


    private void startTimer() {
        resend_timer.setClickable(false);
        resend_timer.setTextColor(ContextCompat.getColor(VerificationActivity.this, R.color.white));
        new CountDownTimer(30000, 1000) {
            int secondsLeft = 0;

            public void onTick(long ms) {
                if (Math.round((float) ms / 1000.0f) != secondsLeft) {
                    secondsLeft = Math.round((float) ms / 1000.0f);
                    resend_timer.setText("Resend  ( " + secondsLeft + " )");
                }
            }

            public void onFinish() {
                resend_timer.setClickable(true);
                resend_timer.setText("Resend ");
                resend_timer.setTextColor(ContextCompat.getColor(VerificationActivity.this, R.color.white));
            }
        }.start();
    }

    private void hideKeypad() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //    SendOTP.getInstance().getTrigger().stop();
    }
}
