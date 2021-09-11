package com.example.projectandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectandroid.R;
import com.example.projectandroid.adapter.SanPhamAdminAdapter;
import com.example.projectandroid.model.Sanpham;
import com.example.projectandroid.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ManagerProduct extends AppCompatActivity {
    ImageView add,edit,del,back;


    RecyclerView recyclerView;
    ArrayList<Sanpham> sanphamArrayList;
    SanPhamAdminAdapter sanPhamAdminAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_product);
        Anhxa();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerProduct.this,AddProduct.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerProduct.this,Admin.class));
            }
        });
        GetDuLieuSanphammoinhat();




    }

    private void GetDuLieuSanphammoinhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(ManagerProduct.this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanloaispmoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                sanphamArrayList.clear();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject1 = response.getJSONObject(i);
                        Log.d("apkss", jsonObject1.toString());
                        // lấy xong gán vào thêm vào arraylist để hiển thị
                        int idsanpham=jsonObject1.getInt("id");
                        String tensanpham = jsonObject1.getString("tensp");
                        String giasanpham = jsonObject1.getString("giasp");
                        String hinhanhsp = jsonObject1.getString("hinhanhsp");
                        String motasp = jsonObject1.getString("mota");
                        int idloaisanpham=jsonObject1.getInt("idloaisp");
                        String hang=jsonObject1.getString("hang");
                        sanphamArrayList.add(new Sanpham(idsanpham,tensanpham,giasanpham,hinhanhsp,motasp,idloaisanpham,hang));
                        sanPhamAdminAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void Anhxa() {
        add=findViewById(R.id.imageViewAdd);
        back=findViewById(R.id.imgBack);
        recyclerView=findViewById(R.id.recyclerView);
        AdapterSPmoinhat();
    }
    public void AdapterSPmoinhat() {
        sanphamArrayList = new ArrayList<>();
        sanPhamAdminAdapter = new SanPhamAdminAdapter(ManagerProduct.this, sanphamArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(ManagerProduct.this, 1));
        recyclerView.setAdapter(sanPhamAdminAdapter);

    }
    public void deleteSp(int id){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.duongdanxoasp, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(ManagerProduct.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    GetDuLieuSanphammoinhat();

                }else{
                    Toast.makeText(ManagerProduct.this,"Thất bại",Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ManagerProduct.this,"Xrl",Toast.LENGTH_SHORT).show();
                Log.d("AAA","Looi"+error.toString());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("id",String.valueOf(id));

                System.out.println(params);



                return params;
            }
        };
        requestQueue.add(stringRequest);
    }


}