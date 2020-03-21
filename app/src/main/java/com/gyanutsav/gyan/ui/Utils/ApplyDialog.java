package com.gyanutsav.gyan.ui.Utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.gyanutsav.gyan.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplyDialog extends Dialog {

    @BindView(R.id.tv_apply)
    AppCompatTextView tv_apply;

    @BindView(R.id.tv_cancel)
    AppCompatTextView tv_cancel;

    @BindView(R.id.tv_title)
    AppCompatTextView tv_title;

    @BindView(R.id.et_aadharcard)
    AppCompatTextView et_adharcardno;

    @BindView(R.id.check)
    AppCompatCheckBox check;

    Context context;
    Apply progress;
    String msg;
    String title;

    public ApplyDialog(Context context, Apply progress, String msg, String title) {
        super(context);
        this.context = context;
        this.progress = progress;
        this.title = title;
        this.msg = msg;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.apply_layout);
        ButterKnife.bind(this);
        tv_title.setText(title);
        et_adharcardno.setText("Last date of submission: "+msg);


        tv_cancel.setOnClickListener(o -> {
            dismiss();
        });

        tv_apply.setOnClickListener(o -> {
            if (check.isChecked()) {
                progress.onApply("");
                dismiss();
            } else {
                Toast.makeText(context, "Please check terms of service and privacy policy.", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public interface Apply {
        void onApply(String aadharcardnumber);
    }


}
