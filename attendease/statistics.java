package com.example.mahe.attendease;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class statistics extends AppCompatActivity {
    String usrn;
    String cid;
    SQLiteDatabase db;
    ListView lv;
    ArrayList<String> studs;
    ArrayList<String> regnos;
    Bundle b;
    TextView tv;
    int taken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        db = openOrCreateDatabase("attendease.db",MODE_PRIVATE,null);
        lv = (ListView)findViewById(R.id.list1);
        tv= (TextView)findViewById(R.id.tv);
        studs = new ArrayList<String>();
        b = getIntent().getExtras();
        usrn = b.getString("usrn");
        regnos= new ArrayList<String>();
        cid = b.getString("cid");
        Cursor c1 = db.rawQuery("SELECT COUNT(REG_NO) FROM STUDENT WHERE ATTENDANCE < 75.00 AND COURSE_ID = '"+cid+"' AND USERNAME = '"+usrn+"';",null);
        c1.moveToFirst();
        int count = c1.getInt(0);
        tv.setText("Number of students with attendance shortage : "+count);
        Cursor c = db.rawQuery("SELECT REG_NO, ATTENDED, TAKEN, ATTENDANCE FROM STUDENT WHERE USERNAME = '"+usrn+"' AND COURSE_ID = '"+cid+"';",null);
        c.moveToFirst();
        studs.add(c.getString(0)+"  : "+String.valueOf(c.getInt(1))+"/"+String.valueOf(c.getInt(2))+" = "+c.getFloat(3));
        //regnos.add(c.getString(0));
        while(c.moveToNext()) {
            studs.add(c.getString(0)+"  : "+String.valueOf(c.getInt(1))+"/"+String.valueOf(c.getInt(2))+" = "+c.getFloat(3));
            //regnos.add(c.getString(0));
        }
        final int size = studs.size();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, studs);
        lv.setAdapter(adapter);

    }
}
