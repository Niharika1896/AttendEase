package com.example.mahe.attendease;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class commonpage extends AppCompatActivity {
    String usrn;
    SQLiteDatabase db;
    Bundle b;
    public static Button b1,b2,b3,b4;
    public static TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8;
    DBHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = openOrCreateDatabase("attendease.db",MODE_PRIVATE,null);
        setContentView(R.layout.activity_commonpage);
        b=getIntent().getExtras();
        usrn = b.getString("usrn");
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
        tv7 = (TextView) findViewById(R.id.tv7);
        tv8 = (TextView) findViewById(R.id.tv8);
        dbh = new DBHelper(getApplicationContext());
        Cursor mCount= db.rawQuery("SELECT COUNT(COURSE_NAME) FROM ASSIGN WHERE USERNAME='" + usrn +"';", null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        Cursor c1= db.rawQuery("SELECT COURSE_ID, COURSE_NAME FROM ASSIGN WHERE USERNAME='" + usrn +"';", null);
        mCount.close();
        if(count == 0){
            b1.setText("BACK");
            b2.setVisibility(View.GONE);
            b3.setVisibility(View.GONE);
            b4.setVisibility(View.GONE);
            tv1.setText("You have no classes to display");
            tv2.setVisibility(View.GONE);
            tv3.setVisibility(View.GONE);
            tv4.setVisibility(View.GONE);
            tv5.setVisibility(View.GONE);
            tv6.setVisibility(View.GONE);
            tv7.setVisibility(View.GONE);
            tv8.setVisibility(View.GONE);

            b1.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent i = new Intent(commonpage.this,setup.class);
                    startActivity(i);
                }
            });


        }
        else if(count ==1)
        {
            b2.setVisibility(View.GONE);
            b3.setVisibility(View.GONE);
            b4.setVisibility(View.GONE);
            tv3.setVisibility(View.GONE);
            tv4.setVisibility(View.GONE);
            tv5.setVisibility(View.GONE);
            tv6.setVisibility(View.GONE);
            tv7.setVisibility(View.GONE);
            tv8.setVisibility(View.GONE);
            //Cursor c1= db.rawQuery("SELECT COURSE_ID, COURSE_NAME FROM ASSIGN WHERE USERNAME='" + usrn +"';", null);
            c1.moveToFirst();
            final String a1= c1.getString(0);//'final' declared to be accessed from inner class
            final String a2= c1.getString(1);
            tv1.setText("COURSE ID : "+a1);
            tv2.setText("COURSE NAME : "+a2);
            b1.setText("View Class");
            b1.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent i = new Intent(commonpage.this,subjectone.class);
                    i.putExtra("cid1",a1);
                    i.putExtra("usrn",usrn);
                    startActivity(i);
                }
            });
            c1.close();
        }
        else if(count ==2)
        {
            final String a[] = new String[4];
            int lp=0;
            b3.setVisibility(View.GONE);
            b4.setVisibility(View.GONE);
            tv5.setVisibility(View.GONE);
            tv6.setVisibility(View.GONE);
            tv7.setVisibility(View.GONE);
            tv8.setVisibility(View.GONE);
           // Cursor c1= db.rawQuery("SELECT COURSE_ID, COURSE_NAME FROM ASSIGN WHERE USERNAME='" + usrn +"';", null);
            c1.moveToFirst();
            a[lp++]= c1.getString(0);
            a[lp++]= c1.getString(1);
            while(c1.moveToNext()){
                a[lp++]= c1.getString(0);
                a[lp++]= c1.getString(1);

            }

            tv1.setText("COURSE ID : "+a[0]);
            tv2.setText("COURSE NAME : "+a[1]);
            tv3.setText("COURSE ID : "+a[2]);
            tv4.setText("COURSE NAME : "+a[3]);
            b1.setText("View Class");
            b2.setText("View Class");
            b1.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent i = new Intent(commonpage.this,subjectone.class);
                    i.putExtra("usrn",usrn);
                    i.putExtra("cid1",a[0]);
                    startActivity(i);
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent i = new Intent(commonpage.this,subjecttwo.class);
                    i.putExtra("usrn",usrn);
                    i.putExtra("cid2",a[2]);
                    startActivity(i);
                }
            });
            c1.close();
        }
        else if(count ==3)
        {
            final String a[] = new String[6];
            int lp=0;

            b4.setVisibility(View.GONE);

            tv7.setVisibility(View.GONE);
            tv8.setVisibility(View.GONE);
            //Cursor c1= db.rawQuery("SELECT COURSE_ID, COURSE_NAME FROM ASSIGN WHERE USERNAME='" + usrn +"';", null);
            c1.moveToFirst();
            a[lp++]= c1.getString(0);
            a[lp++]=c1.getString(1);
            while(c1.moveToNext()){
                a[lp++]= c1.getString(0);
                a[lp++]=c1.getString(1);
            }

            tv1.setText("COURSE ID : "+a[0]);
            tv2.setText("COURSE NAME : "+a[1]);
            tv3.setText("COURSE ID : "+a[2]);
            tv4.setText("COURSE NAME : "+a[3]);
            tv5.setText("COURSE ID : "+a[4]);
            tv6.setText("COURSE NAME : "+a[5]);
            b1.setText("View Class");
            b2.setText("View Class");
            b3.setText("View Class");
            b1.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent i = new Intent(commonpage.this,subjectone.class);
                    i.putExtra("usrn",usrn);
                    i.putExtra("cid1",a[0]);
                    startActivity(i);
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent i = new Intent(commonpage.this,subjecttwo.class);
                    i.putExtra("usrn",usrn);
                    i.putExtra("cid2",a[2]);
                    startActivity(i);
                }
            });
            b3.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent i = new Intent(commonpage.this,subjectthree.class);
                    i.putExtra("usrn",usrn);
                    i.putExtra("cid3",a[4]);
                    startActivity(i);
                }
            });
            c1.close();
        }
        else if(count ==4)
        {
            final String a[] = new String[8];
            int lp=0;

            //Cursor c1= db.rawQuery("SELECT COURSE_ID, COURSE_NAME FROM ASSIGN WHERE USERNAME='" + usrn +"'", null);
            c1.moveToFirst();
            a[lp++]= c1.getString(0);
            a[lp++]=c1.getString(1);
            while(c1.moveToNext()){
                a[lp++]= c1.getString(0);
                a[lp++]=c1.getString(1);
            }

            tv1.setText("COURSE ID : "+a[0]);
            tv2.setText("COURSE NAME : "+a[1]);
            tv3.setText("COURSE ID : "+a[2]);
            tv4.setText("COURSE NAME : "+a[3]);
            tv5.setText("COURSE ID : "+a[4]);
            tv6.setText("COURSE NAME : "+a[5]);
            tv7.setText("COURSE ID : "+a[6]);
            tv8.setText("COURSE NAME : "+a[7]);
            b1.setText("View Class");
            b2.setText("View Class");
            b3.setText("View Class");
            b4.setText("View Class");
            b1.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent i = new Intent(commonpage.this,subjectone.class);
                    i.putExtra("usrn",usrn);
                    i.putExtra("cid1",a[0]);
                    startActivity(i);
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent i = new Intent(commonpage.this,subjecttwo.class);
                    i.putExtra("usrn",usrn);
                    i.putExtra("cid2",a[2]);
                    startActivity(i);
                }
            });
            b3.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent i = new Intent(commonpage.this,subjectthree.class);
                    i.putExtra("usrn",usrn);
                    i.putExtra("cid3",a[4]);
                    startActivity(i);
                }
            });
            b4.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent i = new Intent(commonpage.this,subjectfour.class);
                    i.putExtra("usrn",usrn);
                    i.putExtra("cid4",a[6]);
                    startActivity(i);
                }
            });
            c1.close();
        }
    }
}
