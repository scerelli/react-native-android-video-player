package com.reactnativeandroidmediaplayer.mediaplayer;

/**
 * Created by vood on 03/11/2016.
 */

import android.app.Activity;
import android.media.session.MediaController;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactMethod;
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
                view.start();
            }
        });
    }

    @ReactProp(name = "speed")
    public void setVideoSpeed(VideoView view, @Nullable String speed) {
        float fSpeed = Float.parseFloat(speed);
        view.setPlaybackSpeed(fSpeed);
    }

    @ReactMethod
    public void play(VideoView view) {
        if(!view.isPlaying()) {

        }
    }

    @ReactMethod
    public void pause(VideoView view) {
        if(view.isPlaying()) {
            view.pause();
        }
    }

    @ReactMethod
    public void onPreparedCallback(final VideoView view, Callback onPrepared){
        final Callback onPreparedCallback = onPrepared;
        try{
            view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer player) {
                    try {
                        onPreparedCallback.invoke(view.getDuration());
                    } catch (Exception e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }
                }
            });
        }catch(Exception e){
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
