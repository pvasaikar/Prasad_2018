package com.example.prasad.prasad_2018;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Button bu;
    TextView tv;
    EditText et;
    String s1;
    String s2;
    String s3;
    String sharedpreferencename = "com.example.prasad.prasad_2018.spdemo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bu =(Button)findViewById(R.id.Submit_button);
        tv =(TextView) findViewById(R.id.OutputString);
        et =(EditText) findViewById(R.id.Uname);
        s1=getString(R.string.text1);
        s2=getString(R.string.text2);


        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(s1+" "+et.getText()+" "+s2);
                s3=et.getText().toString();
                /*
				 * Intent is just like glue which helps to navigate one activity
				 * to another.
				 */
                Intent intent = new Intent(MainActivity.this,
                        Main2Activity.class);
                intent.putExtra("Output",s3);
                startActivity(intent); // startActivity allow you to move
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout_menu) {
            SharedPreferences sp = getSharedPreferences(sharedpreferencename , Context.MODE_PRIVATE);
            sp.edit().remove("login").commit();
            finish();
            finish();
            return true;
        }
        if (id == R.id.todo) {
            Intent nav= new Intent(MainActivity.this,TODOActivity.class);
            startActivity(nav);

            //test
        }
        return super.onOptionsItemSelected(item);
    }

}
