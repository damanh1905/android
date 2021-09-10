package com.example.projectandroid.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

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
import com.example.projectandroid.ultil.Server;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText editTextName,editTextEmail,editTextMobile,editTextPassword;
    Button cirRegisterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();
        anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            EventButton();
        }
    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));

        }
    }

    private void anhxa() {
        editTextName=findViewById(R.id.editTextName);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextMobile=findViewById(R.id.editTextMobile);
        editTextPassword=findViewById(R.id.editTextPassword);
        cirRegisterButton=findViewById(R.id.cirRegisterButton);
    }
        private  void EventButton(){
            cirRegisterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   final String tendangnhap=editTextName.getText().toString().trim();
                    final String Email=editTextEmail.getText().toString().trim();
                    final String sodt=editTextMobile.getText().toString().trim();
                    final String pass=editTextPassword.getText().toString().trim();
                    if(tendangnhap.length()>0 && Email.length()>0 && sodt.length()>0 && pass.length()>0){
                        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                        StringRequest stringRequest=new StringRequest(Request.Method.POST, Server.insertuser, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                            Log.d("iduser",response);
                                CheckConnection.ShowToast_short(getApplicationContext(),"đăng kí thành công");
                                

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                HashMap<String,String> hashMap=new HashMap<String,String>();
                                hashMap.put("tendangnhap",tendangnhap);
                                hashMap.put("email",Email);
                                hashMap.put("sodienthoaidk",sodt);
                                hashMap.put("pass",pass);


                                return hashMap;
                            }
                        };

requestQueue.add(stringRequest);
                    }else{
                        CheckConnection.ShowToast_short(getApplicationContext(),"hay nhap day du thong tin");
                    }
                }
            });

        }
    public void onLoginClick(View view){
        startActivity(new Intent(this,loginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }
}