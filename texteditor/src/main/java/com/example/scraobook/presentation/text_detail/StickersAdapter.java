package com.example.scraobook.presentation.text_detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.scraobook.R;
import com.example.scraobook.util.RecyclerViewClickListener;
import java.util.ArrayList;

public class StickersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerViewClickListener mListener;
    private ArrayList<String> list;

    public StickersAdapter(ArrayList<String> list, RecyclerViewClickListener mListener) {
        this.list = list;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(com.example.scraobook.R.layout.sticker_row, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
//        UtilFunctions.setImage(myViewHolder.imageView, list.get(position), R.drawable.loading_img, R.drawable.error_img);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.loading_img);
        requestOptions.error(R.drawable.error_img);

        Glide.with(myViewHolder.imageView.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(list.get(position)).into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        private RecyclerViewClickListener mListener;

        MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(com.example.scraobook.R.id.imageView);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v, getAdapterPosition());
        }
    }
}
