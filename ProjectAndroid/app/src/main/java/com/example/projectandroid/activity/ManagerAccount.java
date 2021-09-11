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
import com.example.projectandroid.adapter.AccountAdapter;
import com.example.projectandroid.model.Account;
import com.example.projectandroid.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ManagerAccount extends AppCompatActivity {
    ImageView imgback,add;
    RecyclerView recyclerView;
    ArrayList<Account> accountArrayList;
    AccountAdapter accountAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_account);
        Anhxa();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerAccount.this,AddAccount.class));
            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerAccount.this,Admin.class));
            }
        });
        GetDuLieuAccount();
    }
    private void GetDuLieuAccount() {
        RequestQueue requestQueue = Volley.newRequestQueue(ManagerAccount.this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdandataacc, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                accountArrayList.clear();

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject1 = response.getJSONObject(i);
                        Log.d("apkss", jsonObject1.toString());
                        // lấy xong gán vào thêm vào arraylist để hiển thị
                        int id=jsonObject1.getInt("id");
                        String email = jsonObject1.getString("email");
                        String ten = jsonObject1.getString("ten");
                        String matkhau = jsonObject1.getString("matkhau");
                        String sodt = jsonObject1.getString("sodt");
                        String quyen = jsonObject1.getString("quyen");

                        accountArrayList.add(new Account(id,email,ten,matkhau,sodt,quyen));
                        System.out.println(accountArrayList);
                        accountAdapter.notifyDataSetChanged();
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
        imgback=findViewById(R.id.imgBack);
        recyclerView=findViewById(R.id.recyclerViewAcc);
        AdapterAccmoinhat();
    }
    public void AdapterAccmoinhat() {
        accountArrayList = new ArrayList<>();
        accountAdapter = new AccountAdapter(ManagerAccount.this, accountArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(ManagerAccount.this, 1));
        recyclerView.setAdapter(accountAdapter);

    }

    public void deleteAcc(int id) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.duongdanxoaacc, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("success")){
                    Toast.makeText(ManagerAccount.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    GetDuLieuAccount();

                }else{
                    Toast.makeText(ManagerAccount.this,"Thất bại",Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ManagerAccount.this,"Xrl",Toast.LENGTH_SHORT).show();
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