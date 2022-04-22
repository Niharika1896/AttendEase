package com.example.mahe.attendease;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginpage extends AppCompatActivity implements View.OnClickListener{
    public static Button b1;
    public static EditText ed1,ed2;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        db = new DBHelper(getApplicationContext());

        b1 = (Button) findViewById(R.id.loginBtn);
        b1.setOnClickListener(this);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);

    }

    public void onClick(View v) {

        String userName = ed1.getText().toString();
        String password = ed2.getText().toString();
        String storedPassword = db.getSinlgeEntry(userName);
        if (userName.equals("") || password.equals("") ) {

            Toast.makeText(getApplicationContext(), "Field Vaccant",
                    Toast.LENGTH_LONG).show();
            ed1.setText("");
            ed2.setText("");
            return;

        }
        else if (password.equals(storedPassword)) {

            Toast.makeText(getApplicationContext(), "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
            Intent i1 = new Intent(loginpage.this, setup.class);

            i1.putExtra("usrn",userName);

            startActivity(i1);

        } else {

            Toast.makeText(getApplicationContext(),
                    "User Name or Password does not match",
                    Toast.LENGTH_LONG).show();
            ed1.setText("");
            ed2.setText("");

        }


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
