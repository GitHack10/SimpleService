package com.example.administrator.simpleservice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MusicItemAdapter extends RecyclerView.Adapter<MusicItemAdapter.ItemViewHolder> {

    private List<Music> musicList;
    private OnMusicsItemListener onMusicsItemListener;
    private OnStopMusicListener onStopMusicListener;

    public MusicItemAdapter(List<Music> musicList) {
        this.musicList = musicList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_music, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.setData(musicList.get(position));
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public void setOnMusicsItemListener(OnMusicsItemListener onMusicsItemListener) {
        this.onMusicsItemListener = onMusicsItemListener;
    }

    public void setOnStopMusicListener(OnStopMusicListener onStopMusicListener) {
        this.onStopMusicListener = onStopMusicListener;
    }

    public interface OnMusicsItemListener {
        void OnMusicItemClick(Music music);
    }

    public interface OnStopMusicListener {
        void OnStopClick(Music music);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView trackNameTextView;
        private TextView trackExecutorTextView;

        private ImageView stopImageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            trackNameTextView = itemView.findViewById(R.id.TextView_itemMusic_trackName);
            trackExecutorTextView = itemView.findViewById(R.id.TextView_itemMusic_trackExecutor);
            stopImageView = itemView.findViewById(R.id.ImageView_itemMusic_stopMusic);
        }

        private void setData(Music music) {
            trackNameTextView.setText(music.getTrackName());
            trackExecutorTextView.setText(R.string.trackExecutor);

            stopImageView.setOnClickListener(view -> {
                if (onStopMusicListener != null) onStopMusicListener.OnStopClick(music);
                stopImageView.setVisibility(View.GONE);
            });

            itemView.setOnClickListener(view -> {
                if (onMusicsItemListener != null) onMusicsItemListener.OnMusicItemClick(music);
                stopImageView.setVisibility(View.VISIBLE);
            });
        }
    }
}