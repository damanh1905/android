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

public class listviewhangsanphamAdapter  extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> sanphamArrayListhang;

    public listviewhangsanphamAdapter(Context context, ArrayList<Sanpham> sanphamArrayListhang) {
        this.context = context;
        this.sanphamArrayListhang = sanphamArrayListhang;
    }

    @Override
    public int getCount() {
        return sanphamArrayListhang.size();
    }

    @Override
    public Object getItem(int i) {
        return sanphamArrayListhang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHoder {
        ImageView imghang;
        TextView txtTenhang, txtGiahang,txtMotahang;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoder viewHoder = null;
        if(view ==null){
            viewHoder= new ViewHoder();;
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.dong_hang_samsung,null);
            viewHoder.imghang = view.findViewById(R.id.imghang);
            viewHoder.txtTenhang = view.findViewById(R.id.txtTenhang);
            viewHoder.txtGiahang = view.findViewById(R.id.txtGiahang);
            viewHoder.txtMotahang = view.findViewById(R.id.txtMotahang);
            view.setTag(viewHoder);

        }else {
            viewHoder= (ViewHoder) view.getTag();

        }
        Sanpham sanpham= sanphamArrayListhang.get(i);
        viewHoder.txtTenhang.setText(sanpham.getTensanpham());
        viewHoder.txtMotahang.setMaxLines(2);
        viewHoder.txtMotahang.setEllipsize(TextUtils.TruncateAt.END);
        viewHoder.txtMotahang.setText(sanpham.getMotasanpham());
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format( Integer.parseInt(sanpham.getGia()));
        viewHoder.txtGiahang.setText(str1);
        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHoder.imghang);

        return view;
    }

}
