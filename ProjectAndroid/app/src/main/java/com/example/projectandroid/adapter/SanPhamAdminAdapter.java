package com.example.projectandroid.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectandroid.R;
import com.example.projectandroid.activity.EditProduct;
import com.example.projectandroid.activity.ManagerProduct;
import com.example.projectandroid.model.Sanpham;

import java.util.ArrayList;

public class SanPhamAdminAdapter extends RecyclerView.Adapter<SanPhamAdminAdapter.ItemHolder> {
    private ManagerProduct context;
    ArrayList<Sanpham> sanphamArrayList;


    public SanPhamAdminAdapter(ManagerProduct context, ArrayList<Sanpham> sanphamArrayList) {
        this.context = context;
        this.sanphamArrayList = sanphamArrayList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sp_admin, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull  ItemHolder holder, int position) {
        Sanpham sanpham = sanphamArrayList.get(position);
        holder.txtTensp.setText(sanpham.getTensanpham());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditProduct.class);
                intent.putExtra("dataP", sanpham);
                context.startActivity(intent);
            }
        });
        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XacNhanXoa(sanpham.getTensanpham(),sanpham.getIdsanpham());
            }
        });

    }
    private void XacNhanXoa(String ten,int id){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setMessage("Bạn có muốn xóa sản phẩm "+ten+" không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.deleteSp(id);

            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();

    }

    @Override
    public int getItemCount() {
        return sanphamArrayList.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        ImageView imgEdit,imgDel;
        TextView txtTensp;
        public ItemHolder(@NonNull  View itemView) {
            super(itemView);
            imgDel=itemView.findViewById(R.id.imageDel);
            imgEdit=itemView.findViewById(R.id.imageEdit);
            txtTensp=itemView.findViewById(R.id.textViewNameProduct);

        }
    }
}
