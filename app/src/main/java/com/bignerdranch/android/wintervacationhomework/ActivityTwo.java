package com.bignerdranch.android.wintervacationhomework;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class ActivityTwo extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        if(ContextCompat.checkSelfPermission(ActivityTwo.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ActivityTwo.this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },1);
        }
        else {
            mediaPlayer = MediaPlayer.create(this, R.raw.dream);
            mediaPlayer.start();
        }

    }

    @Override
    protected void onPause(){
        super.onPause();
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            position = mediaPlayer.getCurrentPosition();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(position != 0){
            mediaPlayer.seekTo(position);
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    mediaPlayer = MediaPlayer.create(this, R.raw.man);
                    mediaPlayer.start();
                }else {
                    Toast.makeText(this,"无法播放音乐",Toast.LENGTH_SHORT).show();
                }
        }
    }

}