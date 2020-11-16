package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.logging.Level;

public class AlarmActivity extends AppCompatActivity  {

    MediaPlayer player;
    LinearLayout alarmscreen;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        alarmscreen = (LinearLayout) findViewById(R.id.alarmscreen);
        final int myRed = ContextCompat.getColor(this, R.color.myRed);
        final Button button1 = (Button) findViewById(R.id.button2);
        final Button button2 = (Button) findViewById(R.id.button3);

        player = MediaPlayer.create(AlarmActivity.this, R.raw.siren);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.start();
                alarmscreen.setBackgroundColor(myRed);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.isPlaying()) {
                    player.reset();
                    alarmscreen.setBackgroundColor(Color.WHITE);
                    player = MediaPlayer.create(AlarmActivity.this, R.raw.siren);
                }
            }
        });
    }
}