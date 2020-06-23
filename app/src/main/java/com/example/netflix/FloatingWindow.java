/*
 * Create by Hamza elkhatri
 */

/*
 * Create by Hamza elkhatri
 */

package com.example.netflix;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.example.netflix.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class FloatingWindow extends Service {

    public FloatingWindow() {}
    WindowManager windowManager;
    private View mFloatingWidget;
    SimpleExoPlayer exoPlayer;
    PlayerView playerView;
    PlayerView playerView1;
    BandwidthMeter bandwidthMeter;
    RelativeLayout relativeLayout;
    TrackSelector trackSelector;
    DefaultDataSourceFactory defaultDataSourceFactory;
    ExtractorsFactory extractorsFactory;

    Uri videoUri;
    String uristr;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        if(intent!=null) {

            uristr = "https://firebasestorage.googleapis.com/v0/b/netflix-c836a.appspot.com/o/2020-04-23%2012-52-14.mp4?alt=media&token=ae320b0b-f116-40a6-ad54-007af746685d";
            videoUri = Uri.parse(uristr);
            if (windowManager != null && mFloatingWidget.isShown() && exoPlayer != null) {
                windowManager.removeView(mFloatingWidget);
                mFloatingWidget = null;
                windowManager = null;
                exoPlayer.setPlayWhenReady(false);
                exoPlayer.release();
                exoPlayer = null;

            }
            final WindowManager.LayoutParams params;

            mFloatingWidget = LayoutInflater.from(this).inflate(R.layout.popup, null);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                params = new WindowManager.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        PixelFormat.TRANSLUCENT
                );
            } else {
                params = new WindowManager.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_PHONE,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        PixelFormat.TRANSLUCENT
                );
            }

            params.gravity = Gravity.TOP
            | Gravity.LEFT;
            params.x = 0;
            params.y = 0;

            windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
            windowManager.addView(mFloatingWidget,params);

            bandwidthMeter = new DefaultBandwidthMeter();
            trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this,trackSelector);


            playerView = mFloatingWidget.findViewById(R.id.playerview);

            relativeLayout= mFloatingWidget.findViewById(R.id.tt);
            ImageView imageViewClose= mFloatingWidget.findViewById(R.id.imageViewDismiss);
            ImageView imageViewMaximaze = mFloatingWidget.findViewById(R.id.imageViewMaximaze);

            imageViewMaximaze.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(windowManager !=null && mFloatingWidget.isShown() && exoPlayer !=null)
                    {
                        windowManager.removeView(mFloatingWidget);
                        mFloatingWidget= null;
                        windowManager= null;
                        exoPlayer.setPlayWhenReady(false);
                        exoPlayer.release();
                        exoPlayer=null;
                        stopSelf();
                        Intent openIntent= new Intent(FloatingWindow.this,vedioplayer.class);
                        openIntent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(openIntent);
                    }
                }
            });

            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            imageViewClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(windowManager!=null && mFloatingWidget.isShown() && exoPlayer !=null)
                    {
                        windowManager.removeView(mFloatingWidget);
                        mFloatingWidget= null;
                        windowManager= null;
                        exoPlayer.setPlayWhenReady(false);
                        exoPlayer.release();
                        exoPlayer=null;
                        stopSelf();
                    }
                }
            });

            playVidio();
            mFloatingWidget.findViewById(R.id.relativeLayoutPopup).setOnTouchListener(new View.OnTouchListener()
            {
                private int initialX;
                private int initialY;

                private float InitialTouchX;
                private float InitialTouchY;
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    switch (event.getAction())
                    {
                        case MotionEvent.ACTION_DOWN :
                            initialX = params.x;
                            initialY = params.y;
                            InitialTouchX= event.getRawX();
                            InitialTouchY = event.getRawY();
                            return true;
                        case MotionEvent.ACTION_UP:
                                return true;
                        case MotionEvent.ACTION_MOVE:
                                params.x = initialX + (int)(event.getRawX() - InitialTouchX);
                                params.y = initialY + (int)(event.getRawY() - InitialTouchY);
                                windowManager.updateViewLayout(mFloatingWidget,params);
                                return true;

                    }
                    return false;
                }
            });
        }
        return super.onStartCommand(intent, flags, startId);
    }


    public void playVidio()
    {
        try {
            String playInfo = Util.getUserAgent(this, "webservices");
            defaultDataSourceFactory = new DefaultDataSourceFactory(this, playInfo);
            extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videoUri, defaultDataSourceFactory, extractorsFactory,
                    null, null);
            playerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mFloatingWidget!=null){
            windowManager.removeView(mFloatingWidget);
        }
    }
}
