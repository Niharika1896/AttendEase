package com.example.mahe.attendease;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class takeattendance extends AppCompatActivity {
    static ArrayList<Integer> selectedID;
    String usrn;
    String cid;
    SQLiteDatabase db;
    ListView lv;
    ArrayList<String> studs;
    ArrayList<String> regnos;
    Bundle b;
    Button b1;
    int taken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takeattendance);
        db = openOrCreateDatabase("attendease.db",MODE_PRIVATE,null);
        lv = (ListView)findViewById(R.id.list);
        b1=(Button)findViewById(R.id.button1);
        studs = new ArrayList<String>();
        b = getIntent().getExtras();
        usrn = b.getString("usrn");
        regnos= new ArrayList<String>();
        cid = b.getString("cid");
        Cursor c = db.rawQuery("SELECT REG_NO,NAME FROM STUDENT WHERE USERNAME = '"+usrn+"' AND COURSE_ID = '"+cid+"';",null);
        c.moveToFirst();
        studs.add(c.getString(0)+"  : "+c.getString(1));
        regnos.add(c.getString(0));
        while(c.moveToNext()) {
            studs.add(c.getString(0) + "  : " + c.getString(1));
            regnos.add(c.getString(0));
        }


        final int size = studs.size();
        final int[] attend = new int[size];

        for(int iter = 0; iter <size ; iter++ ){
            attend[iter] = 1;
        }

        Cursor c1 = db.rawQuery("SELECT TAKEN FROM STUDENT WHERE USERNAME = '"+usrn+"' AND COURSE_ID = '"+cid+"';",null);
        c1.moveToFirst();
        taken = Integer.parseInt(c1.getString(0));
        taken++;
        //Log.d("taken:", Integer.toString(taken));
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, studs);
        lv.setAdapter(adapter);
        selectedID = new ArrayList<Integer>();



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("POS:",String.valueOf(position));
                int pos = position;
                if(attend[pos]== 1){
                    attend[pos] = 0;
                    view.setBackgroundColor(Color.parseColor("#e15c39"));
                    adapter.notifyDataSetChanged();
                }
                else {
                    attend[pos] = 1;
                   view.setBackgroundColor(Color.TRANSPARENT);
                }
            }

        });



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int m;
                Log.d("REGSIZE", Integer.toString(regnos.size()));
                for (String x: regnos) {
                    db.execSQL("UPDATE STUDENT SET TAKEN ="+taken+" WHERE USERNAME ='"+usrn+"' AND COURSE_ID = '"+cid+"' AND REG_NO='"+x+"';");
                }

                Cursor c2;
                for(m=0;m<size;m++){
                    if(attend[m] == 1){
                        db.execSQL("UPDATE STUDENT SET ATTENDED = ATTENDED + 1 WHERE USERNAME ='"+usrn+"' AND COURSE_ID = '"+cid+"' AND REG_NO ='"+ regnos.get(m) +"';");
                    }

                    c2 = db.rawQuery("SELECT ATTENDED FROM STUDENT WHERE USERNAME ='"+usrn+"' AND COURSE_ID = '"+cid+"' AND REG_NO ='"+ regnos.get(m) +"';",null);
                    c2.moveToFirst();
                    float attended = c2.getInt(0);
                    Log.d("attended:",String.valueOf(attended));
                    float attendance = attended / taken * 100;
                    Log.d("attended:",String.valueOf(attendance));
                    db.execSQL("UPDATE STUDENT SET ATTENDANCE ="+attendance+" WHERE USERNAME ='"+usrn+"' AND COURSE_ID = '"+cid+"' AND REG_NO ='"+ regnos.get(m) +"';");

                }
                Intent i = new Intent(takeattendance.this,commonpage.class);

             
                i.putExtra("usrn",usrn);
                startActivity(i);
            }
        });
    }


}
