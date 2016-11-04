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
    private ThemedReactContext mContext = null;

    @ReactProp(name = "uri")
    public void setStreamUrl(final VideoView view, String uri) {
        UriSource source = new UriSource(this.mContext, Uri.parse(uri));
        view.setVideoSource(source);
        view.requestFocus();
        view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer player) {
                player.setLooping(true);
                view.start();
            }
        });
    }

    @ReactProp(name = "speed")
    public void setVideoSpeed(VideoView view, @Nullable String speed) {
        float fSpeed = Float.parseFloat(speed);
        view.setPlaybackSpeed(fSpeed);
    }

    @ReactProp(name = "play")
    public void play(VideoView view, final Boolean play) {
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
