package com.example.aptmc;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileAdapter extends RecyclerView.Adapter <ProfileAdapter.ProfileViewHolder>{

    @NonNull
    @Override
    public ProfileAdapter.ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ProfileViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {
        private TextView profileScaleName;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            profileScaleName = itemView.findViewById(R.id.scaleNameProfile);
        }

    }
}
