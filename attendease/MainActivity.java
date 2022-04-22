package com.example.mahe.attendease;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
Button login,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



            login = (Button) findViewById(R.id.b1);
            signup = (Button) findViewById(R.id.b2);
            login.setOnClickListener(this);
            signup.setOnClickListener(this);

        }

    public void onClick(View v) {
        if (v == login) {
            Intent i1 = new Intent(MainActivity.this, loginpage.class);
            startActivity(i1);
        }
        if (v == signup) {
            Intent i2 = new Intent(MainActivity.this, Registeration.class);
            startActivity(i2);
        }
    }
}
