package com.example.roomdatabase.Entities;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String Name;

    public User() {

    }

    public User(String name) {
        this.Name = name;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }
}
