package com.example.roomdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.roomdatabase.DAO.UserDao;
import com.example.roomdatabase.Entities.User;


@Database(entities = User.class, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase instance;
    public abstract UserDao userDao();

    public static synchronized UserDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    UserDatabase.class, "user_database")
                    .fallbackToDestructiveMigration()           // Allows Room to destructively recreate database tables
                    .build();
        }
        return instance;
    }
}
