package com.example.user.smartvillage.Activity.dashboard_user.request;


import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.smartvillage.Activity.SignInActivity;
import com.example.user.smartvillage.Model.DefaultModel;
import com.example.user.smartvillage.Model.Kategori;
import com.example.user.smartvillage.Model.KategoriPembangunanModel;
import com.example.user.smartvillage.R;
import com.example.user.smartvillage.service.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RequestFragment extends Fragment {
    DefaultModel request_pembangunan;
    private Spinner spNamen2;
    ArrayList<String> dataPembangunan;

    public RequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View request = inflater.inflate(R.layout.fragment_request, container, false);
        spNamen2 = (Spinner) request.findViewById(R.id.spList);
        loadDataPembangunan();

        Button bt_request = (Button) request.findViewById(R.id.button_request);
        final EditText et_judul = (EditText) request.findViewById(R.id.judul_request);
        final EditText et_deskripsi = (EditText) request.findViewById(R.id.deskripsi_request);
        final Spinner kategoriPembangunanId = (Spinner) request.findViewById(R.id.spList);
        bt_request.setOnClickListener(new View.OnClickListener() {
            @Override
                  public void onClick(View v) {
                     String set_judul = et_judul.getText().toString();
                     String set_deskripsi = et_deskripsi.getText().toString();
                     int set_kategoriPembangunan = (int) kategoriPembangunanId.getSelectedItemId();
                     if (set_judul.isEmpty() && set_deskripsi.isEmpty()) {
                         Toast.makeText(getActivity(), "Data Kosong", Toast.LENGTH_SHORT).show();
                     } else {
                         ApiService.service_post.postRequest("Bearer bmFuZGE=",
                                 set_judul,
                                 set_deskripsi,
                                 set_kategoriPembangunan
                         ).enqueue(new Callback<DefaultModel>() {
                             @Override
                             public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                                 System.out.println("response:  " + response);
                                 Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                             }
                             @Override
                             public void onFailure(Call<DefaultModel> call, Throwable t) {
                                 t.printStackTrace();
                             }
                         });
                     }
            }
                     });

        // mengeset listener untuk mengetahui saat item dipilih
        spNamen2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // memunculkan toast + value Spinner yang dipilih (diambil dari adapter)

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return request;
    }

    private void loadDataPembangunan()
    {
        ApiService.service_get.getkategori("Bearer bmFuZGE=").enqueue(new Callback<KategoriPembangunanModel>() {
            @Override
            public void onResponse(Call<KategoriPembangunanModel> call, Response<KategoriPembangunanModel> response) {
//                dropdownpembangunan = response.body();
                ArrayList<Kategori> dataPembangunanModelArrayList = response.body().getData();
                dataPembangunan = new ArrayList<String>();
                String[] stringArray = new String[dataPembangunanModelArrayList.size()];
                Log.v("lala", dataPembangunanModelArrayList.get(0) + "");
                int i = 0;
                for (Kategori dt : dataPembangunanModelArrayList)
                {
//                    Log.v("asd", ""+dt.getId());
                    stringArray[i] = dt.getNama();
                    i++;
                }
                // inisialiasi Array Adapter dengan memasukkan string array di atas
                final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_spinner_item, stringArray);

                // mengeset Array Adapter tersebut ke Spinner
                spNamen2.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<KategoriPembangunanModel> call, Throwable t) {
            }
        });

    }
}

