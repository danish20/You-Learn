package com.applications.arunjotsingh.youlearn;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by arunjotsingh on 4/27/16.
 */
public class SignupActivity extends AppCompatActivity{

    Cursor mycurr;
    SQLiteDatabase db;
    EditText name,email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        db = openOrCreateDatabase("users", MODE_PRIVATE, null);
        try
        {
            db.execSQL("CREATE TABLE userlogin(email VARCHAR PRIMARYKEY, password VARCHAR, name VARCHAR )");
        }
        catch (Exception e)
        {
            db.execSQL("DROP TABLE userlogin");
            db.execSQL("CREATE TABLE userlogin(email VARCHAR PRIMARYKEY, password VARCHAR, name VARCHAR )");
        }

        Button createAccount = (Button) findViewById(R.id.create_account);
        createAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                boolean flag=false;
                if(name.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals(""))
                {
                    Snackbar.make(view,"All Fields Are Required",Snackbar.LENGTH_LONG).show();
                }
                else
                {
                    Cursor c = db.query("userlogin",null,null,null,null,null,null);
                    while (c.moveToNext())
                    {
                        String temp=c.getString(c.getColumnIndex("email"));
                        if(temp.equals(email.getText().toString()))
                        {
                            Snackbar.make(view,"This Email is already Registered",Snackbar.LENGTH_LONG).show();
                            flag=true;
                            email.requestFocus();
                            break;
                        }
                    }
                    if(!flag)
                    {
                        db.execSQL("INSERT INTO userlogin VALUES('"+email.getText().toString()+"','"+password.getText().toString()+"','"+name.getText().toString()+"')");
                        startActivity(new Intent(getApplicationContext(),LoginActivity1.class));
                    }
                }
            }

        });
    }
}
