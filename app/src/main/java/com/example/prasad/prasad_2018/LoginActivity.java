package com.example.prasad.prasad_2018;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArraySet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    String sharedpreferencename = "com.example.prasad.prasad_2018.spdemo";
    int loginvalid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button singin= (Button) findViewById(R.id.sign_in);
        loginvalid=checkLogin();
        if(loginvalid==1)
        {
            Intent nav= new Intent(LoginActivity.this
                    ,MainActivity.class);
            startActivity(nav);// startActivity allow you to move
            //finish();
            //System.exit(0);
        }

        singin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences(sharedpreferencename , Context.MODE_PRIVATE);
                sp.edit().putInt("login",1).commit();
                Intent nav= new Intent(LoginActivity.this,MainActivity.class);
                startActivity(nav);
                    finish();
                    System.exit(0);
            }
        });

    }

    private int checkLogin() {
        SharedPreferences sp = getSharedPreferences(sharedpreferencename ,Context.MODE_PRIVATE);
        int i  = sp.getInt("login",0);
        Log.d("Prasad_2018","Login Check is "+ i);
        return i;
    }
}
