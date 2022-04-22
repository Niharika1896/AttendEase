//Back Button not added on purpose
package com.example.mahe.attendease;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class viewclasslist extends AppCompatActivity {

    String usrn;
    String cid;
    ListView lv;
    ArrayList<String> studs;
    SQLiteDatabase db;
    int k=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewclasslist);
        db = openOrCreateDatabase("attendease.db",MODE_PRIVATE,null);
        studs = new ArrayList<String>();


        lv = (ListView)findViewById(R.id.list);

        Intent i = getIntent();
        usrn = i.getStringExtra("usrn");
        cid = i.getStringExtra("cid");

        Cursor c = db.rawQuery("SELECT REG_NO,NAME FROM STUDENT WHERE USERNAME = '"+usrn+"' AND COURSE_ID = '"+cid+"';",null);
        c.moveToFirst();
        studs.add(c.getString(0)+"  : "+c.getString(1));

        while(c.moveToNext()) {
            studs.add(c.getString(0) + "  : " + c.getString(1));

        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, studs);
        lv.setAdapter(adapter);



    }
}
