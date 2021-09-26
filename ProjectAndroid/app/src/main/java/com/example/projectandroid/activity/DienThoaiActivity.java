package com.example.projectandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectandroid.R;
import com.example.projectandroid.adapter.DienThoaiAdapter;
import com.example.projectandroid.model.Sanpham;
import com.example.projectandroid.ultil.CheckConnection;
import com.example.projectandroid.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class    DienThoaiActivity extends AppCompatActivity {
    private int idDienthoai = 6;
    private int page = 1;
    Toolbar toolbar;
    Boolean isLoading = false;
    ListView lvDienThoai;
    SearchView searchView;
    DienThoaiAdapter dienThoaiAdapter;
    ArrayList<Sanpham> DienThoais;
    View footerview;
    mHanler mHanler;
    boolean limitData = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
        AnhXa();


        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            GetIDLoaiSP();
            ActionToolbar();
            GetData(page);
            LoadMoreData();
            initSearchwidgets();
        } else {
            CheckConnection.ShowToast_short(getApplicationContext(), "bạn hãy kiểm tra kết nối");
            finish();
        }
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
    //serach and filter
    public void initSearchwidgets(){

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Sanpham> lisSearch=new ArrayList<Sanpham>();
                for (Sanpham sanpham:DienThoais){
                    if(sanpham.getTensanpham().toLowerCase().contains(newText.toLowerCase())){
                        lisSearch.add(sanpham);
                    }
                }
                dienThoaiAdapter =new DienThoaiAdapter(DienThoaiActivity.this,lisSearch);
                lvDienThoai.setAdapter(dienThoaiAdapter);
                return false;
            }
        });

    }

    private void LoadMoreData() {
        lvDienThoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ChiTietSanPhamActivity.class);

                intent.putExtra("thongtinsanpham", DienThoais.get(i));
                startActivity(intent);
            }
        });
        lvDienThoai.setOnScrollListener(new AbsListView.OnScrollListener() {
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

    private void GetData(int page) {
        final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.duongdanThietbi + String.valueOf(page), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response != null && response.length() != 2) {
                    lvDienThoai.removeFooterView(footerview);

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
                            DienThoais.add(sanpham);
                            dienThoaiAdapter.notifyDataSetChanged();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    limitData = true;
                    lvDienThoai.removeFooterView(footerview);
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
                param.put("idloaisp", String.valueOf(idDienthoai));
                return param;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarDienThoai);
        lvDienThoai = findViewById(R.id.lvDienThoai);
        DienThoais = new ArrayList<>();
searchView=(SearchView)findViewById(R.id.productSearch);
        dienThoaiAdapter = new DienThoaiAdapter(DienThoaiActivity.this, DienThoais);
        lvDienThoai.setAdapter(dienThoaiAdapter);
        dienThoaiAdapter.notifyDataSetChanged();
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.processbar, null);
        mHanler = new mHanler();

    }

    private void GetIDLoaiSP() {
        idDienthoai = getIntent().getIntExtra("idDienthoai", 7);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public class mHanler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:
                    lvDienThoai.addFooterView(footerview);
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
}
