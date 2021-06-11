package com.example.projectandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectandroid.R;
import com.example.projectandroid.model.Loaisp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaispAdapter extends BaseAdapter {
    Context context;
    ArrayList<Loaisp> loaispArrayList;

    public LoaispAdapter(Context context, ArrayList<Loaisp> loaispArrayList) {
        this.context = context;
        this.loaispArrayList = loaispArrayList;
    }

    @Override
    public int getCount() {
        return loaispArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return loaispArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHoder {
        TextView textViewloaisp;
        ImageView imageViewloaisp;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoder viewHoder = null;
        if(view ==null){
            viewHoder= new ViewHoder();
            // 2 tạo view
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.dong_listview_loaisp,null);
            //  tim vi tri
            viewHoder.textViewloaisp = view.findViewById(R.id.textViewtenloaisp);
            viewHoder.imageViewloaisp= view.findViewById(R.id.imageViewloaisp);
            view.setTag(viewHoder);

        }else {
            viewHoder= (ViewHoder) view.getTag();

        }
        // gan cho tưng vi tri
        Loaisp loaisp= (Loaisp) getItem(i);
        viewHoder.textViewloaisp.setText(loaisp.getLoaisp());
        Picasso.with(context).load(loaisp.getHiinhanh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHoder.imageViewloaisp);

        return view;
    }
}
