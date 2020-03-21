package com.gyanutsav.gyan.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.Utils.ApplyDialog;
import com.gyanutsav.gyan.ui.activities.ProgramDesCriptionActivity;
import com.gyanutsav.gyan.ui.models.ProgramsModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProgramsAdapter extends RecyclerView.Adapter<ProgramsAdapter.mViewHolder> {

    Context context;
    ArrayList<ProgramsModel> list;
    OnApply onApply;

    public ProgramsAdapter(Context context, ArrayList<ProgramsModel> list, OnApply onApply) {
        this.context = context;
        this.list = list;
        this.onApply = onApply;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.program_item, parent, false);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        ProgramsModel model = list.get(position);
//        Log.e("TAG",">>   "+new Gson().toJson(model));
        Glide.with(context).load(model.getPrograms_pic()).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                Log.e("IMAGELOAD"," onLoadFailed "+position);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Log.e("IMAGELOAD"," onResourceReady "+position);
                return false;
            }
        }).into(holder.iv_image);



        holder.tv_programname.setText(model.getPrograms_title());
        holder.tv_theme.setText(model.getTheme());

        holder.tv_lastdate.setText("last date: " + model.getLastDate());
        Log.e("TAG", "Applystatus>>   " + model.getApplystatus());
        Log.e("TAG", "getFiletype>>   " + model.getFiletype());
        Log.e("TAG", "getPartitype>>   " + model.getPartitype());

        if (model.getPartitype() == 0) {
            holder.tv_applynow.setVisibility(View.VISIBLE);
            if (model.getApplystatus() == 0) {
                holder.tv_applynow.setText("Apply Now");
                holder.tv_msg.setVisibility(View.GONE);
            } else {
                holder.tv_applynow.setText("Upload");
                holder.tv_msg.setVisibility(View.VISIBLE);
            }
        } else {
            holder.tv_applynow.setVisibility(View.GONE);
        }


/*
        switch (model.getFiletype()) {
            case "1":
                holder.tv_msg.setText("Upload Images");
                break;
            case "2":
                holder.tv_msg.setText("Upload Video below 5MB");
                break;
            case "3":
                holder.tv_msg.setText("Upload Audio below 5MB ");
                break;
            case "4":
                holder.tv_msg.setText("Upload pdf/doc/images");
                break;

        }
*/
        holder.tv_msg.setText("Upload pdf/doc/images/video");


        holder.tv_applynow.setOnClickListener(view -> {
//            onApply.onUpload(model.getPrograms_id(), model.getFiletype());

            if (model.getApplystatus() == 0) {
                //new ApplyDialog(context, cardno -> {
                onApply.onapply(model, position);
                // }).show();

            } else {
                onApply.onUpload(model.getPrograms_id(), model.getFiletype());
            }
        });

        holder.tv_readmore.setOnClickListener(view -> {
            context.startActivity(new Intent(context, ProgramDesCriptionActivity.class).putExtra("DES", model));

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class mViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_lastdate)
        AppCompatTextView tv_lastdate;

        @BindView(R.id.tv_msg)
        AppCompatTextView tv_msg;

        @BindView(R.id.tv_programname)
        AppCompatTextView tv_programname;

        @BindView(R.id.tv_applynow)
        AppCompatTextView tv_applynow;
        @BindView(R.id.tv_readmore)
        AppCompatTextView tv_readmore;

        @BindView(R.id.iv_image)
        AppCompatImageView iv_image;

        @BindView(R.id.tv_theme)
        AppCompatTextView tv_theme;


        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public void NotifyMe(int position, ProgramsModel model) {
        list.set(position, model);
        notifyDataSetChanged();
    }


    public interface OnApply {
        void onapply(ProgramsModel program, int position);

        void onUpload(String programId, String filetype);

    }

}
