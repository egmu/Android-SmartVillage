package com.example.user.smartvillage.Model;

import java.util.ArrayList;

/**
 * Created by user on 28/12/2017.
 */

public class PembangunanModel {
    private boolean status;
    private String message;
    private ArrayList<DataPembangunanModel> data;

    public PembangunanModel(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<DataPembangunanModel> getData() {
        return data;
    }
}
