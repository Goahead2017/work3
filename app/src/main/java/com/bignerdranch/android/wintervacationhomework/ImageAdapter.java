package com.bignerdranch.android.wintervacationhomework;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 14158 on 2018/3/6.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder>{

    private List<Integer>imageId;
    private Context context;

    public ImageAdapter(List<Integer>list){
        imageId = list;
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.another_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ViewHolder holder, int position) {
        Integer Id = imageId.get(position);
        Glide.with(context).load(Id).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageId.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView)itemView;
            imageView = itemView.findViewById(R.id.another_image);
        }
    }
}
