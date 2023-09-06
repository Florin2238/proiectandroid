package com.example.moneysaver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.Shapeable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<NewsModel> localDataSet;

    private static OnItemClickListener itemClickListener;


    public CustomAdapter(List<NewsModel> localDataSet, OnItemClickListener itemClickListener) {
        this.localDataSet = localDataSet;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(localDataSet.get(position));
    }


    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;

        private final ImageView imageId;

        private final ConstraintLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvHeading);
            imageId = itemView.findViewById(R.id.title_image);

            layout = itemView.findViewById(R.id.container);

        }

        public void bind(NewsModel item) {
            title.setText(item.getTitle());
            imageId.setImageDrawable(ContextCompat.getDrawable(imageId.getContext(), item.getImageId()));

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(item);
                }
            });
        }
    }
}
