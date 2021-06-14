package com.example.projectandroid.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.projectandroid.R;
import com.example.projectandroid.adapter.AcountAdapter;
import com.example.projectandroid.ultil.CheckConnection;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class Account extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);
        bottomNavigationView=findViewById(R.id.nav_bottom_account);




        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final AcountAdapter adapter=new AcountAdapter(getSupportFragmentManager(),this, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        Toast.makeText(Account.this, "vo login ", Toast.LENGTH_SHORT).show();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            enventButomNav();
        }else {
            CheckConnection.ShowToast_short(getApplicationContext(), "bạn hãy kiểm tra kết nối");
            finish();
        }
    }
    public  void enventButomNav(){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent i;
                switch (menuItem.getItemId()){
                    case R.id.navigation_insert:
                        i=new Intent(Account.this,MainActivity.class);
                        startActivity(i);
                       
                        break;
                    case R.id.navigation_update:
                        i=new Intent(Account.this,GioHangActivity.class);
                        startActivity(i);

                        break;


                }
                return true;
            }
        });

    }
}