package com.example.projectandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectandroid.R;
import com.example.projectandroid.adapter.LoaispAdapter;
import com.example.projectandroid.adapter.SanphamAdapter;
import com.example.projectandroid.model.Loaisp;
import com.example.projectandroid.model.Sanpham;
import com.example.projectandroid.ultil.CheckConnection;
import com.example.projectandroid.ultil.GioHang;
import com.example.projectandroid.ultil.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    NavigationView navigationView;
    ListView lvManhinh;
    DrawerLayout drawerLayout;
    ArrayList<Loaisp> loaispArrayList;
    ArrayList<Sanpham> sanphamArrayList;
    SanphamAdapter sanphamAdapter;
    LoaispAdapter loaispAdapter;
    int id=0;
    String tenloaisp="";
    String hinhanhloaisp="";
    public static ArrayList<GioHang> gioHangArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            ActionBar();
            ActionViewFlipper();
            GetDuLieu();
            GetDuLieuSanphammoinhat();
            CatchOnItemListView();


        } else {
            CheckConnection.ShowToast_short(getApplicationContext(), "bạn hãy kiểm tra kết nối");
            finish();
        }
        if (gioHangArrayList != null) {

        } else {
            gioHangArrayList = new ArrayList<>();
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

    private void CatchOnItemListView() {
        lvManhinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "vui lòng kiểm tra kết nối", Toast.LENGTH_SHORT).show();
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, DienThoaiActivity.class);
                            intent.putExtra("idTraicay", 1);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "vui lòng kiểm tra kết nối", Toast.LENGTH_SHORT).show();
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, RauActivity.class);
                            intent.putExtra("idRau", 2);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "vui lòng kiểm tra kết nối", Toast.LENGTH_SHORT).show();
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, Map.class);
                            intent.putExtra("idCu", 3);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "vui lòng kiểm tra kết nối", Toast.LENGTH_SHORT).show();
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
                            Intent intent = new Intent(MainActivity.this, loginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "vui lòng kiểm tra kết nối", Toast.LENGTH_SHORT).show();
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;


                }
            }
        });
    }


    public void GetDuLieu() {

        String url = Server.duongdanloaisp;
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);


        final JsonArrayRequest stringRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if(response!=null) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject1 = response.getJSONObject(i);
                            Log.d("testss", jsonObject1.toString());
                            id = jsonObject1.getInt("id");
                            tenloaisp = jsonObject1.getString("tenloaisanpham");
                            hinhanhloaisp = jsonObject1.getString("hinhanhloaisanpham");
                            loaispArrayList.add(new Loaisp(id, tenloaisp, hinhanhloaisp));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                loaispArrayList.add(response.length() + 1, new Loaisp(2000, "Thong tin", "https://upload.wikimedia.org/wikipedia/commons/5/54/Information.png"));
                loaispArrayList.add(response.length() + 2, new Loaisp(2001, "Đăng xuất", "https://ak.picdn.net/shutterstock/videos/22592884/thumb/10.jpg"));
                loaispAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("testss", "khong doc duoc du lieu");
            }
        });

// thêm vào màn hình
        queue.add(stringRequest);

    }

    private void GetDuLieuSanphammoinhat() {

        // lây dữ liệu từ database
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.duongdanloaispmoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject1 = response.getJSONObject(i);
                        Log.d("apkss", jsonObject1.toString());

                        // lấy xong gán vào thêm vào arraylist để hiển thị
                        int id = jsonObject1.getInt("id");
                        String tensanpham = jsonObject1.getString("tensp");
                        int gia = jsonObject1.getInt("giasp");
                        String hinhanhsanpham = jsonObject1.getString("hinhanhsp");
                        String motasanpham = jsonObject1.getString("mota");
                        int idloaisp = jsonObject1.getInt("idloaisp");
                        sanphamArrayList.add(new Sanpham(id, tensanpham, String.valueOf(gia), hinhanhsanpham, motasanpham, idloaisp));
                        sanphamAdapter.notifyDataSetChanged();
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


    private void ActionViewFlipper() {
        // thêm mấy cái hình vào mảng
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://image.thanhnien.vn/660/uploaded/minhnguyet/2019_10_10/trai-cay_boqu.jpg");
        mangquangcao.add("https://cdn.tuoitre.vn/thumb_w/640/2018/3/24/photo-1-152187920921975519736.jpg");
        mangquangcao.add("https://www.sapo.vn/blog/wp-content/uploads/2017/03/buon-ban-kinh-doanh-hoa-qua-tuoi-trai-cay-sach-online1.jpg");
        mangquangcao.add("https://sohanews.sohacdn.com/thumb_w/660/2019/1/11/cac-loai-trai-cay-1547174171357506224603-crop-1547174182816570687384.jpg");

        // chuyển đổi dạng url sang hình ảnh để hiển thị
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        // thời gian hiển thị 50000
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        // hành
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);

    }

    private void ActionBar() {
        //set hanh động
        setSupportActionBar(toolbar);
        // thêm mũi tên
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // thêm hình sắp xếp
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        // thêm bấm vào nó xổ ra menu
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void Anhxa() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        lvManhinh = (ListView) findViewById(R.id.lvManhinh);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        AdapterloaiSP();
        AdapterSPmoinhat();

    }

    public void AdapterloaiSP() {
        loaispArrayList = new ArrayList<>();
        loaispArrayList.add(0, new Loaisp(0, "Trang Chính", "https://img.icons8.com/cotton/2x/home.png"));
        loaispAdapter = new LoaispAdapter(MainActivity.this, loaispArrayList);
        lvManhinh.setAdapter(loaispAdapter);
        loaispAdapter.notifyDataSetChanged();

    }

    public void AdapterSPmoinhat() {
        sanphamArrayList = new ArrayList<>();
        sanphamAdapter = new SanphamAdapter(MainActivity.this, sanphamArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        recyclerView.setAdapter(sanphamAdapter);

    }

}
