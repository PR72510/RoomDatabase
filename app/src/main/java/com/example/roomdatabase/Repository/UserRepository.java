package com.example.roomdatabase.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.roomdatabase.DAO.UserDao;
import com.example.roomdatabase.Entities.User;
import com.example.roomdatabase.UserDatabase;

import java.util.List;


public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application) {
        UserDatabase database = UserDatabase.getInstance(application);

        userDao = database.userDao();           // Room will generate an implementation of Dao class when it is referenced by a Database
        allUsers = userDao.getAllUsers();
    }

    public void insert(User user){
        new InsertAsyncTask(userDao).execute(user);
    }

    public void update(User user){
        new UpdateAsyncTask(userDao).execute(user);
    }

    public void delete(User user){
        new DeleteAsyncTask(userDao).execute(user);
    }

    public void deleteAllUsers(){
        new DeleteAllAsyncTask(userDao).execute();
    }

    public LiveData<List<User>> getAllUser(){
        return allUsers;
    }

    public static class InsertAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao userDao;

        private InsertAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
           userDao.insert(users[0]);
            return null;
        }
    }

    public static class UpdateAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao userDao;

        private UpdateAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    public static class DeleteAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao userDao;

        private DeleteAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

    public static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void>{

        private UserDao userDao;

        private DeleteAllAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();
            return null;
        }
    }
}
