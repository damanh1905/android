package com.example.projectandroid.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectandroid.R;

public class thongtincanhan  extends Fragment {
    TextView email,pass;
    Button login;
    TextView forgetpass;
    float v=0;
    ViewGroup root;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root=(ViewGroup) inflater.inflate(R.layout.thongtincanhan,container,false);
//        email=root.findViewById(R.id.Email);
//        pass=root.findViewById(R.id.Pass);
//
//
//
//        email.setTranslationX(800);
//        pass.setTranslationX(800);
//
//
//        email.setAlpha(v);
//        pass.setAlpha(v);
//
//
//        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
//        pass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
//
        return root;
    }
}
