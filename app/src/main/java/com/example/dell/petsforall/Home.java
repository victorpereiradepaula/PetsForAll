package com.example.dell.petsforall;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Home extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabLayout = (TabLayout) findViewById(R.id.tabHome);
        viewPager = (ViewPager) findViewById(R.id.pagerHome);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);

    }

    public void setupViewPager(ViewPager viewPager) {
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        myViewPagerAdapter.addFragmentPage(new DonateFragment(), "Doar");
        myViewPagerAdapter.addFragmentPage(new AdoptFragment(), "Adotar");
        myViewPagerAdapter.addFragmentPage(new EditProfileFragment(), "Perfil");

        viewPager.setAdapter(myViewPagerAdapter);
    }

}
