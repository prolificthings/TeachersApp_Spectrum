package com.example.teachers_app;

public class tdlModel {
    private String task;
    private String id;
    private String date;

    public tdlModel(){

    }

    public tdlModel(String task, String id, String date) {
        this.task = task;
        this.id = id;
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
