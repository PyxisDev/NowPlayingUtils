package com.github.windsekirun.narae.nowplaying_enhanced;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

/**
 * PlayingModule
 * Created by WindSekirun on 2016-09-08.
 */
public class PlayingModule {
    OnPlayingCallbackListener mListener = null;
    PlayingFile mNowFile = new PlayingFile();
    FetchMode fetchMode = FetchMode.NORMAL;
    ArrayList<PlayingFile> musicList = new ArrayList<>();

    Context context;
    BroadcastReceiver receiver;

    public static final String LOGTAG = "NaraeNowplaying";
    public final static Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");      

    boolean isDebug = false;

    public PlayingModule(Context c) {
        this.context = c;

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                String cmd = intent.getStringExtra("command");

                if (isDebug)
                    Log.d(LOGTAG, action + " / " + cmd);

                String artist;
                String album;
                String track;

                // try for sony devices
                artist = getFromIntent(intent, "ARTIST_NAME");
                album = getFromIntent(intent, "ALBUM_NAME");
                track = getFromIntent(intent, "TRACK_NAME");

                if (artist == null && album == null && track == null) {
                    artist = getFromIntent(intent, "artist");
                    album = getFromIntent(intent, "album");
                    track = getFromIntent(intent, "track");
                }

                mNowFile = new PlayingFile()
                        .setArtist(artist)
                        .setAlbum(album)
                        .setTitle(track);

                if (mListener != null) {
                    mListener.onCallback(mNowFile);
                }

                if (fetchMode == FetchMode.ENHANCED) {
                    // TODO: read ID3 Tag using given text such as title, artist, album.
                    // TODO: sometimes, user don't input album.
                    // TODO: title is NonNull. cause, MediaStore apply title within FileName automatically.
                }

                if (isDebug)
                    Log.d(LOGTAG, artist + ":" + album + ":" + track);
            }
        };

        context.registerReceiver(receiver, new PlayingFilter());
    }

    public void setOnPlayingCallbackListener(OnPlayingCallbackListener mListener) {
        this.mListener = mListener;
    }

    public void setDebug(boolean debuggable) {
        this.isDebug = debuggable;
    }

    public void setFetchMode(FetchMode fetchMode) {
        this.fetchMode = fetchMode;
    }

    public PlayingFile getNowPlayingFile() {
        return mNowFile;
    }

    private String getFromIntent(Intent intent, String name) {
        if (intent.getStringExtra(name) != null) {
            return intent.getStringExtra(name);
        } else {
            return null;
        }
    }

    private class getEnhancedInfoTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            ContentResolver contentResolver = context.getContentResolver();
            Uri contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            Cursor cursor = contentResolver.query(contentUri, null, null, null, null);

            if (cursor != null) {
                int titleColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
                int idColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID);
                int artistColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST);
                int albumId = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
                int data = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
                int size = cursor.getColumnIndex(MediaStore.Audio.Media.SIZE);
                int albumKey = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_KEY);

                while (cursor.moveToNext()) {
                    long thisId = cursor.getLong(idColumn);
                    String thisTitle = cursor.getString(titleColumn);
                    String thisArtist = cursor.getString(artistColumn);
                    long thisAlbumId = cursor.getLong(albumId);
                    String thisData = cursor.getString(data);
                    String thisAlbumKey = cursor.getString(albumKey);

                    PlayingFile file = new PlayingFile()
                            .setTitle(thisTitle)
                            .setArtist(thisArtist)
                            .setAlbumID(thisAlbumId)
                            .setData(thisData)
                            .setID(thisId)
                            .setAlbumKey(thisAlbumKey);

                    musicList.add(file);
                }

                cursor.close();
            }

            if (!musicList.isEmpty()) {
                for (PlayingFile playingFile : musicList) {
                    if (playingFile.getTitle().contains(mNowFile.getTitle())) {
                        String backupAlbum = playingFile.getAlbum();

                        mNowFile = playingFile;
                        mNowFile.setAlbum(backupAlbum);

                        break;
                    }
                }
            }

            musicList.clear();
            musicList.trimToSize();
            
            long toGetAlbumId = mNowFile.getAlbumID();
            
            Uri uri = ContentUris.withAppendedId(sArtworkUri, toGetAlbumId);
            mNowFile.setAlbumArt(uri);
            // TODO: Process Return
            return null;
        }
    }
}
