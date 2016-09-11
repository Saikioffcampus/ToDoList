package com.example.saikikwok.todolist.Utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import com.example.saikikwok.todolist.Models.Todo;

import java.util.Calendar;

/**
 * Created by saikikwok on 9/9/16.
 */

public class DatePickerFragment extends DialogFragment {
    // should be instantiate with newInstance

    Todo t;
    DatePickerDialog.OnDateSetListener listener;

    public void setFields(Todo td, DatePickerDialog.OnDateSetListener listener) {
        this.t = td;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = getCalendarFromTodo();
        return new DatePickerDialog(getActivity(), listener,
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));

    }


    private Calendar getCalendarFromTodo() {
        Calendar c = Calendar.getInstance();
        if (t != null) {
            c.setTime(t.getInvokedDate());
        }
        return c;
    }


}



