package com.example.aptmc;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter <ProfileAdapter.ProfileViewHolder>{

    private List<String> scaleNames;

    public ProfileAdapter() {
        this.scaleNames = new ArrayList<>();
        this.scaleNames.add("C Dorian");
        this.scaleNames.add("B Harmonic Minor");
        this.scaleNames.add("G Locrian");
    }

    @NonNull
    @Override
    public ProfileAdapter.ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_profile_fav_scale, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ProfileViewHolder holder, int position) {
        String scaleName = scaleNames.get(position);
        holder.profileScaleName.setText(scaleName);

        holder.profileScaleName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] parts = scaleName.split(" ", 2);
                String selectedRoot = parts[0];
                String selectedScaleType = parts[1];
                if(selectedScaleType.contains(" ")){
                    String[] selectedScaleTypeParts = selectedScaleType.split(" ");
                    String tmp1 = selectedScaleTypeParts[0];
                    String tmp2 = selectedScaleTypeParts[1];
                    selectedScaleType = (tmp1+tmp2).toLowerCase();
                }


                Intent intent = new Intent(v.getContext(), ScaleName.class);
                intent.putExtra("ROOT_SELECTION", selectedRoot);
                intent.putExtra("SCALE_TYPE_SELECTION", selectedScaleType);
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return scaleNames.size();
    }

    public static class ProfileViewHolder extends RecyclerView.ViewHolder {
        private TextView profileScaleName;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            profileScaleName = itemView.findViewById(R.id.scaleNameProfile);
        }

    }


}
