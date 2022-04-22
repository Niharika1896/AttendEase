package com.example.mahe.attendease;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class fillclasslist extends AppCompatActivity {
String usrn,cid;
    int counter = 1;
    EditText ed1,ed2;
    TextView tv1,tv2;
    SQLiteDatabase db;
    DBHelper dbh;
    Bundle b1;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillclasslist);
        db = openOrCreateDatabase("attendease.db", MODE_PRIVATE, null);
        tv1 = (TextView) findViewById(R.id.tv2);
        tv2 = (TextView) findViewById(R.id.tv3);
        ed1 = (EditText) findViewById(R.id.ed1);
        ed2 = (EditText) findViewById(R.id.ed2);
        b = (Button) findViewById(R.id.b1);
        dbh = new DBHelper(getApplicationContext());

        b1 = getIntent().getExtras();
        usrn = b1.getString("usrn");
        cid = b1.getString("cid");
        tv1.setText("TAUGHT BY : " + usrn);
        tv2.setText("COURSE_ID : " + cid);
        Cursor c = db.rawQuery("SELECT STRENGTH FROM ASSIGN WHERE USERNAME = '" + usrn + "' AND COURSE_ID = '" + cid + "';", null);
        c.moveToFirst();
        final int strength = c.getInt(0);
        //for(;counter<=strength;counter++){
        ed1.setVisibility(View.VISIBLE);
        ed2.setVisibility(View.VISIBLE);
        b.setVisibility(View.VISIBLE);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                String regno = ed1.getText().toString();
                String name = ed2.getText().toString();

                ed1.setText("");
                ed2.setText("");
                if (counter < strength) Toast.makeText(getApplicationContext(),"Enter the details of the next student..",Toast.LENGTH_SHORT).show();
                dbh.insertStudentEntry(usrn, cid, regno, name, 0, 0, 0.0);
                if (counter > strength) {
                    Intent i1 = new Intent(fillclasslist.this, commonpage.class);
                    i1.putExtra("usrn",usrn);
                    startActivity(i1);

//public void insertStudentEntry(String userName,String course_id,String regno,int taken, int attended, double attendance)
                }
            }
        });
    }
}
