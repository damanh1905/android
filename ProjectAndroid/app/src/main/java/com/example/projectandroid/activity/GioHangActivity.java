package com.example.projectandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.projectandroid.R;
import com.example.projectandroid.adapter.GioHangAdapter;

import java.text.NumberFormat;
import java.util.Locale;

public class GioHangActivity extends AppCompatActivity {
    ListView lvGiohang;
    static TextView txtThongbao;
    TextView txtTongTien;
    static TextView txtGiatri;
    Button btnThanhToan, btnMuaHang;
    Toolbar toolbarThanhToan;
    static GioHangAdapter gioHangAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        anhxa();
        CactOnItemListView();
        ActionToolbar();
        checkData();
        eventUltil();
        button();
    }

    private void button() {
        btnMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.gioHangArrayList.size() > 0) {
                    Intent intent = new Intent(getApplicationContext(), ThongTinKhachHangActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Vui lòng thêm sản phẩm vào giỏ hàng để thanh toán", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public static void CactOnItemListView() {
        if (MainActivity.gioHangArrayList.size() <= 0) {
            txtThongbao.setVisibility(View.VISIBLE);
            gioHangAdapter.notifyDataSetChanged();
            eventUltil();
        } else {
            gioHangAdapter.notifyDataSetChanged();
            eventUltil();
            if (MainActivity.gioHangArrayList.size() <= 0) {
                txtThongbao.setVisibility(View.VISIBLE);
                gioHangAdapter.notifyDataSetChanged();
            } else {
                txtThongbao.setVisibility(View.INVISIBLE);
                gioHangAdapter.notifyDataSetChanged();
            }

        }
    }


    public static void eventUltil() {
        long tongtien = 0;
        for (int i = 0; i < MainActivity.gioHangArrayList.size(); i++) {
            tongtien += Long.parseLong(MainActivity.gioHangArrayList.get(i).getGiasp());
        }
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(tongtien);
        txtGiatri.setText(str1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuGioHang:
                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkData() {
        if (MainActivity.gioHangArrayList.size() <= 0) {
            lvGiohang.deferNotifyDataSetChanged();
            txtThongbao.setVisibility(View.VISIBLE);
            lvGiohang.setVisibility(View.INVISIBLE);
        } else {
            lvGiohang.deferNotifyDataSetChanged();
            txtThongbao.setVisibility(View.INVISIBLE);
            lvGiohang.setVisibility(View.VISIBLE);
        }
    }

    private void anhxa() {
        lvGiohang = findViewById(R.id.lvGiohang);
        txtThongbao = findViewById(R.id.txtThongbao);
        txtTongTien = findViewById(R.id.txtTongTien);
        txtGiatri = findViewById(R.id.txtGiatri);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        btnMuaHang = findViewById(R.id.btnMuaHang);
        toolbarThanhToan = findViewById(R.id.toolbarThanhToan);

        gioHangAdapter = new GioHangAdapter(getApplicationContext(), MainActivity.gioHangArrayList);
        lvGiohang.setAdapter(gioHangAdapter);


    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarThanhToan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarThanhToan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
