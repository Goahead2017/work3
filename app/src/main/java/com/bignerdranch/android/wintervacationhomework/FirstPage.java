package com.bignerdranch.android.wintervacationhomework;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 14158 on 2018/2/11.
 */

public class FirstPage extends Fragment implements View.OnClickListener {

    RecyclerView mRecyclerView;
    ExtraAdapter extraAdapter;
    TextView tv1;
    TextView tv2;

    private SwipeRefreshLayout swipeRefresh;

    private Button button;
    private EditText editText;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_layout, null);

        button = view.findViewById(R.id.joke_button);
        editText = view.findViewById(R.id.joke_text);
        button.setOnClickListener(this);

        /*//发送网络请求，获取数据
        sendRequestWithOkHttp();*/

        if (Other.SECOND) {
            Other.data2.add("有一天上课，老师问小丽：“祖国是什么？”小丽说：“老师，祖国是我的母亲。”老师说：“回答的很好。”接着老师又问小明：“小明，祖国是什么啊？”小明说：“祖国是小丽的母亲。");
            Other.data2.add("一男子在闹市骑摩托车撞昏了一个陌生的老汉！男子惊吓得不知所措！围观群众越来越多！突然，该男抱住老汉，声泪俱下的喊道：“爹，你等着我，我这就去给你找医生！”说后，就跑掉了。老汉挣扎着愤怒的喊道：“给老子回来！”众人纷纷感慨：“这儿子当的真孝顺！");
            Other.data2.add("深夜，老公未归。女儿心急给妈妈打电话：“妈！他还没回来，一定有别的女人了！”妈妈轻声安慰：“傻孩子，乖，别净往坏处想，兴许是出出车祸了！");
            Other.SECOND = false;
        }

        mRecyclerView = view.findViewById(R.id.recycler__view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        extraAdapter = new ExtraAdapter(getActivity(), Other.data2);
        mRecyclerView.setAdapter(extraAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);

        extraAdapter.setOnItemClickListener(new ExtraAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, TextView textView1,TextView textView2,ExtraAdapter.ViewName viewName, int position) {
                //处理点击事件，viewName用于区分
                switch (viewName) {
                    case GREAT:
                        tv1 = textView1.findViewById(R.id.tv1);
                        if (Other.flag1 && Other.flag2) {
                            Other.x++;
                            tv1.setText(String.valueOf(Other.x));
                            Other.flag1 = false;
                        } else if (!Other.flag1 && Other.flag2) {
                            Other.x--;
                            tv1.setText(String.valueOf(Other.x));
                            Other.flag1 = true;
                        }
                        break;
                    case BAD:
                        tv2 = textView2.findViewById(R.id.tv2);
                        if (Other.flag1 && Other.flag2) {
                            Other.y++;
                            tv2.setText(String.valueOf(Other.y));
                            Other.flag2 = false;
                        } else if (Other.flag1 && !Other.flag2) {
                            Other.y--;
                            tv2.setText(String.valueOf(Other.y));
                            Other.flag2 = true;
                        }
                        break;
                }
            }
        });

        //设置刷新
        swipeRefresh = view.findViewById(R.id.swipe__refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshRecycler();
            }
        });

        return view;
    }

    private void refreshRecycler() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //处理具体的刷新逻辑
                        extraAdapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.joke_button:
                String str;
                str = editText.getText().toString();
                if (str.trim().length() != 0) {
                    Other.data2.add(str);
                    extraAdapter = new ExtraAdapter(getActivity(), Other.data2);
                    mRecyclerView.setAdapter(extraAdapter);
                } else {
                    Toast.makeText(getActivity(), "请先写一个笑话", Toast.LENGTH_SHORT).show();
                }
                final Animation loadAnimation3;
                loadAnimation3 = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha);
                button.startAnimation(loadAnimation3);
                editText.setText("");
                break;
        }
    }
}