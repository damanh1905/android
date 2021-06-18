package com.example.projectandroid.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.projectandroid.R;
import com.example.projectandroid.adapter.NotificationAdapter;
import com.example.projectandroid.ultil.CheckConnection;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;

public class Notification extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<com.example.projectandroid.model.Notification> notificationArrayList;
    NotificationAdapter notificationAdapter;
    BottomNavigationView bottomNavigationView;
    ListView listViewnoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            enventButomNav();
        }else {
            CheckConnection.ShowToast_short(getApplicationContext(), "bạn hãy kiểm tra kết nối");
            finish();
        }
    }
    private void Anhxa() {
        recyclerView=findViewById(R.id.recyclerViewNoti);
        bottomNavigationView=findViewById(R.id.nav_bottom_noti);

        AdapterNotification();
    }
    public void AdapterNotification() {
        notificationArrayList = new ArrayList<>();
        notificationArrayList.add(0,new com.example.projectandroid.model.Notification("Khuyến mãi","12H. Chớp lẹ mã giảm giá..."));
        notificationArrayList.add(1,new com.example.projectandroid.model.Notification("Hoạt động","Chưa có hoạt động"));
        notificationArrayList.add(2,new com.example.projectandroid.model.Notification("Cập nhật","Ví AirPay thông báo. TỪ NAY HÃY GỌI TÔI .."));
        notificationArrayList.add(3,new com.example.projectandroid.model.Notification("Giải thưởng","Tham gia tuần lễ hái lộc ..."));
        notificationAdapter = new NotificationAdapter(Notification.this, notificationArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(Notification.this, 1));
        recyclerView.setAdapter(notificationAdapter);
        System.out.println(notificationArrayList);



    }
    public  void enventButomNav() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Intent i;
                switch (menuItem.getItemId()) {
                    case R.id.navigation_insert:
                        i = new Intent(Notification.this, MainActivity.class);
                        startActivity(i);

                        break;
                    case R.id.navigation_update:
                        i = new Intent(Notification.this, GioHangActivity.class);
                        startActivity(i);

                        break;
                    case R.id.navigation_team:
                        i=new Intent(Notification.this,Account.class);
                        startActivity(i);
                        break;


                }
                return true;
            }
        });
    }

}