package com.applications.arunjotsingh.youlearn;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity1 extends AppCompatActivity  {


    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        db=openOrCreateDatabase("users",MODE_PRIVATE,null);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin(textView);
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin(view);
            }
        });


    }



public void attemptLogin(View v)
{
    boolean flag=false;
    String email=mEmailView.getText().toString();
    String password=mPasswordView.getText().toString();
    Cursor c = db.query("userlogin", null, null, null, null, null, null);
    while(c.moveToNext())
    {
        if(c.getString(c.getColumnIndex("email")).equals(email) && c.getString(c.getColumnIndex("password")).equals(password))
        {
            flag=true;
            break;
        }
    }
    if(flag)
    {
        Snackbar.make(v,"Login Successful",Snackbar.LENGTH_LONG).show();
    }
    else
    {
        Snackbar.make(v,"Invalid Username or Password",Snackbar.LENGTH_LONG).show();
    }

}

}

