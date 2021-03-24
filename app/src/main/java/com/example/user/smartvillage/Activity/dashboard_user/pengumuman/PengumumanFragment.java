package com.example.user.smartvillage.Activity.dashboard_user.pengumuman;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.smartvillage.Model.DefaultModel;
import com.example.user.smartvillage.R;
import com.example.user.smartvillage.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PengumumanFragment extends Fragment {

    TextView tvDataPengumuman;
    DefaultModel pengumuman;

    public PengumumanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_pengumuman, container, false);
        tvDataPengumuman = (TextView) rootView.findViewById(R.id.tv_pengumuman);

        ApiService.service_get.getPengumuman("Bearer bmFuZGE=").enqueue(new Callback<DefaultModel>() {
            @Override
            public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                pengumuman = response.body();
                tvDataPengumuman.setText(pengumuman.getMessage());

            }

            @Override
            public void onFailure(Call<DefaultModel> call, Throwable t) {

            }
        });

        return rootView;
    }
}
