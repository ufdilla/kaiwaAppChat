package com.example.easysoft.kaiwaapp;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter{

    ArrayList<ContactModel> myList = new ArrayList();
    LayoutInflater inflater;
    Context context;


    public ContactAdapter(Context context, ArrayList myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public ContactModel getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_listview, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        ContactModel currentListData = getItem(position);

//        mViewHolder.tvTitle.setText(currentListData.getTitle());
        mViewHolder.tvDesc.setText(currentListData.getContact());
//        mViewHolder.ivIcon.setImageResource(currentListData.getImgResId());

        return convertView;
    }

    private class MyViewHolder {
        TextView /*tvTitle,*/ tvDesc;
//        ImageView ivIcon;

        public MyViewHolder(View item) {
//            tvTitle = (TextView) item.findViewById(R.id.tvTitle);
            tvDesc = (TextView) item.findViewById(R.id.tvDesc);
//            ivIcon = (ImageView) item.findViewById(R.id.ivIcon);
        }
    }
}