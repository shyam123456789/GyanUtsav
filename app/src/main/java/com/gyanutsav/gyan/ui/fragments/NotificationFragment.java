package com.gyanutsav.gyan.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.adapters.NotificationAdapter;
import com.gyanutsav.gyan.ui.models.MainModel;
import com.gyanutsav.gyan.ui.models.NotificationModel;
import com.gyanutsav.gyan.ui.server.Api;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends BaseFragment {

    @BindView(R.id.rv_list_notification)
    RecyclerView recyclerView;

    private NotificationAdapter adapter;
    private ArrayList<NotificationModel> list = new ArrayList<>();

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this,view);
        adapter = new NotificationAdapter(getContext(),list);
        Log.e("TAG",">>>>adapter  "+adapter);
        Log.e("TAG",">>>>recyclerview  "+recyclerView);
        recyclerView.setAdapter(adapter);
        getNotificationList();
        return view;
    }

    private void getNotificationList() {
        getmActiviy().getDialog().show();


        Call<MainModel> call = Api.getClient().notification();
        call.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                if (response.body()!=null&&response.body().getStatus()){
                    list.addAll(response.body().getNotificationModelslist());
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(), "Not found!", Toast.LENGTH_SHORT).show();
                }
                getmActiviy().getDialog().dismiss();

            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                Toast.makeText(getContext(), "Not found!", Toast.LENGTH_SHORT).show();
                getmActiviy().getDialog().dismiss();

            }
        });
    }


}
