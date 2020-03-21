package com.gyanutsav.gyan.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.ViewModelProviders;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.bumptech.glide.Glide;
import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.viewmodels.HomeViewModel;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.iv_1)
    AppCompatImageView iv_1;


    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        */View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, root);
        Glide.with(this).load(R.drawable.baba_bg).into(iv_1);
        return root;
    }
}