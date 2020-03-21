package com.gyanutsav.gyan.ui.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.Utils.PrefrenshesManager;
import com.gyanutsav.gyan.ui.adapters.DistrictSpinnerAdapter;
import com.gyanutsav.gyan.ui.adapters.EducationAdapter;
import com.gyanutsav.gyan.ui.adapters.StateSpinnerAdapter;
import com.gyanutsav.gyan.ui.models.DistrictModel;
import com.gyanutsav.gyan.ui.models.MainModel;
import com.gyanutsav.gyan.ui.models.StateModel;
import com.gyanutsav.gyan.ui.models.UserProfile;
import com.gyanutsav.gyan.ui.server.Api;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.gson.Gson;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.io.File;
import java.util.ArrayList;

public class SignUpActivity extends BaseActiviy {

    @BindView(R.id.et_name)
    AppCompatEditText et_name;

    @BindView(R.id.et_adhar_number)
    AppCompatEditText et_adhar_number;

    @BindView(R.id.et_email)
    AppCompatEditText et_email;

    @BindView(R.id.et_fathersname)
    AppCompatEditText et_fathersname;

    @BindView(R.id.et_state)
    Spinner et_state;


    @BindView(R.id.et_district)
    Spinner et_district;

    @BindView(R.id.spinner_education)
    Spinner spinner_education;

    @BindView(R.id.et_age)
    AppCompatTextView et_age;

    @BindView(R.id.et_password)
    AppCompatEditText et_password;

    private ArrayList<StateModel> statelist = new ArrayList<>();
    private ArrayList<DistrictModel> districtlist = new ArrayList<>();
    @BindView(R.id.civ)
    public CircleImageView civ;
    private File file = null;
    private String district = "";
    private String state = "";
    private String education = "";
    private String dob = "";
    StateSpinnerAdapter stateSpinnerAdapter;
    DistrictSpinnerAdapter districtSpinnerAdapter;
    private String[] educationlist = new String[]{ "5th", "6th", "7th", "8th", "9th", "10th", "11th", "12th", "Under Graduate","Graduate", "Post Graduate", "doctorate"};
    private UserProfile userprofile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        stateSpinnerAdapter = new StateSpinnerAdapter(this, statelist);
        districtSpinnerAdapter = new DistrictSpinnerAdapter(this, districtlist);
        et_state.setAdapter(stateSpinnerAdapter);
        et_district.setAdapter(districtSpinnerAdapter);
        spinner_education.setAdapter(new EducationAdapter(this, educationlist));
        getState();
        userprofile = PrefrenshesManager.getUserProfile();
        if (userprofile==null){
            finish();
        }else {
            setData();
        }
        et_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i!=0) {
                    getDist(statelist.get(i).getId());
                    state = statelist.get(i).getName();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        et_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                district = districtlist.get(i).getName();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        et_age.setOnClickListener(c -> {
            showDatePicker();
        });
    }

    private void setData() {
        et_age.setText(userprofile.getDob());
        et_name.setText(userprofile.getUserName());
        et_fathersname.setText(userprofile.getFatherName());
        et_email.setText(userprofile.getUserEmail());
        et_adhar_number.setText(userprofile.getAadharNo());
        Glide.with(this).load(userprofile.getUserPic()).into(civ);
    }


    public void register(View view) {
        String name = et_name.getText().toString();
        String fathersname = et_fathersname.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        String adharno = et_adhar_number.getText().toString();
        if (file == null) {
            Toast.makeText(this, "Please select your picture", Toast.LENGTH_SHORT).show();
            return;
        }
        if (validation(name, fathersname, email, password, district, dob,adharno)) {
            updateprofile(userprofile.getUserPhone(), name, fathersname, dob, district, state, education, email, password, file,adharno);
        }

    }

    public void updateprofile(String phone,
                              String user_name,
                              String father_name,
                              String dob,
                              String user_distict,
                              String user_state,
                              String education,
                              String user_email,
                              String password,
                              File file,
                              String adharno
    ) {
        Log.e("TAG", "phone  " + phone);
        Log.e("TAG", "user_name  " + user_name);
        Log.e("TAG", "father_name  " + father_name);
        Log.e("TAG", "dob  " + dob);
        Log.e("TAG", "user_distict  " + user_distict);
        Log.e("TAG", "user_state  " + user_state);
        Log.e("TAG", "education  " + education);
        Log.e("TAG", "user_email  " + user_email);
        Log.e("TAG", "password  " + password);
        Log.e("TAG", "adharno  " + adharno);
        Log.e("TAG", "file  " + file);

        if (PrefrenshesManager.getUserProfile()!=null){
            UserProfile profile = new UserProfile(PrefrenshesManager.getUserProfile().getUserId(),user_name,phone,password,user_email,user_state,user_distict,father_name,dob,education,file.getAbsolutePath(),adharno);
            PrefrenshesManager.saveUserProfile(profile);
        }
        Call<MainModel> call =
                Api.getClient().UpateProfile(Api.getRequestBody(phone),
                        Api.getRequestBody(user_name),
                        Api.getRequestBody(father_name),
                        Api.getRequestBody(dob),
                        Api.getRequestBody(user_distict),
                        Api.getRequestBody(user_state),
                        Api.getRequestBody(password),
                        Api.getRequestBody(education),
                        Api.getRequestBody(user_email),
                        Api.getRequestBody(adharno),
                        Api.prepareFilePart("userfile", file)
                );
        getDialog().show();
        call.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                Log.e("TAG", "response  " + new Gson().toJson(response.body()));
                if (response.body() != null && response.body().getStatus()) {
                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    finish();
                }
                getDialog().dismiss();

            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                Log.e("TAG", "onFailure  " + t.getMessage());
                getDialog().dismiss();
            }
        });


    }

    void showDatePicker() {

        new SpinnerDatePickerDialogBuilder()
                .context(this)
                .callback((view, year, monthOfYear, dayOfMonth) -> {
                    dob = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    et_age.setText(dob);
                    Log.e("TAG", ">> tag  " + dob);
                })
                .spinnerTheme(R.style.NumberPickerStyle)
                .showTitle(true)
                .showDaySpinner(true)
                .dialogTheme(R.style.DatePicker)
                .defaultDate(2010, 0, 1)
                .maxDate(2020, 0, 1)
                .minDate(1990, 0, 1)
                .build()
                .show();

    }


    private void getState() {
        getDialog().show();
        Call<MainModel> call = Api.getClient().getState();
        call.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                Log.e("TAG", "states " + new Gson().toJson(response.body()));
                if (response.body() != null && response.body().getStatus()) {
                    statelist.add(new StateModel("","Please select state.",""));
                    statelist.addAll(response.body().getStateModelslist());
                    stateSpinnerAdapter.notifyDataSetChanged();
                }
                getDialog().dismiss();
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                getDialog().dismiss();
                Toast.makeText(SignUpActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void getDist(String stateId) {
        districtlist.clear();
        getDialog().show();
        Call<MainModel> call = Api.getClient().getDistrict(stateId);
        call.enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                if (response.body() != null && response.body().getStatus()) {
                    districtlist.addAll(response.body().getDistrictModelslist());
                    districtSpinnerAdapter.notifyDataSetChanged();
                    getDialog().dismiss();
                }
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        });

    }


    public void pickImage(View view) {

        ImagePicker.Companion.with(this)
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }


    private boolean validation(String name, String fathersname, String email, String password, String district, String dob,String adharno) {
        boolean bname = false;
        boolean bfathersname = false;
        boolean bemail = false;
        boolean bpassword = false;
        boolean bdistrict = false;
        boolean bdob = false;
        boolean badharnumber = false;


        if (name.isEmpty()) {
            et_name.setError("Please enter name");
        } else {
            bname = true;
        }

        if (fathersname.isEmpty()) {
            et_fathersname.setError("Please enter your father's name");
        } else {
            bfathersname = true;
        }

        if (email.isEmpty()||!email.contains("@")) {
            et_email.setError("Please enter email");
        } else {
            bemail = true;

        }

        if (password.isEmpty()) {
            et_password.setError("Please enter password");
        } else {
            bpassword = true;
        }

        if (district.isEmpty()) {
            Toast.makeText(this, "please select district", Toast.LENGTH_SHORT).show();
        } else {
            bdistrict = true;
        }
        if (dob.isEmpty()) {
            et_age.setError("Please set your Date of Birth");
        } else {
            bdob = true;
        }
        if (adharno.length()<10) {
            et_adhar_number.setError("Please set your Adhar number");
        } else {
            badharnumber = true;
        }
        return bname && bfathersname && bemail && bpassword && bdistrict && bdob&&badharnumber;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String path = data.getStringExtra(ImagePicker.EXTRA_FILE_PATH);
            Glide.with(this).load(path).into(civ);
            file = new File(path);
            //   updateprofile("7067144471", "xyz", "xyz", "xyz", "xyz", "xyz", "xyz", "xyz@gmail.com", "xyz", file);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            //Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            //Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }


    }
}
