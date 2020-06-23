
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
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.netflix.adapters.MovieAdapter;
import com.example.netflix.adapters.MovieViewAdapter;
import com.example.netflix.adapters.TopMovie;
import com.example.netflix.database.SqliteMovie;
import com.example.netflix.models.Movie;
import com.example.netflix.models.slide;
import com.example.netflix.constants;
import com.example.netflix.adapters.MovieItemClickListener;
import com.example.netflix.R;
import com.example.netflix.adapters.slideAdapter;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Home extends AppCompatActivity implements MovieItemClickListener {


    private List<slide> slideList;
    private TextView tvList,tv_watched;
    private ViewPager slidepager;
    private TabLayout indicator;
    private SqliteMovie dbMovie = new SqliteMovie(this);
    private RecyclerView MovieView,TopMovies,FavMovie,MyListMovie;
    private Intent  intent;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onRestart()
    {
        super.onRestart();
        List<Movie> mv = dbMovie.getMovies().stream().filter(distinctByKey(Movie::getTitle)).
                sorted((Comparator.comparingInt(Movie::getTime)).thenComparing(Movie::getEpisode)).collect(Collectors.toList());
        MovieViewAdapter movieAdapter = new MovieViewAdapter(this,mv,this);
        FavMovie.setAdapter(movieAdapter);
        FavMovie.setLayoutManager(new LinearLayoutManager(Home.this, LinearLayoutManager.HORIZONTAL,false));
        if(mv.size()==0)
            tv_watched.setVisibility(View.GONE);
        else
            tv_watched.setVisibility(View.VISIBLE);

        getListMovie();
    }

    void getListMovie()
    {
        List<Movie> movieList = dbMovie.getMyList();
        MovieAdapter movieAdapter = new MovieAdapter(this,movieList,this);
        MyListMovie.setAdapter(movieAdapter);
        MyListMovie.setLayoutManager(new LinearLayoutManager(Home.this, LinearLayoutManager.HORIZONTAL,false));
        if(movieList.size()==0)
            tvList.setVisibility(View.GONE);
        else
            tvList.setVisibility(View.VISIBLE);


    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_ctivi);
            slidepager = findViewById(R.id.sliderPage);
            indicator = findViewById(R.id.indicator);
            MovieView = findViewById(R.id.Rv_movie);
            tv_watched=findViewById(R.id.tv_watched);
            MyListMovie = findViewById(R.id.rv_listMovie);
            FavMovie=findViewById(R.id.rv_fav);
        tvList=findViewById(R.id.textView8);
        TopMovies= findViewById(R.id.Rv_mostview);
                    slideList = new ArrayList<>();
        slideList.add(new slide(R.drawable.slide1,"Slide Title \nmore Here"));
        slideList.add(new slide(R.drawable.slide2,"Slide Title \nmore Here"));
        slideList.add(new slide(R.drawable.slide1,"Slide Title \nmore Here"));
        slideList.add(new slide(R.drawable.slide2,"Slide Title \nmore Here"));
        slideAdapter slideAdapter = new slideAdapter(this,slideList);
        slidepager.setAdapter(slideAdapter);
        intent= new Intent(Home.this,movieDetaill.class);


        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new sliderTimer(),4000,6000);
        indicator.setupWithViewPager(slidepager,true);
        Task ts = new Task();
        ts.execute();
        getListMovie();
        List<Movie> mv = dbMovie.getMovies().stream().filter(distinctByKey(Movie::getTitle)).collect(Collectors.toList());
      //  Toast.makeText(this,"we have : "+mv.size(),Toast.LENGTH_LONG).show();
        MovieViewAdapter movieAdapter = new MovieViewAdapter(this,mv,this);
        FavMovie.setAdapter(movieAdapter);
        FavMovie.setLayoutManager(new LinearLayoutManager(Home.this, LinearLayoutManager.HORIZONTAL,false));
        if(mv.size()==0)
            tv_watched.setVisibility(View.GONE);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView)
    {
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("description",movie.getDescription());
        intent.putExtra("imgurl",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCover());
        intent.putExtra("StremingLink",movie.getStreamibgLink());
        intent.putExtra("time",movie.getTime());
        intent.putExtra("original",movie.getOriginal());
        intent.putExtra("episode",movie.getEpisode());
        intent.putExtra("season",movie.getSeason());
        Toast.makeText(this,"TITLE"+movie.getEpisode(),Toast.LENGTH_LONG).show();
        ActivityOptions options = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            options = ActivityOptions.makeSceneTransitionAnimation(
                Home.this,movieImageView,"sharedName"
            );
        }
        assert options != null;
        startActivity(intent,options.toBundle());
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


    private class Task extends AsyncTask<Void, Void, Void>
    {

        private ArrayList<Movie> topMovie= new ArrayList<Movie>();
        private MovieAdapter movieAdapter;

        protected void onPreExecute()
        {
            showDialog(1);
        }
        protected Void doInBackground(Void... JSONArray) {

            try
            {


            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        protected void onPostExecute(Void unused) {
            StringRequest request = new StringRequest(StringRequest.Method.POST,constants.URL_MOVIE,
                    response -> {
                        try
                        {
                            JSONObject jsa = new JSONObject(response);
                            JSONArray jsaa =jsa.getJSONArray("Movie");
                            for(int i = 0;i<jsaa.length();i++)
                            {
                                JSONObject jso = jsaa.getJSONObject(i);
                                //String title, String description, String thumbnail,String cover, int original, int episode, int season, String streamibgLink,String title_ep
                                topMovie.add(new Movie(jso.getString("title"),jso.getString("description"),jso.getString("imgthum"),jso.getString("imgCover"),jso.getInt("original"),jso.getInt("Episode"),jso.getInt("season"),jso.getString("streaminglink"),jso.getString("title_ep")));
                            }


                            //Toast.makeText(getApplicationContext(),"test 00   ->" + topMovie.size(),Toast.LENGTH_LONG).show();

                            movieAdapter = new MovieAdapter(Home.this, topMovie.stream().filter(distinctByKey(Movie::getTitle)).collect(Collectors.toList()) , Home.this);
                            MovieView.setAdapter(movieAdapter);
                            MovieView.setLayoutManager(new LinearLayoutManager(Home.this, LinearLayoutManager.HORIZONTAL,false));
                            MovieView.setAnimation(AnimationUtils.loadAnimation(Home.this,R.anim.scal_animation));
                            slidepager.setAnimation(AnimationUtils.loadAnimation(Home.this,R.anim.scal_animation));
                            indicator.setAnimation(AnimationUtils.loadAnimation(Home.this,R.anim.scal_animation));
                            intent.putExtra("movies", jsa.toString());
                            TopMovie nb = new TopMovie(getApplicationContext(), topMovie.stream().filter(distinctByKey(Movie::getTitle)).collect(Collectors.toList()),Home.this);
                            TopMovies.setAdapter(nb);
                            TopMovies.setLayoutManager(new LinearLayoutManager(Home.this,LinearLayoutManager.HORIZONTAL,false));
                        }
                        catch (JSONException ex)
                        {
                            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                            Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);
        }

        }

        class sliderTimer extends TimerTask
        {
        @Override
        public void run()
        {
            Home.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (slidepager.getCurrentItem()<slideList.size()-1)
                    {
                        slidepager.setCurrentItem(slidepager.getCurrentItem()+1);
                    }
                    else
                    {
                        slidepager.setCurrentItem(0);
                    }
                }
            });
        }
    }

}
