package com.example.saikikwok.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.saikikwok.todolist.Models.Todo;
import com.example.saikikwok.todolist.Utils.DatePickerFragment;
import com.example.saikikwok.todolist.Utils.DateUtils;
import com.example.saikikwok.todolist.Utils.TextUtils;
import com.example.saikikwok.todolist.Utils.TimePickerFragment;

import java.util.Calendar;

/**
 * Created by saikikwok on 8/30/16.
 */

public class TaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private Todo todo;

    private EditText taskDetails;
    private CheckBox checkBox;
    private TextView timeRemind;
    private TextView dateRemind;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_details);
        setupUI();
    }

    private void setupUI() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        setTitle(null);
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
            timeRemind.setText("Set Time");
            dateRemind.setText("Set Date");
            findViewById(R.id.task_delete).setVisibility(View.GONE);
        }
        setupCheckbox();
        setupDatePicker();
        setupTimePicker();
    }

    private void setupDelete() {
        TextView delete = (TextView) findViewById(R.id.task_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
    }

    private void setupCheckbox() {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                TextUtils.setTextViewStrikeThrough(taskDetails, b);
                taskDetails.setTextColor(b? Color.GRAY : Color.WHITE);
            }
        });

        // when user click the wrapper (the entire row), the status of the checkbox should be reversed.
        View taskWrapper = findViewById(R.id.details_wrapper);
        taskWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBox.setChecked(!checkBox.isChecked());
            }
        });

    }

    private void setupDatePicker() {
        dateRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.setFields(todo, TaskActivity.this);
                datePickerFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }

    private void setupTimePicker() {
        timeRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setFields(todo, TaskActivity.this);
                timePickerFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });
    }

    private void delete() {
    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = DateUtils.getCalendarFromTodo(todo);
        c.set(i, i1, i2);
        //todo.setInvokedDate(c.getTime());
        dateRemind.setText(DateUtils.dateToStringDate(c.getTime()));
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        Calendar c = DateUtils.getCalendarFromTodo(todo);
        c.set(Calendar.HOUR_OF_DAY, i);
        c.set(Calendar.MINUTE, i1);
        timeRemind.setText(DateUtils.dateToStringTime(c.getTime()));
    }
}
