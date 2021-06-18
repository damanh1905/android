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
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectandroid.R;
import com.example.projectandroid.adapter.LoaispAdapter;
import com.example.projectandroid.adapter.SanphamAdapter;
import com.example.projectandroid.adapter.hangsanphamAdapter;
import com.example.projectandroid.adapter.loaisanphambanAdapter;
import com.example.projectandroid.model.Loaisp;
import com.example.projectandroid.model.Sanpham;
import com.example.projectandroid.model.hangsanpham;
import com.example.projectandroid.model.loaisanpham2;
import com.example.projectandroid.ultil.CheckConnection;
import com.example.projectandroid.ultil.GioHang;
import com.example.projectandroid.ultil.Server;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    public  static  int pos1=0;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView;
    RecyclerView recyclerCategories,recyclerItems;
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
    BottomNavigationView bottomNavigationView;
    public static ArrayList<GioHang> gioHangArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            ActionBar();
            enventButomNav();
            ActionViewFlipper();
            GetDuLieu();
            GetDuLieuSanphammoinhat();
            CatchOnItemListView();
            setCategories();
            setFoodItem(0);
//            CatchOnItemListviewhangsanpham();



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

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
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

                loaispArrayList.add(response.length() + 1, new Loaisp(2000, "Thông tin", "https://upload.wikimedia.org/wikipedia/commons/5/54/Information.png"));
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
        mangquangcao.add("http://banlaptopgiare.com/wp-content/uploads/2019/12/laptop-gia-re-sinh-vien.jpg");
        mangquangcao.add("https://tinhocmiennam.com/wp-content/uploads/2018/06/laptop-banner.jpg");
        mangquangcao.add("https://cdn.tgdd.vn/Files/2018/11/27/1134121/bannerlaptopthang12_800x450.png");
        mangquangcao.add("https://tctshop.com/wp-content/uploads/2015/12/banner-tctshop-com-1.jpg");

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
        bottomNavigationView=(BottomNavigationView) findViewById(R.id.nav_bottom) ;
        recyclerCategories = findViewById(R.id.recycler_categories);
        recyclerItems = findViewById(R.id.recycler_food);

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
    public  void enventButomNav(){
    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
          Intent i;
           switch (menuItem.getItemId()){
               case R.id.navigation_update:
                    i=new Intent(MainActivity.this,GioHangActivity.class);
                   startActivity(i);
                   break;
               case R.id.navigation_team:
                    i=new Intent(MainActivity.this,Account.class);
                   startActivity(i);
                   break;
               case R.id.navigation_team_thongbao:
                   i=new Intent(MainActivity.this,Notification.class);
                   startActivity(i);
                   break;

           }
            return true;
        }
    });
// attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());
}
public void  setCategories(){
    List<loaisanpham2> data = new ArrayList<>();

    loaisanpham2 foodCategory = new loaisanpham2("Điện thoại",R.drawable.phone_android_24);
    loaisanpham2 foodCategory2 = new loaisanpham2("máy tính",R.drawable.laptop_24);
    loaisanpham2 foodCategory3 = new loaisanpham2("máy tính",R.drawable.ic_baseline_tablet_mac_24);
    loaisanpham2 foodCategory4 = new loaisanpham2("máy tính",R.drawable.camera_alt_24);
    loaisanpham2 foodCategory5 = new loaisanpham2("máy tính",R.drawable.desktop_mac_24);


    data.add(foodCategory);
    data.add(foodCategory2);
    data.add(foodCategory3);
    data.add(foodCategory4);
    data.add(foodCategory5);

    loaisanphambanAdapter foodCategoryAdapter = new loaisanphambanAdapter(data, MainActivity.this, new loaisanphambanAdapter.OnCategoryClick() {
        @Override
        public void onClick(int pos) {
            setFoodItem(pos);
        }
    });

    recyclerCategories.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL,false));
    recyclerCategories.setAdapter(foodCategoryAdapter);
    foodCategoryAdapter.notifyDataSetChanged();


}
    private void setFoodItem(int pos){
        pos1=pos;
        List<hangsanpham> foodItems = new ArrayList<>();

        switch (pos) {
            case 2:
                hangsanpham foodItem = new hangsanpham("samsung",4.5f,14,R.drawable.samsung);
                hangsanpham foodItem2 = new hangsanpham("Apple ",5f,14,R.drawable.apple);
                hangsanpham foodItem3 = new hangsanpham("hihi",4f,14,R.drawable.xixaomi);

                foodItems.add(foodItem);
                foodItems.add(foodItem2);
                foodItems.add(foodItem3);

                break;
            case 1:
                hangsanpham foodItem5 = new hangsanpham("dell",4.5f,14,R.drawable.dell);
                hangsanpham foodItem6 = new hangsanpham("lenovo",5f,14,R.drawable.lenovo);


                foodItems.add(foodItem5);
                foodItems.add(foodItem6);



                break;
            case 0:
                hangsanpham foodItem9 = new hangsanpham("samsung",4.5f,14,R.drawable.samsung);
                hangsanpham foodItem10 = new hangsanpham("oppo",5f,14,R.drawable.oppo);
                hangsanpham foodItem11 = new hangsanpham("apple",5f,14,R.drawable.apple);
                hangsanpham foodItem12 = new hangsanpham("xiaomi",5f,14,R.drawable.xixaomi);
                hangsanpham foodItem13 = new hangsanpham("xiaomi",5f,14,R.drawable.xixaomi);
                hangsanpham foodItem14 = new hangsanpham("xiaomi",5f,14,R.drawable.xixaomi);
                hangsanpham foodItem15 = new hangsanpham("xiaomi",5f,14,R.drawable.xixaomi);
                foodItems.add(foodItem9);
                foodItems.add(foodItem10);
                foodItems.add(foodItem11);
                foodItems.add(foodItem12);
                foodItems.add(foodItem13);
                foodItems.add(foodItem14);
                foodItems.add(foodItem15);





                break;
        }

        hangsanphamAdapter foodAdapter = new hangsanphamAdapter(foodItems, MainActivity.this, new hangsanphamAdapter.OnCategoryClick() {
            @Override
            public void onClick(int pos) {
                sethang(pos);
            }
        });
        recyclerItems.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false));

        recyclerItems.setAdapter(foodAdapter);
        foodAdapter.notifyDataSetChanged();
    }
public void sethang(int pos) {
    if (pos1 == 0) {
        if (pos == 0) {
            Intent intent = new Intent(MainActivity.this, hangsamsungActivity.class);
            intent.putExtra("thongtinhangsanpham", pos);
            startActivity(intent);

        }
    }else if(pos1==1){
         if (pos == 0) {
            Intent intent = new Intent(MainActivity.this, hangdelllaptopMainActivity.class);
            intent.putExtra("thongtinhangsanpham", pos);
            startActivity(intent);
        }
    }
}
}