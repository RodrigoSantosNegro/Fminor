package com.example.aptmc;

import android.view.View;
import android.view.ViewGroup;
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
        private TextView productName;
        private ImageView iconProduct;
        private ImageView deleteButton;
        private TextView price;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.productNameTextView);
            iconProduct = itemView.findViewById(R.id.iconProductImageView);
            deleteButton = itemView.findViewById(R.id.deleteButtonImageView);
            price = itemView.findViewById(R.id.priceTextView);
        }

        public void showData(Product product) {
            this.productName.setText(product.getName());
            this.price.setText(Float.toString(product.getPrice())+" €");
            Util.downloadBitmapToImageView(product.getIcon_image_url(), this.iconProduct);
        }

    }
}
