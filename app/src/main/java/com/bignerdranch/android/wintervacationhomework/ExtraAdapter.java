package com.bignerdranch.android.wintervacationhomework;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 14158 on 2018/3/8.
 */

public class ExtraAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private List<String>dataList;
    private Context context;
    private View itemView;

    public ExtraAdapter(Context context,List<String>list){
        this.context = context;
        dataList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.extra_item ,parent,false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemHolder itemHolder = (ItemHolder)holder;
        itemHolder.itemView.setTag(position);
        itemHolder.great.setTag(position);
        itemHolder.bad.setTag(position);

        ((ItemHolder) holder).tv.setText(dataList.get(position));
    }

    @Override
    public long getItemId(int i){
        return i;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onClick(View v) {
        //使用getTag方法获取数据
        int position = (int)v.getTag();
        if(mOnItemClickListener != null){
            switch (v.getId()){
                case R.id.great:
                    mOnItemClickListener.onClick(itemView,ViewName.GREAT,position);
                    break;
                case R.id.bad:
                    mOnItemClickListener.onClick(itemView,ViewName.BAD,position);
                    break;
                    default:
                        mOnItemClickListener.onClick(itemView,ViewName.ITEM,position);
                        break;
            }
        }
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        public ImageView great;
        public ImageView bad;
        public TextView tv;

        public ItemHolder(View itemView) {
            super(itemView);
            great = itemView.findViewById(R.id.great);
            bad = itemView.findViewById(R.id.bad);
            tv = itemView.findViewById(R.id.text_item);

            itemView.setOnClickListener(ExtraAdapter.this);
            great.setOnClickListener(ExtraAdapter.this);
            bad.setOnClickListener(ExtraAdapter.this);
        }
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    //item里面有多个控件可以点击
    public enum ViewName{
        ITEM,
        GREAT,
        BAD
    }

    public interface OnRecyclerViewItemClickListener{
        void onClick(View view,ViewName viewName,int position);
    }

}
