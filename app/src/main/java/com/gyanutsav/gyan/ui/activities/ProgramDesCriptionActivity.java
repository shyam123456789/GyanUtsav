package com.gyanutsav.gyan.ui.activities;

import android.os.Build;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.models.ProgramsModel;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import android.text.Html;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProgramDesCriptionActivity extends BaseActiviy {

    @BindView(R.id.tv_des)
    AppCompatTextView tv_des;

    ProgramsModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_des_cription);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        model = getIntent().getParcelableExtra("DES");
        getSupportActionBar().setTitle(model.getPrograms_title());
        toolbar.setTitle(model.getPrograms_title());
//        tv_des.setText(model.getPrograms_desc());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tv_des.setText(Html.fromHtml(model.getPrograms_desc(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            tv_des.setText(Html.fromHtml(model.getPrograms_desc()));
        }


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}
