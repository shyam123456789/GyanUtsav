package com.gyanutsav.gyan.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gyanutsav.gyan.R;

public class EducationAdapter extends BaseAdapter {
    Context context;
    String[] list;
    LayoutInflater inflater;
    SelectEducation education;

    public EducationAdapter(Context context,String[] list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return list.length;
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
        tv_name.setText(list[i]);

        return view;
    }


    public interface SelectEducation {
        public void onEducationSelect(String education);
    }

}
