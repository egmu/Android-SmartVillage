package com.example.user.smartvillage.Adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.smartvillage.Controller.AppConfig;
import com.example.user.smartvillage.Model.PembangunanModel;
import com.example.user.smartvillage.R;

/**
 * Created by user on 27/12/2017.
 */

public class DataPembangunanAdapter extends RecyclerView.Adapter<DataPembangunanAdapter.ViewHolder> {

    private Context mContext;
    private PembangunanModel list_data_pembangunan;

    public DataPembangunanAdapter(Context mContext, PembangunanModel list_data_pembangunan) {
        this.mContext = mContext;
        this.list_data_pembangunan = list_data_pembangunan;
    }

    @Override
    public DataPembangunanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pembangunan,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataPembangunanAdapter.ViewHolder holder, int position) {


        holder.tvNamaPembangunan.setText(list_data_pembangunan.getData().get(position).getNama_pembangunan());
        holder.tvNamaMitra.setText(list_data_pembangunan.getData().get(position).getMitra_id());
        String bulanmulai = list_data_pembangunan.getData().get(position).getTgl_mulai();
        String partbulan[] = bulanmulai.split("-");
        partbulan[1]= pemisahbulan(partbulan[1]);
        holder.tvStartPembangunan.setText(partbulan[2]+" "+partbulan[1]+" "+partbulan[0]);
        holder.tvKeterangan.setText(list_data_pembangunan.getData().get(position).getKeterangan());
        String bulanselesai = list_data_pembangunan.getData().get(position).getTgl_selesai();
        String partbulan2[] = bulanselesai.split("-");
        partbulan2[1]=pemisahbulan(partbulan2[1]);
        holder.tvUpdatePembangunan.setText(partbulan2[2]+" "+partbulan2[1]+" "+partbulan2[0]);
        holder.tvProsentase.setText(String.valueOf(list_data_pembangunan.getData().get(position).getProsentase())+"%");
        Log.d("gambar", "onBindViewHolder: "+AppConfig.URL_PICTURE+list_data_pembangunan.getData().get(position).getFoto());
    }

    @Override
    public int getItemCount() {
        return list_data_pembangunan.getData().size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder
    {
        TextView tvNamaPembangunan,tvNamaMitra,tvStartPembangunan,tvUpdatePembangunan,tvKeterangan,tvProsentase;
        ImageView imagePembangunan;

        public ViewHolder(View itemView) {
            super(itemView);
             tvProsentase = (TextView) itemView.findViewById(R.id.persentase_pembangunan);
             tvNamaPembangunan = (TextView) itemView.findViewById(R.id.nama_pembangunan);
             tvNamaMitra = (TextView) itemView.findViewById(R.id.nama_mitra);
             tvStartPembangunan = (TextView) itemView.findViewById(R.id.start_pembangunan);
             tvUpdatePembangunan = (TextView) itemView.findViewById(R.id.update_pembangunan);
             tvKeterangan = (TextView) itemView.findViewById(R.id.keterangan_pembangunan);
        }
    }

    private String pemisahbulan(String bulan){
        if (bulan.equals("01")){
            bulan = "Januari";
        } else if (bulan.equals("02")){
            bulan = "Februari";
        }else if (bulan.equals("03")){
            bulan = "Maret";
        }else if (bulan.equals("04")){
            bulan = "April";
        }else if (bulan.equals("05")){
            bulan = "Mei";
        }else if (bulan.equals("06")){
            bulan = "Juni";
        }else if (bulan.equals("07")){
            bulan = "Juli";
        }else if (bulan.equals("08")){
            bulan = "Agustus";
        }else if (bulan.equals("09")){
            bulan = "September";
        }else if (bulan.equals("10")){
            bulan = "Oktober";
        }else if (bulan.equals("11")){
            bulan = "November";
        }else if (bulan.equals("12")){
            bulan = "Desember";
        }
        return bulan;
    }

}
