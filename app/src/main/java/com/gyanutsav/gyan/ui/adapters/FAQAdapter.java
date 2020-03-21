package com.gyanutsav.gyan.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.models.FAQModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FAQAdapter extends RecyclerView.Adapter<FAQAdapter.FAQViewHolder> {

    Context context;
    ArrayList<FAQModel> list;


    @NonNull
    @Override
    public FAQViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_item, parent, false);
        return new FAQViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull FAQViewHolder holder, int position) {
        FAQModel model = list.get(position);
        holder.tv_ans.setText(model.getAns());
        holder.tv_ques.setText(model.getQues());

    }


    class FAQViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_ques)
        TextView tv_ques;

        @BindView(R.id.tv_ans)
        TextView tv_ans;


        public FAQViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
