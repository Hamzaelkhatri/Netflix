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
import com.example.netflix.R;
import com.example.netflix.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MviewHolder> {


    Context context;
    List<Movie> mData;
    MovieItemClickListener movieItemClickListener;



    public MovieAdapter(Context context, List<Movie> mData,MovieItemClickListener movieItemClickListener) {
        this.context = context;
        this.mData = mData;
        this.movieItemClickListener= movieItemClickListener;
    }

    @NonNull
    @Override
    public MviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return  new MviewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MviewHolder holder, int position)
    {
        holder.title.setText(mData.get(position).getTitle());
        Glide.with(context).load(Uri.parse(mData.get(position).getThumbnail())).thumbnail().into(holder.imageView);
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
                    title = itemView.findViewById(R.id.item_movie_title);
                    imageView = itemView.findViewById(R.id.item_movie_img);
                    original=itemView.findViewById(R.id.netflix_logo);

                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            movieItemClickListener.onMovieClick(mData.get(getAdapterPosition()),imageView);
                        }
                    });
        }
    }
}
