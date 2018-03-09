package com.bignerdranch.android.wintervacationhomework;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

public class ImageBarnnerFramLayout extends FrameLayout implements ImageBarnnerViewGroup.ImageBarnnerViewGroupLisnner,ImageBarnnerViewGroup.ImageBarnnerLister {

    private ImageBarnnerViewGroup imageBarnnerViewGroup;
    private LinearLayout linearLayout;
    private FramLayoutLisenner lisenner;
    public FramLayoutLisenner getLisenner(){
        return lisenner;
    }
    public void setLisenner(FramLayoutLisenner lisenner){
        this.lisenner = lisenner;
    }

    public ImageBarnnerFramLayout(@NonNull Context context) {
        super(context);
        initImageBarnnerViewGroup();
        initDotLinearlayout();
    }

    public ImageBarnnerFramLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initImageBarnnerViewGroup();
        initDotLinearlayout();
    }

    public ImageBarnnerFramLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initImageBarnnerViewGroup();
        initDotLinearlayout();
    }

    public void addBitmaps(List<Bitmap>list){
        for(int i = 0;i<list.size();i++){
            Bitmap bitmap = list.get(i);
            addBitmapToImageBarnnerViewGroup(bitmap);
            addDotToLinearLayout();
        }
    }

    private void addBitmapToImageBarnnerViewGroup(Bitmap bitmap){
        ImageView iv = new ImageView(getContext());
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setLayoutParams(new ViewGroup.LayoutParams(Other.WITTH,ViewGroup.LayoutParams.WRAP_CONTENT));
        iv.setImageBitmap(bitmap);
        imageBarnnerViewGroup.addView(iv);
    }

    private void addDotToLinearLayout(){
        ImageView iv = new ImageView(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(5,5,5,5);
        iv.setLayoutParams(lp);
        iv.setImageResource(R.drawable.dot_normal);
        linearLayout.addView(iv);
    }

    private void initImageBarnnerViewGroup(){
        imageBarnnerViewGroup = new ImageBarnnerViewGroup(getContext());
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        imageBarnnerViewGroup.setLayoutParams(lp);
        imageBarnnerViewGroup.setLister(this);
        imageBarnnerViewGroup.setBarnnerViewGroupLisnner(this);
        addView(imageBarnnerViewGroup);
    }

    private void initDotLinearlayout(){
        linearLayout = new LinearLayout(getContext());
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,40);
        linearLayout.setLayoutParams(lp);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setBackgroundColor(Color.TRANSPARENT);
        addView(linearLayout);
        FrameLayout.LayoutParams layoutParams = (LayoutParams) linearLayout.getLayoutParams();
        layoutParams.gravity = Gravity.BOTTOM;
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.getBackground().setAlpha(100);
    }

    @Override
    public void selectImage(int index){
        int count = linearLayout.getChildCount();
        for(int i = 0;i<count;i++){
            ImageView iv = (ImageView)linearLayout.getChildAt(i);
            if(i == index){
                iv.setImageResource(R.drawable.dot_selectl);
            }else {
                iv.setImageResource(R.drawable.dot_normal);
            }
        }
    }

    @Override
    public void clickImageIndex(int pos) {
        lisenner.clickImageIndex(pos);
    }

    public interface FramLayoutLisenner{
        void clickImageIndex(int pos);
    }

}
