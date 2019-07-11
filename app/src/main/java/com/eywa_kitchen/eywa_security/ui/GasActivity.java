package com.eywa_kitchen.eywa_security.ui;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.eywa_kitchen.eywa_security.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.media.AudioManager.STREAM_MUSIC;

public class GasActivity extends AppCompatActivity {

    MediaPlayer mPlayer;
    public static boolean ActState=false;
    private AudioManager audio;
    long currentCount;
    long start_time;
    CountDownTimer countDownTimer;
    TextView mTimer;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("alarm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gas_activity);
        mTimer = (TextView) findViewById(R.id.Time);
        TextView Alarm = (TextView) findViewById(R.id.Alarm);

        Alarm.startAnimation(AnimationUtils.loadAnimation(this, R.anim.larmanim));
        getSupportActionBar().hide();
        myRef.setValue("GAS");

        mPlayer = MediaPlayer.create(this, R.raw.gaslong);
        mPlayer.start();
        mPlayer.setLooping(true);
        SetVolume(12, false);

        countDownTimer = new CountDownTimer( 121000, 1000) {
            @Override
            public void onTick(long millis) {

                int count = (int)millis/1000;
                int chislo_min=count/60;
                int chislo_sec=count%60;
                if(chislo_sec<10) {
                    mTimer.setText(chislo_min + ":" + "0" + chislo_sec);
                }
                else
                {
                    mTimer.setText(chislo_min + ":" + chislo_sec);
                }

            }
            @Override
            public void onFinish(){

            }
        }.start();
    }

    public void SetVolume(int val, boolean showScroll) {
        audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        if (showScroll) {
            audio.setStreamVolume(STREAM_MUSIC, val, 1); // устанавливаем громкость на С показом ползунка
        } else {
            audio.setStreamVolume(STREAM_MUSIC, val, 0); // устанавливаем громкость на БЕЗ показа ползунка
        }
    }

    public void onDestroy() {
        super.onDestroy();
        mPlayer.pause();

    }

    @Override
    public void onPause() {
        super.onPause();
        mPlayer.pause();
        countDownTimer.cancel();


    }

    @Override
    public void onResume() {
        super.onResume();
        SetVolume(13, false);
        mPlayer.start();
        countDownTimer.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_F) {
            myRef.setValue("SIENTLY");
            finishAndRemoveTask();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
