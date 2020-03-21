package com.gyanutsav.gyan.ui.activities;

import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.Utils.PrefrenshesManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActiviy {
    @BindView(R.id.iv_bg)
    AppCompatImageView iv_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        Glide.with(this).load(R.drawable.baba_bg).into(iv_bg);

        new Handler().postDelayed(() -> {
            Log.e("TAG",">>>>"+new Gson().toJson(PrefrenshesManager.getUserProfile()));
            if (PrefrenshesManager.getUserProfile() != null && PrefrenshesManager.getUserProfile().getUserName().isEmpty()) {



                startActivity(new Intent(this, SignUpActivity.class));
                finish();
            } else {



                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        }, 2000);


    }
}
