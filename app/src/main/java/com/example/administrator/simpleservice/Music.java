package com.example.administrator.simpleservice;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Music implements Parcelable {

    public static List<Music> musicList = new ArrayList<>();

    public static int[] tracks = {R.raw.al_fil_105, R.raw.quraish_106, R.raw.al_maun_107,
            R.raw.al_kauther_108, R.raw.al_kafiroon_109, R.raw.an_nasr_110, R.raw.al_masadd_111,
            R.raw.al_ikhlas_112, R.raw.al_falaq_113, R.raw.an_nas_114};

    private int track;
    private String trackName;

    public Music(int track, String trackName) {
        this.track = track;
        this.trackName = trackName;
    }

    protected Music(Parcel in) {
        track = in.readInt();
        trackName = in.readString();
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(track);
        parcel.writeString(trackName);
    }

    public String getTrackName() {
        return trackName;
    }

    public int getTrack() {
        return track;
    }
}