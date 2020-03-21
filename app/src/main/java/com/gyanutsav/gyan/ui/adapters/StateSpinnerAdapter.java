package com.gyanutsav.gyan.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.models.StateModel;

import java.util.ArrayList;

public class StateSpinnerAdapter extends BaseAdapter {
    Context context;
    ArrayList<StateModel> list;
    LayoutInflater inflater;

    public StateSpinnerAdapter(Context context, ArrayList<StateModel> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.spinner_item, viewGroup, false);
        }
        TextView tv_name = view.findViewById(R.id.tv_name);
        tv_name.setText(list.get(i).getName());
        return view;
    }
}
