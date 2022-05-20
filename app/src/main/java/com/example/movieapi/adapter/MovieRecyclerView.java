package com.example.movieapi.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movieapi.R;
import com.example.movieapi.models.MovieModel;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MovieModel> movieModels;
    OnMovieListener onMovieListener;

    public MovieRecyclerView(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((MovieViewHolder) holder).title.setText(movieModels.get(position).getTitle());
        ((MovieViewHolder) holder).category.setText(movieModels.get(position).getRelease_date());
        ((MovieViewHolder) holder).ratingBar.setRating(movieModels.get(position).getVote_average() / 2);

        Glide.with(holder.itemView.getContext()
        ).load("https://image.tmdb.org/t/p/w500/" + movieModels.get(position).getPoster_path())
                .into(((MovieViewHolder) holder).iv_movieItem);

    }

    @Override
    public int getItemCount() {
        if (movieModels!=null) {
            return movieModels.size();
        } else
            return 0;
    }

    public void setMovie(List<MovieModel> movie) {
        this.movieModels = movie;
        notifyDataSetChanged();
    }

    public MovieModel getMovieid(int pos) {
        if (movieModels!=null) {
            if (movieModels.size() > 0) {
                return movieModels.get(pos);

            }
        }
        return null;
    }

}
