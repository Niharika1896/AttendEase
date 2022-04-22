package com.example.mahe.attendease;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registeration extends AppCompatActivity {
    EditText ed_name;
    Button b1;
    Cursor c;
    EditText ed_email,ed_mob,ed_dept,ed_userName,ed_pwd,ed_confirmPwd;
    Context context = this;
    DBHelper db ;
    SQLiteDatabase dbs;
    int k = 0;
    int i;
    String[] a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        dbs = openOrCreateDatabase("attendease.db",MODE_PRIVATE,null);
        ed_name = (EditText) findViewById(R.id.ed1);
        ed_email = (EditText) findViewById(R.id.ed2);
        ed_mob = (EditText) findViewById(R.id.ed3);
        ed_dept = (EditText) findViewById(R.id.ed4);
        ed_userName = (EditText) findViewById(R.id.ed5);
        ed_pwd = (EditText) findViewById(R.id.ed6);
        ed_confirmPwd = (EditText) findViewById(R.id.ed7);
        b1 = (Button) findViewById(R.id.sub);
        db = new DBHelper(getApplicationContext());
        a = new String[200];

        c = dbs.rawQuery("SELECT USERNAME FROM LOGIN",null);
        c.moveToFirst();
        a[k++] = c.getString(0);
        while(c.moveToNext()){
            a[k++] = c.getString(0);
        }
//size = k
        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String name, email,mob,dept;
                name = ed_name.getText().toString();
                email = ed_email.getText().toString();
                mob = ed_mob.getText().toString();
                dept = ed_dept.getText().toString();
                String userName = ed_userName.getText().toString();
                String password = ed_pwd.getText().toString();
                String confirmPassword = ed_confirmPwd.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                for(i=0;i<k;i++){
                    if(a[i].equals(userName))
                    {
                        Toast.makeText(getApplicationContext(),"UserName exists already",Toast.LENGTH_SHORT).show();
                        ed_userName.setText("");
                    }
                }

                if (!email.matches(emailPattern)){

                    Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                    ed_email.setText("");
                    ed_pwd.setText("");
                    ed_confirmPwd.setText("");

                }
                else if (name.equals("") || email.equals("") || mob.equals("") || dept.equals("") || userName.equals("") || password.equals("")
                        || confirmPassword.equals("")) {

                    Toast.makeText(getApplicationContext(), "Field Vaccant",
                            Toast.LENGTH_LONG).show();
                    ed_name.setText("");
                    ed_email.setText("");
                    ed_mob.setText("");
                    ed_dept.setText("");
                    ed_userName.setText("");
                    ed_pwd.setText("");
                    ed_confirmPwd.setText("");
                    return;
                }
                else if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(),
                            "Password does not match", Toast.LENGTH_LONG)
                            .show();
                    ed_email.setText("");
                    ed_mob.setText("");
                    ed_dept.setText("");
                    ed_userName.setText("");
                    ed_pwd.setText("");
                    ed_confirmPwd.setText("");
                    return;
                } else {

                    db.insertLoginEntry(userName, password, name, email, mob ,dept);
                    Toast.makeText(getApplicationContext(),
                            "Account Successfully Created ", Toast.LENGTH_LONG)
                            .show();
                    Intent i = new Intent(Registeration.this,setup.class);
                    i.putExtra("usrn",userName);

                    startActivity(i);
                    //finish();

                }
            }
        });

    }


}