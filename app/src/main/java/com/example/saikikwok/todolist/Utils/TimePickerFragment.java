package com.example.saikikwok.todolist.Utils;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;

import com.example.saikikwok.todolist.Models.Todo;

import java.util.Calendar;

/**
 * Created by saikikwok on 9/10/16.
 */

public class TimePickerFragment extends DialogFragment {

    Todo t;
    TimePickerDialog.OnTimeSetListener listener;

    public void setFields(Todo td, TimePickerDialog.OnTimeSetListener listener) {
        this.t = td;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = getCalendarFromTodo();
        return new TimePickerDialog(getActivity(), listener,
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE), DateFormat.is24HourFormat(getActivity()));

    }


    private Calendar getCalendarFromTodo() {
        Calendar c = Calendar.getInstance();
        if (t != null) {
            c.setTime(t.getInvokedDate());
        }
        return c;
    }

}
