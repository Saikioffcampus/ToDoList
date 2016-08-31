package com.example.saikikwok.todolist.Models;

import java.util.Date;

/**
 * Created by saikikwok on 8/30/16.
 */

public class Todo {
    private String task;
    private Date invokedDate;

    public Todo() {}

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
}
