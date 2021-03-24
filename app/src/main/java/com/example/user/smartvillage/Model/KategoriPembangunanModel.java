package com.example.user.smartvillage.Model;

import java.util.ArrayList;

/**
 * Created by user on 14/01/2018.
 */

public class KategoriPembangunanModel {
    private boolean status;
    private String message;
    private ArrayList<Kategori> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Kategori> getData() {
        return data;
    }

    public void setData(ArrayList<Kategori> data) {
        this.data = data;
    }
}
