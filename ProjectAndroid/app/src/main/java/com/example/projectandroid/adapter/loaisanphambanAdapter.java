package com.example.projectandroid.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectandroid.R;
import com.example.projectandroid.model.loaisanpham2;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class loaisanphambanAdapter extends RecyclerView.Adapter<loaisanphambanAdapter.CategoryHolder> {
   public static List<loaisanpham2> data2;
    Context context;
    int selectedItem = 0;

    OnCategoryClick onCategoryClick;


    public interface OnCategoryClick {
        void onClick(int pos);
    }
    public loaisanphambanAdapter(List<loaisanpham2> data2, Context context,OnCategoryClick onCategoryClick) {
        this.data2 = data2;
        this.context = context;
        this.onCategoryClick = onCategoryClick;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.category_holder,parent,false);
        return new CategoryHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {

        holder.image.setImageResource(data2.get(position).getImage());


        if (position == selectedItem){
            // Make card selected
            holder.cardView.setOutlineSpotShadowColor(context.getColor(R.color.yellow));
            holder.cardView.setOutlineAmbientShadowColor(context.getColor(R.color.yellow));
            holder.cardView.setStrokeWidth(2);

            holder.image.setColorFilter(ContextCompat.getColor(context, R.color.yellow), PorterDuff.Mode.SRC_IN);
        }else {
            // Make card inactive
            holder.cardView.setOutlineSpotShadowColor(context.getColor(R.color.gray1));
            holder.cardView.setOutlineAmbientShadowColor(context.getColor(R.color.gray));

            holder.image.setColorFilter(ContextCompat.getColor(context,R.color.gray1), PorterDuff.Mode.SRC_IN);
            holder.cardView.setStrokeWidth(0);
        }
    }

    @Override
    public int getItemCount() {
        return data2.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder{

        ImageView image;
        MaterialCardView cardView;
        public CategoryHolder(View holder){
            super(holder);

            image = holder.findViewById(R.id.img);
            cardView = holder.findViewById(R.id.card_view);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedItem = getAdapterPosition();
                    //reset items, so that color changes when we click on card
                    if (onCategoryClick != null){
                        onCategoryClick.onClick(getAdapterPosition());
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }
}
