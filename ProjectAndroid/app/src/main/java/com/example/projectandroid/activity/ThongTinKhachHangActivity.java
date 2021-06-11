package com.example.projectandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectandroid.R;
import com.example.projectandroid.ultil.CheckConnection;
import com.example.projectandroid.ultil.GioHang;
import com.example.projectandroid.ultil.Server;

import java.util.HashMap;
import java.util.Map;

public class ThongTinKhachHangActivity extends AppCompatActivity {
    EditText editNameKH, editsdtKH, editemailKH;
    Button btnXacnhan, btnTroVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        anhxa();
        control();
        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            control();
        }
    }

    private void control() {





                btnXacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final String ten = editNameKH.getText().toString().trim();
                        final String sdt = editsdtKH.getText().toString().trim();
                        final String email = editemailKH.getText().toString().trim();

                        if (ten.equals("") || sdt.equals("") || email.equals("")) {
                            Toast.makeText(getApplicationContext(), "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();

                        } else {
                            final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.đuongandonhang, new Response.Listener<String>() {
                                @Override
                                public void onResponse(final String response) {
                                    Log.d("sdfsfsdf", response);
                                    RequestQueue requestQueue1 = Volley.newRequestQueue(getApplicationContext());
                                    StringRequest stringRequest1 = new StringRequest(Request.Method.POST, Server.đuongandonhang1, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {

                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    }) {
                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            HashMap<String, String> hashMap = new HashMap<>();

                                            for (GioHang gioHang1 : MainActivity.gioHangArrayList) {
                                                hashMap.put("madonhang", response);
                                                hashMap.put("tensanpham", gioHang1.getTensp());
                                                hashMap.put("giasanpham", gioHang1.getGiasp());
                                                hashMap.put("soluong", String.valueOf(gioHang1.getSoluongsp()));
                                                return  hashMap;
                                            }


                                            return hashMap;

                                        }
                                    };



                                    requestQueue1.add(stringRequest1);
                                    Intent intent = new Intent(ThongTinKhachHangActivity.this, ThongtindonhangActivity.class);
                                    startActivity(intent);
                                    MainActivity.gioHangArrayList.clear();

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }) {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    HashMap<String, String> hashMap = new HashMap<>();
                                    hashMap.put("tenkhachhang", ten);
                                    hashMap.put("sodienthoai", sdt);
                                    hashMap.put("email", email);
                                    return hashMap;
                                }
                            };
                            requestQueue.add(stringRequest);
                        }
                    }
                });




    }

    private void anhxa() {
        editNameKH = findViewById(R.id.editNameKH);
        editsdtKH = findViewById(R.id.editsdtKH);
        editemailKH = findViewById(R.id.editemailKH);
        btnXacnhan = findViewById(R.id.btnXacnhan);
        btnTroVe = findViewById(R.id.btnTroVe);
    }
}
