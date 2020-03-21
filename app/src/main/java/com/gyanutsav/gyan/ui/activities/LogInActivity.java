package com.gyanutsav.gyan.ui.activities;

import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.Utils.PrefrenshesManager;
import com.gyanutsav.gyan.ui.models.MainModel;
import com.gyanutsav.gyan.ui.server.Api;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends BaseActiviy {

    @BindView(R.id.et_password)
    AppCompatEditText et_pass;
    @BindView(R.id.et_phone)
    AppCompatEditText et_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);
    }

    public void forgetpassword(View view) {
        //     startActivity(new Intent(this, ForgetActivity.class));
//        Intent intent = new Intent();
        Intent intent = new Intent();
        intent.putExtra("FORGOT", true);
        setResult(1218, intent);
        finish();
    }

    public void signup(View view) {
//        startActivity(new Intent(this, SignUpActivity.class));
        Intent intent = new Intent();
        intent.putExtra("FORGOT", false);
        setResult(1218, intent);
        finish();
    }

    public void login(View view) {
        String pass = et_pass.getText().toString();
        String mobile = et_phone.getText().toString();
        if (!pass.isEmpty() && !mobile.isEmpty()) {
            loginserver(mobile, pass);
        } else {
            Toast.makeText(this, "Please enter mobile and password.", Toast.LENGTH_SHORT).show();
        }

    }

    private void loginserver(String mobile, String pass) {
        getDialog().show();
        Call<MainModel> call = Api.getClient().User_sign(mobile, pass);
        call.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                if (response.body() != null && response.body().getStatus()) {
                    PrefrenshesManager.saveUserProfile(response.body().getUserProfile1().get(0));
                    startActivity(new Intent(LogInActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LogInActivity.this, "Please enter valid mobile and password!", Toast.LENGTH_SHORT).show();
                }
                getDialog().dismiss();
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                Toast.makeText(LogInActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();

            }
        });

    }


}
