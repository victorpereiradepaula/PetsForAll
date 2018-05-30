package com.example.dell.petsforall;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public MyViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    public void addFragmentPage(Fragment fragment, String title) {
        fragments.add(fragment);
        titles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return titles.size();
    }
}