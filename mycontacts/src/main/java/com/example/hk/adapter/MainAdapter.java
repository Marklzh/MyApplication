package com.example.hk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;

import com.example.hk.mycontacts.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/24.
 */
public class MainAdapter extends BaseAdapter {
    private ArrayList<String> list;


    private LayoutInflater inflater;


    public MainAdapter(Context context, ArrayList<String> list) {
        super();

        inflater = LayoutInflater.from(context);
        this.list=list;
    }


    /**
     * listview数据的长度
     */
    @Override
    public int getCount() {


        return list.size();
    }


    /**
     * 获取当行数据
     */
    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    /**
     * 返回当前行下标id的位置
     */
    @Override
    public long getItemId(int position) {

        return position;
    }


    /**
     * 显示布局
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //布局填充器
        convertView=inflater.inflate(R.layout.item_main_tv, null);
        TextView name=(TextView)convertView.findViewById(R.id.item_main_tv);
        name.setText(list.get(position));
        return convertView;
    }

}
