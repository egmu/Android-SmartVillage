package com.example.user.smartvillage.Activity.dashboard_user;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by user on 19/12/2017.
 */

public class DashboardTabAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> contents_fragment;

    public DashboardTabAdapter(FragmentManager fm, ArrayList<Fragment> contents_fragment) {
        super(fm);
        this.contents_fragment = contents_fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return contents_fragment.get(position);
    }

    @Override
    public int getCount() {
        return contents_fragment.size();
    }
}
