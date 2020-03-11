package com.example.android.Model;

public class DeptModel {
    private String id;
    private String Name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public DeptModel(String id, String name) {
        this.id = id;
        Name = name;
    }
}
