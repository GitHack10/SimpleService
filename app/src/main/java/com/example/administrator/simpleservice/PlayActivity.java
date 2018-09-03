package com.example.administrator.simpleservice;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {

    private final static String EXTRA_MUSIC = "PLAY_MUSIC";

    public static Intent getStartIntent(Context context, Music music) {
        Intent intent = new Intent(context, PlayActivity.class);
        intent.putExtra(EXTRA_MUSIC, music);
        return intent;
    }

    private Music music;

    private TextView trackNameTextView;
    private TextView trackExecutorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        music = getIntent().getParcelableExtra(EXTRA_MUSIC);
        initViews();
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra(EXTRA_MUSIC, music);
        if (isPlayMus(MyService.class) == false) {
            startService(intent);
        } else {
            stopService(intent);
            startService(intent);
        }
    }

    private void initViews() {
        trackNameTextView = findViewById(R.id.TextView_playMusic_trackName);
        trackExecutorTextView = findViewById(R.id.TextView_playMusic_trackExecutor);

        trackNameTextView.setText(music.getTrackName());
        trackExecutorTextView.setText(R.string.trackExecutor);
    }

    private boolean isPlayMus(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo serviceInfo : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(serviceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}