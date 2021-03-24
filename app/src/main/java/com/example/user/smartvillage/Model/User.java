package com.example.user.smartvillage.Model;

/**
 * Created by user on 03/12/2017.
 */

public class User {
    private String username, access_token, role, message;
    private boolean status;
    private int id;

    public String getUsername() {
        return username;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getRole() {
        return role;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }

    public int getId() {
        return id;
    }
}
