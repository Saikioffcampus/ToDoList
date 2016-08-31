package com.example.saikikwok.todolist.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.saikikwok.todolist.Models.Todo;
import com.example.saikikwok.todolist.R;

import java.util.List;

/**
 * Created by saikikwok on 8/30/16.
 */

public abstract class ViewHolderAdapter extends BaseAdapter {

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = onCreateViewHolder(viewGroup, i);
            view = viewHolder.view;
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        onBindViewHolder(viewHolder, i);
        return view;
    }
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder vh;
//        if (convertView == null) {
//            vh = onCreateViewHolder(parent, position);
//            convertView = vh.view;
//            vh.view.setTag(vh);
//        } else {
//            vh = (ViewHolder) convertView.getTag();
//        }
//
//        onBindViewHolder(vh, position);
//        return convertView;
//    }

    protected abstract ViewHolder onCreateViewHolder(ViewGroup parent, int position);

    protected abstract void onBindViewHolder(ViewHolder viewHolder, int position);

    public static abstract class ViewHolder {
        protected View view;
        public ViewHolder(@NonNull View view) {
            this.view = view;
        }
    }
}
