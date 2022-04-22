package com.example.mahe.attendease;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class setup extends AppCompatActivity implements View.OnClickListener {
    public static Button view_existing, create_new ;
    String usrn;
    Context context = this;
    DBHelper db;
    SQLiteDatabase sqldb;
    public static int a;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        view_existing=(Button)findViewById(R.id.b1);
        view_existing.setOnClickListener((View.OnClickListener) this);
        create_new=(Button)findViewById(R.id.b2);
        create_new.setOnClickListener((View.OnClickListener) this);
        b = getIntent().getExtras();
        usrn = b.getString("usrn");
        db = new DBHelper(getApplicationContext());
        sqldb = openOrCreateDatabase("attendease.db",MODE_PRIVATE,null);
        db.getWritableDatabase();
        Cursor mCount= sqldb.rawQuery("SELECT COUNT(COURSE_ID) FROM ASSIGN where USERNAME='" + usrn +"';", null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
        if(count == 0){
            view_existing.setVisibility(View.INVISIBLE);
            create_new.setVisibility(View.VISIBLE);
        }
        else if(count == 4){
            create_new.setVisibility(View.INVISIBLE);
            view_existing.setVisibility(View.VISIBLE);
        }
        else
        {
            view_existing.setVisibility(View.VISIBLE);
            create_new.setVisibility(View.VISIBLE);
        }
    }

    public void onClick(View v){
        if(v == view_existing){
            Intent i1 = new Intent(setup.this,commonpage.class);
            i1.putExtra("usrn",usrn);
            startActivity(i1);
        }
        if(v == create_new){
            Intent i1 = new Intent(setup.this,coursedetails.class);
            i1.putExtra("usrn",usrn);
            startActivity(i1);

        }

    }
}