package com.example.projectandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectandroid.R;
import com.example.projectandroid.model.Loaisp;
import com.example.projectandroid.model.user;
import com.example.projectandroid.ultil.CheckConnection;
import com.example.projectandroid.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class loginActivity extends AppCompatActivity {
    ImageView  fb,google;
    public static int iduser=0;
    EditText email,pass;
    Button login;
    TextView forgetpass;
    ArrayList<user> listuser;
    float v=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for changing status bar icon colors
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);
        Anhxa();
        animation();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enventButton();
                }
            });

        }



    }

    private void animation() {
        email.setTranslationX(0);
        pass.setTranslationX(0);
        forgetpass.setTranslationX(800);
        login.setTranslationX(800);
        fb.setTranslationY(300);
        google.setTranslationY(300);

        email.setAlpha(v);
        pass.setAlpha(v);
        forgetpass.setAlpha(v);
        login.setAlpha(v);
        fb.setAlpha(v);
        google.setAlpha(v);


        email.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
    }

    private void Anhxa() {
        email=findViewById(R.id.editTextEmail);
        pass=findViewById(R.id.editTextPassword);
        login=findViewById(R.id.cirLoginButton);
        forgetpass=findViewById(R.id.frogotpass);
        fb=findViewById(R.id.tab_fb);
        google=findViewById(R.id.tab_goole);
        listuser=new ArrayList<>();
    }

    public void onLoginClick(View View){
        startActivity(new Intent(this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_right,R.anim.stay);

    }
    private void enventButton(){


        final String tk=email.getText().toString().trim();
        final String mk=pass.getText().toString().trim();

        if(!tk.equals("") && !mk.equals("")){
            String url = Server.getuser;
            RequestQueue queue = Volley.newRequestQueue(loginActivity.this);


            final JsonArrayRequest stringRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    if(response!=null) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject1 = response.getJSONObject(i);
                                Log.d("testss", jsonObject1.toString());
                                int id = jsonObject1.getInt("id");
                                String name = jsonObject1.getString("Ten");
                                String email = jsonObject1.getString("Email");
                                String matkhau = jsonObject1.getString("matkhau");
                                String quyen = jsonObject1.getString("quyen");
                                user u=new user(id,email,name,matkhau,quyen);


                                if(tk.equals(u.getTen()) && mk.equals(u.getMatkhau()) && u.getQuyen().equals("user")){
                                    iduser=id;
                                    Log.d("uuuuuuuuuu", String.valueOf(loginActivity.iduser));
                                    Intent a = new Intent(loginActivity.this, MainActivity.class);
                                    startActivity(a);
                                    finish();

                                }if(tk.equals(u.getTen()) && mk.equals(u.getMatkhau()) && u.getQuyen().equals("admin")){
                                    Intent a = new Intent(loginActivity.this, GioHangActivity.class);
                                    startActivity(a);
                                    finish();
                                }


                                else {
                                    CheckConnection.ShowToast_short(loginActivity.this,"bạn đã nhập sai tài khoản hoặc mật khẩu");

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("testss", "khong doc duoc du lieu");
                }
            });

// thêm vào màn hình
            queue.add(stringRequest);


        }else {
            Toast.makeText(loginActivity.this, "tài khoản hoặc mật khẩu đang trống", Toast.LENGTH_SHORT).show();


        }


    }

}