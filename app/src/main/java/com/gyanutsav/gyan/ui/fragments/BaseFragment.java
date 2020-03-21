package com.gyanutsav.gyan.ui.fragments;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.gyanutsav.gyan.ui.activities.BaseActiviy;

public class BaseFragment extends Fragment {
    Context context;
    BaseActiviy mActiviy;

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);

        context = activity;
        mActiviy = (BaseActiviy) activity;
    }


    public Context getmContext(){
        return context;
    }

    public BaseActiviy getmActiviy(){
        return mActiviy;
    }


}
