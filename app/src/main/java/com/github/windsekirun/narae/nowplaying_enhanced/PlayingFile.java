package com.github.windsekirun.narae.nowplaying_enhanced;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by PC38213 on 2016-09-08.
 */
public class PlayingFile {
    private long ID = -1L;
    private long albumID = -1L;
    private @Nullable String data = "";
    private @Nullable String albumKey = "";
    private @NonNull String title = "";
    private @Nullable String album = "";
    private @Nullable String artist = "";
    private @Nullable String genre = "";
    private @Nullable Uri fileUri = Uri.parse("");
    private @Nullable Uri albumArt = null;

    @Nullable
    public Uri getFileUri() {
        return fileUri;
    }

    @Nullable
    public Uri getAlbumArt() {
        return albumArt;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @Nullable
    public String getGenre() {
        return genre;
    }

    @Nullable
    public String getAlbum() {
        return album;
    }

    @Nullable
    public String getArtist() {
        return artist;
    }

    public long getAlbumID() {
        return albumID;
    }

    public long getID() {
        return ID;
    }

    @Nullable
    public String getAlbumKey() {
        return albumKey;
    }

    @Nullable
    public String getData() {
        return data;
    }

    public PlayingFile setFileUri(@Nullable Uri fileUri) {
        this.fileUri = fileUri;
        return this;
    }

    public PlayingFile setTitle(@NonNull String title) {
        this.title = title;
        return this;
    }

    public PlayingFile setGenre(@Nullable String genre) {
        this.genre = genre;
        return this;
    }

    public PlayingFile setArtist(@Nullable String artist) {
        this.artist = artist;
        return this;
    }

    public PlayingFile setAlbumArt(@Nullable Uri albumArt) {
        this.albumArt = albumArt;
        return this;
    }

    public PlayingFile setAlbum(@Nullable String album) {
        this.album = album;
        return this;
    }

    public PlayingFile setAlbumID(long albumID) {
        this.albumID = albumID;
        return this;
    }

    public PlayingFile setAlbumKey(@Nullable String albumKey) {
        this.albumKey = albumKey;
        return this;
    }

    public PlayingFile setData(@Nullable String data) {
        this.data = data;
        return this;
    }

    public PlayingFile setID(long ID) {
        this.ID = ID;
        return this;
    }
}
