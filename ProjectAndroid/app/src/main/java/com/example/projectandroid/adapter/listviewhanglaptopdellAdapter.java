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

public class listviewhanglaptopdellAdapter  extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> sanphamArrayListhanglaptopdell;

    public listviewhanglaptopdellAdapter(Context context, ArrayList<Sanpham> sanphamArrayListhanglaptopdell) {
        this.context = context;
        this.sanphamArrayListhanglaptopdell = sanphamArrayListhanglaptopdell;
    }

    @Override
    public int getCount() {
        return sanphamArrayListhanglaptopdell.size();
    }

    @Override
    public Object getItem(int i) {
        return sanphamArrayListhanglaptopdell.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHoder {
        ImageView imghanglaptopdell;
        TextView txtTenhanglaptopdell, txtGiahanglaptopdell,txtMotahanglatopdell;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoder viewHoder = null;
        if(view ==null){
            viewHoder= new ViewHoder();;
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.dong_hang_laptopdell,null);
            viewHoder.imghanglaptopdell = view.findViewById(R.id.imghanglaptopdell);
            viewHoder.txtTenhanglaptopdell = view.findViewById(R.id.txtTenhanglaptopdell);
            viewHoder.txtGiahanglaptopdell = view.findViewById(R.id.txtGiahanglaptopdell);
            viewHoder.txtMotahanglatopdell = view.findViewById(R.id.txtMotahanglaptopdell);
            view.setTag(viewHoder);

        }else {
            viewHoder= (ViewHoder) view.getTag();

        }
        Sanpham sanpham= sanphamArrayListhanglaptopdell.get(i);
        viewHoder.txtTenhanglaptopdell.setText(sanpham.getTensanpham());
        viewHoder.txtMotahanglatopdell.setMaxLines(2);
        viewHoder.txtMotahanglatopdell.setEllipsize(TextUtils.TruncateAt.END);
        viewHoder.txtMotahanglatopdell.setText(sanpham.getMotasanpham());
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format( Integer.parseInt(sanpham.getGia()));
        viewHoder.txtGiahanglaptopdell.setText(str1);
        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHoder.imghanglaptopdell);

        return view;
    }
}
