package com.example.projectandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.example.projectandroid.model.Account;
import com.example.projectandroid.ultil.Server;

import java.util.HashMap;
import java.util.Map;

public class EditAccount extends AppCompatActivity {

    EditText editId,editUserName,editMail,editPass,editPhone,editRole;
    ImageView imgBack;
    Button btnSave;
    String url = Server.duongdancapnhatacc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        Intent intent=getIntent();
        Account account=(Account) intent.getSerializableExtra("dataA");
        Toast.makeText(this,account.getTen(),Toast.LENGTH_SHORT).show();

        Anhxa();
        editId.setText(String.valueOf(account.getIdAcc()));
        editMail.setText(account.getEmail());
        editUserName.setText(account.getTen());
        editPass.setText(account.getMatkhau());
        editPhone.setText(account.getSodt());
        editRole.setText(account.getQuyen());
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EditAccount.this,ManagerAccount.class);
                startActivity(i);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail=editMail.getText().toString().trim();
                String ten=editUserName.getText().toString().trim();
                String matkhau=editPass.getText().toString().trim();
                String sodt=editPhone.getText().toString().trim();
                String quyen=editRole.getText().toString().trim();
                if(mail.isEmpty()||ten.isEmpty()||matkhau.isEmpty()||sodt.isEmpty()||quyen.isEmpty()){
                    Toast.makeText(EditAccount.this,"Vui lòng nhập đủ giá trị",Toast.LENGTH_SHORT).show();
                }
                else{
                    editacc(url);

                }


            }
        });

    }
    private void editacc(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast.makeText(EditAccount.this, "Edit Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditAccount.this,ManagerAccount.class));
                        }else{
                            Toast.makeText(EditAccount.this,"Failed",Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditAccount.this,"Xrl",Toast.LENGTH_SHORT).show();
                Log.d("AAA","Looi"+error.toString());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("id",editId.getText().toString().trim());
                params.put("email",editMail.getText().toString().trim());
                params.put("ten",editUserName.getText().toString().trim());
                params.put("matkhau",editPass.getText().toString().trim());
                params.put("sodt",editPhone.getText().toString().trim());
                params.put("quyen",editRole.getText().toString().trim());
                System.out.println(params);



                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void Anhxa() {
        editId=findViewById(R.id.idAccEdit);
        editUserName=findViewById(R.id.username);
        editMail=findViewById(R.id.mail);
        editPass=findViewById(R.id.password);
        editPhone=findViewById(R.id.phone);
        editRole=findViewById(R.id.role);
        imgBack=(ImageView)findViewById(R.id.imgBack);
        btnSave=(Button)findViewById(R.id.idEditAcc);
    }
}