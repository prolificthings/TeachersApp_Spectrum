package com.example.teachers_app;

public class Phone {
    private String name, branch, year, number;

    public Phone(String name, String branch, String year, String number) {
        this.name = name;
        this.branch = branch;
        this.year = year;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
