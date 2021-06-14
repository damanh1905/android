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
        ImageView imglaptop;
        TextView txtTenlaptop, txtGialaptop,txtMotalaptop;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoder viewHoder = null;
        if(view ==null){
            viewHoder= new ViewHoder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.dong_rau,null);
            viewHoder.imglaptop = view.findViewById(R.id.imglaptop);
            viewHoder.txtTenlaptop = view.findViewById(R.id.txtTenlaptop);
            viewHoder.txtGialaptop = view.findViewById(R.id.txtGialatop);
            viewHoder.txtMotalaptop = view.findViewById(R.id.txtMotalaptop);
            view.setTag(viewHoder);

        }else {
            viewHoder= (ViewHoder) view.getTag();

        }
        Sanpham sanpham= sanphamArrayList.get(i);
        viewHoder.txtTenlaptop.setText(sanpham.getTensanpham());

        viewHoder.txtMotalaptop.setMaxLines(2);
        viewHoder.txtMotalaptop.setEllipsize(TextUtils.TruncateAt.END);
        viewHoder.txtMotalaptop.setText(sanpham.getMotasanpham());
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format( Integer.parseInt(sanpham.getGia()));
        viewHoder.txtGialaptop.setText(str1);
        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHoder.imglaptop);

        return view;
    }
}
