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
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.netflix.R;
import com.example.netflix.database.SqliteMovie;
import com.example.netflix.models.Movie;

import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EviewHolder>
{

    Context context;
    List<Movie> mdata;
    private String StreamingLink;
    private String movieTitle;
    private String description;
    private String imgurl;
    private String imgCover;
    private int time;
    private int orig;
    MovieItemClickListener movieItemClickListener;

    public EpisodeAdapter(Context context, List<Movie> mdata,MovieItemClickListener movieItemClickListener)
    {
        this.context = context;
        this.mdata = mdata;
        this.movieItemClickListener=movieItemClickListener;
    }

    @NonNull
    @Override
    public EpisodeAdapter.EviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.episode_item,parent,false);
        return new EviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeAdapter.EviewHolder holder, int position)
    {
        Glide.with(context).load(Uri.parse(mdata.get(position).getStreamibgLink())).thumbnail(.9f).into(holder.thumn);
        SqliteMovie db = new SqliteMovie(context);
        holder.timeline.setProgress(db.getTime(mdata.get(position).getTitle(),mdata.get(position).getSeason(),mdata.get(position).getEpisode()));
        holder.tv_description.setText(mdata.get(position).getDescription());
        if(!mdata.get(position).getTitle_ep().equals("null"))
            holder.tv_title.setText(mdata.get(position).getTitle_ep());
        else
            holder.tv_title.setText(mdata.get(position).getTitle());
        StreamingLink=mdata.get(position).getStreamibgLink();
        imgCover=mdata.get(position).getCover();
        movieTitle=mdata.get(position).getTitle();
        description=mdata.get(position).getDescription();
        time=mdata.get(position).getTime();
        orig=mdata.get(position).getOriginal();
        imgurl=mdata.get(position).getThumbnail();
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class EviewHolder extends RecyclerView.ViewHolder
    {
        ImageView thumn,download,play;
        TextView tv_title,tv_time,tv_description;
        SeekBar timeline;
        EviewHolder(@NonNull View itemView)
        {
            super(itemView);
            thumn=itemView.findViewById(R.id.iv_thunb);

            tv_title=itemView.findViewById(R.id.tv_title);
            tv_time=itemView.findViewById(R.id.tv_time);
            tv_description=itemView.findViewById(R.id.tv_description);
            timeline=itemView.findViewById(R.id.seekBar2);
            itemView.setOnClickListener(v -> movieItemClickListener.onMovieClick(mdata.get(getAdapterPosition()),null));
        }
    }
}
