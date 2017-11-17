package com.example.odoo.mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MediaPlayer mediaPlayer;
    private TextView  leftTime;
    private TextView rightTime;
    private SeekBar musicSeekBar;
    private Button buttonPrev;
    private Button buttonNext;
    private Button buttonPlay;

    private SeekBar seekBarPlayer;

    // instanciate the variables
    public void setUpUI() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.mera_man);
        seekBarPlayer = (SeekBar) findViewById(R.id.seekBarPlayer);
        seekBarPlayer.setMax(mediaPlayer.getDuration());
        buttonPrev = (Button) findViewById(R.id.buttonPrev);
        buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonPlay = (Button) findViewById(R.id.buttonPlay);


        buttonNext.setOnClickListener(this);
        buttonPlay.setOnClickListener(this);
        buttonPrev.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpUI();


        seekBarPlayer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                int duration = mediaPlayer.getDuration();
                String mDuration = String.valueOf(duration);

                Toast.makeText(getApplicationContext(), "duration" + mDuration, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void pauseMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();;
            buttonPlay.setText("PLay");
        }
    }

    public void startMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
            buttonPlay.setText("Pause");
        }
    }

    @Override
    protected void onDestroy() {
        // To free memory and all resources used.
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonNext:
                break;

            case R.id.buttonPrev:
                break;

            case R.id.buttonPlay:
                if (mediaPlayer.isPlaying()) {
                    // stop and give option to start again
                    pauseMusic();
                } else {
                    startMusic();
                }
                break;
        }
    }
}
