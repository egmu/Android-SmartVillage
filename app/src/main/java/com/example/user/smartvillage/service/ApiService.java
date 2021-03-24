package com.example.user.smartvillage.service;

import com.example.user.smartvillage.Model.DefaultModel;
import com.example.user.smartvillage.Model.KategoriPembangunanModel;
import com.example.user.smartvillage.Model.PembangunanModel;
import com.example.user.smartvillage.Model.User;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by user on 28/12/2017.
 */

public class ApiService {
    public static String BASE_URL = "http://192.168.43.191/smartvillage-1/frontend/web/";
    public static PostService service_post = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService.PostService.class);

    public static GetService service_get = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService.GetService.class);

    public interface PostService{
        @POST("index.php?r=API%2Fauth%2Flogin")
        @FormUrlEncoded
        Call<User> postSignIn(
                @Field("LoginForm[username]") String username,
                @Field("LoginForm[password]") String password
        );

        @POST("index.php?r=API/auth/signup")
        @FormUrlEncoded
        Call<DefaultModel> postSignUp(
                @Field("SignupForm[email]") String nik,
                @Field("SignupForm[username]") String username,
                @Field("SignupForm[password]") String password
        );

        @POST("index.php?r=API%2Frequest-pembangunan%2Fcreate")
        @FormUrlEncoded
        Call<DefaultModel> postRequest(
                @Header("Authorization") String auth,
                @Field("RequestPembangunan[judul]") String judul,
                @Field("RequestPembangunan[deskripsi]") String deskripsi,
                @Field("RequestPembangunan[kategori_pembangunan_id]") Integer kategori_pembangunan_id
        );

        @POST("index.php?r=API%2Flapor-aduan%2Fcreate")
        @FormUrlEncoded
        Call<DefaultModel> postLapor(
                @Header("Authorization") String auth,
                @Field("LaporAduan[deskripsi]") String deskripsi,
                @Field("LaporAduan[pembangunan_id]") String pembangunan_id);

    }

    public interface GetService{
        @GET("index.php?r=API%2Fpembangunan")
        Call<PembangunanModel> getPembangunan(@Header("Authorization") String auth);

        @GET("index.php?r=API%2Fpembangunan")
        Call<PembangunanModel> getPembangunandd (@Header("Authorization") String auth);

        @GET("index.php?r=API%2Fkategori-pembangunan")
        Call<KategoriPembangunanModel> getkategori (@Header("Authorization") String auth);

        @GET("index.php?r=API%2Fpengumuman")
        Call<DefaultModel> getPengumuman(@Header("Authorization") String auth);
    }

}