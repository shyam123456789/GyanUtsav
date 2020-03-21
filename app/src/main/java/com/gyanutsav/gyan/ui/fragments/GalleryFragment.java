package com.gyanutsav.gyan.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.adapters.GalleryAdapter;
import com.gyanutsav.gyan.ui.models.MainModel;
import com.gyanutsav.gyan.ui.models.PhotoModel;
import com.gyanutsav.gyan.ui.server.Api;
import com.gyanutsav.gyan.ui.viewmodels.GalleryViewModel;

import java.util.ArrayList;

public class GalleryFragment extends BaseFragment {
    @BindView(R.id.rv_grid)
    RecyclerView rv_grid;
    GalleryAdapter adapter;
    private ArrayList<PhotoModel> list = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*GalleryViewModel mGalleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        */View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        ButterKnife.bind(this, root);
        adapter = new GalleryAdapter(getContext(), list);
        rv_grid.setAdapter(adapter);
        getPhotos();


        return root;
    }

    private void getPhotos() {
        getmActiviy().getDialog().show();
        Call<MainModel> call = Api.getClient().getPhotos();
        call.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                if (response.body() != null && response.body().getStatus()) {
                    list.addAll(response.body().getPhotoModellist());
                    adapter.notifyDataSetChanged();
                }
                getmActiviy().getDialog().dismiss();

            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                getmActiviy().getDialog().dismiss();

            }
        });
    }
}