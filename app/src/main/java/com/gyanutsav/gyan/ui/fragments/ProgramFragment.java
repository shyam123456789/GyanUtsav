package com.gyanutsav.gyan.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.bumptech.glide.Glide;
import com.developer.filepicker.model.DialogConfigs;
import com.developer.filepicker.model.DialogProperties;
import com.developer.filepicker.view.FilePickerDialog;
import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.Utils.ApplyDialog;
import com.gyanutsav.gyan.ui.Utils.PrefrenshesManager;
import com.gyanutsav.gyan.ui.activities.EnterOtpActivity;
import com.gyanutsav.gyan.ui.adapters.ProgramsAdapter;
import com.gyanutsav.gyan.ui.models.MainModel;
import com.gyanutsav.gyan.ui.models.ProgramsModel;
import com.gyanutsav.gyan.ui.models.UserProfile;
import com.gyanutsav.gyan.ui.server.Api;
import com.gyanutsav.gyan.ui.viewmodels.ProgramViewModel;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;

public class ProgramFragment extends BaseFragment implements ProgramsAdapter.OnApply {
    @BindView(R.id.rv_programs)
    RecyclerView rv_programs;
    @BindView(R.id.iv_1)
    AppCompatImageView iv_1;

    ProgramsAdapter adapter;

    private ProgramViewModel programViewModel;
    private ArrayList<ProgramsModel> list = new ArrayList<>();
    private UserProfile profile;
    private int FILE_REQUEST_CODE = 987;
    private DialogProperties properties;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
/*
        programViewModel =
                ViewModelProviders.of(this).get(ProgramViewModel.class);
*/
        View root = inflater.inflate(R.layout.fragment_program, container, false);

        ButterKnife.bind(this, root);
        profile = PrefrenshesManager.getUserProfile();

        adapter = new ProgramsAdapter(getContext(), list, this);
        Glide.with(this).load(R.drawable.baba_bg).into(iv_1);
        rv_programs.setAdapter(adapter);
        getPrograms();
        properties = new DialogProperties();
        setProperty();
        return root;
    }

    private void setProperty() {
        properties.selection_mode = DialogConfigs.SINGLE_MODE;
        properties.selection_type = DialogConfigs.FILE_SELECT;
        properties.root = new File(DialogConfigs.DEFAULT_DIR);
        properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
        properties.offset = new File(DialogConfigs.DEFAULT_DIR);
        properties.extensions = null;
        properties.show_hidden_files = false;
    }

    private void Apply(ProgramsModel program, int position) {
        getmActiviy().getDialog().show();

        Call<MainModel> call = Api.getClient().Apply(profile.getUserId(), "1", program.getPrograms_id());
        call.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                Log.e("TAG", ">>   " + new Gson().toJson(response.body()));
                if (response.body() != null && response.body().getStatus()) {
                    Toast.makeText(getmContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    program.setApplystatus(1);
                    adapter.NotifyMe(position, program);
                }
                getmActiviy().getDialog().dismiss();

            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                getmActiviy().getDialog().dismiss();
                Log.e("TAG", ">>   " + t.getMessage());
            }
        });
    }

    private void getPrograms() {
        Call<MainModel> call;
        if (profile == null) {
            call = Api.getClient().Programs("0");
        } else {
            call = Api.getClient().Programs(profile.getUserId());
        }
        getmActiviy().getDialog().show();
        call.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {

                if (response.body() != null && response.body().getStatus()) {
                    list.addAll(response.body().getProgrammodellist());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Unable to get Data", Toast.LENGTH_SHORT).show();
                }
                getmActiviy().getDialog().dismiss();

            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                Toast.makeText(getContext(), "Unable to get Data", Toast.LENGTH_SHORT).show();
                getmActiviy().getDialog().dismiss();

            }
        });
    }

    @Override
    public void onapply(ProgramsModel program, int position) {
        if (profile == null) {
            getmContext().startActivity(new Intent(getmContext(), EnterOtpActivity.class));
        } else {

            new ApplyDialog(getmContext(), v -> {
                Apply(program, position);
            }, list.get(position).getLastDate(), list.get(position).getPrograms_title()).show();
        }

    }

    @Override
    public void onUpload(String programId, String fileType) {
        FilePickerDialog dialog = new FilePickerDialog(getmContext(), properties);
        dialog.setTitle("Select a File");
        dialog.show();
        dialog.setDialogSelectionListener(files -> {
            String path = files[0];
            uploadFile(new File(path), programId);
        });

/*
        switch (fileType) {
            case "1":
                Intent intent1 = new Intent(getmContext(), ImagePickActivity.class);
                intent1.putExtra(IS_NEED_CAMERA, true);
                intent1.putExtra(Constant.MAX_NUMBER, 1);
                startActivityForResult(intent1, Constant.REQUEST_CODE_PICK_IMAGE);
                break;
            case "2":
                Intent intent2 = new Intent(getmContext(), VideoPickActivity.class);
                intent2.putExtra(IS_NEED_CAMERA, true);
                intent2.putExtra(Constant.MAX_NUMBER, 1);
                startActivityForResult(intent2, Constant.REQUEST_CODE_PICK_VIDEO);
                break;
            case "3":
                Intent intent3 = new Intent(getmContext(), AudioPickActivity.class);
                intent3.putExtra(IS_NEED_RECORDER, true);
                intent3.putExtra(Constant.MAX_NUMBER, 1);
                startActivityForResult(intent3, Constant.REQUEST_CODE_PICK_AUDIO);
                break;
            case "4":
                Intent intent4 = new Intent(getmContext(), NormalFilePickActivity.class);
                intent4.putExtra(Constant.MAX_NUMBER, 1);
                intent4.putExtra(NormalFilePickActivity.SUFFIX, new String[]{"xlsx", "xls", "doc", "docx", "ppt", "pptx", "pdf"});
                startActivityForResult(intent4, Constant.REQUEST_CODE_PICK_FILE);
                break;


        }
*/


    }

    private void uploadFile(File file, String p_id) {

        Log.e("TAG", ">>   " + p_id);
        if (file == null) {
            Toast.makeText(context, "File not found!", Toast.LENGTH_SHORT).show();
            return;
        }
        getmActiviy().getDialog().show();
        Call<MainModel> call = Api.getClient().submitprogram(Api.getRequestBody(profile.getUserId()), Api.getRequestBody(p_id), Api.prepareFilePart("userfile", file));
        call.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {

                Log.e("TAG", ">>   " + response.body().getMessage());
                if (response.body() != null) {
                    Toast.makeText(getmContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                getmActiviy().getDialog().dismiss();
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                getmActiviy().getDialog().dismiss();
                Toast.makeText(getmContext(), "Failed to upload", Toast.LENGTH_SHORT).show();
                Log.e("TAG", ">>   " + t.getMessage());
            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String path;
/*
        switch (requestCode) {
            case Constant.REQUEST_CODE_PICK_IMAGE:
                if (resultCode == RESULT_OK) {
                    ArrayList<ImageFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_IMAGE);
                    path = list.get(0).getPath();
                }
                break;
            case Constant.REQUEST_CODE_PICK_VIDEO:
                if (resultCode == RESULT_OK) {
                    ArrayList<VideoFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_VIDEO);
                    path = list.get(0).getPath();
                }
                break;
            case Constant.REQUEST_CODE_PICK_AUDIO:
                if (resultCode == RESULT_OK) {
                    ArrayList<AudioFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_AUDIO);
                    path = list.get(0).getPath();
                }
                break;
            case Constant.REQUEST_CODE_PICK_FILE:
                if (resultCode == RESULT_OK) {
                    ArrayList<NormalFile> list = data.getParcelableArrayListExtra(Constant.RESULT_PICK_FILE);
                    path = list.get(0).getPath();
                }
                break;
        }
*/
    }
}