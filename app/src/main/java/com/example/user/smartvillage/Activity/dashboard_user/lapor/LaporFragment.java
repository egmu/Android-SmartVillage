package com.example.user.smartvillage.Activity.dashboard_user.lapor;


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
import com.example.user.smartvillage.Model.DataPembangunanModel;
import com.example.user.smartvillage.Model.DefaultModel;
import com.example.user.smartvillage.Model.PembangunanModel;
import com.example.user.smartvillage.R;
import com.example.user.smartvillage.service.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LaporFragment extends Fragment {

    private Spinner spNamen2;
    ArrayList<String> dataPembangunan;
    private String[] germanFeminine = {
            "Karin",
            "Ingrid",
            "Helga",
            "Renate",
            "Elke",
            "Ursula",
            "Erika",
            "Christa",
            "Gisela",
            "Monika"
    };

    PembangunanModel dropdownpembangunan;

    public LaporFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_lapor,container, false);


        spNamen2 = (Spinner) view.findViewById(R.id.spList);
        loadDataPembangunan();

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


        Button bt_lapor = (Button) view.findViewById(R.id.button_lapor);
        final EditText et_deskripsi = (EditText) view.findViewById(R.id.deskripsi_lapor);
        final Spinner id_kategori = (Spinner) view.findViewById(R.id.spList);
        bt_lapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String set_deskripsi = et_deskripsi.getText().toString();
                final int KategoriId = (int) id_kategori.getSelectedItemId();
                System.out.println(KategoriId);
                if (set_deskripsi.isEmpty()) {
                    Toast.makeText(getActivity(), "Data Kosong", Toast.LENGTH_SHORT).show();
                } else {
                    ApiService.service_post.postLapor(
                            "Bearer bmFuZGE=",
                            set_deskripsi,
                            "2"
//                            KategoriId
                    ).enqueue(new Callback<DefaultModel>() {
                        @Override
                        public void onResponse(Call<DefaultModel> call, Response<DefaultModel> response) {
                            System.out.println(response);
                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(Call<DefaultModel> call, Throwable t) {
                            Toast.makeText(getActivity(), "Error!!!", Toast.LENGTH_SHORT).show();
                            Log.d("lapor", "onFailure: " + t.getMessage());
                            t.printStackTrace();
                        }
                    });
                }
            }
        });

        return view;
    }

    private void loadDataPembangunan()
    {
        ApiService.service_get.getPembangunandd("Bearer bmFuZGE=").enqueue(new Callback<PembangunanModel>() {
            @Override
            public void onResponse(Call<PembangunanModel> call, Response<PembangunanModel> response) {
//                dropdownpembangunan = response.body();
                ArrayList<DataPembangunanModel> dataPembangunanModelArrayList = response.body().getData();
                dataPembangunan = new ArrayList<String>();
                String[] stringArray = new String[dataPembangunanModelArrayList.size()];
                Log.v("lala", dataPembangunanModelArrayList.size() + "");
                for (int i = 0 ; i<dataPembangunanModelArrayList.size() ; i++)
                {
                    Log.v("asd", dataPembangunanModelArrayList.get(i).getNama_pembangunan());
                    stringArray[i] = dataPembangunanModelArrayList.get(i).getNama_pembangunan();
                }
                // inisialiasi Array Adapter dengan memasukkan string array di atas
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_spinner_item, stringArray);


                // mengeset Array Adapter tersebut ke Spinner
                spNamen2.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<PembangunanModel> call, Throwable t) {

            }
        });
    }

}
