package com.example.saikikwok.todolist.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by saikikwok on 8/30/16.
 */

public class Todo implements Parcelable {

    private String id;
    private String task;
    private boolean done;
    private Date invokedDate;

    public Todo() {
        this.id = UUID.randomUUID().toString();
    }

    public Todo(String task, Date invokedDate) {
        this.id = UUID.randomUUID().toString();
        this.task = task;
        this.done = false;
        this.invokedDate = invokedDate;
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    private Todo(Parcel in) {
        this.id = in.readString();
        this.task = in.readString();
        this.done = in.readByte() != 0;
        long date = in.readLong();
        this.invokedDate = date == 0 ? null : new Date(date);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Date getInvokedDate() {
        return invokedDate;
    }

    public void setInvokedDate(Date invokedDate) {
        this.invokedDate = invokedDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(task);
        parcel.writeByte((byte) (done ? 1 : 0));
        parcel.writeLong(invokedDate == null ? 0 : invokedDate.getTime());
    }
}
