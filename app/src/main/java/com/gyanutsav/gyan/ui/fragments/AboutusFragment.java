package com.gyanutsav.gyan.ui.fragments;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.viewmodels.AboutusViewModel;

public class AboutusFragment extends BaseFragment {

    private AboutusViewModel aboutusViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       /* aboutusViewModel =
                ViewModelProviders.of(this).get(AboutusViewModel.class);
       */
        View root = inflater.inflate(R.layout.fragment_aboutus, container, false);
        TextView tv = root.findViewById(R.id.text_aboutus);
        tv.setMovementMethod(new ScrollingMovementMethod());
        return root;
    }
}