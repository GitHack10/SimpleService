package com.example.administrator.simpleservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService extends Service {
    private final static String EXTRA_MUSIC = "PLAY_MUSIC";
    private MediaPlayer mediaPlayer;
    private Music music;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        music = intent.getParcelableExtra(EXTRA_MUSIC);
        mediaPlayer = MediaPlayer.create(this, music.getTrack());
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}