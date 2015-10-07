package com.example.abhinandansharma.mp3;

/*
1. Bound service
2. Onstartcommand for mp3 in case the app is closed the song continue to play in background
3.
 */


import android.app.Activity;
import android.view.MotionEvent;
import android.widget.AdapterView;
import android.widget.MediaController.MediaPlayerControl;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import android.net.Uri;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity  {
    public static ArrayList<Song> songList;
    private ListView songView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("abhi", "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        songView = (ListView)findViewById(R.id.song_list);
        songView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.d("abhi","onItemClick");
                Intent intent = new Intent(MainActivity.this,Playing_Activity.class);
                intent.putExtra("Song_number",position);
                startActivity(intent);
            }
        });
        songList = new ArrayList<>();
        getSongList();
        Collections.sort(songList, new Comparator<Song>() {
            public int compare(Song a, Song b) {
                return a.getTitle().compareTo(b.getTitle());
            }
        });

        SongAdapter songAdt = new SongAdapter(this, songList);
        songView.setAdapter(songAdt);

    }


    public void getSongList() {
        Log.d("abhi", "getSongList");
        //retrieve song info
        ContentResolver musicContentResolver = getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicContentResolver.query(musicUri,null,null,null,null);
        if(musicCursor!=null && musicCursor.moveToFirst()){
            //get columns
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            //add songs to list
            do {
                long thisId = musicCursor.getLong(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                songList.add(new Song(thisId, thisTitle, thisArtist));
            }
            while (musicCursor.moveToNext());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
