package com.example.sayacuygulama;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {
    EditText upperLimitText;
    Button upperLimitMinusButton;
    Button upperLimitPlusButton;
    Switch upperLimitSoundSwitch;
    Switch upperLimitVibratorSwitch;

    EditText lowerLimitText;
    Button lowerLimitMinusButton;
    Button lowerLimitPlusButton;
    Switch lowerLimitSoundSwitch;
    Switch lowerLimitVibratorSwitch;

    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPref = sharedPref.getInstance(this);
        upperLimitText = (EditText) findViewById(R.id.upperlimittext);
        upperLimitMinusButton = (Button) findViewById(R.id.upperlimitbtn_minus);
        upperLimitPlusButton = (Button) findViewById(R.id.upperlimitbtn_plus);
        upperLimitSoundSwitch = (Switch) findViewById(R.id.upperlimitsoundswitch);
        upperLimitVibratorSwitch = (Switch) findViewById(R.id.upperlimitvibratorswitch);

        lowerLimitText = (EditText) findViewById(R.id.lowerlimittext);
        lowerLimitMinusButton = (Button) findViewById(R.id.lowerlimitbtn_minus);
        lowerLimitPlusButton = (Button) findViewById(R.id.lowerlimitbtn_plus);
        lowerLimitSoundSwitch = (Switch) findViewById(R.id.lowerlimitsoundswitch);
        lowerLimitVibratorSwitch = (Switch) findViewById(R.id.lowerlimitvibratorswitch);

        upperLimitText.addTextChangedListener(new TextWatcher() {
             @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

            @Override
            public void afterTextChanged(Editable editable) {
        if(upperLimitText.getText().toString().length()==0){
            upperLimitText.setText("0");
            sharedPref.upperLimit=0;
        }else{
            sharedPref.upperLimit=Integer.valueOf(upperLimitText.getText().toString());
        }
            }
        });


        upperLimitPlusButton.setOnClickListener(view -> {
            sharedPref.upperLimit++;
            upperLimitText.setText(String.valueOf(sharedPref.upperLimit));
        });

        upperLimitMinusButton.setOnClickListener(view -> {
            sharedPref.upperLimit--;
            upperLimitText.setText(String.valueOf(sharedPref.upperLimit));
        });
        upperLimitSoundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPref.upperLimitSound=b;
            }
        });
        upperLimitVibratorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPref.upperLimitVibrator=b;
            }
        });

        lowerLimitText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(lowerLimitText.getText().toString().length()==0){
                    lowerLimitText.setText("0");
                    sharedPref.lowerLimit=0;
                }else{
                    sharedPref.lowerLimit=Integer.valueOf(lowerLimitText.getText().toString());
                }
            }
        });


        lowerLimitPlusButton.setOnClickListener(view -> {
            sharedPref.lowerLimit++;
            upperLimitText.setText(String.valueOf(sharedPref.lowerLimit));
        });

        lowerLimitMinusButton.setOnClickListener(view -> {
            sharedPref.lowerLimit--;
            upperLimitText.setText(String.valueOf(sharedPref.lowerLimit));
        });
        lowerLimitSoundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPref.lowerLimitSound=b;
            }
        });
        lowerLimitVibratorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharedPref.lowerLimitVibrator=b;
            }
        });




    }
      @Override
        protected  void onResume() {
         super.onResume();


         upperLimitText.setText(String.valueOf(sharedPref.upperLimit));
         upperLimitSoundSwitch.setChecked(sharedPref.upperLimitSound);
         upperLimitVibratorSwitch.setChecked(sharedPref.upperLimitVibrator);

         lowerLimitText.setText(String.valueOf(sharedPref.lowerLimit));
         lowerLimitSoundSwitch.setChecked(sharedPref.upperLimitSound);
         lowerLimitVibratorSwitch.setChecked(sharedPref.upperLimitVibrator);


        }
    @Override
    protected  void  onPause(){
    super.onPause();
    sharedPref.saveValue();
    }

}