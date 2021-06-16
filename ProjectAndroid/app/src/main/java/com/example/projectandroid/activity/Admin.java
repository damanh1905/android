package com.example.projectandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectandroid.R;

public class Admin extends AppCompatActivity {
    TextView managerUser,managerPro,logout,contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Anhxa();
        managerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin.this,ManagerAccount.class));
            }
        });
        managerPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin.this,ManagerProduct.class));
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin.this,Contact.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin.this,loginActivity.class));
            }
        });
    }

    private void Anhxa() {
        managerPro=findViewById(R.id.textViewManagerPro);
        managerUser=findViewById(R.id.textViewManagerAcc);
        logout=findViewById(R.id.textViewLogout);
        contact=findViewById(R.id.textViewContact);

    }
}