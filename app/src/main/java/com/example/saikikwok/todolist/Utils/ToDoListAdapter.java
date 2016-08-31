package com.example.saikikwok.todolist.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.saikikwok.todolist.R;


import com.example.saikikwok.todolist.Models.Todo;

import java.util.List;

/**
 * Created by saikikwok on 8/30/16.
 */

public class ToDoListAdapter extends BaseAdapter {

    private Context context;
    private List<Todo> todos;

    public ToDoListAdapter(@NonNull Context context, List<Todo> todos) {
        this.context = context;
        this.todos = todos;
    }

    @Override
    public int getCount() {
        return todos.size();
    }

    @Override
    public Object getItem(int i) {
        return todos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.main_list_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.view = (TextView) view.findViewById(R.id.main_list_item_text);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        Todo todo = todos.get(i);
        viewHolder.view.setText(todo.getTask());
        return view;
    }

    private class ViewHolder {
        TextView view;
    }
}
