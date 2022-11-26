package com.example.sayacuygulama;

import android.content.Context;
import android.content.SharedPreferences;




public class SharedPref {

    private static SharedPref instance;
    public int counter;
    public int upperLimit;
    public boolean upperLimitSound;
    public boolean upperLimitVibrator;
    public int lowerLimit;
    public boolean lowerLimitSound;
    public boolean lowerLimitVibrator;

    private static final String KEY_COUNTER="COUNTER";
    private static final String KEY_UPPERlİMİT="UPPERLİMİT";
    private static final String KEY_UPPERLİMİTSOUND="UPPERLİMİTSOUND";
    private static final String KEY_UPPERlİMİTVİBRATOR="UPPERLİMİTVİBRATOR";
    private static final String KEY_lOWERLİMİT="LOWERLİMİT";
    private static final String KEY_lOWERLİMİTSOUND="lOWERLİMİTSOUND";
    private static final String KEY_lOWERlİMİTVİBRATOR="LOWERLİMİTVİBRATOR";

    private SharedPreferences sharedPreferences;



    private SharedPref(Context context){
sharedPreferences=context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
loadValue();

    }
public static  SharedPref getInstance(Context context){
    if(instance==null){
        instance=new SharedPref(context);
    }
    return instance;
}

private void loadValue(){
     counter=sharedPreferences.getInt(KEY_COUNTER,0);
     upperLimit=sharedPreferences.getInt(KEY_UPPERlİMİT,20);
     upperLimitSound=sharedPreferences.getBoolean(KEY_UPPERLİMİTSOUND,true);
    upperLimitVibrator=sharedPreferences.getBoolean(KEY_UPPERlİMİTVİBRATOR,true);
     lowerLimit=sharedPreferences.getInt(KEY_lOWERLİMİT,0);
   lowerLimitSound=sharedPreferences.getBoolean(KEY_lOWERLİMİTSOUND,true);
    lowerLimitVibrator=sharedPreferences.getBoolean(KEY_lOWERlİMİTVİBRATOR,true);

}

public void saveValue(){
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt(KEY_COUNTER,counter);
    editor.putInt(KEY_UPPERlİMİT,upperLimit);
        editor.putBoolean(KEY_UPPERLİMİTSOUND,upperLimitSound);
    editor.putBoolean(KEY_UPPERlİMİTVİBRATOR,upperLimitVibrator);

        editor.putInt(KEY_lOWERLİMİT,lowerLimit);
    editor.putBoolean(KEY_lOWERLİMİTSOUND,lowerLimitSound);
    editor.putBoolean(KEY_lOWERlİMİTVİBRATOR,lowerLimitVibrator);

        editor.commit();
}
}