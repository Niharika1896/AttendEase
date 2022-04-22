package com.example.mahe.attendease;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class subjectfour extends AppCompatActivity {
    String cid4,usrn;
    SQLiteDatabase db;
    Button b1,b2,b3;
    TextView tv1,tv2;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectfour);
        db = openOrCreateDatabase("attendease.db",MODE_PRIVATE,null);
        b1 = (Button)findViewById(R.id.b1);
        b2= (Button)findViewById(R.id.b2);
        b3= (Button)findViewById(R.id.b3);
        b = getIntent().getExtras();
        cid4 = b.getString("cid4");
        usrn = b.getString("usrn");
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv1.setText("COURSE_ID : "+cid4);
        tv2.setText("TAUGHT BY : "+usrn);
        Cursor c = db.rawQuery("SELECT COUNT(REG_NO) FROM STUDENT WHERE COURSE_ID ='"+cid4+"' AND USERNAME = '"+usrn+"';",null);
        c.moveToFirst();
        int count = c.getInt(0);
        if (count == 0){
            b1.setText("Create Class");
            b2.setVisibility(View.GONE);
            b3.setVisibility(View.GONE);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(subjectfour.this,fillclasslist.class);
                    i.putExtra("cid",cid4);
                    i.putExtra("usrn",usrn);
                    startActivity(i);
                }
            });
        }
        else {
            b1.setText("View Classlist");
            b2.setVisibility(View.VISIBLE);
            b3.setVisibility(View.VISIBLE);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(subjectfour.this, viewclasslist.class);
                    i.putExtra("cid", cid4);
                    i.putExtra("usrn", usrn);
                    startActivity(i);
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(subjectfour.this, takeattendance.class);
                    i.putExtra("usrn", usrn);
                    i.putExtra("cid", cid4);
                    startActivity(i);
                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(subjectfour.this, statistics.class);
                    i.putExtra("usrn", usrn);
                    i.putExtra("cid", cid4);
                    startActivity(i);
                }
            });
        }
    }
}
