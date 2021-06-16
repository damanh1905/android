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
import com.example.projectandroid.model.Sanpham;
import com.example.projectandroid.ultil.Server;

import java.util.HashMap;
import java.util.Map;

public class EditProduct extends AppCompatActivity {
    EditText editId,editName,editPri,editDes,editImg,editIdType,editHang;
    ImageView imgBack;
    Button btnSave;
    String url = Server.duongdancapnhatsp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        Intent intent=getIntent();
        Sanpham sanpham=(Sanpham) intent.getSerializableExtra("dataP");
        Toast.makeText(this,sanpham.getTensanpham(),Toast.LENGTH_SHORT).show();

        Anhxa();
        editId.setText(String.valueOf(sanpham.getIdsanpham()));
        editName.setText(sanpham.getTensanpham());
        editPri.setText(sanpham.getGia());
        editImg.setText(sanpham.getHinhanhsanpham());
        editDes.setText(sanpham.getMotasanpham());
        editIdType.setText(String.valueOf(sanpham.getIdloaisp()));
        editHang.setText(sanpham.getHang());
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(EditProduct.this,ManagerProduct.class);
                startActivity(i);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=editId.getText().toString().trim();
                String name=editName.getText().toString().trim();
                String price=editPri.getText().toString().trim();
                String img=editImg.getText().toString().trim();
                String des=editDes.getText().toString().trim();
                String idType=editIdType.getText().toString().trim();
                String hang=editHang.getText().toString().trim();
                if(id.isEmpty()||name.isEmpty()||price.isEmpty()||img.isEmpty()||des.isEmpty()||idType.isEmpty()||hang.isEmpty()){
                    Toast.makeText(EditProduct.this,"Vui lòng nhập đủ giá trị",Toast.LENGTH_SHORT).show();
                }
                else{
                    editsp(url);

                }


            }
        });

    }
    private void editsp(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast.makeText(EditProduct.this, "Edit Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditProduct.this,ManagerProduct.class));
                        }else{
                            Toast.makeText(EditProduct.this,"Failed",Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditProduct.this,"Xrl",Toast.LENGTH_SHORT).show();
                Log.d("AAA","Looi"+error.toString());

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("id",editId.getText().toString().trim());
                params.put("name",editName.getText().toString().trim());
                params.put("price",editPri.getText().toString().trim());
                params.put("img",editImg.getText().toString().trim());
                params.put("des",editDes.getText().toString().trim());
                params.put("idType",editIdType.getText().toString().trim());
                params.put("hang",editHang.getText().toString().trim());
                System.out.println(params);



                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    private void Anhxa() {
        editId=findViewById(R.id.idEdit);
        editName=findViewById(R.id.nameEdit);
        editPri=findViewById(R.id.priceEdit);
        editImg=findViewById(R.id.imgEdit);
        editDes=findViewById(R.id.desEdit);
        editIdType=findViewById(R.id.idTypeEdit);
        editHang=findViewById(R.id.hangEdit);
        imgBack=(ImageView)findViewById(R.id.imgBack);
        btnSave=(Button)findViewById(R.id.idEditProduct);
    }
}