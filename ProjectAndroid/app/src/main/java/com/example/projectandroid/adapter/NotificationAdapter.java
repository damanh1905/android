package com.example.projectandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectandroid.R;
import com.example.projectandroid.activity.ManagerProduct;
import com.example.projectandroid.model.Notification;
import com.example.projectandroid.model.Sanpham;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ItemHolder> {
    private com.example.projectandroid.activity.Notification context;
    ArrayList<Notification> notificationArrayList;

    public NotificationAdapter(com.example.projectandroid.activity.Notification context, ArrayList<Notification> notificationArrayList) {
        this.context = context;
        this.notificationArrayList = notificationArrayList;
    }

    @NonNull
    @Override
    public NotificationAdapter.ItemHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_thongbao, parent, false);
        return new NotificationAdapter.ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder( NotificationAdapter.ItemHolder holder, int position) {
        Notification notification = notificationArrayList.get(position);
        holder.name.setText(notification.getName());
        holder.detail.setText(notification.getDetail());

    }

    @Override
    public int getItemCount() {
        return notificationArrayList.size();
    }
    public class ItemHolder extends RecyclerView.ViewHolder {
        TextView name,detail;


        public ItemHolder( View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.textViewNameNoti);
            detail=itemView.findViewById(R.id.textViewNoti);
        }
    }
}
