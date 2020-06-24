/*
 * Create by Hamza elkhatri
 */

/*
 * Create by Hamza elkhatri
 */

/*
 * Create by Hamza elkhatri
 */

package com.example.netflix.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.netflix.models.Movie;
import com.example.netflix.R;

import java.util.List;

public class TopMovie extends RecyclerView.Adapter<TopMovie.MviewHolder>
{

    Context context;
    List<Movie> mData;
    MovieItemClickListener movieItemClickListener;

    public TopMovie(Context context, List<Movie> mData, MovieItemClickListener movieItemClickListener) {
        this.context = context;
        this.mData = mData;
        this.movieItemClickListener = movieItemClickListener;
    }

    @NonNull
    @Override
    public TopMovie.MviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.tendence_movie,parent,false);
        return  new TopMovie.MviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopMovie.MviewHolder holder, int position)
    {
        Glide.with(context).load(Uri.parse(mData.get(position).getThumbnail())).into(holder.imageView);
        if(mData.get(position).getOriginal()==1)
                holder.original.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MviewHolder extends RecyclerView.ViewHolder
    {
        private TextView title;
        private ImageView imageView,original;
        public MviewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_movie_img);
            original= itemView.findViewById(R.id.netflix_logo);
            itemView.setOnClickListener(
                    new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieItemClickListener.onMovieClick(mData.get(getAdapterPosition()),imageView);
                }
            });
        }
    }
}
