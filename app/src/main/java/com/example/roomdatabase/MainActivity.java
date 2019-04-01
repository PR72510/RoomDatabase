package com.example.roomdatabase;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.roomdatabase.Adapter.UserAdapter;
import com.example.roomdatabase.Entities.User;
import com.example.roomdatabase.ViewModel.UserViewMiodel;
import com.example.roomdatabase.databinding.ActivityMainBinding;

import java.util.List;


public class MainActivity extends AppCompatActivity implements Dialog.DialogListener {

    ActivityMainBinding binding;
    private UserViewMiodel userViewMiodel;
    UserAdapter adapter;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setClicker(this);

        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter();
        binding.recycler.setAdapter(adapter);

        userViewMiodel = ViewModelProviders.of(this).get(UserViewMiodel.class);
        userViewMiodel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                adapter.setUsers(users);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                userViewMiodel.delete(adapter.getUserAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "User Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.recycler);

    }

    @Override
    public void sendData(String name) {
        this.name = name;
        User user = new User(name);
        userViewMiodel.insert(user);
        Toast.makeText(this, "User created", Toast.LENGTH_SHORT).show();
    }

    public void deleteAll(View view) {
        userViewMiodel.deleteAllUser();
        Toast.makeText(MainActivity.this, "All User Deleted", Toast.LENGTH_SHORT).show();
    }

    public void addUser(View view) {
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "Dialog");
    }
}
