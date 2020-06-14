package com.example.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<User> arrayList;
    private Context context;

    public CustomAdapter(ArrayList<User> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);



        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.user_profile);
        holder.user_id.setText(arrayList.get(position).getId());
        holder.user_pw.setText(String.valueOf(arrayList.get(position).getPw()));
        holder.user_favorite.setText(arrayList.get(position).getFavorite());

    }

    @Override
    public int getItemCount() {
        return (arrayList != null? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView user_profile;
        TextView user_id;
        TextView user_pw;
        TextView user_favorite;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.user_profile =  itemView.findViewById(R.id.user_profile);
            this.user_id = itemView.findViewById(R.id.user_id);
            this.user_pw = itemView.findViewById(R.id.user_pw);
            this.user_favorite = itemView.findViewById(R.id.user_favorite);
        }
    }
}
