package com.example.niramaya_health;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapterUser extends FragmentPagerAdapter {


    public PagerAdapterUser(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new User_home();
            case 1:
                return new User_Symptoms();
            case 2:
                return new User_appoint();
            case 3:
                return new user_timeline();
            default:
                return null;


        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
