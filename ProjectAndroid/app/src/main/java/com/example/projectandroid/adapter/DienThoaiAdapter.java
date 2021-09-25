package com.example.projectandroid.adapter;

import android.content.Context;
import android.text.TextUtils;
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

public class DienThoaiAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> sanphamArrayList;

    public DienThoaiAdapter(Context context, ArrayList<Sanpham> loaispArrayList) {
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
        ImageView imgDienThoai;
        TextView txtTenDienThoai, txtGiaDienThoai, txtMotaDienThoai;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       ViewHoder viewHoder = null;
        if(view ==null){
            viewHoder= new ViewHoder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.dong_dienthoai,null);
            viewHoder.imgDienThoai = view.findViewById(R.id.imgDienThoai);
            viewHoder.txtTenDienThoai = view.findViewById(R.id.txtTenDienThoai);
            viewHoder.txtGiaDienThoai = view.findViewById(R.id.txtGiaDienThoai);
            viewHoder.txtMotaDienThoai = view.findViewById(R.id.txtMotaDienThoai);
            view.setTag(viewHoder);

        }else {
            viewHoder= (ViewHoder) view.getTag();

        }
        Sanpham sanpham= sanphamArrayList.get(i);
        viewHoder.txtTenDienThoai.setText(sanpham.getTensanpham());
        viewHoder.txtMotaDienThoai.setMaxLines(2);
        viewHoder.txtMotaDienThoai.setEllipsize(TextUtils.TruncateAt.END);
        viewHoder.txtMotaDienThoai.setText(sanpham.getMotasanpham());
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format( Integer.parseInt(sanpham.getGia()));
        viewHoder.txtGiaDienThoai.setText(str1);
        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHoder.imgDienThoai);

        return view;
    }

}
