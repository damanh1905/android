package com.example.projectandroid.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectandroid.R;
import com.example.projectandroid.ultil.Server;

import java.util.HashMap;
import java.util.Map;

public class FragmentInsertActivity extends Fragment {
    EditText editnamesp, editgiasp, edithinhsp, editmotasp, editloaisp;
    Button btnadd, btnhomes;
    View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_insert, container, false);

        editnamesp = view.findViewById(R.id.editnamesp);
        editgiasp = view.findViewById(R.id.editgiasp);
        edithinhsp = view.findViewById(R.id.edithinhsp);
        editmotasp = view.findViewById(R.id.editmotasp);
        editloaisp = view.findViewById(R.id.editloaisp);
        btnadd = view.findViewById(R.id.btnadd);
        btnhomes = view.findViewById(R.id.btnhomes);


        control();
        return view;

    }

    private void control() {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String namesp = editnamesp.getText().toString();
                final String giasp = editgiasp.getText().toString();
                final String hinhsp = edithinhsp.getText().toString();
                final String motasp = editmotasp.getText().toString();
                final String idloai = editloaisp.getText().toString();

                if (namesp.equals("") || giasp.equals("") || hinhsp.equals("") || motasp.equals("") || idloai.equals("")) {
                    Toast.makeText(view.getContext(), "Hãy nhập thông tin đầy đủ", Toast.LENGTH_SHORT).show();
                } else {
                    final RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.insertDB, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
                    ) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("tensanpham", namesp);
                            hashMap.put("gia", giasp);
                            hashMap.put("hinhanhsanpham", hinhsp);
                            hashMap.put("motasanpham", motasp);
                            hashMap.put("idloaisp", idloai);
                            return hashMap;

                        }
                    };
                    editnamesp.setText("");
                    editgiasp.setText("");
                    edithinhsp.setText("");
                    editmotasp.setText("");
                    editloaisp.setText("");
                    requestQueue.add(stringRequest);
                }


            }
        });
    }


}
