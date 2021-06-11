package com.example.projectandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectandroid.R;
import com.example.projectandroid.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RauAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> sanphamArrayList;

    public RauAdapter(Context context, ArrayList<Sanpham> loaispArrayList) {
        this.context = context;
        this.sanphamArrayList = loaispArrayList;
    }

    @Override
    public int getCount() {
        return sanphamArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return sanphamArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHoder {
        ImageView imgTraiCay;
        TextView txtTenTraiCay, txtGiaTraiCay,txtMotaTraiCay;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoder viewHoder = null;
        if(view ==null){
            viewHoder= new ViewHoder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.dong_traicay,null);
            viewHoder.imgTraiCay = view.findViewById(R.id.imgTraiCay);
            viewHoder.txtTenTraiCay = view.findViewById(R.id.txtTenTraiCay);
            viewHoder.txtGiaTraiCay = view.findViewById(R.id.txtGiaTraiCay);
            viewHoder.txtMotaTraiCay = view.findViewById(R.id.txtMotaTraiCay);
            view.setTag(viewHoder);

        }else {
            viewHoder= (ViewHoder) view.getTag();

        }
        Sanpham sanpham= sanphamArrayList.get(i);
        viewHoder.txtTenTraiCay.setText(sanpham.getTensanpham());

        viewHoder.txtMotaTraiCay.setText(sanpham.getMotasanpham());
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format( Integer.parseInt(sanpham.getGia()));
        viewHoder.txtGiaTraiCay.setText(str1);
        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHoder.imgTraiCay);

        return view;
    }
}
