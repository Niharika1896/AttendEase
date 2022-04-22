package com.example.mahe.attendease;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class coursedetails extends AppCompatActivity implements View.OnClickListener {
    public static Button b1,b2;
    public static EditText e1,e2,e3,e4;
    public static TextView tv1;
    Context context = this;
    DBHelper dbh;
    String cname, cid;
    int strn,max_class;
    String usrn;
    Bundle b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursedetails);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        e1 = (EditText) findViewById(R.id.ed1);
        e2 = (EditText) findViewById(R.id.ed2);
        e3 = (EditText) findViewById(R.id.ed3);
        e4 = (EditText) findViewById(R.id.ed4);
        tv1 = (TextView) findViewById(R.id.tv1);

        dbh= new DBHelper(getApplicationContext());


        b=getIntent().getExtras();
        usrn = b.getString("usrn");
        tv1.setText("Add a course under: "+usrn);
        b1.setOnClickListener((View.OnClickListener) this);
        b2.setOnClickListener((View.OnClickListener) this);
    }
    public void onClick(View v){
        strn = Integer.parseInt(e3.getText().toString());
        max_class = Integer.parseInt(e4.getText().toString());
        cname = e2.getText().toString();
        cid = e1.getText().toString();
        if(v == b1){
            if (cid.equals("") || cname.equals("") || e3.equals("")||e4.equals("")) {

                Toast.makeText(getApplicationContext(), "Field Vaccant",
                        Toast.LENGTH_LONG).show();

                return;
            }
            else {

                dbh.insertAssignEntry(usrn, cid, cname, strn,max_class);
                Toast.makeText(getApplicationContext(), cid+" course Successfully Added ", Toast.LENGTH_LONG).show();
                Intent i1 = new Intent(coursedetails.this,setup.class);
                i1.putExtra("usrn",usrn);
                startActivity(i1);
                //finish();

            }

        }
        if(v == b2){
            Intent i1 = new Intent(coursedetails.this,setup.class);
            i1.putExtra("usrn",usrn);
            startActivity(i1);
        }

    }
}
