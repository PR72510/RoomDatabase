package com.example.roomdatabase.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.roomdatabase.Entities.User;
import com.example.roomdatabase.R;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private List<User> list = new ArrayList<>();

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User user = list.get(position);
        holder.tvId.setText(String.valueOf(user.getId()));
        holder.tvName.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setUsers(List<User> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public User getUserAt(int position) {
        return list.get(position);
    }

    class UserHolder extends RecyclerView.ViewHolder {
        private TextView tvId;
        private TextView tvName;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
