package com.gyanutsav.gyan.ui.server;

import java.io.File;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Retrofit retrofit = null;
    public final static String BASE_URL = "https://www.gyanutsav.com/Gyanutsav/User/";

    public static ApiInterface getClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

/*
       client.interceptors().add(new Interceptor() {
           @NotNull
           @Override
           public Response intercept(@NotNull Chain chain) throws IOException {
               Request request = chain.request();
               Log.e("HTTP","TAG:"+new Gson().toJson(request));
               return chain.proceed(request);
           }
       });
*/

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .callTimeout(2, TimeUnit.MINUTES)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(130, TimeUnit.SECONDS)
                .writeTimeout(180, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        if (retrofit != null) {
            return retrofit.create(ApiInterface.class);
        } else {
            return null;
        }
    }

    @NonNull
    public static RequestBody getRequestBody(String value) {
        return RequestBody.create(MultipartBody.FORM, value);
    }

    @NonNull
    public static MultipartBody.Part prepareFilePart(String partName, File file) {
        // create RequestBody instance from file
        if (file == null) return null;
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"),
                        file
                );
        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

}
