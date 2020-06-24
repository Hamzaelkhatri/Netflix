/*
 * Create by Hamza elkhatri
 */

/*
 * Create by Hamza elkhatri
 */

package com.example.netflix.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.netflix.models.Movie;
import com.example.netflix.ui.movieDetaill;
import com.example.netflix.R;

import java.util.List;

public class MovieViewAdapter extends RecyclerView.Adapter<MovieViewAdapter.MoviewHolder>
{
    private Context context;
    private List<Movie> mData;
    private String StreamingLink;
    private String movieTitle;
    private String description;
    private String imgurl;
    private String imgCover;
    private int time;
    private int orig;
    private MovieItemClickListener movieItemClickListener;

    public MovieViewAdapter(Context context, List<Movie> mData, MovieItemClickListener movieItemClickListener) {
        this.context = context;
        this.mData = mData;
        this.movieItemClickListener = movieItemClickListener;
    }

    @NonNull
    @Override
    public MoviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie_seek,parent,false);
        return  new MoviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewAdapter.MoviewHolder holder, int position)
    {
        holder.title.setText(mData.get(position).getTitle());
        Glide.with(context).load(Uri.parse(mData.get(position).getThumbnail())).into(holder.imageView);
        holder.seekBar.setProgress(mData.get(position).getTime());
        StreamingLink=mData.get(position).getStreamibgLink();
        imgCover=mData.get(position).getCover();
        movieTitle=mData.get(position).getTitle();
        description=mData.get(position).getDescription();
        time=mData.get(position).getTime();
        orig=mData.get(position).getOriginal();
        imgurl=mData.get(position).getThumbnail();
        if(mData.get(position).getOriginal()==1)
            holder.original.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MoviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView title;
        private ImageView imageView;
        private ImageView original;
        private SeekBar seekBar;
        @SuppressLint("ClickableViewAccessibility")
        MoviewHolder(@NonNull View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.item_movie_title);
            imageView = itemView.findViewById(R.id.item_movie_img);
            seekBar= itemView.findViewById(R.id.seekBar);
            original=itemView.findViewById(R.id.netflix_logo);
            ImageView info = itemView.findViewById(R.id.icon_info);
            seekBar.setOnTouchListener((v, event) -> true);
            info.setOnClickListener(v -> movieItemClickListener.onMovieClick(mData.get(getAdapterPosition()),imageView));
            imageView.setOnClickListener(this);
            title.setOnClickListener(this);

        }



        @Override
        public void onClick(View v) {
            if(v==title || v==imageView)
            {
                Intent i = new Intent(context, movieDetaill.vedioplayer.class);
                i.putExtra("StremingLink",StreamingLink);
                i.putExtra("title",movieTitle);
                i.putExtra("description",description);
                i.putExtra("imgurl",imgurl);
                i.putExtra("imgCover",imgCover);
                i.putExtra("time",time);
                i.putExtra("original",orig);
                context.startActivity(i);
            }

        }
    }
}
