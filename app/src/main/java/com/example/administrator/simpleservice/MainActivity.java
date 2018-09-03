package com.example.administrator.simpleservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MusicItemAdapter musicItemAdapter;
    private static final int REQUEST_CODE_MUSIC_PLAY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.RecyclerView_main_music);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),((LinearLayoutManager)recyclerView.getLayoutManager()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        String[] names = getResources().getStringArray(R.array.trackNames);

        for (int i = 0; i < Music.tracks.length; i++) {
            Music.musicList.add(new Music(Music.tracks[i], names[i]));
        }

        musicItemAdapter = new MusicItemAdapter(Music.musicList);
        musicItemAdapter.setOnStopMusicListener(music -> {
            stopService(new Intent(this, MyService.class));
        });
        musicItemAdapter.setOnMusicsItemListener(music -> {
            startActivityForResult(PlayActivity.getStartIntent(this, music), REQUEST_CODE_MUSIC_PLAY);
        });
        recyclerView.setAdapter(musicItemAdapter);
    }
}