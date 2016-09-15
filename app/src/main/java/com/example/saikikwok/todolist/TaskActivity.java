package com.example.saikikwok.todolist;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.saikikwok.todolist.Models.Todo;
import com.example.saikikwok.todolist.Utils.AlarmUtils;
import com.example.saikikwok.todolist.Utils.DatePickerFragment;
import com.example.saikikwok.todolist.Utils.DateUtils;
import com.example.saikikwok.todolist.Utils.ModelUtils;
import com.example.saikikwok.todolist.Utils.TextUtils;
import com.example.saikikwok.todolist.Utils.TimePickerFragment;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by saikikwok on 8/30/16.
 */

public class TaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    public static final String KEY_TASK_DETAILS = "task_details";
    public static final String KEY_TASK_ID = "task_id";

    private Todo todo;
    private Date dateAndTime;

    private EditText taskDetails;
    private CheckBox checkBox;
    private TextView timeRemind;
    private TextView dateRemind;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_details);
        todo = getIntent().getParcelableExtra(KEY_TASK_DETAILS);
        setupUI();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
        setupSaveButton();
        setupDelete();
    }

    private void setupSaveButton() {
        FloatingActionButton saveBtn = (FloatingActionButton) findViewById(R.id.task_done_fab);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAndExit();
            }
        });
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
        Intent intent = new Intent();
        intent.putExtra(KEY_TASK_ID, todo.getId());
        setResult(Activity.RESULT_OK, intent);
        AlarmUtils.cancelAlarm(this, todo);
        finish();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = DateUtils.getCalendarFromTodo(todo);
        c.set(i, i1, i2);
        dateAndTime = c.getTime();
        dateRemind.setText(DateUtils.dateToStringDate(c.getTime()));
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        Calendar c = DateUtils.getCalendarFromTodo(todo);
        c.set(Calendar.HOUR_OF_DAY, i);
        c.set(Calendar.MINUTE, i1);
        dateAndTime = c.getTime();
        timeRemind.setText(DateUtils.dateToStringTime(c.getTime()));
    }


    private void saveAndExit() {
        if (todo == null) {
            todo = new Todo();
        }
        todo.setTask(taskDetails.getText().toString());
        todo.setInvokedDate(dateAndTime);
        todo.setDone(checkBox.isChecked());

        AlarmUtils.setAlarm(this, todo);

        Intent res = new Intent();
        res.putExtra(KEY_TASK_DETAILS, todo);
        setResult(Activity.RESULT_OK, res);
        finish();

    }


}
