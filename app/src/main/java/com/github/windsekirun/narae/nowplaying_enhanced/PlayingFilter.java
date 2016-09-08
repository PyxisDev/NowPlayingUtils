package com.github.windsekirun.narae.nowplaying_enhanced;

import android.annotation.SuppressLint;
import android.content.IntentFilter;

/**
 * Playing Filter
 * Created by WindSekirun on 2016-09-08.
 */
@SuppressLint("ParcelCreator")
public class PlayingFilter extends IntentFilter {

    public PlayingFilter() {
        super();

        this.addAction("com.android.music.metachanged");
        this.addAction("com.android.music.playstatechanged");
        this.addAction("com.android.music.playbackcomplete");
        this.addAction("com.android.music.queuechanged");

        // various
        this.addAction("com.miui.player.metachanged");
        this.addAction("com.htc.music.metachanged");
        this.addAction("com.nullsoft.winamp.metachanged");
        this.addAction("com.real.IMP.metachanged");
        this.addAction("fm.last.android.metachanged");
        this.addAction("com.sec.android.app.music.metachanged");
        this.addAction("com.amazon.mp3.metachanged");
        this.addAction("com.real.IMP.metachanged");
        this.addAction("com.rdio.android.metachanged");
        this.addAction("com.andrew.apollo.metachanged");
        this.addAction("com.lge.music.metachanged");
        this.addAction("com.pantech.app.music.metachanged");
        this.addAction("com.neowiz.android.bugs.metachanged");
        this.addAction("com.soundcloud.android.metachanged");
        this.addAction("com.soundcloud.android.playback.playcurrent");
        this.addAction("com.nullsoft.winamp.metachanged");
        this.addAction("com.amazon.mp3.metachanged");
        this.addAction("com.miui.player.metachanged");
        this.addAction("com.real.IMP.metachanged");
        this.addAction("com.samsung.sec.android.MusicPlayer.metachanged");
        this.addAction("com.andrew.apollo.metachanged");
        this.addAction("com.htc.music.metachanged");
        this.addAction("com.spotify.music.metadatachanged");

        // sony walkman
        this.addAction("com.sonyericsson.music.metachanged");
        this.addAction("com.sonyericsson.music.playbackcontrol.ACTION_PLAYBACK_PLAY");
        this.addAction("com.sonyericsson.music.TRACK_COMPLETED");
        this.addAction("com.sonyericsson.music.playbackcomplete");
        this.addAction("com.sonyericsson.music.playstatechanged");
        this.addAction("com.sonyericsson.music.playbackcontrol.ACTION_TRACK_STARTED");
        this.addAction("com.sonyericsson.music.playbackcontrol.ACTION_PAUSED");

        // xiaomi
        this.addAction("soundbar.music.metachanged");
    }
}
