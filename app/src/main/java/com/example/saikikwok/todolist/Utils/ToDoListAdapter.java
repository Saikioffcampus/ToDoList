package com.example.saikikwok.todolist.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.saikikwok.todolist.MainActivity;
import com.example.saikikwok.todolist.R;


import com.example.saikikwok.todolist.Models.Todo;
import com.example.saikikwok.todolist.TaskActivity;

import java.util.List;

/**
 * Created by saikikwok on 8/30/16.
 */

public class ToDoListAdapter extends ViewHolderAdapter {

    private Activity context;
    private List<Todo> todos;

    public ToDoListAdapter(@NonNull Activity context, List<Todo> todos) {
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
    protected ViewHolderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View listView = LayoutInflater.from(context).inflate(R.layout.main_list_item, parent, false);
        return new TodoListViewHolder(listView);
    }

    @Override
    protected void onBindViewHolder(ViewHolderAdapter.ViewHolder viewHolder, int position) {
        final Todo todo = (Todo) getItem(position);
        TextView task = ((TodoListViewHolder) viewHolder).todoText;
        task.setText(todo.getTask());
        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TaskActivity.class);
                intent.putExtra(TaskActivity.KEY_TASK_DETAILS, todo);
                context.startActivityForResult(intent, MainActivity.REQ_CODE_TASK_DETAILS);
            }
        });
    }

    public static class TodoListViewHolder extends ViewHolderAdapter.ViewHolder {
        TextView todoText;
        public TodoListViewHolder(@NonNull View view) {
            super(view);
            todoText = (TextView)view.findViewById(R.id.main_list_item_text);
        }
    }


}
