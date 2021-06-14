package com.example.projectandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import com.example.projectandroid.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bottomenu extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment=null;
            switch (item.getItemId()) {
                case R.id.navigation_insert:
                    fragment= new FragmentInsertActivity();
//                    Intent i =new Intent(bottomenu.this,MainActivity.class);
//                    startActivity(i);
                    break;
                case R.id.navigation_update:
                    fragment= new FragmentUpdateActivity();
                    break;
                case R.id.navigation_team:
                    fragment= new FragmentTeam();
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frament,fragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomenu);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frament,new FragmentInsertActivity()).commit();
    }

}
