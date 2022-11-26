package com.example.sayacuygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button minus,plus;
    TextView value;
    Button settings;

    SharedPref sharedPref;
    MediaPlayer mediaPlayer;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref=SharedPref.getInstance(this);
       mediaPlayer=MediaPlayer.create(this,R.raw.bell);
       vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);

        value=(TextView) findViewById(R.id.value);
        minus=(Button) findViewById(R.id.btn_minus);
        plus=(Button) findViewById(R.id.btn_plus);
        settings=(Button) findViewById(R.id.btn_settings);


        plus.setOnClickListener(view -> {
           UpdateValue(+1);

        });
        minus.setOnClickListener(view -> {
               UpdateValue(-1);
        });
        settings.setOnClickListener(view ->{
            startActivity(new Intent(MainActivity.this,SettingsActivity.class));
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        sharedPref.saveValue();
    }

    @Override
    protected void onResume(){
        super.onResume();
        value.setText(String.valueOf(sharedPref.counter));
    }

private void UpdateValue(int step ) {
    if (step > 0) {
        if (sharedPref.counter + step > sharedPref.upperLimit) {
   if(sharedPref.upperLimitSound){
       Bell();

 }   if(sharedPref.upperLimitVibrator){
Vibrator();
            }

            sharedPref.counter = sharedPref.upperLimit;
        } else {
            sharedPref.counter += step;
        }
        } else {
            if (sharedPref.counter + step < sharedPref.lowerLimit) {
                if(sharedPref.lowerLimitSound){

    Bell();
                }
                if(sharedPref.lowerLimitVibrator){
     Vibrator();

                }

                sharedPref.counter = sharedPref.lowerLimit;
            } else {
                sharedPref.counter += step;
            }
        }
        sharedPref.counter += step;
        value.setText(String.valueOf(sharedPref.counter));
    }
    private void Bell(){
        mediaPlayer.seekTo(0);
        mediaPlayer.start();

    }
private void Vibrator() {

    if (!vibrator.hasVibrator())
        return;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
    } else {
        vibrator.vibrate(1000);
    }
}
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
if(keyCode==KeyEvent.KEYCODE_VOLUME_UP){
    UpdateValue(+5);
}else if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN)
UpdateValue(-5);
        return true;
    }




}
