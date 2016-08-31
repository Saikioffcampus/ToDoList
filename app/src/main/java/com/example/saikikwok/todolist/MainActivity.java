package com.example.saikikwok.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.saikikwok.todolist.Models.Todo;
import com.example.saikikwok.todolist.Utils.ToDoListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI(fakedata());
    }

    private void setupUI(List<Todo> todos) {
        ListView listView = (ListView)findViewById(R.id.todoList_listview);
        listView.setAdapter(new ToDoListAdapter(this, todos));
    }

    private List<Todo> fakedata() {
        List<Todo> todos = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Todo todo = new Todo();
            todo.setTask(i + "");
            todos.add(todo);
        }
        return todos;
    }
}
