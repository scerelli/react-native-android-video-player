package com.reactnativeandroidmediaplayer.mediaplayer;

/**
 * Created by vood on 03/11/2016.
 */

import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import net.protyposis.android.mediaplayer.MediaPlayer;
import net.protyposis.android.mediaplayer.UriSource;
import net.protyposis.android.mediaplayer.VideoView;

public class MediaPlayerViewManager extends SimpleViewManager<VideoView> {

    public static final String REACT_CLASS = "RCTMediaPlayer";
    public static final String PROP_SRC_URI = "uri";
    public static final String PROP_SPEED = "speed";
    public static final String PROP_PLAY = "play";

    public float settedSpeed = 1.0F;
    public String settedUri = "";

    private ThemedReactContext mContext = null;


    @ReactProp(name = PROP_SRC_URI)
    public void setVideoUrl(final VideoView view, String uri) {
        this.settedUri = uri;
        this.initVideo(view);
    }

    @ReactProp(name = PROP_SPEED)
    public void setVideoSpeed(VideoView view, final @Nullable String speed) {
        this.settedSpeed = Float.parseFloat(speed);
        view.setPlaybackSpeed(this.settedSpeed);
    }

    @ReactProp(name = PROP_PLAY)
    public void play(VideoView view, final @Nullable Boolean play) {
        try {
            if(!view.isPlaying() && play) {
                view.start();
            } else if(view.canPause() && !play) {
                view.pause();
            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
    }

    public UriSource convertUri (String uri) {
        return new UriSource(this.mContext, Uri.parse(this.settedUri));
    }

    public void initVideo (final VideoView view) {
        UriSource source = new UriSource(this.mContext, Uri.parse(this.settedUri));
        float settedSpeed = this.settedSpeed;
        view.setVideoSource(source);
        view.requestFocus();
        view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer player) {
                view.setPlaybackSpeed(MediaPlayerViewManager.this.settedSpeed);
                view.start();
            }
        });
        view.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer player) {
                player.reset();
                view.setVideoSource(MediaPlayerViewManager.this.convertUri(MediaPlayerViewManager.this.settedUri));
                view.setPlaybackSpeed(MediaPlayerViewManager.this.settedSpeed);
                view.start();
            }
        });
    }

    @Override
    public VideoView createViewInstance(ThemedReactContext context){
        mContext = context;
        return new VideoView(context);
    }


    @Override
    public String getName() {
        return REACT_CLASS;
    }
}
