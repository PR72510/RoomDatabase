package com.example.roomdatabase.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.roomdatabase.Entities.User;
import com.example.roomdatabase.Repository.UserRepository;


import java.util.List;

public class UserViewMiodel extends AndroidViewModel {

    private UserRepository repository;
    private LiveData<List<User>> allUsers;

    public UserViewMiodel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUser();
    }

    public void insert(User user) {
        repository.insert(user);
    }

    public void update(User user) {
        repository.update(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public void deleteAllUser() {
        repository.deleteAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        allUsers = repository.getAllUser();
        return allUsers;
    }
}
