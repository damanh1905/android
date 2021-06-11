package com.example.projectandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectandroid.R;
import com.example.projectandroid.activity.ChiTietSanPhamActivity;
import com.example.projectandroid.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ItemHolder> {
    Context context;
    ArrayList<Sanpham> sanphamArrayList;

    public SanphamAdapter(Context context, ArrayList<Sanpham> sanphamArrayList) {
        this.context = context;
        this.sanphamArrayList = sanphamArrayList;
    }

    @NonNull
    @Override
    // tạo Item View
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_listview_sanpham, parent, false);
        return new ItemHolder(itemView);

    }

    @Override
    // gan du lieu từ Array list cho Item dong_listview_sanpham
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Sanpham sanpham = sanphamArrayList.get(position);
        // chuyển từ url sang hình ảnh
        Picasso.with(context).load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(holder.imgSanpham);
        holder.txtTensp.setText(sanpham.getTensanpham());

        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(Integer.parseInt(sanpham.getGia()));
        holder.txtGiasp.setText("Giá: " + str1);

    }

    @Override
    public int getItemCount() {
        return sanphamArrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        ImageView imgSanpham;
        TextView txtTensp, txtGiasp;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgSanpham = (ImageView) itemView.findViewById(R.id.imgSanpham);
            txtTensp = (TextView) itemView.findViewById(R.id.txtTensp);
            txtGiasp = (TextView) itemView.findViewById(R.id.txtGiasp);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChiTietSanPhamActivity.class);
                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("thongtinsanpham", sanphamArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
