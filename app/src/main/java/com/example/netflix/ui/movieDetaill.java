
/*
 * Create by Hamza elkhatri
 */

/*
 * Create by Hamza elkhatri
 */

package com.example.netflix.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.netflix.adapters.EpisodeAdapter;
import com.example.netflix.adapters.cast_adapter;
import com.example.netflix.database.SqliteMovie;
import com.example.netflix.models.Movie;
import com.example.netflix.models.cast;
import com.example.netflix.vedioplayer;
import com.example.netflix.R;
import com.example.netflix.adapters.MovieItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class movieDetaill extends AppCompatActivity implements MovieItemClickListener
{

    ImageView movieThum,movieCover,addlist;
    RecyclerView rv_episodes;
    TextView tv_title,tv_description,tv_episode;
    SqliteMovie sqliteMovie = new SqliteMovie(this);
    RecyclerView rvCast;
    com.example.netflix.adapters.cast_adapter cast_adapter;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detaill);
        iniViews();

    }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        Intent intent = new Intent(this, vedioplayer.class);
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("description",movie.getDescription());
        intent.putExtra("imgurl",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCover());
        intent.putExtra("StremingLink",movie.getStreamibgLink());
        intent.putExtra("time",movie.getTime());
        intent.putExtra("original",movie.getOriginal());
        intent.putExtra("episode",movie.getEpisode());
        intent.putExtra("season",movie.getSeason());
        startActivity(intent);
    }

    class Task extends AsyncTask<Void, Void, Void>
    {
        List<Movie> episodeList=new ArrayList<>();
        EpisodeAdapter episodeAdapter;

        protected void onPreExecute()
        {
        }
        protected Void doInBackground(Void... JSONArray)
        {
            try
            {

                JSONObject jso= new JSONObject(Objects.requireNonNull((Objects.requireNonNull(getIntent().getExtras())).getString("movies")));
                JSONArray jsa= jso.getJSONArray("Movie");
                for(int i = 0;i<jsa.length();i++)
                {
                    jso = jsa.getJSONObject(i);
                    if(jso.getString("title").equals(getIntent().getExtras().getString("title")))
                    {
                        // Toast.makeText(movieDetaill.this,"TESTTTT:"+jso.getString("title"),Toast.LENGTH_LONG).show();
                         episodeList.add(new Movie(jso.getString("title"),jso.getString("description"),jso.getString("imgthum"),jso.getString("imgCover"),jso.getInt("original"),jso.getInt("Episode"),jso.getInt("season"),jso.getString("streaminglink"),jso.getString("title_ep")));
                    }
                }

                 episodeAdapter = new EpisodeAdapter(movieDetaill.this,episodeList,movieDetaill.this);
            }
            catch (Exception ex)
            {
                Toast.makeText(movieDetaill.this,"Here we go again "+ex.getMessage(),Toast.LENGTH_LONG).show();
            }
            return null;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        protected void onPostExecute(Void unused)
        {
            try
            {
                if(episodeList.size()<=1)
                {
                    rv_episodes.setVisibility(View.GONE);
                    tv_episode.setVisibility(View.GONE);
                }
                else {
                    rv_episodes.setVisibility(View.VISIBLE);
                    tv_episode.setVisibility(View.VISIBLE);
                }
                rv_episodes.setAdapter(episodeAdapter);
                rv_episodes.setLayoutManager(new LinearLayoutManager(movieDetaill.this,LinearLayoutManager.VERTICAL,false));

            }
            catch (Exception ex)
            {
                Toast.makeText(movieDetaill.this,"Here we go again "+ex.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

    }

    private void RvCst()
    {
        List<cast> mdata = new ArrayList<>();
        mdata.add(new cast("Brandon Routh",R.drawable.t1));
        mdata.add(new cast("Clive Standen",R.drawable.t2));
        mdata.add(new cast("Denise Richards",R.drawable.t3));
        mdata.add(new cast(" Ben Kingsley\n",R.drawable.t4));
        cast_adapter= new cast_adapter(this,mdata);
        rvCast.setAdapter(cast_adapter);
        rvCast.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        Task ts = new Task();
        ts.execute();

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    void iniViews()
    {
        rvCast=findViewById(R.id.rv_cast);
        final String StreamingLink = Objects.requireNonNull(getIntent().getExtras()).getString("StremingLink");
        FloatingActionButton play_fab = findViewById(R.id.floatingActionButton);
        tv_episode=findViewById(R.id.tv_episode);
        final String movieTitle = getIntent().getExtras().getString("title");
        final Uri imageResource = Uri.parse(getIntent().getExtras().getString("imgurl"));
        final Uri imageResourceCover = Uri.parse(getIntent().getExtras().getString("imgCover"));
        final int original=getIntent().getExtras().getInt("original");
        movieThum = findViewById(R.id.detailMovieImg);
        rv_episodes=findViewById(R.id.rv_episodes);
        addlist=findViewById(R.id.add_list);
        if(sqliteMovie.searchMovies(getIntent().getExtras().getString("title")))
            addlist.setImageResource(R.drawable.ic_check_black_24dp);
        Glide.with(this).load(imageResource).into(movieThum);
        movieCover = findViewById(R.id.detaill_movie_cover);
        Glide.with(this).load(imageResourceCover).into(movieCover);
        tv_title=findViewById(R.id.detaill_movie_title);
        tv_title.setText(movieTitle);
        tv_description=findViewById(R.id.detaill_movie_description);
        tv_description.setText(getIntent().getExtras().getString("description"));
        movieCover.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scal_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this,R.anim.scal_animation));

        addlist.setOnClickListener(v -> {

           if(!sqliteMovie.searchMovies(tv_title.getText().toString()))
           {
               sqliteMovie.AddList(tv_title.getText().toString(),imageResourceCover.toString(),imageResource.toString(),tv_description.getText().toString(),StreamingLink,original);//(String title, String imgCover, String imgThum, String description,String streamingLink)
               addlist.setImageResource(R.drawable.ic_check_black_24dp);
                Toast.makeText(getApplicationContext(), "Add to List", Toast.LENGTH_SHORT).show();
           }
           else {
               addlist.setImageResource(R.drawable.add_delete);
               sqliteMovie.deleteFromList(tv_title.getText().toString());
               Toast.makeText(getApplicationContext(), "delete from your List", Toast.LENGTH_SHORT).show();

           }

        });
        play_fab.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), vedioplayer.class);
            i.putExtra("StremingLink",StreamingLink);
            i.putExtra("title",movieTitle);
            i.putExtra("description", Objects.requireNonNull(getIntent().getExtras()).getString("description"));
            i.putExtra("imgurl",getIntent().getExtras().getString("imgurl"));
            i.putExtra("imgCover",getIntent().getExtras().getString("imgCover"));
            i.putExtra("time",getIntent().getExtras().getInt("time"));
            i.putExtra("original",getIntent().getExtras().getInt("original"));
            i.putExtra("episode",getIntent().getExtras().getInt("season"));
            i.putExtra("season",getIntent().getExtras().getInt("episode"));
            startActivity(i);
        });
        RvCst();

    }
}
