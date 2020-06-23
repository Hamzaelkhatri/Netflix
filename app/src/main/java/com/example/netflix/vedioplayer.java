/*
 * Create by Hamza elkhatri
 */

/*
 * Create by Hamza elkhatri
 */

package com.example.netflix;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.netflix.R;
import com.example.netflix.database.SqliteMovie;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.SingleSampleMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.MimeTypes;

public class vedioplayer extends AppCompatActivity
{
    SqliteMovie dbMovie = new SqliteMovie(this);
    SeekBar seekBar;
    PlayerView playerView;
    ProgressBar progressBar;
    int brightness,original;
    ImageView floating;
    AlertDialog alertDialog;
    Uri Url = Uri.EMPTY;
    String StrUrl;
    MergingMediaSource mergedSource;
    boolean float_ing  = false;
    MediaSource mediaSource;
    ImageView translate;
    String title,description,imgthun,streamingLink,imgCover;



    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return super.getLayoutInflater();
    }


    SimpleExoPlayer simpleExoPlayer;
    int episode,season;
    int min = 0, max = 100;
    boolean first=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedioplayer);
        playerView =findViewById(R.id.playerv);
        progressBar=findViewById(R.id.progress_bar);
        seekBar =findViewById(R.id.seekBar4);
        floating=findViewById(R.id.floatingbtb);

        translate=findViewById(R.id.ic_sub);
        TextView ed = findViewById(R.id.exo_duration);
        final int time;
        title=getIntent().getExtras().getString("title");
        time = getIntent().getExtras().getInt("time");
        description=getIntent().getExtras().getString("description");
        streamingLink = getIntent().getExtras().getString("StremingLink");
        imgthun =getIntent().getExtras().getString("imgurl");
        imgCover =getIntent().getExtras().getString("imgCover");
        original =getIntent().getExtras().getInt("original");
        episode=getIntent().getExtras().getInt("episode");
        season=getIntent().getExtras().getInt("season");



        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starStopFloating();
            }
        });

        try{
            brightness =
                    Settings.System.getInt(getContentResolver(),
                            Settings.System.SCREEN_BRIGHTNESS, 0);

            brightness = Settings.System.getInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
            seekBar.setProgress(brightness);
            seekBar.setProgress(max - min);
            seekBar.setProgress(brightness - min);
        }
        catch(Settings.SettingNotFoundException e){
            Log.e("Error", "Cannot access system brightness");
            e.printStackTrace();
        }


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, progress);
//                Toast.makeText(vedioplayer.this,progress,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        StrUrl=getIntent().getExtras().getString("StremingLink");
        Url=Uri.parse(StrUrl);
        LoadControl loadControl = new DefaultLoadControl();

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector trackSelector =new DefaultTrackSelector(

                new AdaptiveTrackSelection.Factory(bandwidthMeter)
        );
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(
                vedioplayer.this,trackSelector,loadControl
        );
        DefaultHttpDataSourceFactory factory = new DefaultHttpDataSourceFactory(
                "exoplayer_video"
        );

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

         mediaSource = new ExtractorMediaSource(Url,factory,extractorsFactory,null,null);
        Format subtitle= Format.createTextSampleFormat(
                null,
                MimeTypes.APPLICATION_SUBRIP,
                Format.NO_VALUE,
                "en"
                ,null
        );

        Uri uriS= Uri.parse("https://firebasestorage.googleapis.com/v0/b/netflix-c836a.appspot.com/o/Despacito%20Remix%20Luis%20Fonsi%20ft.Daddy%20Yankee%20Justin%20Bieber%20Lyrics%20%5BSpanish%5D.srt?alt=media&token=dd900ad1-fdba-4fac-b4d6-c0e17566781e");

        MediaSource subtitleSource;
        subtitleSource = new SingleSampleMediaSource(uriS, factory, subtitle, C.TIME_UNSET);
       mergedSource =
                new MergingMediaSource(mediaSource, subtitleSource);




        playerView.setPlayer(simpleExoPlayer);

        playerView.setKeepScreenOn(true);

        translate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        if(float_ing)
                        {

                            simpleExoPlayer.prepare(mediaSource);
                            simpleExoPlayer.setPlayWhenReady(true);
                            float_ing = false;
                        }
                        else
                        {
                            simpleExoPlayer.prepare(mergedSource);
                            simpleExoPlayer.setPlayWhenReady(true);
                            float_ing = true;
                        }
                    }
                }
        );


        playerView.setOnFocusChangeListener(
                new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {

                        if(hasFocus)
                        {
                            playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
                        }

                    }
                }
        );



        simpleExoPlayer.prepare(mediaSource);



        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.addListener(new Player.EventListener()
                                    {

                                        @Override
                                        public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

                                        }

                                        @Override
                                        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

                                        }

                                        @Override
                                        public void onLoadingChanged(boolean isLoading) {

                                        }

                                        @Override
                                        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                                            if(playbackState == Player.STATE_BUFFERING)
                                            {
                                                progressBar.setVisibility(View.VISIBLE);
                                            }
                                            else if(playbackState==Player.STATE_READY)
                                            {
                                                if(first)
                                                {double i =(double)(((double)time/100)*simpleExoPlayer.getDuration());
                                               // Toast.makeText(getApplicationContext(),"TIME TIME IS :"+i,Toast.LENGTH_LONG).show();
                                                simpleExoPlayer.seekTo((long) (i));
                                                 first=false;
                                                }
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }

                                        @Override
                                        public void onRepeatModeChanged(int repeatMode) {

                                        }

                                        @Override
                                        public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

                                        }

                                        @Override
                                        public void onPlayerError(ExoPlaybackException error) {

                                        }

                                        @Override
                                        public void onPositionDiscontinuity(int reason) {

                                        }

                                        @Override
                                        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

                                        }

                                        @Override
                                        public void onSeekProcessed() {

                                        }
                                    }
        );

    }

    private boolean checkPermisstion()
    {
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M)
        {
            if(!Settings.canDrawOverlays(this))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
            return true;
    }

    private void reqPermission() {
        final AlertDialog.Builder alertBuiler = new AlertDialog.Builder(this);
        alertBuiler.setCancelable(true);
        alertBuiler.setTitle("Screen Floating detected");
        alertBuiler.setMessage("Enable Draw over other apps in your system setting" );
        alertBuiler.setPositiveButton("Open Setting", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,Uri.parse("package:"+getPackageName()));
                startActivityForResult(intent,RESULT_OK );
            }
        });
            alertDialog = alertBuiler.create();
            alertDialog.show();
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event)
//    {
//        if(keyCode == KeyEvent.KEYCODE_BACK)
//        {
//            Log.d("Test", "Back button pressed!");
//        }
//        else if(keyCode == KeyEvent.KEYCODE_HOME)
//        {
//
//        }
//        return super.onKeyDown(keyCode, event);
//    }
Intent i;
    private void starStopFloating() {
        if(checkPermisstion())
        {
            i = new Intent(vedioplayer.this,FloatingWindow.class);
            simpleExoPlayer.setPlayWhenReady(false);
            simpleExoPlayer.release();
        //    Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show();
            startService(i);
            finish();
        }
        else
                reqPermission();

    }

    @Override
    protected void onStop() {
        super.onStop();
        //starStopFloating();
          //  Toast.makeText(this, "Here", Toast.LENGTH_SHORT).show();
//        dbMovie.DeleteMovies(title);
//        dbMovie.insertData(title,imgCover,imgthun, description,seekBar.getProgress(),StrUrl);
            //finish();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        simpleExoPlayer.setPlayWhenReady(false)
        ;
        simpleExoPlayer.getPlaybackState();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.getPlaybackState();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        simpleExoPlayer.setPlayWhenReady(false);
        simpleExoPlayer.release();
        dbMovie.DeleteMovies(title,season,episode);
       dbMovie.insertData(title,imgCover,imgthun, description,(int)((simpleExoPlayer.getCurrentPosition()*100)/simpleExoPlayer.getDuration()),streamingLink,original,episode,season);
       Toast.makeText(this,"THUM URL"+imgthun,Toast.LENGTH_LONG).show();
       if((int)((simpleExoPlayer.getCurrentPosition()*100)/simpleExoPlayer.getDuration())==100)
           dbMovie.DeleteMovies(title,season,episode);
    }
}
