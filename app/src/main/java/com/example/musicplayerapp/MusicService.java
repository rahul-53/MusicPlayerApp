package com.example.musicplayerapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.MediaStore;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;

    public MusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this,R.raw.send_my_love);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

    public class ServiceBinder extends Binder{
        public MusicService getMusicService(){
            return MusicService.this;
        }
    }

    public void play(){
        if (!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }

    }
    public void pause(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }

    }
    public void resume(){
        if (!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }

    }
    public void stop(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }

}