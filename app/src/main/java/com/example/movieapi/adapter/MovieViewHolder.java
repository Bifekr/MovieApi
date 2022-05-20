package com.example.movieapi.adapter;



import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapi.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView iv_movieItem;
    TextView title,category,duration;
    RatingBar ratingBar;
    OnMovieListener onMovieListener;

    public MovieViewHolder(@NonNull View itemView,OnMovieListener onMovieListener) {
        super(itemView);

        this.onMovieListener=onMovieListener;
        iv_movieItem=itemView.findViewById(R.id.iv_movieHeader);
        title=itemView.findViewById(R.id.tv_title_searchItem);
        category=itemView.findViewById(R.id.tv_category_searchItem);
        ratingBar=itemView.findViewById(R.id.rb_movieList_item);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        onMovieListener.onMovieClick(getAdapterPosition());
    }
}
