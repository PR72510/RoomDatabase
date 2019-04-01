package com.example.roomdatabase.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.roomdatabase.Entities.User;

import java.util.List;



@Dao
public interface UserDao {
/* An abstract @Dao class can optionally have a constructor that takes a Database as its only parameter. */
    @Insert
    void insert(User user);
    @Update
    void update(User user);
    @Delete
    void delete(User user);

    @Query("Delete from user_table")
    void deleteAllUsers();

    @Query("Select * From user_table")
    LiveData<List<User>> getAllUsers();

}

