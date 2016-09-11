package com.example.saikikwok.todolist;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.example.saikikwok.todolist.Models.Todo;
import com.example.saikikwok.todolist.Utils.ToDoListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    public static final int REQ_CODE_TASK_DETAILS = 100;

    private List<Todo> todos;
    private ToDoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI(fakedata());
    }

    private void setupUI(List<Todo> todos) {
        ListView listView = (ListView)findViewById(R.id.todoList_listview);
        adapter = new ToDoListAdapter(this, todos);
        listView.setAdapter(adapter);

        setupFab();
    }

    private void setupFab() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                startActivityForResult(intent, REQ_CODE_TASK_DETAILS);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_TASK_DETAILS && resultCode == Activity.RESULT_OK) {
            Todo todo = data.getParcelableExtra(TaskActivity.KEY_TASK_DETAILS);
            updateTodos(todo);
        }
    }

    private void updateTodos(Todo todo) {
        boolean found = false;
        for (int i = 0; i < todos.size(); i++) {
            Todo elem = todos.get(i);
            if (TextUtils.equals(elem.getId(), todo.getId())) {
                found = true;
                todos.set(i, todo);
                break;
            }
        }
        if (!found) {
            todos.add(todo);
        }
        adapter.notifyDataSetChanged();
    }

    private List<Todo> fakedata() {
        todos = new ArrayList<>();
        return todos;
    }
}
