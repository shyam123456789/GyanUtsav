package com.gyanutsav.gyan.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.models.NotificationModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    Context context;
    ArrayList<NotificationModel> list;


    public NotificationAdapter(Context context, ArrayList<NotificationModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notificationitem, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {

        holder.tv_title.setText(list.get(position).getNotification_title());
        holder.tv_msg.setText(list.get(position).getNotification_desc());
        holder.tv_time.setText(list.get(position).getNoti_date());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_msg)
        TextView tv_msg;
        @BindView(R.id.tv_time)
        TextView tv_time;

        @BindView(R.id.tv_title)
        TextView tv_title;


        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}
