package com.example.saikikwok.todolist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.saikikwok.todolist.Models.Todo;
import com.example.saikikwok.todolist.Utils.DateUtils;
import com.example.saikikwok.todolist.Utils.TextUtils;

/**
 * Created by saikikwok on 8/30/16.
 */

public class TaskActivity extends AppCompatActivity {

    private Todo todo;

    private EditText taskDetails;
    private CheckBox checkBox;
    private TextView timeRemind;
    private TextView dateRemind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_details);

    }

    private void setupUI() {
        taskDetails = (EditText) findViewById(R.id.edit_task);
        checkBox = (CheckBox) findViewById(R.id.edit_checkbox);
        timeRemind = (TextView) findViewById(R.id.task_time);
        dateRemind = (TextView) findViewById(R.id.task_date);
        if (todo != null) {
            taskDetails.setText(todo.getTask());
            TextUtils.setTextViewStrikeThrough(taskDetails, todo.isDone());
            checkBox.setChecked(todo.isDone());
            if (todo.getInvokedDate() != null) {
                timeRemind.setText(DateUtils.dateToStringTime(todo.getInvokedDate()));
                dateRemind.setText(DateUtils.dateToStringDate(todo.getInvokedDate()));
            } else {
                timeRemind.setText("Set Time");
                dateRemind.setText("Set Date");
            }
        } else {
            findViewById(R.id.task_delete).setVisibility(View.GONE);
        }
    }
}
