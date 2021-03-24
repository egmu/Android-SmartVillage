package com.example.user.smartvillage.Model;

/**
 * Created by user on 28/12/2017.
 */

public class DataPembangunanModel {
    private int id, anggaran, id_user;
    private float prosentase;
    private String nama_pembangunan, foto, tgl_mulai, tgl_selesai, longitude, latitude, keterangan, sumber_dana_pembanguan_id, kategori_pembangunan_id, status_pembangunan_id, mitra_id, created_at, updated_at;

    public DataPembangunanModel() {
    }

    public int getId() {
        return id;
    }

    public int getUsers_id() {
        return id_user;
    }

    public float getProsentase() {
        return prosentase;
    }

    public String getNama_pembangunan() {
        return nama_pembangunan;
    }

    public String getFoto() {
        return foto;
    }

    public String getTgl_mulai() {
        return tgl_mulai;
    }

    public String getTgl_selesai() {
        return tgl_selesai;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getSumber_dana_pembanguan_id() {
        return sumber_dana_pembanguan_id;
    }

    public String getKategori_pembangunan_id() {
        return kategori_pembangunan_id;
    }

    public String getStatus_pembangunan_id() {
        return status_pembangunan_id;
    }

    public String getMitra_id() {
        return mitra_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
