package com.gyanutsav.gyan.ui.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.viewmodels.ContactusViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.CLIPBOARD_SERVICE;

public class ContactusFragment extends Fragment {
    @BindView(R.id.et_email)
    AppCompatTextView et_mail;
    private ContactusViewModel contactusViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       /* contactusViewModel =
                ViewModelProviders.of(this).get(ContactusViewModel.class);
       */ View root = inflater.inflate(R.layout.fragment_contactus, container, false);
        ButterKnife.bind(this, root);


        et_mail.setOnClickListener(e->{
            ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", et_mail.getText().toString().trim());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getContext(), "Copied!", Toast.LENGTH_SHORT).show();
        });

        return root;
    }
}