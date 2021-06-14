package com.example.projectandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectandroid.R;
import com.example.projectandroid.activity.hangsamsungActivity;
import com.example.projectandroid.model.hangsanpham;
import com.example.projectandroid.model.loaisanpham2;

import java.util.List;

public class hangsanphamAdapter extends RecyclerView.Adapter<hangsanphamAdapter.FoodHolder> {
    List<hangsanpham> data;
    int selectedItem = 0;
    Context context;
    OnCategoryClick onCategoryClick;
    public interface OnCategoryClick {
        void onClick(int pos);
    }

    public hangsanphamAdapter(List<hangsanpham> data, Context context, OnCategoryClick onCategoryClick){
        this.data = data;
        this.context=context;
        this.onCategoryClick = onCategoryClick;
    }
    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_hoder, parent, false);

        return new FoodHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHolder holder, int position) {

        holder.image.setImageResource(data.get(position).getImage());
        holder.title.setText(data.get(position).getName());


        if (selectedItem == position){
            holder.cardView.animate().scaleX(1.1f);
            holder.cardView.animate().scaleY(1.1f);
            holder.title.setTextColor(Color.WHITE);

//            holder.llBackground.setBackgroundResource(R.drawable.splash);
        }else {
            holder.cardView.animate().scaleX(1f);
            holder.cardView.animate().scaleY(1f);
            holder.title.setTextColor(Color.BLACK);

//            holder.llBackground.setBackgroundResource(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class FoodHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;

        LinearLayout llBackground;
        CardView cardView;
        public FoodHolder(View holder) {
            super(holder);

            title = holder.findViewById(R.id.food_title);
            image = holder.findViewById(R.id.food_img);

            cardView = holder.findViewById(R.id.food_background);
            llBackground = holder.findViewById(R.id.ll_background);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedItem = getAdapterPosition();

                    if (onCategoryClick != null){
                        onCategoryClick.onClick(getAdapterPosition());
                    }

                            notifyDataSetChanged();


                }
            });
        }
    }
}
