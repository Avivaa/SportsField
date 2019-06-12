package com.example.sportsfield;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPageAdapter extends FragmentPagerAdapter {

    private String[] title= new String[]{"羽毛球","网球","游泳"};

    public MyPageAdapter(FragmentManager manager){
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new Badminton();
        }else if(position==1){
            return new Tennis();
        }else{
            return  new Swimming();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

    @Override
    public int getCount() {
        return 3;
    }

}

