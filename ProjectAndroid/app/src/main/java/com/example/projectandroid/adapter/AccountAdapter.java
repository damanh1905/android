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
import com.example.projectandroid.activity.EditAccount;
import com.example.projectandroid.activity.ManagerAccount;
import com.example.projectandroid.model.Account;

import java.util.ArrayList;

public class AccountAdapter extends  RecyclerView.Adapter<AccountAdapter.ItemHolder>{
    private ManagerAccount context;
    ArrayList<Account> accountArrayList;

    public AccountAdapter(ManagerAccount context, ArrayList<Account> accountArrayList) {
        this.context = context;
        this.accountArrayList = accountArrayList;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_acc_admin, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Account account = accountArrayList.get(position);
        holder.txtTenAcc.setText(account.getTen());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditAccount.class);
                intent.putExtra("dataA", account);
                context.startActivity(intent);
            }
        });
        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XacNhanXoa(account.getTen(),account.getIdAcc());
            }
        });


    }
    private void XacNhanXoa(String ten,int id){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setMessage("Bạn có muốn xóa tài khoản "+ten+" không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.deleteAcc(id);

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
        return accountArrayList.size();
    }
    public class ItemHolder extends RecyclerView.ViewHolder {
        ImageView imgEdit,imgDel;
        TextView txtTenAcc;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imgDel=itemView.findViewById(R.id.imageDel);
            imgEdit=itemView.findViewById(R.id.imageEdit);
            txtTenAcc=itemView.findViewById(R.id.textViewNameAcc);

        }
    }
}
