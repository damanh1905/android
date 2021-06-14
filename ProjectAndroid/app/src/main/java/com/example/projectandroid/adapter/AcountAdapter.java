package com.example.projectandroid.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.projectandroid.activity.thongtincanhan;
import com.example.projectandroid.activity.thongTinDonHang;

public class AcountAdapter  extends FragmentPagerAdapter {
    private Context context;


    public AcountAdapter(@NonNull FragmentManager fm, Context context, int behavior) {
        super(fm, behavior);
        this.context=context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    public Fragment getItem(int position) {
        switch (position){
            case 0:
                thongtincanhan thongtin=new thongtincanhan();
                return thongtin;
            case 1:
               thongTinDonHang thongtindonhang=new thongTinDonHang();
                return thongtindonhang;
            default:
                return new thongtincanhan();

        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title="thong tin";
                break;
            case 1:
                title="Don Hang";
                break;
        }
        return title;
    }
}
