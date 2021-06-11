package com.example.projectandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.projectandroid.R;
import com.example.projectandroid.model.Sanpham;
import com.example.projectandroid.ultil.GioHang;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

public class ChiTietSanPhamActivity extends AppCompatActivity {
    Toolbar toolbarChiTiet;
    TextView txtTenChiTiet, txtGiaChiTiet, txtMotaChiTiet;
    ImageView imgChiTiet;
    Spinner spinner;
    Button btnGioHang;
    int id;
    String tenChitiet, motasanpham, gia, hinhanhchitiet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        anhXa();
        actionToolbar();
        getInfmation();
        cachEventSpiner();
        eventButton();
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

    public  void eventButton() {
        btnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.gioHangArrayList.size() > 0) {
                    int s1 = Integer.parseInt((spinner.getSelectedItem().toString()));
                    boolean exit = false;
                    for (int i = 0; i < MainActivity.gioHangArrayList.size(); i++) {
                        if (MainActivity.gioHangArrayList.get(i).getIdsp() == id) {
                            MainActivity.gioHangArrayList.get(i).setSoluongsp(MainActivity.gioHangArrayList.get(i).getSoluongsp() + s1);
                            if (MainActivity.gioHangArrayList.get(i).getSoluongsp() > 10) {
                                MainActivity.gioHangArrayList.get(i).setSoluongsp(10);
                            }
                            MainActivity.gioHangArrayList.get(i).setGiasp(String.valueOf(Integer.parseInt(gia) * MainActivity.gioHangArrayList.get(i).getSoluongsp()));
                            exit = true;
                        }
                    }
                    if (exit == false) {
                        int soluong = Integer.parseInt((spinner.getSelectedItem().toString()));
                        long giamoi = soluong * Integer.parseInt(gia);
                        MainActivity.gioHangArrayList.add(new GioHang(id, tenChitiet, String.valueOf(giamoi), hinhanhchitiet, soluong));
                    }
                } else {
                    int soluong = Integer.parseInt((spinner.getSelectedItem().toString()));
                    long giamoi = soluong * Integer.parseInt(gia);
                    MainActivity.gioHangArrayList.add(new GioHang(id, tenChitiet, String.valueOf(giamoi), hinhanhchitiet, soluong));
                }
                Intent intent = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void cachEventSpiner() {
        Integer[] soluong = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void getInfmation() {

        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        tenChitiet = sanpham.getTensanpham();
        motasanpham = sanpham.getMotasanpham();
        gia = sanpham.getGia();
        hinhanhchitiet = sanpham.getHinhanhsanpham();
        id = sanpham.getIdsanpham();
        txtTenChiTiet.setText(tenChitiet);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(Integer.parseInt(gia));
        txtGiaChiTiet.setText(str1);
        txtMotaChiTiet.setText(motasanpham);
        Picasso.with(getApplicationContext()).load(hinhanhchitiet)
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(imgChiTiet);
    }

    private void actionToolbar() {
        setSupportActionBar(toolbarChiTiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChiTiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void anhXa() {
        toolbarChiTiet = findViewById(R.id.toolbarChiTiet);
        txtTenChiTiet = findViewById(R.id.txtTenChiTiet);
        txtGiaChiTiet = findViewById(R.id.txtGiaChiTiet);
        txtMotaChiTiet = findViewById(R.id.txtMotaChiTiet);
        imgChiTiet = findViewById(R.id.imgChiTiet);
        spinner = findViewById(R.id.spinner);
        btnGioHang = findViewById(R.id.btnGioHang);
    }
}
