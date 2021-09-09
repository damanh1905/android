package com.example.projectandroid.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectandroid.R;
import com.example.projectandroid.adapter.listviewhangsanphamAdapter;
import com.example.projectandroid.model.Sanpham;
import com.example.projectandroid.ultil.CheckConnection;
import com.example.projectandroid.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class hangsamsungActivity extends AppCompatActivity {
    public static int idhang = 6;
    public static int idnhanh = 6;

    private int page = 1;


    Toolbar toolbarhang;
    Boolean isLoading = false;
    ListView lvhang;
    SearchView searchViewhang;
    listviewhangsanphamAdapter listviewhangsanphamadapter;
    ArrayList<Sanpham> hangs;
    View footerview;
    mHanler mHanler;
    boolean limitData = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hangsamsung);



        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            GetIDLoaiSP();
            AnhXa();
            ActionToolbar();
            GetData(page);
            LoadMoreData();
            initSearchwidgets();
        } else {
            CheckConnection.ShowToast_short(getApplicationContext(), "bạn hãy kiểm tra kết nối");
            finish();
        }
    }
    private void AnhXa() {
        toolbarhang = findViewById(R.id.toolbarhang);
        lvhang = (ListView) findViewById(R.id.lvhang);
        hangs = new ArrayList<>();
        searchViewhang=(SearchView)findViewById(R.id.productSearchhang);
        listviewhangsanphamadapter = new listviewhangsanphamAdapter(hangsamsungActivity.this, hangs);
        lvhang.setAdapter(listviewhangsanphamadapter);
        listviewhangsanphamadapter.notifyDataSetChanged();
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.processbar, null);
        mHanler = new mHanler();

    }
    private void GetIDLoaiSP() {
        idhang = getIntent().getIntExtra("thongtinhangsanpham", 7);
//        hangsanpham sanpham = (hangsanpham) getIntent().getSerializableExtra("idhangsanpham");
//        hang=sanpham.getName();

//        hang=getIntent().getStringExtra("thongtinhangsanpham");


//                if (idnhanh == 0) {
//                    hang = "samsung";
//                }
//
//                if (idnhanh == 1) {
//                    hang = "oppo";
//
//                }



        }


    private void ActionToolbar() {
        setSupportActionBar(toolbarhang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarhang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(hangsamsungActivity.this,MainActivity.class);
                startActivity(i);

            }
        });
    }
    private void GetData(int page) {
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.getsanphamtheohang + String.valueOf(page), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null && response.length() != 2) {
                    lvhang.removeFooterView(footerview);

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            int id = jsonObject.getInt("id");
                            String tensanpham = jsonObject.getString("tensp");
                            int gia = jsonObject.getInt("giasp");
                            String hinhanhsanpham = jsonObject.getString("hinhanhsp");
                            String motasanpham = jsonObject.getString("mota");
                            int idloaisp = jsonObject.getInt("idloaisp");
                            Sanpham sanpham = new Sanpham(id, tensanpham, String.valueOf(gia), hinhanhsanpham, motasanpham, idloaisp);
                            hangs.add(sanpham);
                            listviewhangsanphamadapter.notifyDataSetChanged();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    limitData = true;
                    lvhang.removeFooterView(footerview);
                    CheckConnection.ShowToast_short(getApplicationContext(), "Đã hết dữ liệu");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<>();
                    param.put("idloaisp", String.valueOf(1));
                    param.put("hangsp","samsung");


                return param;
            }

        };
        requestQueue.add(stringRequest);

    }
    private void LoadMoreData() {
        lvhang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ChiTietSanPhamActivity.class);

                intent.putExtra("thongtinsanpham", hangs.get(i));
                startActivity(intent);
            }
        });
        lvhang.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firtItem, int VisibleItem, int TotalItem) {
                if ((firtItem + VisibleItem == TotalItem) && TotalItem != 0 && isLoading == false && limitData == false) {
                    isLoading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();

                }
            }
        });
    }
    public class mHanler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:
                    lvhang.addFooterView(footerview);
                    break;
                case 1:
                    GetData(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    public class ThreadData extends Thread {
        @Override
        public void run() {
            mHanler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHanler.obtainMessage(1);
            mHanler.sendMessage(message);
            super.run();
        }
    }
    public void initSearchwidgets(){

        searchViewhang.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Sanpham> lisSearch=new ArrayList<Sanpham>();
                for (Sanpham sanpham:hangs){
                    if(sanpham.getTensanpham().toLowerCase().contains(newText.toLowerCase())){
                        lisSearch.add(sanpham);
                    }
                }
                listviewhangsanphamadapter=new listviewhangsanphamAdapter(hangsamsungActivity.this,lisSearch);
                lvhang.setAdapter(listviewhangsanphamadapter);
                return false;
            }
        });

    }
}